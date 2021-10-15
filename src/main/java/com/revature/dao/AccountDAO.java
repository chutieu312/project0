package com.revature.dao;

import java.util.List;

import com.revature.models.Account;

public interface AccountDAO {
	
	public Account findAccount(String username, String password );

	public int addAccount(String firstName, String lastName, String username, String password, String type);

	public List<Account> findAllCustomers();

	public boolean deleteCustomerAccount(int customer_id);
			
}
