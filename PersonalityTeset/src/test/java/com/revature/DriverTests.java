package com.revature;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DriverTests {
	
	@Test
	public void scoreTest() {
		int[] answers = { 1, 2, 1, 2, 2 }; 	// answers correspond to Gryffindor, Slitherine, Gryffindor, Slitherine, Slitherine;
										   	// that means the score should be { 2, 3, 0, 0 } as score gives out an array with a format corresponding to the order
										   	// Gryffindor, Slitherine, Hufflepuff, Ravenclaw
		int[] expectedResult = {2, 3, 0, 0};
		int[] driverScore = Driver.getScore(answers);
		
		assertEquals(expectedResult[0], driverScore[0]);
		assertEquals(expectedResult[1], driverScore[1]);
		assertEquals(expectedResult[2], driverScore[2]);
		assertEquals(expectedResult[3], driverScore[3]);
	}
}
