package com.revature.models;

public class BankAccount {
	
	
	public long accountNumber;
	public double moneyAmount;
	public boolean approved;
	
	public BankAccount(long accountNumber, double moneyAmount, boolean approved) {
		this.accountNumber = accountNumber;
		this.moneyAmount = moneyAmount;
		this.approved = approved;
	}
}
