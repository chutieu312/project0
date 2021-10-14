package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Account;
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
					account.setBankAccounts(bankDAO.findAllBankAccounts(account.getId()));
				}


			}
			//System.out.println(account.toString());
			return account;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Result is null");
		return null;
	}

}
