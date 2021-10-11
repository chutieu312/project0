package com.revature.controller;

import java.util.Scanner;



public class MenuController {
	
	private static Scanner scan = new Scanner(System.in); 
	private static AccountController accountController = AccountController.getAccountCtr();
	private static CustomerController customerController = CustomerController.getCustomerCtr();
	private static AdminController adminController = AdminController.getAdminCtr();
	private static EmployeeController employeeController = EmployeeController.getEmployeeCtr();
	
	
	public void welcomeMenu() {
		System.out.println("Welcome to Bank Application!");
		printWelcomeMessages();
		String response = scan.nextLine();
		
		while(!response.equals("3")){
			switch (response){
				case "1":
					loginMenu();
					printWelcomeMessages();
					response = scan.nextLine();
					break;
				case "2":
					registerMenu();
					printWelcomeMessages();
					response = scan.nextLine();
					break;
				case "3":
					break;
				default:
					System.out.println("Invalid input. Please try again.");
					printWelcomeMessages();
					response = scan.nextLine();
					break;
			}
		}
		
	}
	
	private void registerMenu() {
		// TODO Auto-generated method stub
		
	}

	private void loginMenu() {
		String response1;
		String response2;
		System.out.println("Enter username and password. \n"
					+"Username: ");
		response1 = scan.nextLine();
		System.out.println("\n Password: ");
		response2 = scan.nextLine();
		
		customerController.login
		
	}

	private void printWelcomeMessages() {
		System.out.println("What would you like to do? \n"
				+"1) Login \n"
				+"2) Register \n"
				+"3) Exit the application.");		
		
	}

	private void avengerMenu() {
		System.out.println("What would you like to do with the Avengers? \n"
				+ "1) Show all Avengers");
		String response = scan.nextLine();
		
		while(!response.equals("4")){
			switch (response){
				case "1":
					avengerController.showAllAvengers();
					System.out.println("What would you like to do with the Avengers? \n"
							+ "1) Show all Avengers");
					response = scan.nextLine();
					break;
				default:
					System.out.println("Type 4 to exit.");
					response = scan.nextLine();
					break;
			}
		}
	}

	private void homeMenu() {
		System.out.println("What would you like to do with your homes? \n"
				+ "1) See all homes \n"
				+ "2) See one home \n"
				+ "3) Add a home to the database. \n"
				+ "4) Return to previous menu.");
		String response = scan.nextLine();
		
		while(!response.equals("4")){
			switch (response){
				case "1":
					homeController.displayAllHomes();
					System.out.println("What would you like to do with your homes? \n"
							+ "1) See all homes \n"
							+ "2) See one home");
					response = scan.nextLine();
					break;
				case "2":
					System.out.println("What is the home name?");
					String name = scan.nextLine();
					homeController.displayOneHome(name);
					
					System.out.println("What would you like to do with your homes? \n"
							+ "1) See all homes \n"
							+ "2) See one home");
					response = scan.nextLine();
					break;
				case "3":
					homeController.addHome();
					System.out.println("What would you like to do with your homes? \n"
							+ "1) See all homes \n"
							+ "2) See one home");
					response = scan.nextLine();
					break;
				case "4":
					break;
				default:
					System.out.println("That was not a valid input. Please try again.");
					System.out.println("What would you like to do with your homes? \n"
							+ "1) See all homes \n"
							+ "2) See one home");
					response = scan.nextLine();
					break;
					
			}
		}
	}

}