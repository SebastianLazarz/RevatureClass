package com.revature.controller;

import java.sql.Connection;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.BankAccountDAO;
import com.revature.dao.CustomerDAO;
import com.revature.models.Customer;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class CustomerController {
	
	Javalin app;
	Connection connection;
	CustomerDAO customerDao;
	BankAccountDAO bankAccountDao;
	private static final Logger logger = LogManager.getLogger(CustomerController.class);
	
	public CustomerController(Javalin app, Connection connection) {
		this.app = app;
		this.connection = connection;
		this.customerDao = new CustomerDAO();
		this.bankAccountDao = new BankAccountDAO();
		
		app.get("/customers/{username}", getCustomer);
		app.get("/customers/", getAllCustomers);
		app.post("/customers", createCustomer);
		app.delete("/users/{username}", deleteCustomer);
		
	}
	
	
	// gets a customer by its username
	public Handler getCustomer = ctx -> {
		Customer customer = customerDao.getCustomer(ctx.pathParam("username"));
		ctx.json(customer);
	};
	
	// gets all customers as an arraylist
	public Handler getAllCustomers = ctx -> {
		ArrayList<Customer> customers = customerDao.getAllCustomers();
		ctx.json(customers);
	};
		
	// creates a regular customer (role "Customer") using the information provided, assuming that there is no conflict with our database
	public Handler createCustomer = ctx -> {
		Customer customer = ctx.bodyAsClass(Customer.class);
		
		if (!customerDao.usernameExists(customer.username) && bankAccountDao.getBankAccount(customer.accountNumber) != null) {
			customerDao.createCustomer(customer.username, customer.password, customer.accountNumber);
			logger.info("Customer " + customer.username + " was created");
			ctx.status(201);
		} else {
			ctx.status(400);
		}
	};
	
	// deletes a customer assuming the customer username exists
	public Handler deleteCustomer = ctx -> {
		Customer customer = ctx.bodyAsClass(Customer.class);
		if (customerDao.usernameExists(customer.username)) {
			customerDao.deleteCustomer(customer);
			logger.info("Customer " + customer.username + " was deleted");
			ctx.status(204);
		} else {
			ctx.status(400);
		}
	};
}
