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
		System.out.println("  Welcome to Bank Application!");
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
		
		// For now just register for customer.
		String firstName,lastName,username,password,
		firstName2,lastName2,username2,password2;

		System.out.println("\t What would you like to register? \n");
		System.out.println("1. Single Account \t 2.Join-Account");
		response = scan.nextLine();
		if (response.equalsIgnoreCase("1")) {
			commandMenu("REGISTER");
			System.out.println("First Name: ");
			firstName = scan.nextLine();
			System.out.println("Last Name: ");
			lastName = scan.nextLine();
			System.out.println("Username: ");
			username = scan.nextLine();
			System.out.println("Password: ");
			password = scan.nextLine();
			
			accountController.register(firstName,lastName,username,password,"CUSTOMER");
		}
		else {
			commandMenu("REGISTER-JOIN");
			System.out.println("First Name1: ");
			firstName = scan.nextLine();
			System.out.println("Last Name1: ");
			lastName = scan.nextLine();
			System.out.println("Username1: ");
			username = scan.nextLine();
			System.out.println("Password1: ");
			password = scan.nextLine();
			commandMenu("REGISTER");
			System.out.println("First Name2: ");
			firstName2 = scan.nextLine();
			System.out.println("Last Name2: ");
			lastName2 = scan.nextLine();
			System.out.println("Username2: ");
			username2 = scan.nextLine();
			System.out.println("Password2: ");
			password2 = scan.nextLine();
			accountController.registerJoint(firstName,lastName,username,password,
					firstName2,lastName2,username2,password2,"CUSTOMER");
		}
	}
	//====================================================
	private void loginMenu() {
		
		String username = "thesky312"; //"thesky312";"youngmax91"
		String password = "password5";
		
		commandMenu("LOGIN");
//		System.out.println("Username: ");
//		username = scan.nextLine();
//		System.out.println("Password: ");
//		password = scan.nextLine();
//		
//		System.out.println(username+" "+password);
		
		accountController.login(username,password);
		
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
			case "REGISTER-JOIN":
				System.out.println("---------------REGISTER--------------------");
				System.out.println();
				System.out.println("Please enter first person information: "
						+ "[First Name] [Last Name] [Username] "
						+ "[password]");
				System.out.println("Please enter second person information: "
						+ "[First Name] [Last Name] [Username] "
						+ "[password]");
				System.out.println("User inputs: ");
				break;
			case "REGISTER":
				System.out.println("-----------------REGISTER------------------");
				System.out.println();
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

			default:
				break;
		}
	}
}


































