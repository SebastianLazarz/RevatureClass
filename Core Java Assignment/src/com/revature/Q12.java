package com.revature;

public class Q12 {
	
	public static int[] numberArray () {
		int[] array = new int[100];
		for (int i=0; i < 100; i++) {
			array[i] = i+1;
		}
		return array;
	}
	
	public static String giveEvenNumbers (int[] array) {
		String result = "";
		for (int number: array) {
			if (Q6.checkEven(number) == true) {
				result = result + number + " ";
			}
		}
		return result;
	}
	
}
