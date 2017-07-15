package com.my.db.entity;

public class User {

	private int id;

	private String login;

	private String name;

	private int roleId;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", name=" + name
				+ ", roleId=" + roleId + "]";
	}

	public User(int id, String login, String name, int roleId) {
		this.id = id;
		this.login = login;
		this.name = name;
		this.roleId = roleId;
	}
	
	public User() {}

}
