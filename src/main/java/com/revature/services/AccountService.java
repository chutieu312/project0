package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.controller.AccountController;
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
	public static Logger log = LoggerFactory.getLogger(AccountService.class);
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
	
	public double deposit(int bank_id, double amount) {
		log.info("In deposit: deposit: "+amount+" to "+bank_id);
		if (amount < 0) {
			System.out.println("Please enter a positive amount.");
			return -1;
		}
		else {
			BankAccount ba =  bankService.getBankAccount(bank_id);
			if(!ba.isActive()) {
				System.out.println("Account is not active!");
				return -1;
			}
			else {
				ba.setAccount_balance(ba.getAccount_balance()+amount);
				double newBalance = bankService.udateBalance(bank_id,ba.getAccount_balance());
				if(newBalance!=-1) {
					System.out.println("Deposit successfully.");
					return newBalance;
				}
				else {
					System.out.println("Can not deposit. Please try again.");
					return -1;
				}
			}		
		}	
	}
	public double withdraw(int bank_id, double amount) {
		log.info("In withdraw: withdraw: "+amount+" from "+bank_id);
		if (amount < 0) {
			System.out.println("Please enter a positive amount.");
			return -1;
		}
		else {
			BankAccount ba =  bankService.getBankAccount(bank_id);
			if(!ba.isActive()) {
				System.out.println("Account is not active!");
				return -1;
			}
			else if (ba.getAccount_balance()<amount) { 
				System.out.println("The amount withdraw is larger than account balance.");
				return -1;
			}
			else {
				ba.setAccount_balance(ba.getAccount_balance()-amount);
				double newBalance = bankService.udateBalance(bank_id,ba.getAccount_balance());
				if(newBalance!=-1) {
					System.out.println("withdraw successfully.");
					return newBalance;
				}
				else {
					System.out.println("Can not withdraw. Please try again.");
					return -1;
				}
			}
				
		}	
	}
	public ArrayList<Double> transfer(int bank_id1,int bank_id2, double amount) {
		log.info("In transfer: transfer: "+amount+" from account "+bank_id1
				+" to account "+bank_id2);
		ArrayList<Double> arrayList = new ArrayList<>();
		if (amount < 0) {
			System.out.println("Please enter a positive amount.");
			return arrayList;
		}
		else {
			BankAccount ba1 =  bankService.getBankAccount(bank_id1);
			BankAccount ba2 =  bankService.getBankAccount(bank_id2);
			if (!ba1.isActive() || !ba2.isActive()) {
				System.out.println("Account is not active!");
				return arrayList;
			}
			else if(ba1.getAccount_balance()<amount) {
				System.out.println("The amount withdraw is larger than account balance.");
				return arrayList;
			}
			else {
				ba1.setAccount_balance(ba1.getAccount_balance()-amount);
				ba2.setAccount_balance(ba2.getAccount_balance()+amount);
				double newBalance1 = bankService.udateBalance(bank_id1,ba1.getAccount_balance());
				double newBalance2 = bankService.udateBalance(bank_id2,ba2.getAccount_balance());
				arrayList.add(newBalance1);
				arrayList.add(newBalance2);
				if(newBalance1 !=-1 && newBalance2 !=-1) {
					System.out.println("Transfer successfully.");
					return arrayList;
				}
				else {
					System.out.println("Can not transfer. Please try again.");
					return arrayList;
				}		
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
