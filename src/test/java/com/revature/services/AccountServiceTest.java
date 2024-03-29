package com.revature.services;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;





public class AccountServiceTest {
	
	public static AccountService accountService;
	public static int id1;
	public static int id2;
	public static double amount;
	public static double result;
	public static ArrayList<Double> results;
	public static Logger log = LoggerFactory.getLogger(AccountServiceTest.class);	
	
	
	@BeforeAll
	public static void setAccountServiceTest() {
		//log.info("In setAccountServiceTest");
		accountService = AccountService.getAccount();
		results = new ArrayList<>();
	}	
	
	@BeforeEach
	public void setAccountBalance() {
		id1 = 20;
		id2 = 5;
		amount = 100;
		log.info("setAccountBalance");
	}
			
	@Test
	public void testDeposit() {
		log.info("In testDeposit");
		result = accountService.deposit(id1,amount);
		assertEquals(200, result);
	}			
	
	@Test
	public void testWithdraw() {
	log.info("In testWithdraw");
		result = accountService.withdraw(id1,amount);
		assertEquals(100, result);
	}
	
	@Test
	public void testTransfer() {
		log.info("In testTransfer");
		results = accountService.transfer(id1,id2,amount);
		assertEquals(200.0, results.get(0));
		assertEquals(600.0, results.get(1));
	}
	
	@AfterEach
	public void clearResult() {
		result = 0;
		log.info("In clearResult");
	}
	
	@AfterAll 
	public static void clearAccountService() {
		accountService = null;
		log.info("in clearAccountService");
	}
			
}
