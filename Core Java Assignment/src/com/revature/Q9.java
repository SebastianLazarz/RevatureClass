package com.revature;

import java.util.ArrayList;

public class Q9 {
	
	public static ArrayList<Integer> createNumberList (int start, int end) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = start; i <= end; i++ ) {
			list.add(i);
		}
		return list;
	}
	
	public static ArrayList<Integer> givePrimeNumbers (ArrayList<Integer> list) {
		ArrayList<Integer> primeList = new ArrayList<Integer>();
		
		for (int i=0; i < list.size(); i++) {
			int currentNumber = list.get(i);
			
			if (checkPrime(currentNumber) == true) {
				primeList.add(currentNumber);
			}
			
		}
		
		return primeList;
	}
	
	public static boolean checkPrime (int n) {
		boolean isPrime = true;
		
		if (n <= 1) {
			isPrime = false;
		}
		
		for (int i=2; i < n; i++) {
			if (n % i == 0) {
				isPrime = false;
			}
		}
		
		return isPrime;
	}
	
}
