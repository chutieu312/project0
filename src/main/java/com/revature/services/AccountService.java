package com.revature.services;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.models.Account;

public class AccountService {
	
	private AccountDAO accountDAO = new AccountDAOImpl();
	
	private static AccountService accService;
	private AccountService() {}
	public static AccountService getAccount() {
		if (accService == null) 
			accService = new AccountService();
		return accService;
	}

	public Account login(String username, String password) {
		
		return accountDAO.findAccount(username, password);
	}
	
	public void deposit(int account_id, double amount) {
		
		
	}
}
