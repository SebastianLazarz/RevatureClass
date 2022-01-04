package com.revature;

public class Q4 {

	public int factorials (int n) {
		int result = 1;	// result variable, preinitialized with default result for 0! and 1!
		
		for (int i=2; i <= n; i++) { // go through integers starting at 2 until the final number n
			result = result * i;	// multiply previous result with current number;
		}
		
		return result;
	}
}
