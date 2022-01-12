package com.revature;

public class BankAccount {
	
	private long accountNumber;
	private double moneyAmount;
	
	public BankAccount() {
		setMoneyAmount(0);
	}

	public long getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}

	public double getMoneyAmount() {
		return moneyAmount;
	}

	public void setMoneyAmount(double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}
	
	
}
