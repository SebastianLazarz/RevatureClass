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
			prepStatement.setLong(3, accountNumber);
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
	
	public boolean usernameExists (String username) {
		try {
			Connection c = ConnectionManager.getConnection();
			PreparedStatement prepStatement = c.prepareStatement("SELECT * FROM Customer WHERE username = ?");
			prepStatement.setString(1, username);
			ResultSet results = prepStatement.executeQuery();
			
			if (results.next() == false) {
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
			
			while(results.next()) {
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
			PreparedStatement prepStatement = c.prepareStatement("SELECT * FROM Customer ORDER BY account_number ASC");
			ResultSet results = prepStatement.executeQuery();
			
			ArrayList<Customer> customers = new ArrayList<Customer>();
			
			while(results.next()) {
				String username = results.getString("username");
				long accountNumber = results.getLong("account_number");
				String password = results.getString("password");
				String role = results.getString("role");
				Customer customer = new Customer(username, password, accountNumber, role);
				customers.add(customer);
			}
			
			return customers;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public boolean deleteCustomer (Customer customer) {
		try {
			Connection c = ConnectionManager.getConnection();
			
			PreparedStatement prepStatement = c.prepareStatement("DELETE FROM Customer WHERE username = ?");
			prepStatement.setString(1, customer.username);
			prepStatement.executeUpdate();
			
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false;
	}
}
