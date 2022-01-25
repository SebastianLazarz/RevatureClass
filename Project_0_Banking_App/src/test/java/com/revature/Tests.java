package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.revature.dao.BankAccountDAO;
import com.revature.dao.ConnectionManager;
import com.revature.dao.CustomerDAO;
import com.revature.models.BankAccount;
import com.revature.models.Customer;

public class Tests {
	Scanner scanner = new Scanner(System.in);
	CustomerDAO customerDao = new CustomerDAO();
	BankAccountDAO bankAccountDao = new BankAccountDAO();
	private static final Logger logger = LogManager.getLogger(Tests.class);
	
	@Test
	public void getConnectionTest() {
		Connection connection = ConnectionManager.getConnection();
		assertNotNull(connection);
	}
	
	@Test
	public void createBankAccountTest() {
		BankAccount account = bankAccountDao.createBankAccount();
		assertNotNull(account);
		assertTrue(account.accountNumber > 100000000);
		assertEquals(account.moneyAmount, 0);
		assertTrue(!account.approved);
		bankAccountDao.deleteBankAccount(account, logger);
	}
	
	@Test
	public void getBankAccountTest() {
		BankAccount account = bankAccountDao.createBankAccount();
		BankAccount accountGet = bankAccountDao.getBankAccount(account.accountNumber);
		assertNotNull(accountGet);
		assertEquals(account.accountNumber, accountGet.accountNumber);
		bankAccountDao.deleteBankAccount(account, logger);
	}
	
	@Test
	public void updateBankAccountTest() {
		BankAccount account = bankAccountDao.createBankAccount();
		account.approved = true;
		account.moneyAmount = 100;
		assertTrue(bankAccountDao.updateBankAccount(account));
		BankAccount accountGet = bankAccountDao.getBankAccount(account.accountNumber);
		assertNotNull(accountGet);
		assertEquals(account.accountNumber, accountGet.accountNumber);
		assertEquals(account.approved, accountGet.approved);
		assertEquals(account.moneyAmount, accountGet.moneyAmount);
		bankAccountDao.deleteBankAccount(account, logger);
	}
	
	@Test
	public void createCustomerTest() {
		
		String username = "Martini";
		Customer customer = customerDao.getCustomer(username);
		if (customer == null) {
			String password = "12345";
			BankAccount account = bankAccountDao.createBankAccount();
			customer = customerDao.createCustomer(username, password, account.accountNumber);
			assertNotNull(customer);
			assertEquals(customer.accountNumber, account.accountNumber);
			assertEquals(customer.username, username);
			assertEquals(customer.role, "Customer");
			assertEquals(customer.password, password);

			bankAccountDao.deleteBankAccount(account, logger);
		} else {
			assertNotNull(customer);
			assertEquals(username, customer.username);
		}
		
		
	}
	
	@Test
	public void deleteBankAccountTest() {
		String username = "Martini";
		Customer customer = customerDao.getCustomer(username);
		if (customer == null) {
			String password = "12345";
			BankAccount account = bankAccountDao.createBankAccount();
			customer = customerDao.createCustomer(username, password, account.accountNumber);
			boolean check = bankAccountDao.deleteBankAccount(account, logger);
			assertTrue(check);
			assertNull(bankAccountDao.getBankAccount(account.accountNumber));
			assertNull(customerDao.getCustomer(username));
		}
	}
	
	@Test
	public void deleteCustomerTest() {
		String username = "Martini";
		Customer customer = customerDao.getCustomer(username);
		
		if (customer == null) {
			String password = "12345";
			BankAccount account = bankAccountDao.createBankAccount();
			customer = customerDao.createCustomer(username, password, account.accountNumber);
			assertTrue(customerDao.deleteCustomer(customer));
			assertNull(customerDao.getCustomer(username));
			bankAccountDao.deleteBankAccount(account, logger);
		} 
	}
	
	@Test
	public void getAllCustomersTest() {
		String username = "Martini";
		Customer customer = customerDao.getCustomer(username);
		if (customer == null) {
			String password = "12345";
			BankAccount account = bankAccountDao.createBankAccount();
			customer = customerDao.createCustomer(username, password, account.accountNumber);
			ArrayList<Customer> customers = customerDao.getAllCustomers();
			assertNotNull(customers);
			bankAccountDao.deleteBankAccount(account, logger);
		}
	}
	
	@Test
	public void getCustomerTest() {
		String username = "Martini";
		Customer customer = customerDao.getCustomer(username);
		if (customer == null) {
			String password = "12345";
			BankAccount account = bankAccountDao.createBankAccount();
			customer = customerDao.createCustomer(username, password, account.accountNumber);
			Customer customerGet = customerDao.getCustomer(username);
			assertNotNull(customerGet);
			assertEquals(username, customerGet.username);
			assertEquals(password, customerGet.password);
			bankAccountDao.deleteBankAccount(account, logger);
		}
	}
	
	@Test
	public void usernameExistsTest() {
		String username = "Martini";
		Customer customer = customerDao.getCustomer(username);
		
		if (customer == null) {
			String password = "12345";
			BankAccount account = bankAccountDao.createBankAccount();
			customer = customerDao.createCustomer(username, password, account.accountNumber);
			assertTrue(customerDao.usernameExists(username));
			bankAccountDao.deleteBankAccount(account, logger);
		} else {
			assertTrue(customerDao.usernameExists(username));
		}
	}

	
	@BeforeEach
	public void setupEachTest() {
		
		scanner = new Scanner(System.in);
		customerDao = new CustomerDAO();
		bankAccountDao = new BankAccountDAO();
	}
}
