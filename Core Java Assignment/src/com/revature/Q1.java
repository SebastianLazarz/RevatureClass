package com.revature;

public class Q1 {
	
	public void bubbleSort(int[] array) {
		
		boolean done = false;	// condition for our loop
		int temp = 0;			// temporary storage variable to perform swapping procedures on the array
		
		while (done == false) {	// loop until done, when the array is sorted
			done = true;		// assuming nothing else happens anymore, we are done
			
			for (int i= 0; i < array.length-1; i++) {	// go through every number pair of the array
				if (array[i] > array[i+1]) {			// if the first number is bigger than the second, we need to swap
					temp = array[i];
					array[i] = array[i+1];
					array[i+1] = temp;
					done = false;						// since we are still swapping numbers, we are not done yet
				}
			}
		}
		
		
		
	}
}
