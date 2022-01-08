package com.revature;

public class Q2 {
	
	public static int[] fibonacci(int length) {
		int[] result = new int[length];
		
		for (int i=0; i < length; i++) {
			switch(i) {
			case 0: 
				result[i] = 0;
				break;
			case 1:
				result[i] = 1;
				break;
			default:
				result[i] = result[i-1] + result[i-2];
			}
		}
		
		return result;
	}
}
