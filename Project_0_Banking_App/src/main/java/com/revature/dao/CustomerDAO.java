package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.revature.models.Customer;

public class CustomerDAO {
	
	public Customer createCustomer(String username, String password, long accountNumber) {
		
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("INSERT INTO Customer VALUES (?, ?, ?, ?)");
			prepStatement.setString(1, username);
			prepStatement.setString(2, password);
			prepStatement.setLong(3, 0);
			prepStatement.setString(4, "Customer");
			
			prepStatement.executeUpdate();
			
			Customer customer = new Customer(username, password, accountNumber, "Customer");
			return customer;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		
	}
	
	public void addAccountNumber (Customer customer) {
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("UPDATE Customer SET acccount_number = ? WHERE username = ?;");
			prepStatement.setLong(1, customer.accountNumber);
			prepStatement.setString(2, customer.username);
			
			prepStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean usernameExists (String username) {
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("SELECT * FROM Customer WHERE username = ?");
			prepStatement.setString(1, username);
			ResultSet results = prepStatement.executeQuery();
			
			if (results.first() == false) {
				return false;
			} else {
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return true;
	}
	
	
	public Customer getCustomer (String username) {
		
		
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("SELECT * FROM Customer WHERE username = ?");
			prepStatement.setString(1, username);
			ResultSet results = prepStatement.executeQuery();
			
			if (results.first() == false) {
				return null;
			} else {
				
				long accountNumber = results.getLong("account_number");
				String password = results.getString("password");
				String role = results.getString("role");
				Customer customer = new Customer(username, password, accountNumber, role);
				return customer;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}
	
	public ArrayList<Customer> getAllCustomers () {
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("SELECT * FROM Customer");
			ResultSet results = prepStatement.executeQuery();
			
			ArrayList<Customer> customers = new ArrayList<Customer>();
			
			if (results.first() == false) {
				return null;
			} else {
				do {
					String username = results.getString("username");
					long accountNumber = results.getLong("account_number");
					String password = results.getString("password");
					String role = results.getString("role");
					Customer customer = new Customer(username, password, accountNumber, role);
					customers.add(customer);
				} while (results.next() == true);
			}
			
			return customers;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
}