package com.revature.controller;

public class Menu {

	public void display(Page page ) {
		
		switch (page) {
			case WELCOME:
				System.out.println("\t WELLCOME TO BANK APPLICATION");
				System.out.println("1. Register \t 2. Login \t 3. Exit");
				System.out.println("User inputs: ");
				break;
			case REGISTER:
				System.out.println("\t REGISTER");
				System.out.println("Please enter: [Your full name] [email] "
						+ "[password] OR [Exit]");
				System.out.println("User inputs: ");
				break;
			case LOGIN:
				System.out.println("\t LOGIN");
				System.out.println("Please enter: [Your Username] [password]");
				System.out.println("OR [Exit] to quit the application");
				System.out.println("User inputs: ");
				break;
			// 	customerHOMEPAGE when service got info from db and let controller know about it
			case customerHOMEPAGE:
				System.out.println("\t customer HOMEPAGE");
				System.out.println("Please enter what you would like to do: ");
				System.out.println(	"[DEPOSIT] [WITHDRAW] [TRANSFER] OR [Exit]");
				System.out.println("User inputs: ");
				break;
			case employeeHOMEPAGE:
				System.out.println("\t employee HOMEPAGE");
				System.out.println("Please enter what you would like to do: ");
				System.out.println(	"[ACCOUNT INFO] [ACCOUNT BALANCE] [PERSONAL INFO] "
						+ "[PENDIN APPROVING] OR [Exit]");				
				System.out.println("User inputs: ");
				break;
			case approvalPAGE:
				System.out.println("\t approval PAGE");
				System.out.println("Please enter the account that will be approved. OR [Exit]");
				System.out.println("User inputs: ");
				break;
			case adminHOMEPAGE:
				System.out.println("\t admin HOMEPAGE");
				System.out.println("Please enter what you would like to do: ");
				// if choose deposit -> go to deposit page normal
				System.out.println(	"[DEPOSIT] [WITHDRAW] [TRANSFER] "
						+ "[PENDIN APPROVING] OR [Exit]");
				System.out.println("User inputs: ");
				break;
			case DEPOSIT:
				System.out.println("\t DEPOSIT");
				System.out.println("Please enter: [ACCOUNT] [AMOUNT] OR [Exit]");
				System.out.println("User inputs: ");
				break;
			case WITHDRAW:
				System.out.println("\t WITHDRAW");
				System.out.println("Please enter: [ACCOUNT] [AMOUNT] OR [Exit]");
				System.out.println("User inputs: ");
				break;
			case TRANSFER:
				System.out.println("\t TRANSFER");
				System.out.println("Please enter: [ACCOUNT] [AMOUNT] OR [Exit]");
				System.out.println("User inputs: ");
				break;
			case EXIT:
				System.out.println("\t Thank you for using the App.");
				
				break;
			default:
				break;
		}	
	}
}
