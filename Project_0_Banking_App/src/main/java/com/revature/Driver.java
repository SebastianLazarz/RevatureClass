package com.revature;

import java.util.HashSet;
import java.util.Scanner;

public class Driver {
	
	private static Scanner sc;
	private static BankAccountList bankAccounts;
	private static CustomerList customers;
	
	public static void main (String[] args) {
		sc = new Scanner(System.in);
		bankAccounts = new BankAccountList();
		customers = new CustomerList();
		
		// for testing add a few default customers
		customers.add(new Customer("helmut", "12345"));
		customers.add(new Customer("marlowe", "12345"));
		for (int i=0; i < customers.getCustomerList().size(); i++) {
			Customer customer = customers.getCustomerList().get(i);
			customer.createBankAccount();
			bankAccounts.add(customer.getBankAccount());
		}
		
		entryScreen();
		sc.close();
	}
	
	
	private static void entryScreen () {
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
			// this should never happen if our method getOptionInput is working correctly
			System.out.println("Unexpected behaviour on entryScreen!");
		}
	}
	
	private static void loginScreen () {
		Customer customer = null;
		do {
			System.out.println("Please enter your username.");
			String username = sc.next();
			System.out.println("Please enter your password.");
			String password = sc.next();
			
			customer = attemptLogin(username, password);
			if (customer == null) {
				System.out.println("Either this username doesn't exist or your password was incorrect. Please try again.");
			} 
		} while (customer == null);
		
		customerMenu(customer);
	}
	
	private static void registerScreen () {
		System.out.println("Please enter your username.");
		String username = sc.next();
		while (customers.getCustomerByName(username) != null) {
			System.out.println("This username already exists! Please try a different one.");
			username = sc.next();
		}
		System.out.println("Please enter your passsword.");
		String password = sc.next();
		
		customers.add(new Customer(username, password));
		// for now just automatically opening a bank account
		customers.getCustomerByName(username).createBankAccount();
		bankAccounts.add(customers.getCustomerByName(username).getBankAccount());
		
		System.out.println("Congratulations, you successfully registered. You should write down your username and password.");
		System.out.println("By the way, we already opened a bank account for you as well. Your bank account number is " + customers.getCustomerByName(username).getBankAccount().getAccountNumber());
		entryScreen();
	}
	
	private static void customerMenu (Customer customer) {
		System.out.println("Welcome " + customer.getUsername() + ".");
		System.out.println("Your account number is " + customer.getBankAccount().getAccountNumber());
		System.out.println("Current amount of money deposited: " + customer.getBankAccount().getMoneyAmount());
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
	}
	
	private static void depositScreen (Customer customer) {
		System.out.println("How much do you want to deposit? Write as double value.");
		double amount = getMoneyInput();
		customer.depositMoney(amount);
		System.out.println("You successfully deposited " + amount + "!");
		
		customerMenu(customer);
	}
	
	private static void withdrawScreen (Customer customer) {
		System.out.println("How much do you want to withdraw? Write as double value > 0.");
		double amount = getMoneyInput();
		while (amount > customer.getBankAccount().getMoneyAmount()) {
			System.out.println("You tried to withdraw more money than you have deposited! Please try again.");
			amount = getMoneyInput();
		}
		customer.withdrawMoney(amount);
		System.out.println("You successfully withdrew " + amount + "!");
		
		customerMenu(customer);
	}
	
	private static void transferScreen (Customer customer) {
		System.out.println("Please enter the bank account number of the account you want to send money to.");
		
		BankAccount account = null;
		do {
			long accountNumber = getLongInput();
			account = bankAccounts.getBankAccountByAccountNumber(accountNumber);
			if (account == null) {
				System.out.println("This account number is unknown to us. Please try again.");
			} 
		} while (account == null);
		
		System.out.println("Ok. How much money do you want to send?");
		double amount = getMoneyInput();
		while (amount > customer.getBankAccount().getMoneyAmount()) {
			System.out.println("You tried to send more money than you have deposited! Please try again.");
			amount = getMoneyInput();
		}
		customer.withdrawMoney(amount);
		account.setMoneyAmount(account.getMoneyAmount() + amount);
		System.out.println("You successfully sent " + amount + " to " + account.getAccountNumber() + "!");
		
		customerMenu(customer);
	}
	
	private static Customer attemptLogin (String username, String password) {
		Customer customer = customers.getCustomerByName(username);
		if (customer != null) {
			if (password.equals(customer.getPassword())) {
				return customer;
			}
		}
		return null;
	}
	
	
	
	private static int getOptionInput (HashSet<Integer> optionSet) {
		int option = 0;
		do {
			while (!sc.hasNextInt() || optionSet.contains(option) == false) {
				System.out.println("Invalid input! Enter one of the option integers listed above.");
				sc.next();
			}
			option = sc.nextInt();
		} while(!optionSet.contains(option));
		
		return option;
	}
	
	private static HashSet<Integer> generateOptionSet (int n) {
		HashSet<Integer> optionSet = new HashSet<Integer>();
		
		for (int i=1; i <= n; i++) {
			optionSet.add(i);
		}
		
		return optionSet;
	}
	
	private static double getMoneyInput () {
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
	
	private static long getLongInput () {
		while (!sc.hasNextLong()) {
			System.out.println("Invalid input! Try again.");
			sc.next();
		}
		return sc.nextLong();
	}
	
}
