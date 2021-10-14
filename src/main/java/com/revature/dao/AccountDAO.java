package com.revature.dao;

import com.revature.models.Account;

public interface AccountDAO {
	
	public Account findAccount(String username, String password );
		
	
}
