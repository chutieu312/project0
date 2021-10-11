package com.revature;

import com.revature.controller.AccountController;


public class Driver {

	
	public static void main(String[] args) {
		AccountController accCtr = AccountController.getAccount();
		accCtr.run();

	}

}
