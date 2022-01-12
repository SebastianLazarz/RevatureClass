package com.revature;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Driver {
	
	public static void main(String[] args) {
		
		try {
			// Class.forName ensures this class is loaded and avoids a ClassNotFoundException
			Class.forName("org.postgresql.Driver");
			
			String connectionString = "jdbc:postgresql://tyke.db.elephantsql.com:5432/zgakreqi",
					username = "zgakreqi",
					password = "rAzxZB5Aga1FeYpMRbEl_oQpKulwd_N1";
			
			// Connection object is used to manage the network connection with the database
			Connection connection = DriverManager.getConnection(connectionString, username, password);
			
			// Statement object can be used to run queries and receive ResultSets
			Statement statement = connection.createStatement();
			
			//ResultSets have a built-in iterator that starts one row ABOVE the first row
			//This allows us to call while on its next() method, which moves
			
			ResultSet result = statement.executeQuery("SELECT * FROM apartments");
			while (result.next()) {
				System.out.println("ID :" + result.getInt("apartment_id") + ", price: " + result.getDouble("price"));
			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
