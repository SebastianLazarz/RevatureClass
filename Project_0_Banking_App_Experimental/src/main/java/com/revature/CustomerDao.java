package com.revature;

import java.util.ArrayList;

public interface CustomerDao {
	public ArrayList<Customer> getallUsers();
	public Customer getUser(String username);
	public void addUser(Customer user);
}
