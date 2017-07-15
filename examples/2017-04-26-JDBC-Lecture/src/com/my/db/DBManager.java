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

public class DBManager {

	private static final String CONNECTION_URL = "jdbc:derby://localhost:1527/testdb;user=test;password=test";

	private static final String SQL_FIND_ALL_USERS = "SELECT * FROM users";

	private static final String SQL_FIND_USER = "SELECT * FROM users WHERE login=?";

	private static final String SQL_INSERT_USER = "INSERT INTO users VALUES (DEFAULT, ?, ?, ?)";

	private static final String SQL_UPDATE_USER = "UPDATE users SET login=?, name=?, roleId=? WHERE id=?";

	private static DBManager instance;

	public static synchronized DBManager getInstance() {
		if (instance == null) {
			instance = new DBManager();
		}
		return instance;
	}

	private DBManager() {
	}

	// //////////////////////////

	// User
	public boolean createUser(User user) throws DBException {
		try (Connection con = getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_USER,
					Statement.RETURN_GENERATED_KEYS);

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
			// write to log: log.error("Cannot obtain users", ex);
			throw new DBException("Cannot obtain a user", ex);
		}
		return false;
	}

	public boolean updateUser(User user) throws DBException {
		boolean res = false;
		try (Connection con = getConnection()) {
			PreparedStatement pstmt = con.prepareStatement(SQL_UPDATE_USER);

			int k = 1;
			pstmt.setString(k++, user.getLogin());
			pstmt.setString(k++, user.getName());
			pstmt.setInt(k++, user.getRoleId());
			pstmt.setInt(k++, user.getId());

			res = pstmt.executeUpdate() > 0;
		} catch (SQLException ex) {
			// write to log: log.error("Cannot obtain users", ex);
			throw new DBException("Cannot obtain a user", ex);
		}
		return res;
	}

	// high level method
	public User findUser(String login) throws DBException {
		Connection con = null;
		try {
			con = getConnection();
			User user = findUser(con, login);
			con.commit();
			return user;
		} catch (SQLException ex) {
			// write to log
			rollback(con);
			throw new DBException("Cannot obtain a user", ex);
		} finally {
			close(con);
		}

	}

	// low level method
	public User findUser(Connection con, String login) throws SQLException {
		User user = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;

		pstmt = con.prepareStatement(SQL_FIND_USER);

		int k = 1;
		pstmt.setString(k++, login);

		rs = pstmt.executeQuery();
		if (rs.next()) {
			user = extractUser(rs);
		}
		rs.close();
		pstmt.close();
		return user;
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

	public Connection getConnection() throws SQLException {
		Connection con = DriverManager.getConnection(CONNECTION_URL);
		con.setAutoCommit(false);
		con.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);
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

	public void close(Connection con) {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public void rollback(Connection con) {
		if (con != null) {
			try {
				con.rollback();
			} catch (SQLException ex) {
				ex.printStackTrace();
			}
		}
	}

	public static void main(String[] args) throws SQLException, DBException {
		DBManager dbManager = DBManager.getInstance();
		User user = dbManager.findUser("admin");
		System.out.println(user);
	}

}
