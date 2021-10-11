package com.revature.models;

// first create abstract data type

abstract class Account {
	
	private AccountType type=null ;
	
	Account(AccountType type){ this.type = type;}
	
	AccountType getModel(){return type;}

	 void setModel(AccountType type){this.type = type;}
}

//Classes that inherit the abstract data type

class Customer extends Account {
	
	public Customer(AccountType type) {
		super(type);
	}
}

class Employee extends Account {
	
	public Employee(AccountType type) {
		super(type);
	}
}

class Admin extends Account {
	
	public Admin(AccountType type) {
		super(type);
	}
}

//Factory class that returns the concrete instance
public class AccountFactory {
	 static Account buildAccount(AccountType type)
	 {
		 Account acc = null;
	     switch (type)
	     {
	         case CUSTOMER:
	        	 acc = new Customer(AccountType.CUSTOMER);
	             break;
	           
	         case EMPLOYEE:
	        	 acc = new Employee(AccountType.EMPLOYEE);
	             break;
	               
	         case ADMIN:
	        	 acc = new Admin(AccountType.ADMIN);
	             break;
	               
	         default:
	             break;
	           
	     }
	     return acc;
	 }
	
}
