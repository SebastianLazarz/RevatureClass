package com.revature;

public class Q13 {
	
	public static void printNumbers (int iterations) {
		
		
		String currentLine = "0"; // starting value for first iteration, which is also the minimum amount of iterations for our method
		System.out.println(currentLine);
		
		for (int i=2; i <= iterations; i++) {
			if (Q6.checkEven(i) == true) {
				currentLine = "1 " + currentLine;
				System.out.println(currentLine);
			} else {
				currentLine = "0 " + currentLine;
				System.out.println(currentLine);
			}
		}
	}
}
