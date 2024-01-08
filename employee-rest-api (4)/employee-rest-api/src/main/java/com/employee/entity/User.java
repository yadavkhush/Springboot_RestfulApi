package com.employee.entity;

public class User {

    private String username;
    private String password;

    // Getters and setters

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Default constructor for JSON serialization
    public User() {
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
    
}
