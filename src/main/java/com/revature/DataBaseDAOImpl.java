package com.revature;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.models.Account;
import com.revature.utils.ConnectionUtil;

// This is a class for show database manipulation 

public class DataBaseDAOImpl {
	
	public void run() {
		try(Connection conn = ConnectionUtil.getConnection()){
			//---------------------------------
			// ADD a new customer
//			String first_name = "Mina";
//			String last_name = "Nguyen";
//			String user_name = "mina18";
//			String pass_word = "password6";
//			
//			String sql = "INSERT INTO customers (first_name, last_name, user_name, pass_word )\r\n"
//					+ "		VALUES (?, ?, ?, ?) RETURNING id;";
//			int count =0;
//			
//			PreparedStatement statement = conn.prepareStatement(sql);
//			
//			statement.setString(++count, first_name);
//			statement.setString(++count, last_name);
//			statement.setString(++count, user_name);
//			statement.setString(++count, pass_word);
//			
//			ResultSet result = statement.executeQuery();
//			int id=-1;
//			if(result.next())
//				id = result.getInt("id");
//			
//			System.out.println("This is the result: "+ id );
			
			//---------------------------------
			// ADD a new bank account
//			String sql = "INSERT INTO accounts (account_balance, active)\r\n"
//					+ "	VALUES (?, ?) RETURNING id;";
//			int count =0;
//			
//			PreparedStatement statement = conn.prepareStatement(sql);
//			
//			statement.setDouble(++count, 0.0);
//			statement.setBoolean(++count, false);
//			
//			
//			ResultSet result = statement.executeQuery();
//			int id=-1;
//			if(result.next())
//				id = result.getInt("id");
//			
//			System.out.println("This is the result: "+ id );
			
			//---------------------------------
			// ADD a new customer-account
//			String sql = "INSERT INTO customer_account (customer_id, account_id)\r\n"
//					+ "	VALUES (?,?);";
//			int count =0;
//			
//			PreparedStatement statement = conn.prepareStatement(sql);
//			
//			statement.setInt(++count, 6);
//			statement.setInt(++count, 10);
//			
//			
//			statement.execute();

			//---------------------------------
			
			
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
