package com.revature.dao;

public class AccountDAO {
	
	private static AccountDAO acc;
	
	private AccountDAO() {}
	
	public static AccountDAO getAccount() {
		if (acc == null) 
			acc = new AccountDAO();
		return acc;
	}

}
