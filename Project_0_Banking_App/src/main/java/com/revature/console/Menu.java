package com.revature.console;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.models.BankAccount;
import com.revature.models.Customer;
import com.revature.dao.BankAccountDAO;
import com.revature.dao.CustomerDAO;

public class Menu {
	
	private static final Logger logger = LogManager.getLogger(Menu.class);
	private Scanner sc;
	
	public Menu (Scanner scanner) {
		sc = scanner;
	}
	
	public void entryScreen () {
		System.out.println("Welcome to my banking app! To navigate this menu, please enter the integer number next to each option listed below.");
		System.out.println("(1) Login");
		System.out.println("(2) Register");

		int option = getOptionInput(generateOptionSet(2));
		
		switch (option) {
		case 1:
			loginScreen();
			break;
		case 2:
			registerScreen();
			break;
		default:
			// this should never happen
			System.out.println("Unexpected behaviour on entryScreen!");
		}
	}
	
	private void loginScreen () {
		Customer customer = null;
		CustomerDAO customerDao = new CustomerDAO();
		
		while (customer == null) {
			System.out.println("Please enter your username.");
			String username = sc.next();
			System.out.println("Please enter your password.");
			String password = sc.next();
			
			customer = customerDao.getCustomer(username);
			
			if (customer == null || !customer.password.equals(password)) {
				System.out.println("Either this username doesn't exist or your password was incorrect. Please try again.");
				customer = null;
			}
		}
		
		customerMenu(customer);		
	}
	
	private void registerScreen () {
		System.out.println("Please enter your desired username.");
		String username = sc.next();
		
		CustomerDAO customerDao = new CustomerDAO();
		while (customerDao.usernameExists(username)) {
			System.out.println("This username already exists! Please try a different one.");
			username = sc.next();
		}
		System.out.println("Please enter your passsword.");
		String password = sc.next();
		
		System.out.println("In order to use this banking app, you will need a Bank Account from our bank. Please select one of the following options:");
		System.out.println("(1) Open a new bank account");
		System.out.println("(2) Apply for a joint account");
		
		int option = getOptionInput(generateOptionSet(2));
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		long accountNumber;
		
		switch (option) {
		case 1:
			BankAccount newAccount = bankAccountDao.createBankAccount();
			customerDao.createCustomer(username, password, newAccount.accountNumber);
			System.out.println("Congratulations, you successfully registered. Your bank account number is " + newAccount.accountNumber);
			logger.info("New customer " + username + " created with new account " + newAccount.accountNumber);
			break;
		case 2:
			System.out.println("Please enter the account number of the joint account");
			accountNumber = getLongInput();
			while (bankAccountDao.getBankAccount(accountNumber) == null) {
				System.out.println("This account number doesn't exist. Please try again.");
				accountNumber = getLongInput();
			}
			customerDao.createCustomer(username,  password, accountNumber);
			System.out.println("Congratulations, you successfully gained access to your joint account.");
			logger.info("New customer " + username + " created with joint account " + accountNumber);
			break;
		default:
			// this should never happen
			System.out.println("Unexpected behaviour on entryScreen!");
		}
		
		entryScreen();
		

	}
	
	private void customerMenu (Customer customer) {
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		BankAccount bankAccount = bankAccountDao.getBankAccount(customer.accountNumber);
		
		if (bankAccount.approved) {
			switch (customer.role) {
			case "Employee": 
				employeeMenu(customer);
				break;
			case "Admin":
				adminMenu(customer);
				break;
			default: 		
				System.out.println("Welcome " + customer.username + ".");
				System.out.println("Your account number is " + customer.accountNumber);
				System.out.println("Current amount of money deposited: " + bankAccount.moneyAmount);
				System.out.println("(1) Deposit money");
				System.out.println("(2) Withdraw money");
				System.out.println("(3) Transfer money");
				System.out.println("(4) Log out");
				
				int option = getOptionInput(generateOptionSet(4));
				
				switch (option) {
				case 1:
					depositScreen(customer);
					break;
				case 2:
					withdrawScreen(customer);
					break;
				case 3:
					transferScreen(customer);
					break;
				case 4:
					entryScreen();
					break;
				default:
					// this should never happen if our method getOptionInput is working correctly
					System.out.println("Unexpected behaviour on entryScreen!");
				}
				break;
			}
		} else {
			System.out.println("Your bank account is not approved yet. Please talk to a bank employee if you have any questions regarding the approval process.");
			entryScreen();
		}
	}
	
