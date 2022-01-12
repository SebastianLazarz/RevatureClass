package com.revature;

import java.util.ArrayList;
import java.util.List;

public class BankAccountList {

	private List<BankAccount> accountList;

	public BankAccountList() {
		accountList = new ArrayList<BankAccount>();
	}
	
	public void add(BankAccount account) {
		account.setAccountNumber(generateUniqueNumber());
		accountList.add(account);
	}
	
	private long generateUniqueNumber() {
		long result = 100000000;		// starting account numbers at 9 digits
		for (int i=0; i < accountList.size(); i++) {
			result = Math.max(result, accountList.get(i).getAccountNumber());
		}
		return ++result;
	}
	
	public List<BankAccount> getBankAccountList () {
		return accountList;
	}
	
	public BankAccount getBankAccountByAccountNumber (long accountNumber) {
		for (int i=0; i < accountList.size(); i++) {
			if (accountNumber == accountList.get(i).getAccountNumber()) {
				return accountList.get(i);
			}
		}
		return null;
	}
}
