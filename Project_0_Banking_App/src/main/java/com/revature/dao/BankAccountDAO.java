package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.logging.log4j.Logger;

import com.revature.models.BankAccount;
import com.revature.models.Customer;

public class BankAccountDAO {
	
	public BankAccount createBankAccount() {
		
		try {
			long accountNumber = generateAccountNumber();
			
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("INSERT INTO account VALUES (?, ?, ?)");
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
			PreparedStatement prepStatement = c.prepareStatement("SELECT * FROM account WHERE account_number = ?");
			prepStatement.setLong(1, accountNumber);
			ResultSet results = prepStatement.executeQuery();
			
			while(results.next()) {
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
			PreparedStatement prepStatement = c.prepareStatement("UPDATE account SET money_amount = ?, approved = ? WHERE account_number= ?");
			prepStatement.setDouble(1, account.moneyAmount);
			prepStatement.setBoolean(2, account.approved);
			prepStatement.setLong(3, account.accountNumber);
			prepStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean deleteBankAccount (BankAccount account, Logger logger) {
		try {
			Connection c = ConnectionManager.getConnection();
			CustomerDAO customerDao = new CustomerDAO();
			ArrayList<Customer> customerList = customerDao.getAllCustomers();
			
			for (int i=0; i < customerList.size(); i++) {
				Customer currentCustomer = customerList.get(i);
				if (currentCustomer.accountNumber == account.accountNumber) {
					customerDao.deleteCustomer(currentCustomer);
					logger.info("Deleted customer " + currentCustomer.username);
				}
			}
			
			PreparedStatement prepStatement = c.prepareStatement("DELETE FROM account WHERE account_number = ?");
			prepStatement.setLong(1, account.accountNumber);
			prepStatement.executeUpdate();
			
			return true;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public long generateAccountNumber () {
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("SELECT MAX(account_number) AS number FROM account");
			ResultSet results = prepStatement.executeQuery();
			
			if (results.next()) {
				long number = results.getLong("number");
				return ++number;
			} else {
				return 100000001;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return 0;
	}
}
