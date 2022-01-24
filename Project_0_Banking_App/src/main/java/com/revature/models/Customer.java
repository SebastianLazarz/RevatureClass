package com.revature.models;

public class Customer {
	public String username;
	public String password;
	public long accountNumber;
	public String role;
	
	public Customer (String username, String password, long accountNumber, String role) {
		this.username = username;
		this.password = password;
		this.accountNumber = accountNumber;
		this.role = role;
	}	
}
