package com.revature;

import java.util.ArrayList;

public interface AccountDao {
	public ArrayList<Account> getallAccounts();
	public Account getAccount(long accountNumber);
	public void updateAccount(Account account);
	public void deleteAccount(Account account);
	public void addAccount(Account account);
}
