package com.revature;

import java.util.Scanner;

public class Driver {
	
	public static void main (String[] args) {
		
		Scanner sc = new Scanner(System.in);
		
		// easy adding machine
		/*
		
		int a = 0;
		int b = 0;
		int result = 0;
		
		System.out.println("Enter your first number");
		a = sc.nextInt();
		System.out.println("Enter your second number");
		b = sc.nextInt();
		result = a+b;
		
		System.out.println(result);
		*/
		
		// calculator with +, -, *, /

		int a;
		int b;
		char operator;
		int result;
		
		System.out.println("Enter your first number");
		a = sc.nextInt();
		System.out.println("Enter your operator symbol: +, -, *, /");
		operator = sc.next().charAt(0);
		System.out.println("Enter your second number");
		b = sc.nextInt();
		
		switch (operator) {
			case '+': 
				result = a+b;
				System.out.println("The result is " + result);
				break;
				
			case '-':
				result = a-b;
				System.out.println("The result is " + result);
				break;
				
			case '*':
				result = a*b;
				System.out.println("The result is " + result);
				break;
				
			case '/':
				result = a/b;
				System.out.println("The result is " + result);
				break;
				
			default:
				System.out.println("Invalid operator!");
		}
		
	}
}
