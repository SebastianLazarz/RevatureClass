package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.BankAccount;

public class BankAccountDAO {
	
	public BankAccount createBankAccount() {
		
		try {
			long accountNumber = generateAccountNumber();
			
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("INSERT INTO BankAccount VALUES (?, ?, ?)");
			prepStatement.setLong(1, accountNumber);
			prepStatement.setDouble(2, 0);
			prepStatement.setBoolean(3, false);
			
			prepStatement.executeUpdate();
			
			BankAccount newAccount = new BankAccount(accountNumber, 0, false);
			return newAccount;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public BankAccount getBankAccount (long accountNumber) {
		
		
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("SELECT * FROM BankAccount WHERE account_number = ?");
			prepStatement.setLong(1, accountNumber);
			ResultSet results = prepStatement.executeQuery();
			
			if (results.first() == false) {
				return null;
			} else {
				double moneyAmount = results.getDouble("money_amount");
				boolean approved = results.getBoolean("approved");
				BankAccount bankAccount = new BankAccount (accountNumber, moneyAmount, approved);
				return bankAccount;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public boolean updateBankAccount (BankAccount account) {
		
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("UPDATE BankAccount SET money_amount = ?, approved = ? WHERE account_number= ?");
			prepStatement.setDouble(1, account.moneyAmount);
			prepStatement.setBoolean(2, account.approved);
			prepStatement.setLong(3, account.accountNumber);
			prepStatement.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteBankAccount (BankAccount account) {
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("DELETE FROM BankAccount WHERE account_number = ?");
			prepStatement.setLong(1, account.accountNumber);
			prepStatement.executeQuery();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	private long generateAccountNumber () {
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("SELECT MAX(account_number) AS number FROM BankAccount");
			ResultSet results = prepStatement.executeQuery();
			
			if (results.first() == false) {
				return 100000001;
			} else {
				long number = results.getLong("number");
				if (number == 0) {
					return 100000001;
				} else {
					return ++number;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 100000000;
	}
}
