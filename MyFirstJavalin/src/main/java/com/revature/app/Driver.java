package com.revature.app;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class Driver {
	
	
	public static void main (String[] args) {
		
		// This reserves the port and sets up Javalin
		Javalin app = Javalin.create().start(7070);
		
		// In order to access the request and build a response, we use the ctx static variable
		
		// Every URL endpoint and HTTP verb requires a handler
		// Handlers can be defined in one line like below
		app.get("/hello", ctx -> {
			// The syntax above is called a "lambda", an anonymous method (ie no name) passed as a parameter
			// Code to execute when someone accesses site.com/users
			// ctx.result returns a String in the response body
			ctx.result("Hello World!");
		});
		// Access the above behavior at localhost:7070/hello
		
		// If we want to seperate the behavior from the path, we can use Handler variables to store our lambdas
		app.get("/user/{id}", getUserById);
		
		// The {id} syntax represents a path parameter called it. It can be accessed using ctx
	}
	
	public static Handler getUserById = ctx -> {
		// Can get the path parameter using ctx like so 
		ctx.result(ctx.pathParam("id"));
	};
}
