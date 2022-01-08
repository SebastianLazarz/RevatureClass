package com.revature;

import java.util.ArrayList;

public interface UserDao {
	public ArrayList<User> getallUsers();
	public User getUser(String username);
	public void addUser(User user);
}
