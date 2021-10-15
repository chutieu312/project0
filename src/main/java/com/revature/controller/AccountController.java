package com.revature.controller;

import java.util.List;
import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.BankAccount;
import com.revature.services.AccountService;
import com.revature.services.BankService;


public class AccountController {

	private static Scanner scan = new Scanner(System.in); 
	private AccountService accountService = AccountService.getAccount();
	private static BankService bankService = new BankService();
	String response;
	
	private static AccountController accCtr;
	private AccountController() {}
	public static AccountController getAccountCtr() {
		if (accCtr == null) 
			accCtr = new AccountController();
		return accCtr;
	}
	
	public void login(String username, String password) {
		Account account;
		account =  accountService.login(username,password);
		
		if (account == null) {
			System.out.println("Invalid username or password.");
		}
		else {
			if (account.getType().equalsIgnoreCase("customers")) {
				customerMenu(account);
			}
			else if (account.getType().equalsIgnoreCase("employees")) {
				employeeMenu(account);
			}
			else if(account.getType().equalsIgnoreCase("administrators")) {
					adminMenu(account);
			}
		}
	
	}

//=======================================================
	private void customerMenu(Account account) {
		System.out.println("-----------------------------------");
		System.out.println("\t CUSTOMER");
		System.out.println("Hi, " + account.getName());
		for(BankAccount ba : account.getBankAccounts())
			System.out.println(ba.toString());
		commandMenu("CUSTOMER");
		response = scan.nextLine();
		
		int account_id;
		double amount;
		
		while (!response.equals("4")) {
			switch (response){
				case "1":
					commandMenu("DEPOSIT");
					account_id = scan.nextInt();
					amount = scan.nextDouble();
					accountService.deposit(account_id,amount);
					commandMenu("CUSTOMER");
					response = scan.nextLine();
					response = scan.nextLine();
					break;
				case "2":
					commandMenu("WITHDRAW");
					account_id = scan.nextInt();
					amount = scan.nextDouble();
					accountService.withdraw(account_id,amount);	
					commandMenu("CUSTOMER");
					response = scan.nextLine();
					response = scan.nextLine();
					break;
				case "3":
					commandMenu("TRANSFER");
					account_id = scan.nextInt();
					amount = scan.nextDouble();// will make cursor point to new line
					accountService.transfer(account_id,amount);
					commandMenu("CUSTOMER");
					response = scan.nextLine(); // to read a newline
					response = scan.nextLine();
					break;
				case "4":
					break;
				default:
					System.out.println("Invalid input. Please try again.");
					commandMenu("CUSTOMER");
					response = scan.nextLine();
					break;
			}			
		} 
		
	}
	
//=======================================================
	private void employeeMenu(Account account) {
		System.out.println("-----------------------------------");
		System.out.println("\t EMPLOYEE");
		System.out.println("Hi, " + account.getName());
		
		commandMenu("EMPLOYEE");
		response = scan.nextLine();
		
		
		while (!response.equals("4")) {
			switch (response){
				case "1":
					bankService.displayAccountInfo();
					commandMenu("EMPLOYEE");
					response = scan.nextLine();
					break;
				case "2":
					accountService.displayCustomerInfo();
					commandMenu("EMPLOYEE");
					response = scan.nextLine();
					break;
				case "3":
					if(bankService.displayPendingAccount()) {
						commandMenu("APPROVING");
						int id = scan.nextInt();
						response = scan.nextLine();
						response = scan.nextLine();
						bankService.approveAccount(id);
					}
					commandMenu("EMPLOYEE");
					response = scan.nextLine();				
					break;
				default:
					System.out.println("Invalid input. Please try again.");
					commandMenu("EMPLOYEE");
					response = scan.nextLine();
					break;
			}			
		}
		
	}
	
//====================================================
	private void adminMenu(Account account) {
		System.out.println("-----------------------------------");
		System.out.println("\t ADMIN");
		System.out.println("Hi, " + account.getName());
		
		commandMenu("ADMIN");
		response = scan.nextLine();
		
		int account_id;
		int account_id2;
		double amount;
		
		while (!response.equals("8")) {
			switch (response){
				case "1":
					bankService.displayAccountInfo();
					commandMenu("ADMIN");
					response = scan.nextLine();
					break;
				case "2":
					accountService.displayCustomerInfo();
					commandMenu("ADMIN");
					response = scan.nextLine();
					break;
				case "3":
					commandMenu("DEPOSIT");
					account_id = scan.nextInt();
					amount = scan.nextDouble(); // cursor point to new line
					response = scan.nextLine(); // to read a newline
					accountService.deposit(account_id,amount);
					commandMenu("ADMIN");
					response = scan.nextLine(); // now we can read as normal
					break;
				case "4":
					commandMenu("WITHDRAW");
					account_id = scan.nextInt();
					amount = scan.nextDouble();
					response = scan.nextLine();
					accountService.withdraw(account_id,amount);	
					commandMenu("ADMIN");
					response = scan.nextLine();
					break;
				case "5":
					commandMenu("TRANSFER");
					account_id = scan.nextInt();
					account_id2 = scan.nextInt();
					amount = scan.nextDouble();
					response = scan.nextLine();
					accountService.transfer(account_id,account_id2,amount);
					commandMenu("ADMIN");
					response = scan.nextLine();
					break;
				case "6":
					bankService.displayPendingAccount();
					commandMenu("APPROVING");
					int id = scan.nextInt();
					response = scan.nextLine();
					bankService.approveAccount(id);
					commandMenu("ADMIN");
					response = scan.nextLine();
					break;
				case "7":
					accountService.displayCustomerInfo();
					commandMenu("DELETE");
					id = scan.nextInt();
					response = scan.nextLine();
					accountService.deleteAccount(id);
					commandMenu("ADMIN");
					response = scan.nextLine();
					break;
				case "8":
					break;
				default:
					System.out.println("Invalid input. Please try again.");
					commandMenu("ADMIN");
					response = scan.nextLine();
					break;
			}			
		}
	}	
	
