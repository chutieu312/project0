package com.revature;


import com.revature.controller.MenuController;


public class Driver {

	
	public static void main(String[] args) {
		
		DataBaseDAOImpl dataBaseDAOImpl = new DataBaseDAOImpl();
		//dataBaseDAOImpl.run();
		
		MenuController menuController = new MenuController();
		menuController.welcomeMenu();

	}

}
