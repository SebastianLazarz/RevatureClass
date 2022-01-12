package com.revature;

import java.util.ArrayList;

public class Employee extends Customer {

	public Employee(String username, String password) {
		super(username, password);
		setEmployee(true);
		setBankAccount(new Account());
		getBankAccount().setApproved(true);
	}
	
	public void viewCustomer (String username) {
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		Customer customer = customerDao.getUser(username);
		String status = "";
		
		if (customer.isBankAdmin()) {
			status = "Bank Admin";
		} else if (customer.isEmployee()) {
			status = "Employee";
		} else {
			status = "Customer";
		}
		
		System.out.println("Username: " + customer.getUsername());
		System.out.println("Password: " + customer.getPassword());
		System.out.println("Status: " + status);
		System.out.println("Account: " + customer.getBankAccount());
		System.out.println("Account Balance: " + customer.getBankAccount().getMoneyAmount());
		System.out.println("Account Approved: " + customer.getBankAccount().isApproved());
		System.out.println("");
	}

	public void approveAccount (Account account) {
		account.setApproved(true);
	}
	
	public void denyAccount (Account account) {
		account.setApproved(false);
	}
	
}
