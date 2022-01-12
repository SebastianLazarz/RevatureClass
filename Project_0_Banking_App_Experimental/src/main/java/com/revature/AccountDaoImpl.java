package com.revature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class AccountDaoImpl implements AccountDao {
	
	private static final String fileName = "C:\\Users\\slaza\\Desktop\\bankAppData\\accounts.txt";	// constant that leads to the textfile where we save our accounts
	private ArrayList<Account> accountList;		// userList object for runtime
	
	public AccountDaoImpl () {
		deserialize();
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

	public void deleteAccount(Account account) {
		for (int i=0; i <= accountList.size(); i++) {
			if (accountList.get(i).equals(account)) {
				accountList.remove(i);
				break;
			}
		}
		serialize();
	}

	public void addAccount(Account account) {
		// TODO Auto-generated method stub
		accountList.add(account);
		serialize();
	}
	
	public long generateAccountNumber() {
		long result = 100000000; // starting account numbers at this value
		for (int i=0; i < accountList.size(); i++) {
			result = Math.max(result, accountList.get(i).getAccountNumber());
		}
		return result++;
	}
	
	private void deserialize () {
		try {
			FileInputStream fileIn = new FileInputStream (fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
				
			try {
				accountList = (ArrayList<Account>) in.readObject();
			} catch (ClassNotFoundException classException) {
				// TODO
				classException.printStackTrace();
			}
			in.close();
		} catch (IOException ioException) {
			// TODO
			ioException.printStackTrace();
		} 
	}
	
	private void serialize () {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName, false);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(accountList);
			out.close();
		} catch (IOException ioException) {
			// TODO
			ioException.printStackTrace();
		}
	}
	
}
