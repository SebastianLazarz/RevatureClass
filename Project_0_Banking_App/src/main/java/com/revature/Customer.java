package com.revature;

public class Customer {
	
	private String username;
	private String password;
	private BankAccount bankAccount;
	
	public Customer(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public void createBankAccount () {
		setBankAccount(new BankAccount());
	}
	
	public void withdrawMoney (double amount) {
		if (amount > 0 && amount <= bankAccount.getMoneyAmount()) {
			bankAccount.setMoneyAmount(bankAccount.getMoneyAmount() - amount);
		}
	}
	
	public void depositMoney (double amount) {
		if (amount > 0) {
			bankAccount.setMoneyAmount(bankAccount.getMoneyAmount() + amount);
		}
	}
	
	public void transferMoney (BankAccount otherAccount, double amount) {
		withdrawMoney(amount);
		otherAccount.setMoneyAmount(otherAccount.getMoneyAmount() + amount);
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
	public BankAccount getBankAccount() {
		return bankAccount;
	}
	public void setBankAccount(BankAccount bankAccount) {
		this.bankAccount = bankAccount;
	}
	
}
