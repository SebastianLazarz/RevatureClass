package com.revature.datatypes;

public class Driver {
	
	public static void main(String[] args) {
		
		//Here I am instantiating a new DataTypes object. (instantiate = to make an instance of)
		DataTypes dt = new DataTypes(); // DataTypes dt is declaring a variable of type DataTypes.
		// The new keyword requests a block memory of DataTypes' size to store the object in, and returns the memory address. This address is stored in dt.
		// The final word, DataTypes(), says to run the constructor immediately on creation.
		
		System.out.println(dt.add(1, 2));
		
		int answer = dt.add(1, 2);
		System.out.println(answer);
		
		Dog felix = new Dog(), henry = new Dog(), patty = new Dog();
		
		felix.name = "Felix";
		felix.age = 2;
		felix.weight = 106.5;
		felix.breed = "German Shepard";
		
		dt.printDogName(felix);
	}
}
