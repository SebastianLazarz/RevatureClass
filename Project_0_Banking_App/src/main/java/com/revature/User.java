package com.revature;

import java.io.Serializable;

public class User implements Serializable {
	private String username; 
	private String password; 
	private Account bankAccount; // can only have one account per username currently
	private boolean isCustomer;
	private boolean isEmployee;
	private boolean isBankAdmin;
	
	// Create a fresh user
	
	public User (String username, String password) {
		setUsername(username);
		setPassword(password);
		setBankAccount(null);
		setCustomer(false);
		setEmployee(false);
		setBankAdmin(false);
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
	public boolean isCustomer() {
		return isCustomer;
	}
	public void setCustomer(boolean isCustomer) {
		this.isCustomer = isCustomer;
	}
	public boolean isEmployee() {
		return isEmployee;
	}
	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}
	public boolean isBankAdmin() {
		return isBankAdmin;
	}
	public void setBankAdmin(boolean isBankAdmin) {
		this.isBankAdmin = isBankAdmin;
	}
	public Account getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(Account bankAccount) {
		this.bankAccount = bankAccount;
	}
}
