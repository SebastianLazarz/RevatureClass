package com.revature;

import java.util.ArrayList;

public class Q19 {
	
	private ArrayList<Integer> list;
	
	public Q19 () {
		list = new ArrayList<Integer>();
		for (int i=1; i <= 10; i++) {
			list.add(i);
		}
	}
	
	public void displayList() {
		System.out.println("Displaying ArrayList: " + allNumbers());
		System.out.println("Displaying sum off all even numbers: " + addEvenNumbers());
		System.out.println("Displaying sum off all odd numbers: " + addOddNumbers());
		System.out.println("Displaying ArrayList without prime numbers: " + noPrimes());
	}

	public ArrayList<Integer> getList() {
		return list;
	}
	
	public String allNumbers () {
		String result = "";
		for (int i=0; i < list.size(); i++) {
			result = result + list.get(i) + " ";
		}
		return result;
	}
	
	public int addEvenNumbers () {
		int result = 0;
		
		for (int i=0; i < list.size(); i++) {
			if (Q6.checkEven(list.get(i)) == true) {
				result += list.get(i);
			}
		}
		
		return result;
	}
	
	public int addOddNumbers () {
		int result = 0;
		
		for (int i=0; i < list.size(); i++) {
			if (Q6.checkEven(list.get(i)) == false) {
				result += list.get(i);
			}
		}
		
		return result;
	}
	
	public String noPrimes () {
		String result = "";
		
		for (int i=0; i < list.size(); i++) {
			if (Q9.checkPrime(list.get(i)) == false) {
				result = result + list.get(i) + " ";
			}
		}		
		return result;
	}
	
	
}