	//=======================================================
	private void commandMenu(String page) {
		switch (page) {

			// 	customerHOMEPAGE when service got info from db and let controller know about it
			case "CUSTOMER":
				System.out.println("--------------------CUSTOMER---------------\n");
				System.out.println("Please enter what you would like to do: ");
				System.out.println(	"[1. DEPOSIT]  [2. WITHDRAW]  [3. TRANSFER]  [4. Exit]");
				System.out.println("User inputs: ");
				break;
			case "EMPLOYEE":
				System.out.println("-----------------EMPLOYEE------------------\n");
				System.out.println("Please enter what you would like to do: ");
				System.out.println(	"[1. ACCOUNT INFO]  [2. CUSTOMER INFO] "
						+ " [3. APPROVE/DENY ACCOUNT]  [4. Exit]");				
				System.out.println("User inputs: ");
				break;
			case "APPROVING":
				System.out.println("-----------------APPROVING------------------\n");
				// Must display a list of pending accounts
				System.out.println("Please enter the account ID that will be approved.");
				System.out.println("User inputs: ");
				break;
			case "ADMIN":
				System.out.println("-----------------ADMIN------------------ \n");
				System.out.println("Please enter what you would like to do: ");
				// if choose deposit -> go to deposit page normal
				System.out.println(	"[1. ACCOUNT INFO]  [2. CUSTOMER INFO]  [3. DEPOSIT] \n"
						+ " [4. WITHDRAW]  [5. TRANSFER]  [6. APPROVE/DENY ACCOUNT] \n"
						+ " [7. DELETE ACCOUNT]  [8. Exit]");
				System.out.println("User inputs: ");
				break;
			case "DEPOSIT":
				System.out.println("---------------DEPOSIT--------------------\n");
				System.out.println("Please enter: [ACCOUNT]  [AMOUNT] ");
				System.out.println("User inputs: ");
				break;
			case "WITHDRAW":
				System.out.println("---------------WITHDRAW--------------------\n");
				System.out.println("Please enter: [ACCOUNT]  [AMOUNT] ");
				System.out.println("User inputs: ");
				break;
			case "TRANSFER":
				System.out.println("---------------TRANSFER--------------------\n");
				System.out.println("Please enter: [FROM ACCOUNT]  [TO ACCOUNT]  [AMOUNT] ");
				System.out.println("User inputs: ");
				break;
			case "DELETE":
				System.out.println("-----------------DELETE------------------\n");
				// must display Bank Account table
				System.out.println("Please enter account ID to be deleted. ");
				System.out.println("User inputs: ");
				break;
			default:
				break;
		}
		
	}
	public void register(String firstName, String lastName, String username, String password, String type) {
		
		// Continue from register-Menu
		System.out.println("Creating account...");
		Account account = accountService.createAccount(firstName,lastName,username,password,type);
		if(account!=null) {
			System.out.println("Account created successfully."
					+ "\n Please waiting for approving.");
		}
		else
			System.out.println("Unsuccessfully. Try agian.");
		
	}
	public void registerJoint(String firstName, String lastName, String username, String password, String firstName2,
			String lastName2, String username2, String password2, String type) {
		
		System.out.println("Creating account...");
		List<Account> accounts = accountService.createJoinAccount(firstName,lastName,username,password,firstName2,lastName2,username2,password2,type);
		if(accounts!=null) {
			System.out.println("Join-Account created successfully."
					+ "\n Please waiting for approving.");
		}
		else
			System.out.println("Unsuccessfully. Try agian.");
		
	}	
}

