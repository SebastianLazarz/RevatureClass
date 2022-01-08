package com.revature;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class UserDaoImpl implements UserDao {
	
	private static final String fileName = "/serialize/users.txt";	// constant that leads to the textfile where we save our Users
	private ArrayList<User> userList = new ArrayList<User>();		// userList object for runtime
	
	public UserDaoImpl () {
		try {
			FileInputStream fileIn = new FileInputStream (fileName);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			
			boolean condition = true;
			while (condition == true) {
				User user = null;
				
				try {
					user = (User) in.readObject();
				} catch (ClassNotFoundException classException) {
					// TODO
					classException.printStackTrace();
				}
				
				if (user != null) {
					userList.add(user);
				} else {
					condition = false;
				}
			}
			in.close();
		} catch (IOException ioException) {
			// TODO
			ioException.printStackTrace();
		} 

	}

	public ArrayList<User> getallUsers() {
		// TODO Auto-generated method stub
		return userList;
	}

	public User getUser(String username) {
		// TODO Auto-generated method stub
		for (int i=0; i < userList.size(); i++) {
			if (userList.get(i).getUsername() == username) {
				return userList.get(i);
			}
		}
		return null;
	}

	public void addUser(User user) {
		// TODO Auto-generated method stub
		try {
			FileOutputStream fileOut = new FileOutputStream(fileName);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			
			out.writeObject(user);
			out.close();
			userList.add(user);
		} catch (IOException ioException) {
			// TODO
			ioException.printStackTrace();
		}
	}

}
