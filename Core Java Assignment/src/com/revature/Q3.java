package com.revature;

public class Q3 {
	
	public static String reverse(String originalString) {
		String reversedString = "";	// Variable for our result
		char[] stringAsChars = originalString.toCharArray(); // transfering the original string into an array of characters so we can go through them one by one
		
		for (int i=stringAsChars.length - 1; i >= 0; i--) { // go through each character of the array in reverse order
			reversedString = reversedString + stringAsChars[i]; // add the current character to our reversed String
		}
		
		return reversedString;
	}
}
