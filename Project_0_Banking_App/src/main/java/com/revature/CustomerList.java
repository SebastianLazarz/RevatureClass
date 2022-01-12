package com.revature;

import java.util.ArrayList;
import java.util.List;

public class CustomerList {
	
	private List<Customer> customerList;
	
	public CustomerList () {
		customerList = new ArrayList<Customer>();
	}

	public void add(Customer customer) {
		customerList.add(customer);
	}
	
	public List<Customer> getCustomerList () {
		return customerList;
	}
	
	public Customer getCustomerByName (String username) {
		for (int i=0; i < customerList.size(); i++) {
			if (username.equals(customerList.get(i).getUsername())) {
				return customerList.get(i);
			}
		}
		return null;
	}
}