	private void employeeMenu (Customer customer) {
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		BankAccount bankAccount = bankAccountDao.getBankAccount(customer.accountNumber);
					
		System.out.println("Welcome " + customer.username + ".");
		System.out.println("Your account number is " + customer.accountNumber);
		System.out.println("Current amount of money deposited: " + bankAccount.moneyAmount);
		System.out.println("(1) Deposit money");
		System.out.println("(2) Withdraw money");
		System.out.println("(3) Transfer money");
		System.out.println("(4) View customer information");
		System.out.println("(5) Change approval status of an account");
		System.out.println("(6) Log out");
		
		int option = getOptionInput(generateOptionSet(6));
		
		switch (option) {
		case 1:
			depositScreen(customer);
			break;
		case 2:
			withdrawScreen(customer);
			break;
		case 3:
			transferScreen(customer);
			break;
		case 4:
			viewCustomerScreen(customer);
			break;
		case 5:
			approvalScreen(customer);
			break;
		case 6:
			entryScreen();
			break;
		default:
			// this should never happen if our method getOptionInput is working correctly
			System.out.println("Unexpected behaviour on entryScreen!");
		}
	}
	
	private void adminMenu (Customer customer) {
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		BankAccount bankAccount = bankAccountDao.getBankAccount(customer.accountNumber);
					
		System.out.println("Welcome " + customer.username + ".");
		System.out.println("Your account number is " + customer.accountNumber);
		System.out.println("Current amount of money deposited: " + bankAccount.moneyAmount);
		System.out.println("(1) Deposit money");
		System.out.println("(2) Withdraw money");
		System.out.println("(3) Transfer money");
		System.out.println("(4) View customer information");
		System.out.println("(5) Edit an account");
		System.out.println("(6) Log out");
		
		int option = getOptionInput(generateOptionSet(6));
		
		switch (option) {
		case 1:
			depositScreen(customer);
			break;
		case 2:
			withdrawScreen(customer);
			break;
		case 3:
			transferScreen(customer);
			break;
		case 4:
			viewCustomerScreen(customer);
			break;
		case 5:
			editAccountScreen(customer);
			break;
		case 6:
			entryScreen();
			break;
		default:
			// this should never happen if our method getOptionInput is working correctly
			System.out.println("Unexpected behaviour on entryScreen!");
		}
	}
	
	private void depositScreen (Customer customer) {
		System.out.println("How much do you want to deposit? Write as double value.");
		double amount = getMoneyInput();
		
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		BankAccount bankAccount = bankAccountDao.getBankAccount(customer.accountNumber);
		bankAccount.moneyAmount += amount;
		bankAccountDao.updateBankAccount(bankAccount);
		
		System.out.println("You successfully deposited " + amount + "!");
		logger.info(customer.username + " deposited " + amount);
		
		customerMenu(customer);
	}
	
	private void withdrawScreen (Customer customer) {		
		System.out.println("How much do you want to withdraw? Write as double value > 0.");
		double amount = getMoneyInput();
		
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		BankAccount bankAccount = bankAccountDao.getBankAccount(customer.accountNumber);
		
		while (amount > bankAccount.moneyAmount) {
			System.out.println("You tried to withdraw more money than you have deposited! Please try again.");
			amount = getMoneyInput();
		}
		bankAccount.moneyAmount -= amount;
		bankAccountDao.updateBankAccount(bankAccount);
		System.out.println("You successfully withdrew " + amount + "!");
		logger.info(customer.username + " withdrew " + amount);
		
		customerMenu(customer);
	}
	
