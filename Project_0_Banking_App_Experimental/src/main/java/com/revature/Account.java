package com.revature;

import java.io.Serializable;

public class Account implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -6141539836090337018L;
	private double moneyAmount;
	private long accountNumber;
	private boolean isApproved;
	
	public Account () {
		AccountDaoImpl accountDao = new AccountDaoImpl();
		setApproved(false);
		setAccountNumber(accountDao.generateAccountNumber()); // generates a unique Account number
		setMoneyAmount(0);
	}
	
	public double getMoneyAmount() {
		return moneyAmount;
	}
	public void setMoneyAmount(double moneyAmount) {
		this.moneyAmount = moneyAmount;
	}
	public long getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(long accountNumber) {
		this.accountNumber = accountNumber;
	}
	public boolean isApproved() {
		return isApproved;
	}
	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}
	
}
