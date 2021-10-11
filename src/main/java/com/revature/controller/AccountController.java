package com.revature.controller;

import java.util.Scanner;

import com.revature.services.AccountService;

enum Page { WELCOME, REGISTER, LOGIN, customerHOMEPAGE, employeeHOMEPAGE, approvalPAGE, adminHOMEPAGE, DEPOSIT, WITHDRAW, TRANSFER, EXIT }

public class AccountController {

	String input = null;
	
	private static AccountController accCtr;
	
	private AccountController() {}
	
	public static AccountController getAccountCtr() {
		if (accCtr == null) 
			accCtr = new AccountController();
		return accCtr;
	}
	
		
	public void run() {
		AccountService accService = AccountService.getAccount();
		Menu menu = new Menu();
//		menu.display(Page.WELCOME);
//		menu.display(Page.REGISTER);
//		menu.display(Page.LOGIN);
//		menu.display(Page.customerHOMEPAGE);
//		menu.display(Page.employeeHOMEPAGE);
//		menu.display(Page.approvalPAGE);
//		menu.display(Page.adminHOMEPAGE);
//		menu.display(Page.DEPOSIT);
//		menu.display(Page.WITHDRAW);
//		menu.display(Page.TRANSFER);
//		menu.display(Page.EXIT);
		Scanner sc = new Scanner(System.in);
		do {
		  input = sc.nextLine();
		  System.out.println("Your input: " + input);
		} while (!input.equalsIgnoreCase("exit"));

		System.out.println("Thank you for using the App.");
	}
	
}