	private void transferScreen (Customer customer) {
		System.out.println("Please enter the bank account number of the account you want to send money to.");
		
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		BankAccount account1 = bankAccountDao.getBankAccount(customer.accountNumber);
		BankAccount account2 = null;
		
		while (account2 == null) {
			long accountNumber = getLongInput();
			account2 = bankAccountDao.getBankAccount(accountNumber);
			if (account2 == null) {
				System.out.println("This account number is unknown to us. Please try again.");
			} 
		}
		
		System.out.println("Ok. How much money do you want to send?");
		double amount = getMoneyInput();
		while (amount > account1.moneyAmount) {
			System.out.println("You tried to send more money than you have deposited! Please try again.");
			amount = getMoneyInput();
		}
		account1.moneyAmount -= amount;
		account2.moneyAmount += amount;
		bankAccountDao.updateBankAccount(account1);
		bankAccountDao.updateBankAccount(account2);
		System.out.println("You successfully sent " + amount + " to " + account2.accountNumber + "!");
		logger.info(customer.username + " transfered " + amount + " to " + account2.accountNumber);
		
		customerMenu(customer);
	}
	
	private void viewCustomerScreen (Customer customer) {
		CustomerDAO customerDao = new CustomerDAO();
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		ArrayList<Customer> customers = customerDao.getAllCustomers();
		
		System.out.println("Showing all customers in our database:");
		
		if (customers == null) {
			// This should never happen
			System.out.println("Something is not right! Cannot find any customers.");
		} else {
			for (int i=0; i < customers.size(); i++) {
				Customer currentCustomer = customers.get(i);
				BankAccount currentAccount = bankAccountDao.getBankAccount(currentCustomer.accountNumber);
				
				System.out.println(i + ": Username: " + currentCustomer.username + ", Password: " + currentCustomer.password + ", Account Number: " +currentCustomer.accountNumber + ", Role: " + currentCustomer.role);
				System.out.println("Money deposited: " + currentAccount.moneyAmount + ", Approval Status: " + currentAccount.approved);
			}
		}
		
		customerMenu(customer);
	}
	
	private void approvalScreen (Customer customer) {
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		System.out.println("For what account number do you want to change the approval status?");
		
		BankAccount account = null;
		
		while (account == null) {
			long accountNumber = getLongInput();
			account = bankAccountDao.getBankAccount(accountNumber);
			if (account == null) {
				System.out.println("This account number is unknown to us. Please try again.");
			} 
		}
		
		if (account.approved == true) {
			account.approved = false;
			bankAccountDao.updateBankAccount(account);
			System.out.println(account.accountNumber + " is no longer approved.");
			logger.info(account.accountNumber + " is no longer approved by " + customer.username);
		} else {
			account.approved = true;
			bankAccountDao.updateBankAccount(account);
			System.out.println(account.accountNumber + " is now approved");
			logger.info(account.accountNumber + " is now approved by " + customer.username);
		}
		
		customerMenu(customer);
	}
	
