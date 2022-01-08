package com.revature;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import com.revature.Q7.Employee;
import com.revature.additional.Q11;

public class Driver {
	
	public static void main(String[] args) {
		Q1 q1 = new Q1();
		Q4 q4 = new Q4();
		Q5 q5 = new Q5();
		Q7 q7 = new Q7();
		Q15 q15 = new Q15();
		
		// Q1
		int[] bubbleArray = {1,0,5,6,3,2,3,7,9,8,4};
		q1.bubbleSort(bubbleArray);
		System.out.println("Q1: " + Arrays.toString(bubbleArray));
		
		// Q2
		int[] fibonacciArray = new int[25];
		fibonacciArray = Q2.fibonacci(fibonacciArray.length);
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
		List<Employee> unsortedList = new ArrayList<>();
		unsortedList.add(empA);
		unsortedList.add(empB);
		
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
		
		// Q11
		Q11 q11 = new Q11(2.4f, 3.5f);
		System.out.println("Q11: Value 1 = " + q11.getValue1() + "; Value 2 = " + q11.getValue2());
		
		// Q12
		int[] numberArray = Q12.numberArray();
		String evenNumbers = Q12.giveEvenNumbers(numberArray);
		System.out.println("Q12: " + evenNumbers);
		
		// Q13 not printing the correct triangle
		System.out.println("Q13:");
		Q13.printNumbers(7);
		
		// Q14
		int caseQ14 = 3; // case 1, 2 or 3
		String[] q14Result = Q14.doSomething(caseQ14);
		System.out.println("Q14: case " + caseQ14);
		for (int i=0; i < q14Result.length; i++) {
			System.out.println(q14Result[i]);
		}
		
		// Q15
		double testA = 2.5;
		double testB = 2;
		System.out.println("Q15:");
		System.out.println(testA + " + " + testB + " = " + q15.addition(testA, testB));
		System.out.println(testA + " - " + testB + " = " + q15.subtraction(testA, testB));
		System.out.println(testA + " * " + testB + " = " + q15.multiplication(testA, testB));
		System.out.println(testA + " / " + testB + " = " + q15.division(testA, testB));
		
		// Q16
		Q16.stringArgSize(args);
		
		/*
		// Q17
		Scanner sc = new Scanner(System.in);
		System.out.println("Q17: Please enter your principal: ");
		double principal = sc.nextDouble();
		System.out.println("Please enter your rate of interest: ");
		double interest = sc.nextDouble();
		System.out.println("Please enter the number of years: ");
		int years = sc.nextInt();
		double interestResult = Q17.calculateInterest(principal, interest, years);
		System.out.println("The result is: " + interestResult);
		sc.close();
		*/
		
		// Q18
		Q18 q18 = new Q18();
		String str18 = "132451"; 
		System.out.println("Q18: String " + str18 + " contains uppercases: " + q18.checkUppercase(str18));
		System.out.println("Converted to Uppercase: " + q18.toUppercase(str18));
		System.out.println("Converted to integer + 10: " + q18.toInteger(str18));
		
		// Q19
		Q19 q19 = new Q19();
		System.out.println("Q19: ");
		q19.displayList();
		
		// Q20
		File file = new File("C:\\Users\\slaza\\Desktop\\data.txt");
		Q20 q20 = new Q20(file);
		System.out.println("Q20: ");
		try {
			q20.print();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
