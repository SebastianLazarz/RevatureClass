package com.revature;

import java.util.Arrays;

public class Driver {
	
	public static void main(String[] args) {
		Q1 q1 = new Q1();
		Q2 q2 = new Q2();
		Q3 q3 = new Q3();
		Q4 q4 = new Q4();
		Q5 q5 = new Q5();
		
		// Q1
		int[] bubbleArray = {1,0,5,6,3,2,3,7,9,8,4};
		q1.bubbleSort(bubbleArray);
		System.out.println(Arrays.toString(bubbleArray));
		
		// Q2
		int[] fibonacciArray = new int[25];
		fibonacciArray = q2.fibonacci(fibonacciArray.length);
		System.out.println(Arrays.toString(fibonacciArray));
		
		// Q3
		String reversedString = q3.reverse("Test String");
		System.out.println(reversedString);
		
		// Q4
		int factorial = 10; // example number
		int factorialResult = q4.factorials(factorial);
		System.out.println(factorial + "! = " + factorialResult);
		
		// Q5
		String str = "Test String"; // example String
		int idx = 4; // example index
		String subString = q5.subString(str, idx);
		System.out.println(subString);
	}
	
}
