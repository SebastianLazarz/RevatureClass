package com.revature;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Q14 {
	public static String[] doSomething (int caseValue) {
		
		switch (caseValue) {
		case 1: 
			double randomNumber = Math.random();
			String[] resultA = { Double.toString(Math.sqrt(randomNumber)) };
			return resultA;
		case 2:
			DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss"); 
			LocalDateTime now = LocalDateTime.now();
			String[] resultB = { dtf.format(now) };
			return resultB;
		case 3:
			String example = "I am learning Core Java";
			String[] resultC = example.split(" ");
			return resultC;
		default:
			String[] resultD = { "invalid case" };
			return resultD;
		}	
	}
}
