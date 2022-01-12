package com.revature;

import java.io.Serializable;

public class Customer implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8304185356372637891L;
	private String username; 
	private String password; 
	private Account bankAccount; // can only have one account per username currently
	private boolean isEmployee;
	private boolean isBankAdmin;
	
	// Create a fresh customer
	
	public Customer (String username, String password) {
		setUsername(username);
		setPassword(password);
		setBankAccount(null);
		setEmployee(false);
		setBankAdmin(false);
	}
	
	public void showMenu () {
		
	}
	
	public void withdrawMoney(double amount) {
		bankAccount.setMoneyAmount(bankAccount.getMoneyAmount() - amount);
	}
	
	public void depositMoney(double amount) {
		bankAccount.setMoneyAmount(bankAccount.getMoneyAmount() + amount);
	}
	
	public void transferMoney(double amount, Account account) {
		withdrawMoney(amount);
		account.setMoneyAmount(bankAccount.getMoneyAmount() + amount);
	}
	
	public void applyForAccount() {
		AccountDaoImpl accountDao = new AccountDaoImpl(); 
		setBankAccount(new Account());
	}
	
	public void applyForJointAccount(Account account) {
		setBankAccount(account);
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
