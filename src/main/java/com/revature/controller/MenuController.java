package com.revature.controller;

import java.util.Scanner;

import com.revature.models.Account;
import com.revature.models.BankAccount;



public class MenuController {
	
	private static Scanner scan = new Scanner(System.in); 
	private static AccountController accountController = AccountController.getAccountCtr();
	private static BankController bankController = new BankController();
	
	String response;
	//====================================================	
	public void welcomeMenu() {
		System.out.println("Welcome to Bank Application!");
		commandMenu("WELCOME");
		response = scan.nextLine();
		
		while(!response.equals("3")){
			switch (response){
				case "1":
					loginMenu();
					commandMenu("WELCOME");
					response = scan.nextLine();
					break;
				case "2":
					registerMenu();
					commandMenu("WELCOME");
					response = scan.nextLine();
					break;
				default:
					System.out.println("Invalid input. Please try again.");
					commandMenu("WELCOME");
					response = scan.nextLine();
					break;
			}
		}
		System.out.println("Thank you. Good Bye!");
	}
	//====================================================	
	private void registerMenu() {
		// TODO Auto-generated method stub
		
	}
	//====================================================
	private void loginMenu() {
		
		Account account;
		String username = "sarah92";
		String password = "password3";
		
		commandMenu("LOGIN");
//		System.out.println("Username: ");
//		username = scan.nextLine();
//		System.out.println("Password: ");
//		password = scan.nextLine();
//		
//		System.out.println(username+" "+password);
		
		account = accountController.login(username,password);
		
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
//====================================================
	private void adminMenu(Account account) {
		System.out.println("Hi, " + account.getName());
		
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
					bankController.getAllAccounts();
					commandMenu("EMPLOYEE");
					response = scan.nextLine();
					break;
				case "2":
					bankController.getAllCustomers();
					commandMenu("EMPLOYEE");
					response = scan.nextLine();
					break;
				case "3":
					bankController.getAllPendingAccounts();
					commandMenu("APPROVING");
					int id = scan.nextInt();
					if(bankController.approvedAccount(id))
						System.out.println("Approved sucessfully.");
					else
						System.out.println("Cannot approve. Please try again.");
					
					break;
				case "4":
					break;
				default:
					System.out.println("Invalid input. Please try again.");
				
					response = scan.nextLine();
					break;
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
					accountController.deposit(account_id,amount);
					
					
					commandMenu("CUSTOMER");
					response = scan.nextLine();
					break;
				case "2":
					commandMenu("WITHDRAW");
					
					response = scan.nextLine();
					break;
				case "3":
					commandMenu("TRANSFER");
					break;
				case "4":
					break;
				default:
					System.out.println("Invalid input. Please try again.");
				
					response = scan.nextLine();
					break;
			}			
		} 
		
	}
	//====================================================
	private void commandMenu(String page) {
		switch (page) {
			case "WELCOME":
				System.out.println("-----------------------------------");
				System.out.println("\t What would you like to do? \n");
				System.out.println("1. Login \t 2. Register \t 3. Exit");
				System.out.println("User inputs: ");
				break;
			case "REGISTER":
				System.out.println("-----------------------------------");
				System.out.println("\t REGISTER");
				System.out.println("Please enter: [First Name] [Last Name] [Username] "
						+ "[password]");
				System.out.println("User inputs: ");
				break;
			case"LOGIN":
				System.out.println("-----------------------------------");
				System.out.println("\t LOGIN");
				System.out.println("Please enter: [Username] [password]");
				System.out.println("User inputs: ");
				break;
			// 	customerHOMEPAGE when service got info from db and let controller know about it
			case "CUSTOMER":
				System.out.println("-----------------------------------");
				System.out.println("Please enter what you would like to do: ");
				System.out.println(	"[1. DEPOSIT] [2. WITHDRAW] [3. TRANSFER] [4. Exit]");
				System.out.println("User inputs: ");
				break;
			case "EMPLOYEE":
				System.out.println("-----------------------------------");
				System.out.println("Please enter what you would like to do: ");
				System.out.println(	"[1. ACCOUNT INFO] [2. PERSONAL INFO] "
						+ "[3. APPROVE/DENY ACCOUNT] [4. Exit]");				
				System.out.println("User inputs: ");
				break;
			case "APPROVING":
				System.out.println("-----------------------------------");
				// Must display a list of pending accounts
				System.out.println("Please enter the account ID that will be approved.");
				System.out.println("User inputs: ");
				break;
			case "ADMIN":
				System.out.println("-----------------------------------");
				System.out.println("Please enter what you would like to do: ");
				// if choose deposit -> go to deposit page normal
				System.out.println(	"[1. ACCOUNT INFO] [2. PERSONAL INFO] [3. DEPOSIT] "
						+ "[4. WITHDRAW] [5. TRANSFER] [6. DELETE ACCOUNT] "
						+ "[7. APPROVE/DENY ACCOUNT] [8. Exit]");
				System.out.println("User inputs: ");
				break;
			case "DEPOSIT":
				System.out.println("-----------------------------------");
				System.out.println("Please enter: [ACCOUNT] [AMOUNT] ");
				System.out.println("User inputs: ");
				break;
			case "WITHDRAW":
				System.out.println("-----------------------------------");
				System.out.println("Please enter: [ACCOUNT] [AMOUNT] ");
				System.out.println("User inputs: ");
				break;
			case "TRANSFER":
				System.out.println("-----------------------------------");
				System.out.println("Please enter: [FROM ACCOUNT] [TO ACCOUNT] [AMOUNT] ");
				System.out.println("User inputs: ");
				break;
			case "DELETE":
				System.out.println("-----------------------------------");
				// must display Bank Account table
				System.out.println("Please enter account ID to be deleted.] ");
				System.out.println("User inputs: ");
				break;
			default:
				break;
		}
		
	}

}