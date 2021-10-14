package com.revature.dao;

import java.util.List;

import com.revature.models.BankAccount;

public interface BankDAO {
	
	List<BankAccount> findAllBankAccounts(int customer_id);
	
}
