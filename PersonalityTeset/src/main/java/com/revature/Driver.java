package com.revature;

import java.util.Scanner;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Driver {
	private static final Logger logger = LogManager.getLogger(Driver.class);
	
	public static void main(String[] args) {
		
		Scanner sc = new Scanner(System.in);
		int[] answers = new int[5];
		
		
		try {		
		
		introduction();
		questionA();
		answers[0] = sc.nextInt();
		questionB();
		answers[1] = sc.nextInt();
		questionC();
		answers[2] = sc.nextInt();
		questionD();
		answers[3] = sc.nextInt();
		questionE();
		answers[4] = sc.nextInt();
		sc.close();
		
		getResult(getScore(answers));
		} catch(Exception e) {
			logger.error("An exception got caught!" + e.getMessage());
		}
				
	}
	
	public static void introduction() {
		System.out.println("Welcome to the most exciting personality test ever: Which Hogwarts house are you?");
		System.out.println("Each question will have four options to choose from. Select it by typing the number next to the option and pressing Enter. You can only select one option!");
	}
	
	public static void questionA() {
		System.out.println("Question 1: What is your favourite color combination of the following options?");
		System.out.println("1: red and gold");		// Gryffindor
		System.out.println("2: green and silver");	// Slitherine
		System.out.println("3: yellow and black");	// Hufflepuff
		System.out.println("4: blue and bronze"); 	// Ravenclaw
	}
	
	public static void questionB() {
		System.out.println("Question 2: Which of these traits best describe you?");
		System.out.println("1: intelligent");	// Ravenclaw
		System.out.println("2: ambitious");		// Slitherine
		System.out.println("3: courageous");	// Gryffindor
		System.out.println("4: humble");		// Hufflepuff
	}
	
	public static void questionC() {
		System.out.println("Question 3: What is your favourite school subject?");
		System.out.println("1: sports");		// Gryffindor
		System.out.println("2: mathematics");	// Ravenclaw
		System.out.println("3: arts");			// Hufflepuff
		System.out.println("4: politics");		// Slitherine
	}
	
	public static void questionD() {
		System.out.println("Question 4: What is most important in life?");
		System.out.println("1: A good heart");	// Hufflepuff
		System.out.println("2: Power");			// Slitherine
		System.out.println("3: Success");		// Ravenclaw
		System.out.println("4: A bit of each");	// Gryffindor
	}
	
	public static void questionE() {
		System.out.println("Question 5: What Hogwarts houses name do you like the most?");
		System.out.println("1: Hufflepuff");	// Hufflepuff
		System.out.println("2: Slitherine");	// Slitherine
		System.out.println("3: Ravenclaw");		// Ravenclaw
		System.out.println("4: Gryffindor");	// Gryffindor
	}
	
	public static int[] getScore(int[] answers) {
		int[] score = { 0, 0, 0, 0 }; // Gryffindor, Slitherine, Hufflepuff, Ravenclaw 
		
		// questionA
		switch (answers[0]) {
		case 1:
			score[0]++;
			break;
		case 2:
			score[1]++;
			break;
		case 3:
			score[2]++;
			break;
		case 4:
			score[3]++;
			break;
		}
		// questionB
		switch (answers[1]) {
		case 1:
			score[3]++;
			break;
		case 2:
			score[1]++;
			break;
		case 3:
			score[0]++;
			break;
		case 4:
			score[2]++;
			break;
		}
		// questionC
		switch (answers[2]) {
		case 1:
			score[0]++;
			break;
		case 2:
			score[3]++;
			break;
		case 3:
			score[2]++;
			break;
		case 4:
			score[1]++;
			break;
		}
		//questionD
		switch(answers[3]) {
		case 1:
			score[2]++;
			break;
		case 2:
			score[1]++;
			break;
		case 3:
			score[3]++;
			break;
		case 4:
			score[0]++;
			break;
		}
		//questionE
		switch(answers[4]) {
		case 1:
			score[2]++;
			break;
		case 2:
			score[1]++;
			break;
		case 3:
			score[3]++;
			break;
		case 4:
			score[0]++;
			break;
		}
		
		return score;
	}
	
	public static void getResult(int[] score) {
		int highestIndex = 0;
		int currentMax = 0;
		
		for (int i=0; i < score.length; i++) {
			if (score[i] > currentMax) {
				highestIndex = i;
				currentMax = score[i];
			}
		}
		
		switch (highestIndex) {
		case 0:
			System.out.println("You belong to Gryffindor!");
			break;
		case 1:
			System.out.println("You belong to Slitherine!");
			break;
		case 2:
			System.out.println("You belong to Hufflepuff!");
			break;
		case 3:
			System.out.println("You belong to Ravenclaw!");
			break;
		}
		
	}
	
	public static boolean checkInput (int i) {
		if (i > 0 && i <= 4) {
			return true;
		} else {
			return false;
		}
	}
}
