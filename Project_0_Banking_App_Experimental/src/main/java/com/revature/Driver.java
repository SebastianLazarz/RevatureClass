package com.revature;


import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;

public class Driver {
	
	private static final Scanner sc = new Scanner(System.in);
	private static final Logger logger = LogManager.getLogger(Driver.class);
	
	public static void main(String[] args) {
		entryScreen();
	}
	
	public static void entryScreen () {
		HashSet<Integer> validAnswers = new HashSet<Integer>();
		validAnswers.add(1);
		validAnswers.add(2);
		validAnswers.add(3);
		
		System.out.println("Welcome to easyBank! This user interface can be navigated by selecting an option by entering its corresponding number.");
		System.out.println("What do you want to do?");
		System.out.println("(1) Login");
		System.out.println("(2) Register");
		
		int input = getValidUserInput(validAnswers);
		switch (input) {
		case 1: // Login
			loginScreen();
			break;
		case 2: // Register
			registerScreen();
			break;
		default:
			// Since we supposedly already validated our Input, this should never happen!
			System.out.println("Something is not right here!");
			break;
		}
	}
	
	// Register screen
	private static void registerScreen() {
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		
		System.out.println("Enter your Username");
		String username = sc.next();
		
		// check if Username already exists
		if (customerDao.getUser(username) != null) {
			System.out.println("This username already exists! Please try again.");
			registerScreen(); 
		} else {
			System.out.println("Enter your password");
			String password = sc.next();
			Customer newUser = new Customer(username, password);
			customerDao.addUser(newUser);
			logger.info("New user " + newUser.getUsername() + "was created");
			System.out.println("Your customer account was successfully created!");
			entryScreen();
		}
	}
	
	// Login screen
	private static void loginScreen () {
		CustomerDaoImpl customerDao = new CustomerDaoImpl();
		Customer user = null;
		
		System.out.println("Enter your Username.");
		String username = sc.next();
		System.out.println("Enter your password.");
		String password = sc.next();
		
		user = customerDao.getUser(username);
		
		if (user == null) {
			System.out.println("We found no user with this username and password combination. Please try again.");
			loginScreen();
			return;
		} else if (!user.getPassword().equals(password)) {
			System.out.println("We found no user with this username and password combination. Please try again.");
			loginScreen();
			return;
		} else {
			if (user.isBankAdmin()) {
				bankAdminMenu((BankAdmin) user);
			} else if (user.isEmployee()) {
				employeeMenu((Employee) user);
			} else {
				customerMenu(user);
			}
		}
	}
	
	// Go to the main menu of a normal customer
	private static void customerMenu (Customer customer) {
		HashSet<Integer> validAnswers = new HashSet<Integer>();
		AccountDaoImpl accountDao = new AccountDaoImpl();
		int input = 0;
		
		// If the customer doesn't have a bank account yet, he can apply for an own or a joint account
		if (customer.getBankAccount() == null) {
			
			System.out.println("You don't have a bank account yet. Would you like to apply for an own or a joint account?");
			System.out.println("(1) Own account");
			System.out.println("(2) Joint Account");
			
			validAnswers.add(1);
			validAnswers.add(2);
			input = getValidUserInput(validAnswers);
			
			switch (input) {
			case 1: // Apply for own account
				customer.applyForAccount();
				System.out.println("You applied for an account. A bank employee will have to review your application before you can use it.");
				customerMenu(customer);
				return;
			case 2: // Apply for joint account
				System.out.println("In order to gain access to a joint account, please enter first the bank account number of the account you want to have joined ownership with");
				long accountNumber = 0;
				
				// first the user needs to put in an existing account number of another customer
				do {
					while (sc.hasNextLong() == false) {
						System.out.println("Invalid input! Please enter a valid number.");
						sc.next();
					}
					accountNumber = sc.nextLong();
					Account account = accountDao.getAccount(accountNumber);
					if (account == null) {
						System.out.println("This account number does not exist within our bank. Please try again.");
						accountNumber = 0;
						sc.next();
					}
				} while (accountNumber == 0);
				// Placeholder, for now just assuming you can join account without review
				System.out.println("You successfully gained access to your joint account!");
				customer.applyForJointAccount(accountDao.getAccount(accountNumber));
				customerMenu(customer);
				return;
			default:
				// Since we supposedly already validated our Input, this should never happen!
				System.out.println("Something is not right here!");
				return;
			}
			// if the customer does have a bank account but it is not approved (yet), he will get an explanation message
		} else if (customer.getBankAccount().isApproved() == false) {
			System.out.println("Your bank account is not approved yet.");
			entryScreen();
			return;
			// if the customer does have an approved bank account, he will get the real main menu screen
		} else {
			
			System.out.println("Welcome. Your account balance currently is " + customer.getBankAccount().getMoneyAmount());
			System.out.println("What would you like to do?");
			System.out.println("(1) Withdraw");
			System.out.println("(2) Deposit");
			System.out.println("(3) Transfer");
			
			validAnswers.add(1);
			validAnswers.add(2);
			validAnswers.add(3);
			input = getValidUserInput(validAnswers);
			
			// switch input
			switch (input) {
			case 1: // Withdraw
				// TODO
				return;
			case 2: // Deposit
				// TODO
			case 3: // Transfer
				//TODO 
			default:
				// Since we supposedly already validated our Input, this should never happen!
				System.out.println("Something is not right here!");
				return;
			}
		}
	}
	
	private static void employeeMenu (Employee employee) {
		// TODO
	}
	
	private static void bankAdminMenu (BankAdmin bankAdmin) {
		// TODO
	}
	
	// Makes sure that the user eventually inputs a valid integer value
	private static int getValidUserInput (HashSet<Integer> validAnswers) {
		int result = 0;
		boolean validAnswer = false;
		
		do {
			while (sc.hasNextInt() == false) {		// Checks if the User put in an integer and not something else. If not, ask for new input.
				System.out.println("Invalid input! Please enter a valid number.");
				sc.next();
			}
			// checks if the integer we now have is actually part of the valid answer set; if not, ask for a new input again
			result = sc.nextInt();
			validAnswer = validateUserInput(result, validAnswers);
			if (validAnswer == false) {
				System.out.println("Invalid input! Please enter a valid number.");
			}
		} while (validAnswer == false);
		
		return result;
	}
	
	// Makes sure that the input is within the range of valid answers
	private static boolean validateUserInput (int input, HashSet<Integer> validAnswers) {
		if (validAnswers.contains(input)) {
			return true;
		} else {
			return false;
		}
	}
}
