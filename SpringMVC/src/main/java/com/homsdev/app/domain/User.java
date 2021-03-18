package com.homsdev.app.domain;

public class User {
	private String userID;
	private String username;
	private String password;
	private String role;
	private boolean enable;

	public User() {
		super();
	}

	public User(String userID, String username, String password, String role, boolean enable) {
		super();
		this.userID = userID;
		this.username = username;
		this.password = password;
		this.role = role;
		this.enable = enable;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public boolean isEnable() {
		return enable;
	}

	public void setEnable(boolean enable) {
		this.enable = enable;
	}

	@Override
	public String toString() {
		return "User [userID=" + userID + ", username=" + username + ", password=" + password + ", role=" + role
				+ ", enable=" + enable + "]";
	}
	
	

}
