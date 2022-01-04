package com.revature;

public class Q5 {

	public String subString (String str, int idx) {
		String subString = "";	// our result String
		char[] strToChars = str.toCharArray(); // transfer original String to char array so we can easily go through each letter one by one
		
		for (int i=0; i < idx; i++) {	// go through each character up to the given idx
			subString = subString + strToChars[i];	// add current character to our result String
		}
		
		return subString;
	}
}
