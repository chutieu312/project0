package com.revature.dao;

import java.util.List;

import com.revature.models.BankAccount;

public interface BankDAO {

	int addBankAccount(int customer_id);

	void addCustomerBankAccount(int customer_id, int bank_id);
		// add new record to join table

	List<BankAccount> findAll();

	List<BankAccount> findAllCustomerBankAccounts(int customer_id);

	List<BankAccount> findAllPendingAccounts();

	boolean setTrueActive(int id);

	boolean deleteBankAccount(int account_id, int customer_id);

	BankAccount findAccountById(int bank_id);

	boolean udateAccountBalance(int bank_id, double account_balance);
	
}	
