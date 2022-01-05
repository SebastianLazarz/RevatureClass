package com.revature;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.revature.Q7.Employee;

public class Driver {
	
	public static void main(String[] args) {
		Q1 q1 = new Q1();
		Q2 q2 = new Q2();
		Q4 q4 = new Q4();
		Q5 q5 = new Q5();
		Q7 q7 = new Q7();
		
		// Q1
		int[] bubbleArray = {1,0,5,6,3,2,3,7,9,8,4};
		q1.bubbleSort(bubbleArray);
		System.out.println("Q1: " + Arrays.toString(bubbleArray));
		
		// Q2
		int[] fibonacciArray = new int[25];
		fibonacciArray = q2.fibonacci(fibonacciArray.length);
		System.out.println("Q2: " + Arrays.toString(fibonacciArray));
		
		// Q3
		String reversedString = Q3.reverse("Test String");
		System.out.println("Q3: " + reversedString);
		
		// Q4
		int factorial = 10; // example number
		int factorialResult = q4.factorials(factorial);
		System.out.println("Q4: " + factorial + "! = " + factorialResult);
		
		// Q5
		String str = "Test String"; // example String
		int idx = 4; // example index
		String subString = q5.subString(str, idx);
		System.out.println("Q5: " + subString);
		
		// Q6
		int checkEvenInteger = 22; // example integer
		boolean isEven = Q6.checkEven(checkEvenInteger);
		System.out.println("Q6: " + checkEvenInteger + " is even: " + isEven);
		
		// Q7 unfinished
		Employee empA = q7.createEmployee("Tom Jones", "IT", 36); // example data
		Employee empB = q7.createEmployee("John Andrews", "Production", 44); 
		
		
		// Q8
		ArrayList<String> listOfStrings = new ArrayList<String>();
		listOfStrings.add("karan");
		listOfStrings.add("madam");
		listOfStrings.add("tom");
		listOfStrings.add("civic");
		listOfStrings.add("radar");
		listOfStrings.add("jimmy");
		listOfStrings.add("kayak");
		listOfStrings.add("john");
		listOfStrings.add("refer");
		listOfStrings.add("billy");
		listOfStrings.add("did");
		
		ArrayList<String> palindromesList = Q8.findPalindromes(listOfStrings);
		String palindromes = "";
		for (int i=0; i < palindromesList.size(); i++) {
			palindromes = palindromes + palindromesList.get(i) + " ";
		}
		System.out.println("Q8: " + palindromes);
		
		// Q9
		ArrayList<Integer> numberList = Q9.createNumberList(1, 100);
		ArrayList<Integer> primeList = Q9.givePrimeNumbers(numberList);
		String primeString = "";
		
		for (int i=0; i < primeList.size(); i++) {
			primeString = primeString + primeList.get(i) + " ";
		}
		System.out.println("Q9: The prime numbers between " + numberList.get(0) + " and " + numberList.get(numberList.size()-1) + " are: " + primeString);
		
		// Q10
		double a = 2.4; // example values
		double b = 52.3;
		double minimum = Q10.getMinimum(a, b);
		System.out.println("Q10: " + minimum);
		
	}
	
}
