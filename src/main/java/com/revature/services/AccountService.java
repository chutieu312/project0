package com.revature.services;

public class AccountService {
	
	private static AccountService acc;
	
	private AccountService() {}
	
	public static AccountService getAccount() {
		if (acc == null) 
			acc = new AccountService();
		return acc;
	}
}
