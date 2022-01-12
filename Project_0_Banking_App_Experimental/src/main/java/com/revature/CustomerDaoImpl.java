package com.revature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class CustomerDaoImpl implements CustomerDao {
	
	private static final String fileName = "C:\\Users\\slaza\\Desktop\\bankAppData\\users.txt";	// constant that leads to the textfile where we save our users
	private ArrayList<Customer> userList = new ArrayList<Customer>();		// userList object for runtime
	
	public CustomerDaoImpl () {
		//deserialize();
	}

	public ArrayList<Customer> getallUsers() {
		// TODO Auto-generated method stub
		return userList;
	}

	public Customer getUser(String username) {
		// TODO Auto-generated method stub
		for (int i=0; i < userList.size(); i++) {
			if (userList.get(i).getUsername() == username) {
				return userList.get(i);
			}
		}
		return null;
	}

	public void addUser(Customer user) {
		// TODO Auto-generated method stub
		userList.add(user);
		serialize();
	}
	
	private void serialize() {
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName, false);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(userList);
			out.close();
		} catch (IOException ioException) {
			// TODO
			ioException.printStackTrace();
		}
	}
	
	private void deserialize() {
		try {
			FileInputStream fileIn = new FileInputStream (fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
				
			try {
				userList = (ArrayList<Customer>) in.readObject();

			} catch (ClassNotFoundException classException) {
				// TODO
				classException.printStackTrace();
			}
			in.close();
		} catch (IOException ioException) {
			// TODO
			ioException.printStackTrace();
		} 
	}

}
