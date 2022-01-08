package com.revature;

public class Q16 {
	
	public static void stringArgSize (String[] args) {
		if (args.length > 0) {
			System.out.println("Q16: Your argument " + args[0] + " is " + args[0].length() + " characters long"); // only takes the first argument
		}
	}
}
