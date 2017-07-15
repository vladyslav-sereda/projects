package com.my.db.logic;

import java.sql.Connection;
import java.sql.SQLException;

import com.my.db.DBException;
import com.my.db.DBManager;
import com.my.db.entity.User;

public class UserManager {
	
	/////////////////////////////
	// singleton
	
	private static UserManager instance;
	
	public static synchronized UserManager getInstance() {
		if (instance == null) {
			instance = new UserManager();
		}
		return instance;
	}
	
	private UserManager() {
	}
	
	////////////////////

	private static DBManager dbManager = DBManager.getInstance();
	
	
	// high level method
	public User findUser(String login) throws DBException {
		Connection con = null;
		try {
			con = dbManager.getConnection();
			User user = dbManager.findUser(con, login);
			con.commit();
			return user;
		} catch (SQLException ex) {
			// write to log
			dbManager.rollback(con);
			throw new DBException("Cannot obtain a user", ex);
		} finally {
			dbManager.close(con);
		}

	}
}
