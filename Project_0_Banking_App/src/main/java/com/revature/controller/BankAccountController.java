package com.revature.controller;

import java.sql.Connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.BankAccountDAO;
import com.revature.models.BankAccount;

import io.javalin.Javalin;
import io.javalin.http.Handler;

public class BankAccountController {
	
	Javalin app;
	Connection connection;
	BankAccountDAO bankAccountDao;
	private static final Logger logger = LogManager.getLogger(BankAccountController.class);
	
	public BankAccountController(Javalin app, Connection connection) {
		this.app = app;
		this.connection = connection;
		this.bankAccountDao = new BankAccountDAO();
		
		app.get("/accounts/{accountNumber}", getBankAccount);
		app.post("/accounts/", createBankAccount);
		app.put("/accounts/", updateBankAccount);
		app.delete("/accounts/{accountNumber}", deleteBankAccount);
		
	}
	
	// gets a bank account using its account number
	public Handler getBankAccount = ctx -> {
		try {
			long accountNumber = Long.parseLong(ctx.pathParam("accountNumber"));
			BankAccount account = bankAccountDao.getBankAccount(accountNumber);
			ctx.json(account);
			ctx.status(200);
		} catch (NumberFormatException e) {;
			ctx.status(400);
		}
	};
	
	// creates a fresh bank account with default values
	public Handler createBankAccount = ctx -> {
		BankAccount account = bankAccountDao.createBankAccount();
		logger.info("Bank Account " + account.accountNumber + " was created");
		ctx.json(account);
		ctx.status(201);
	};
	
	// updates a bank account
	public Handler updateBankAccount = ctx -> {
		BankAccount account = ctx.bodyAsClass(BankAccount.class);
		if (bankAccountDao.getBankAccount(account.accountNumber) != null) {
			bankAccountDao.updateBankAccount(account);
			logger.info("Account " + account.accountNumber + " was updated");
			ctx.status(204);
		} else {
			ctx.status(400);
		}
	};
	
	// deletes a bank account and all related customers
	public Handler deleteBankAccount = ctx -> {
		try {
			long accountNumber = Long.parseLong(ctx.pathParam("accountNumber"));
			BankAccount account = bankAccountDao.getBankAccount(accountNumber);
			if (account == null) {
				ctx.status(400);
			} else {
				bankAccountDao.deleteBankAccount(account, logger);
				logger.info("Deleted Bank Account " + account.accountNumber);
				ctx.status(204);
			}
		} catch (NumberFormatException e) {
			ctx.status(400);
		}
		
	};
}



