package com.revature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AccountDaoImpl implements AccountDao {
	
	private static final String fileName = "/serialize/accounts.txt";	// constant that leads to the textfile where we save our Users
	private ArrayList<Account> accountList = new ArrayList<Account>();		// userList object for runtime
	
	public AccountDaoImpl () {
		try {
			FileInputStream fileIn = new FileInputStream (fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			boolean condition = true;
			while (condition == true) {
				Account account = null;
				
				try {
					account = (Account) in.readObject();
				} catch (ClassNotFoundException classException) {
					// TODO
					classException.printStackTrace();
				}
				
				if (account != null) {
					accountList.add(account);
				} else {
					condition = false;
				}
			}
			in.close();
		} catch (IOException ioException) {
			// TODO
			ioException.printStackTrace();
		} 
	}

	public ArrayList<Account> getallAccounts() {
		// TODO Auto-generated method stub
		return accountList;
	}

	public Account getAccount(long accountNumber) {
		// TODO Auto-generated method stub
		for (int i=0; i < accountList.size(); i++) {
			if (accountList.get(i).getAccountNumber() == accountNumber) {
				return accountList.get(i);
			}
		}
		return null;
	}

	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	public void deleteAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(account);
			out.close();
			accountList.add(account);
		} catch (IOException ioException) {
			// TODO
			ioException.printStackTrace();
		}
	}
	
}
