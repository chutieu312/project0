package com.revature.controller;

import java.util.Scanner;

import com.revature.models.Account;
import com.revature.services.AccountService;


public class AccountController {

	
	private AccountService accountService = AccountService.getAccount();
	
	private static AccountController accCtr;
	private AccountController() {}
	public static AccountController getAccountCtr() {
		if (accCtr == null) 
			accCtr = new AccountController();
		return accCtr;
	}
	
	public Account login(String username, String password) {
		
		return accountService.login(username,password);
	
	}
	public void deposit(int account_id, double amount) {
		
		accountService.deposit(account_id,amount);
	}
	
}