	private void editAccountScreen (Customer customer) {
		BankAccountDAO bankAccountDao = new BankAccountDAO();
		System.out.println("What account do you want to edit?");
		
		BankAccount account = null;
		
		while (account == null) {
			long accountNumber = getLongInput();
			account = bankAccountDao.getBankAccount(accountNumber);
			if (account == null) {
				System.out.println("This account number is unknown to us. Please try again.");
			} 
		}
		
		System.out.println("What do you want to do with Account " + account.accountNumber + "?");
		System.out.println("(1) Change approval status");
		System.out.println("(2) Withdraw money");
		System.out.println("(3) Deposit money");
		System.out.println("(4) Transfer money");
		System.out.println("(5) Cancel account");
		System.out.println("(6) Go back");
		
		int option = getOptionInput(generateOptionSet(6));
		
		switch (option) {
		case 1:
			if (account.approved == true) {
				account.approved = false;
				bankAccountDao.updateBankAccount(account);
				System.out.println(account.accountNumber + " is no longer approved.");
				logger.info(account.accountNumber + " is no longer approved by " + customer.username);
			} else {
				account.approved = true;
				bankAccountDao.updateBankAccount(account);
				System.out.println(account.accountNumber + " is now approved.");
				logger.info(account.accountNumber + " is now approved by " + customer.username);
			}
			adminMenu(customer);
			break;
			
		case 2:
			System.out.println("How much do you want to withdraw? Write as double value > 0.");
			double withdrawalAmount = getMoneyInput();
			
			while (withdrawalAmount > account.moneyAmount) {
				System.out.println("You tried to withdraw more money than you have deposited! Please try again.");
				withdrawalAmount = getMoneyInput();
			}
			account.moneyAmount -= withdrawalAmount;
			bankAccountDao.updateBankAccount(account);
			System.out.println("You successfully withdrew " + withdrawalAmount + "!");
			logger.info("Admin " + customer.username + " withdrew " + withdrawalAmount + " from " + account.accountNumber);
			adminMenu(customer);
			break;
			
		case 3:
			System.out.println("How much do you want to deposit? Write as double value.");
			double depositAmount = getMoneyInput();
			account.moneyAmount += depositAmount;
			bankAccountDao.updateBankAccount(account);
			System.out.println("You successfully deposited " + depositAmount + "!");
			logger.info("Admin " + customer.username + " deposited " + depositAmount + " into " + account.accountNumber);
			adminMenu(customer);
			break;
			
		case 4:
			System.out.println("To what account do you want to transfer money?");
			BankAccount account2 = null;
			
			while (account2 == null) {
				long accountNumber = getLongInput();
				account2 = bankAccountDao.getBankAccount(accountNumber);
				if (account2 == null) {
					System.out.println("This account number is unknown to us. Please try again.");
				} 
			}
			
			System.out.println("Ok. How much money do you want to send?");
			double amount = getMoneyInput();
			while (amount > account.moneyAmount) {
				System.out.println("You tried to send more money than is deposited! Please try again.");
				amount = getMoneyInput();
			}
			account.moneyAmount -= amount;
			account2.moneyAmount += amount;
			bankAccountDao.updateBankAccount(account);
			bankAccountDao.updateBankAccount(account2);
			System.out.println("You successfully sent " + amount + " from " + account.accountNumber + " to " + account2.accountNumber + "!");
			logger.info("Admin " + customer.username + " transfered " + amount + " from " + account.accountNumber + " to " + account2.accountNumber);
			adminMenu(customer);
			break;
		case 5:
			bankAccountDao.deleteBankAccount(account, logger);
			System.out.println("Account was cancelled!");
			logger.info("Admin " + customer.username + " deleted account " + account.accountNumber + "; deleted all customers associated with this account");
			adminMenu(customer);
			break;
		case 6:
			adminMenu(customer);
			break;
		default:
			// this should never happen
			System.out.println("Something is not right here!");
		}		
	}
	
	private int getOptionInput (HashSet<Integer> optionSet) {
		int option = 0;
		do {
			while (!sc.hasNextInt()) {
				System.out.println("Invalid input! Enter one of the option integers listed above.");
				sc.next();
			}
			option = sc.nextInt();
			if (!optionSet.contains(option)) {
				System.out.println("Invalid input! Enter one of the option integers listed above.");
			}
		} while(!optionSet.contains(option));
		
		return option;
	}
	
	private HashSet<Integer> generateOptionSet (int n) {
		HashSet<Integer> optionSet = new HashSet<Integer>();
		
		for (int i=1; i <= n; i++) {
			optionSet.add(i);
		}
		
		return optionSet;
	}
	
	private double getMoneyInput () {
		double money = 0;
		do {
			while (!sc.hasNextDouble()) {
				System.out.println("Invalid input! Try again.");
				sc.next();
			}
			money = sc.nextDouble();
			if (money <= 0) {
				System.out.println("Invalid input! Try again.");
			}
		} while (money <= 0);
		return money;
	}
	
	private long getLongInput () {
		while (!sc.hasNextLong()) {
			System.out.println("Invalid input! Try again.");
			sc.next();
		}
		return sc.nextLong();
	}
	
	
}
