package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.AccountDAO;
import com.revature.dao.AccountDAOImpl;
import com.revature.dao.BankDAO;
//import com.revature.dao.BankDAOImpl;
import com.revature.models.Account;
import com.revature.models.BankAccount;
//import com.revature.models.BankAccount;

public class AccountService {
	
	private AccountDAO accountDAO = new AccountDAOImpl();
	private BankService bankService = new BankService();
	//private BankDAO bankDAO = new BankDAOImpl();
	
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
	
	public void deposit(int bank_id, double amount) {
		if (amount < 0) 
			System.out.println("Please enter a positive amount.");
		else {
			BankAccount ba =  bankService.getBankAccount(bank_id);
			if(!ba.isActive())
				System.out.println("Account is not active!");
			else {
				ba.setAccount_balance(ba.getAccount_balance()+amount);
				if(bankService.udateBalance(bank_id,ba.getAccount_balance()))
					System.out.println("Deposit successfully.");
				else
					System.out.println("Can not deposit. Please try again.");
			}
				
		}	
	}
	public void withdraw(int bank_id, double amount) {
		if (amount < 0) 
			System.out.println("Please enter a positive amount.");
		else {
			BankAccount ba =  bankService.getBankAccount(bank_id);
			if(!ba.isActive())
				System.out.println("Account is not active!");
			else if (ba.getAccount_balance()<amount) {
				System.out.println("The amount withdraw is larger than account balance.");
			}
			else {
				ba.setAccount_balance(ba.getAccount_balance()-amount);
				if(bankService.udateBalance(bank_id,ba.getAccount_balance()))
					System.out.println("withdraw successfully.");
				else
					System.out.println("Can not withdraw. Please try again.");
			}
				
		}	
	}
	public void transfer(int bank_id1,int bank_id2, double amount) {
		if (amount < 0) 
			System.out.println("Please enter a positive amount.");
		else {
			BankAccount ba1 =  bankService.getBankAccount(bank_id1);
			BankAccount ba2 =  bankService.getBankAccount(bank_id2);
			if (!ba1.isActive() || !ba2.isActive()) 
				System.out.println("Account is not active!");
			else if(ba1.getAccount_balance()<amount) 
				System.out.println("The amount withdraw is larger than account balance.");
			else {
				ba1.setAccount_balance(ba1.getAccount_balance()-amount);
				ba2.setAccount_balance(ba2.getAccount_balance()+amount);
				if(bankService.udateBalance(bank_id1,ba1.getAccount_balance()) && 
						bankService.udateBalance(bank_id2,ba2.getAccount_balance()))
					System.out.println("Transfer successfully.");
				else
					System.out.println("Can not transfer. Please try again.");				
			}
		}
		
	}
	public Account createAccount(String firstName, String lastName, String username, String password, String type) {
		
		int account_id;
		account_id = accountDAO.addAccount(firstName,lastName,username,password,type);
		bankService.createBankAccount(account_id);
		return accountDAO.findAccount(username, password);
	}
	public void displayCustomerInfo() {
		List<Account> customers;
		customers = accountDAO.findAllCustomers();
		System.out.println("CUSTOMERS INFORMATIONS");
		System.out.println("-----------------------------------");		
		for (Account a : customers)
			System.out.println(a.toString());
		
	}
	public void deleteAccount(int customer_id) {
		if(accountDAO.deleteCustomerAccount(customer_id)) 
			System.out.println("Delete account successfully.");
		else
			System.out.println("Can not delete account. Please try again.");
		
	}
	public List<Account> createJoinAccount(String firstName, String lastName, String username, String password,
			String firstName2, String lastName2, String username2, String password2, String type) {
	
		int account_id1,account_id2;
		List<Account> accounts = new ArrayList<>();	
		account_id1 = accountDAO.addAccount(firstName,lastName,username,password,type);
		account_id2 = accountDAO.addAccount(firstName2,lastName2,username2,password2,type);
		bankService.createBankAccount(account_id1,account_id2);
		accounts.add(accountDAO.findAccount(username, password));
		accounts.add(accountDAO.findAccount(username2, password2));
		return accounts;
		
	}
}
