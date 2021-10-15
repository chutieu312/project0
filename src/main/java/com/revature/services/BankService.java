package com.revature.services;

import java.util.List;

import com.revature.dao.BankDAO;
import com.revature.dao.BankDAOImpl;
import com.revature.models.BankAccount;

public class BankService {

	private BankDAO bankDAO = new BankDAOImpl();

	public void displayAccountInfo() {
		List<BankAccount> bankAccounts;
		bankAccounts = bankDAO.findAll();
		System.out.println("BANK ACCOUNT INFORMATIONS");
		System.out.println("-----------------------------------");
		for (BankAccount ba : bankAccounts)
			System.out.println(ba.toString());	
	}

	public boolean displayPendingAccount() {
		List<BankAccount> pendingAccounts;
		pendingAccounts = bankDAO.findAllPendingAccounts();
		if(pendingAccounts.isEmpty()) {
			System.out.println("There is NO pending account.");
			return false;
		}
		else {
			System.out.println("PENDING ACCOUNTS LIST");
			System.out.println("-----------------------------------");
			for (BankAccount ba : pendingAccounts)
				System.out.println(ba.toString());	
			return true;
		}
	}
	public void approveAccount(int id) {
		if(bankDAO.setTrueActive(id))
			System.out.println("Account have been approved.");
		else
			System.out.println("Can not approved account.");
		
	}
	public void deleteAccount(int id) {
		// TODO Auto-generated method stub
		
	}

	public void createBankAccount(int customer_id) {

		int bank_id;
		bank_id = bankDAO.addBankAccount(customer_id);
		// Now we add new record to a join table Customer-BankAccount
		bankDAO.addCustomerBankAccount(customer_id,bank_id);
		
	}

	public BankAccount getBankAccount(int bank_id) {
		
		return bankDAO.findAccountById(bank_id);
	}

	public boolean udateBalance(int bank_id, double account_balance) {
	
		return bankDAO.udateAccountBalance(bank_id,account_balance);
	}

	public void createBankAccount(int customer_id1, int customer_id2) {
		int bank_id;
		bank_id = bankDAO.addBankAccount(customer_id1);
		// Now we add new record to a join table Customer-BankAccount
		bankDAO.addCustomerBankAccount(customer_id1,bank_id);
		bankDAO.addCustomerBankAccount(customer_id2,bank_id);
	}


}
