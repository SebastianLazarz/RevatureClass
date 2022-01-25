package com.revature.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.BankAccountDAO;
import com.revature.dao.CustomerDAO;
import com.revature.models.BankAccount;
import com.revature.models.Customer;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AdminController {
	Javalin app;
	Connection connection;
	CustomerDAO customerDao;
	BankAccountDAO bankAccountDao;
	CustomerDAO customerAccountDao;
	private static final Logger logger = LogManager.getLogger(AdminController.class);
	
	public AdminController (Javalin app, Connection connection) {
		this.app = app;
		this.connection = connection;
		this.customerDao = new CustomerDAO();
		this.bankAccountDao = new BankAccountDAO();
		
		app.get("/admin/customers/count", getCustomerCount);
		app.get("/admin/accounts/count", getBankAccountCount);
		app.get("/admin/accounts/highestnumber", getHighestAccountNumber);
		app.get("/admin/accounts/highestmoney", getHighestMoneyAccount);
		app.put("/admin/customers", updateCustomer);
	}
	
	// shows the number of customers in the database
	public Handler getCustomerCount = ctx -> {
		String count = String.valueOf(customerDao.getAllCustomers().size());
		ctx.result(count);
		ctx.status(200);
	};
	
	// shows the number of bank accounts in the database
	public Handler getBankAccountCount = ctx -> {
		try {
			PreparedStatement prepStatement = connection.prepareStatement("SELECT COUNT(*) AS number FROM account");
			ResultSet results = prepStatement.executeQuery();
			String number = "0";
			
			if (results.next()) {
				number = String.valueOf(results.getInt("number"));
			}
			ctx.result(number);
			ctx.status(200);
			
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.status(500);
		}
		
	};
	
	// shows the highest bank account number
	public Handler getHighestAccountNumber = ctx -> {
		try {
			PreparedStatement prepStatement = connection.prepareStatement("SELECT MAX(account_number) AS number FROM account");
			ResultSet results = prepStatement.executeQuery();
			String number = "0";
			
			if (results.next()) {
				number = String.valueOf(results.getInt("account_number"));
			}
			ctx.result(number);
			ctx.status(200);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.status(500);
		}
	};
	
	// shows the account with the highest money count
	public Handler getHighestMoneyAccount = ctx -> {
		try {
			PreparedStatement prepStatement = connection.prepareStatement("SELECT * FROM account ORDER BY money_amount DESC");
			ResultSet results = prepStatement.executeQuery();			
			BankAccount account = null;
			
			if (results.next()) {
				long number = results.getLong("account_number");
				account = bankAccountDao.getBankAccount(number);
			}
			ctx.json(account);
			ctx.status(200);
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.status(500);
		}
	};
	
	// updates a customer in the database
	// useful to for example change the role of a customer to employee or admin, for which there is no way in the console program
	public Handler updateCustomer = ctx -> {
		try {
			
			Customer customer = ctx.bodyAsClass(Customer.class);
			
			if (customerDao.getCustomer(customer.username) != null && bankAccountDao.getBankAccount(customer.accountNumber) != null) {
				PreparedStatement prepStatement = connection.prepareStatement("UPDATE Customer SET password = ?, account_number = ?, role = ? WHERE username = ?" );
				prepStatement.setString(1, customer.password);
				prepStatement.setLong(2, customer.accountNumber);
				prepStatement.setString(3, customer.role);
				prepStatement.setString(4, customer.username);
				prepStatement.executeUpdate();
				logger.info("Customer " + customer.username + " was updated");
				ctx.status(204);
			} else {
				ctx.status(400);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			ctx.status(500);
		}
	};
}
