package com.revature;

import java.util.ArrayList;

public class Q8 {
	
	public static ArrayList<String> findPalindromes (ArrayList<String> list) {
		ArrayList<String> palindromes = new ArrayList<String>();
		
		for (int i=0; i < list.size(); i++) {
			String currentString = list.get(i);
			String reversedString = Q3.reverse(currentString);
			
			if (currentString.equals(reversedString) == true) {
				palindromes.add(currentString);
			}
		}
		
		return palindromes;
	}
	
}
