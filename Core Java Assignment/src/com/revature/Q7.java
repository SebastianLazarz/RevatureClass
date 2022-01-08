package com.revature;

public class Q7 {
	class Employee {
		String name;
		String department;
		int age;
		
		public Employee (String name, String department, int age) {
			this.name = name;
			this.department = department;
			this.age = age;
		}
	}
	
	public Employee createEmployee(String name, String department, int age) {
		return new Employee(name, department, age);
	}
	
}

