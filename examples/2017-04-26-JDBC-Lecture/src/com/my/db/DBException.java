package com.my.db;

public class DBException extends Exception {

	private static final long serialVersionUID = -2463510719267465786L;

	public DBException(String message, Throwable cause) {
		super(message, cause);
	}

	public DBException(String message) {
		super(message);
	}
	
}
