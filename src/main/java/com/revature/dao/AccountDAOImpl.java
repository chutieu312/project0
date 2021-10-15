package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Account;
import com.revature.models.BankAccount;
import com.revature.utils.ConnectionUtil;

public class AccountDAOImpl implements AccountDAO {
	
	private BankDAO bankDAO = new BankDAOImpl();

	@Override
	public Account findAccount(String username, String password) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT  * "
					+ "FROM "
					+ "	(SELECT * , 'customers' as type_acc "
					+ "		FROM customers "
					+ "	UNION "
					+ "	SELECT * , 'employees' "
					+ "		FROM employees "
					+ "	UNION\r\n"
					+ "	SELECT * , 'administrators' "
					+ "		FROM administrators ) AS union_acc "
					+ "WHERE union_acc.user_name = ? AND union_acc.pass_word = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			System.out.println("In AccountDAOImpl: "+username+" "+password); 
			
			statement.setString(1, username);
			statement.setString(2, password);
			
			ResultSet result = statement.executeQuery();
			
			Account account = new Account();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			if(result.next()) {
				
				//System.out.println("Result is not null");
				account.setId(result.getInt("id"));
				account.setFirstName(result.getString("first_name"));
				account.setLastName(result.getString("last_name"));
				account.setUserName(result.getString("user_name"));
				account.setPassword(result.getString("pass_word"));
				account.setType(result.getString("type_acc"));
				
				if(account.getType().equalsIgnoreCase("customers")) {
					// very technical ^^
					account.setBankAccounts(bankDAO.findAllCustomerBankAccounts(account.getId()));
				}


			}
			//System.out.println(account.toString());
			return account;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		//System.out.println("Result is null");
		return null;
	}

	@Override
	public int addAccount(String firstName, String lastName, String username, String password, String type) {
		try(Connection conn = ConnectionUtil.getConnection()){
			String sql = "INSERT INTO customers (first_name, last_name, user_name, pass_word )\r\n"
			+ "		VALUES (?, ?, ?, ?) RETURNING id;";
			int count =0;
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setString(++count, firstName);
			statement.setString(++count, lastName);
			statement.setString(++count, username);
			statement.setString(++count, password);
			
			ResultSet result = statement.executeQuery();
			int id=-1;
			if(result.next())
				id = result.getInt("id");	
			
			return id;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}
	return -1;			
	}

	@Override
	public List<Account> findAllCustomers() {
		try(Connection conn = ConnectionUtil.getConnection()){ 
			
			String sql = "SELECT * FROM customers;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<Account> list = new ArrayList<>();			
			
			while(result.next()) {
				Account account = new Account();
				account.setType("CUSTOMER");
				account.setId(result.getInt("id"));
				account.setFirstName(result.getString(2));
				account.setLastName(result.getString(3));
				account.setUserName(result.getString(4));
				account.setPassword(result.getString(5));
				account.setBankAccounts(bankDAO.findAllCustomerBankAccounts(account.getId()));
				list.add(account);
			}
			return list;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public boolean deleteCustomerAccount(int customer_id) {
		List<BankAccount> bankAccounts = bankDAO.findAllCustomerBankAccounts(customer_id);
		for(BankAccount ba : bankAccounts)
			if(!bankDAO.deleteBankAccount(ba.getId(),customer_id))
				return false;
		try(Connection conn = ConnectionUtil.getConnection()){ 
			
			String sql = "DELETE FROM customers\r\n"
					+ "WHERE id = ? RETURNING true;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, customer_id);
			
			ResultSet result = statement.executeQuery();
			
			if(result.next())
				return result.getBoolean(1);
			
		
		}catch (SQLException e) {
			e.printStackTrace();
		}			
		return false;
	}
	
}
