package com.revature;

public class BankAdmin extends Employee {

	public BankAdmin(String username, String password) {
		super(username, password);
		setBankAdmin(true);
		
	}
	
	public void transferMoney (Account accountA, Account accountB, double amount) {
		accountA.setMoneyAmount(accountA.getMoneyAmount() - amount);
		accountB.setMoneyAmount(accountB.getMoneyAmount() + amount);
	}
	
	public void withdrawMoney (Account account, double amount) {
		account.setMoneyAmount(account.getMoneyAmount() - amount);
	}
	
	public void depositMoney (Account account, double amount) {
		account.setMoneyAmount(account.getMoneyAmount() + amount);
	}
	
	public void cancelAccount (Account account) {
		AccountDaoImpl accountDao = new AccountDaoImpl();
		accountDao.deleteAccount(account);
	}

}
