package com.OlikTask.Library.payloads;

public class UserDto {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public String toString() {
		return "UserDto [username=" + username + "]";
	}

	public UserDto(String username) {
		super();
		this.username = username;
	}

	public UserDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
