package com.revature.console;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

import com.revature.controller.AdminController;
import com.revature.controller.BankAccountController;
import com.revature.controller.CustomerController;
import com.revature.dao.ConnectionManager;

import io.javalin.Javalin;

public class Driver {
	
	public static void main (String[] args) {
		try {
			// initializes our Javalin and console interfaces
			Javalin app = Javalin.create().start(7070);
			Connection connection = ConnectionManager.getConnection();
			CustomerController customerController = new CustomerController(app, connection);
			BankAccountController accountController = new BankAccountController(app, connection);
			AdminController adminController = new AdminController(app, connection);
			
			
			Scanner sc = new Scanner(System.in);
			Menu menu = new Menu(sc);
			menu.entryScreen();
		} finally {
			// When the program is stopped, this will trigger and close the connection
			// You have to use the stop button in your IDE. Similar to finalize in ConnectionManager
			try {
				ConnectionManager.getConnection().close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
