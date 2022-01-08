package com.revature;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.HashSet;

public class Driver {
	
	private static final Scanner sc = new Scanner(System.in);
	
	public static void main(String[] args) {
		entryScreen();
	}
	
	
	// Prints introduction message to the console and presents the Login/Register menu
	// Returns 1 for Login or 2 for Register
	private static void entryScreen () {
		HashSet<Integer> validAnswers = new HashSet<Integer>();
		validAnswers.add(1);
		validAnswers.add(2);
		validAnswers.add(3);
		
		System.out.println("Welcome to easyBank! This user interface can be navigated by selecting an option by entering its corresponding number.");
		System.out.println("What do you want to do?");
		System.out.println("(1) Login");
		System.out.println("(2) Register");
		System.out.println("(3) Close Program");
		
		int input = getValidUserInput(validAnswers);
		switch (input) {
		case 1: // Login
			//todo
			break;
		case 2: // Register
			//todo
			break;
		case 3: // Close Program
			closeProgram();
			break;
		default:
			// Since we supposedly already validated our Input, this should never happen!
			System.out.println("Something is not right here!");
			break;
		}
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
	
	// Give a goodbye message to the user, cleanup and end the program
	private static void closeProgram () {
		System.out.println("Have a nice day!");
		sc.close();
		System.exit(0);
	}
	
	// Register screen to gain user input to create a new User account
	private static void register () {
		System.out.println("Registration menu:");
		System.out.println("Enter a username.");
		String username = sc.next();
		System.out.println("Now enter a password.");
		String password = sc.next();
	}
	
}
