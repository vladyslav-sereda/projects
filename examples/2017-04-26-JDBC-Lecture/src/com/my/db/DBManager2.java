package com.my.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.my.db.entity.User;

public class DBManager2 {

	private static final String CONNECTION_URL = "jdbc:derby://localhost:1527/testdb;user=test;password=test";

	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

	private static final String SQL_FIND_USER = "SELECT * FROM users WHERE login=?";

	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?)";

	private static final String SQL_UPDATE_USER = "UPDATE users SET login=?, name=?, roleId=? WHERE id=?";

	private static DBManager2 instance;

	public static synchronized DBManager2 getInstance() {
		if (instance == null) {
			instance = new DBManager2();
		}
		return instance;
	}

	private DBManager2() {
	}

	// //////////////////////////

	// User
	public boolean createUser(User user) throws DBException {
		try (Connection con = getConnection()) {
				PreparedStatement pstmt = 
					con.prepareStatement(SQL_INSERT_USER, Statement.RETURN_GENERATED_KEYS);

				int k = 1;
				pstmt.setString(k++, user.getLogin());
				pstmt.setString(k++, user.getName());
				pstmt.setInt(k++, user.getRoleId());

				if (pstmt.executeUpdate() > 0) {
					ResultSet rs = pstmt.getGeneratedKeys();
					if (rs.next()) {
						user.setId(rs.getInt(1));
					}
					return true;
				}
		} catch (SQLException ex) {
			// write to log:      log.error("Cannot obtain users", ex);
			throw new DBException("Cannot obtain a user", ex);
		}
		return false;
	}
	
	public boolean updateUser(User user) throws DBException {
		boolean res = false;
		try (Connection con = getConnection()) {
				PreparedStatement pstmt = 
					con.prepareStatement(SQL_UPDATE_USER);

				int k = 1;
				pstmt.setString(k++, user.getLogin());
				pstmt.setString(k++, user.getName());
				pstmt.setInt(k++, user.getRoleId());
				pstmt.setInt(k++, user.getId());

				res = pstmt.executeUpdate() > 0; 
		} catch (SQLException ex) {
			// write to log:      log.error("Cannot obtain users", ex);
			throw new DBException("Cannot obtain a user", ex);
		}
		return res;
	}


	public User findUser(String login) throws DBException {
		try (Connection con = getConnection()) {
				PreparedStatement pstmt = con.prepareStatement(SQL_FIND_USER);

				int k = 1;
				pstmt.setString(k++, login);

				ResultSet rs = pstmt.executeQuery();
				if (rs.next()) {
					return extractUser(rs);
				}
		} catch (SQLException ex) {
			// write to log:      log.error("Cannot obtain users", ex);
			throw new DBException("Cannot obtain a user", ex);
		}
		return null;
	}

	public List<User> findAllUsers() throws DBException {
		List<User> users = new ArrayList<>();
		try (Connection con = getConnection();
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(SQL_FIND_ALL_USERS)) {
			while (rs.next()) {
				User user = extractUser(rs);
				users.add(user);
			}
		} catch (Exception ex) {
			// ex.printStackTrace();

			// (1)
			// write to log: log.error("Cannot obtain users", ex);

			// (2)
			throw new DBException("Cannot obtain users list", ex);
		}

		return users;
	}

	// ////////////////////

	private Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(CONNECTION_URL);
		return con;
	}

	private User extractUser(ResultSet rs) throws SQLException {
		User user = new User();
		user.setId(rs.getInt("id"));
		user.setLogin(rs.getString("login"));
		user.setName(rs.getString("name"));
		user.setRoleId(rs.getInt("role_id"));
		return user;
	}
	
	public static void main(String[] args) throws SQLException {
		DBManager2 dbManager = DBManager2.getInstance();
		Connection con = dbManager.getConnection();
		System.out.println(con.getAutoCommit());
	}

}
