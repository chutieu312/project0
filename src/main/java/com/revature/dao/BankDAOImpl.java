package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import com.revature.models.BankAccount;
import com.revature.utils.ConnectionUtil;

public class BankDAOImpl implements BankDAO {

	@Override
	public List<BankAccount> findAllCustomerBankAccounts(int customer_id) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources 
			String sql = "SELECT account_id , account_balance, active\r\n"
					+ "FROM customer_account ca\r\n"
					+ "INNER JOIN accounts a ON ca.account_id = a.id\r\n"
					+ "WHERE ca.customer_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1,customer_id);
			
			ResultSet result = statement.executeQuery();
			
			List<BankAccount> list = new ArrayList<>();
			
			//ResultSets have a cursor (similar to Scanner or other I/O classes) that can be used 
			//with a while loop to iterate through all the data. 
			
			while(result.next()) {
				BankAccount bankAccount = new BankAccount(
						result.getInt("account_id"), 
						result.getDouble("account_balance"),
						result.getBoolean("active")

						);
				list.add(bankAccount);
			}
			
			return list;
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public int addBankAccount(int customer_id) {
		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources		
			
			String sql = "INSERT INTO accounts (account_balance, active)\r\n"
			+ "	VALUES (?, ?) RETURNING id;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count =0;
			statement.setDouble(++count, 0.0);
			statement.setBoolean(++count, false);
			
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
	public void addCustomerBankAccount(int customer_id, int bank_id) {

		try(Connection conn = ConnectionUtil.getConnection()){ //try-with-resources		
			
			String sql = "INSERT INTO customer_account (customer_id, account_id)\r\n"
			+ "	VALUES (?,?);";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			int count =0;
			statement.setInt(++count, customer_id);
			statement.setInt(++count, bank_id);
			
			statement.execute();			
			
		}catch (SQLException e) {
			e.printStackTrace();
		}	
	}


	@Override
	public List<BankAccount> findAll() {
		
		try(Connection conn = ConnectionUtil.getConnection()){ 
			
			String sql = "SELECT * FROM accounts;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<BankAccount> list = new ArrayList<>();			
			
			while(result.next()) {
				BankAccount bankAccount = new BankAccount();
				bankAccount.setId(result.getInt("id"));
				bankAccount.setAccount_balance(result.getDouble("account_balance"));
				bankAccount.setActive(result.getBoolean("active"));
				list.add(bankAccount);
			}
			return list;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public List<BankAccount> findAllPendingAccounts() {
		try(Connection conn = ConnectionUtil.getConnection()){ 
			
			String sql = "SELECT * FROM accounts WHERE active = FALSE;";
			Statement statement = conn.createStatement();
			ResultSet result = statement.executeQuery(sql);
			List<BankAccount> list = new ArrayList<>();			
			
			while(result.next()) {
				BankAccount bankAccount = new BankAccount();
				bankAccount.setId(result.getInt("id"));
				bankAccount.setAccount_balance(result.getDouble("account_balance"));
				bankAccount.setActive(result.getBoolean("active"));
				list.add(bankAccount);
			}
			return list;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public boolean setTrueActive(int id) {
		try(Connection conn = ConnectionUtil.getConnection()){ 
			String sql = "UPDATE accounts\r\n"
					+ "SET active = TRUE \r\n"
					+ "WHERE id = ? RETURNING active;";
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setInt(1,id);
			statement.execute();
			
			ResultSet result = statement.executeQuery();
			
			if(result.next())
				return result.getBoolean("active");
			
		
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return false;		
	}

	@Override
	public boolean deleteBankAccount(int account_id,int customer_id) {
		try(Connection conn = ConnectionUtil.getConnection()){ 
			String sql = "SELECT customer_id FROM customer_account "
					+ "WHERE account_id = ?;";
			
			PreparedStatement statement = conn.prepareStatement(sql);
			
			statement.setInt(1, account_id);	
			
			ResultSet result = statement.executeQuery();
			
			int count=0;
			
			while (result.next()) {
				count++;
			}
			if (count==2) {
				return true;
			}
			else {
				sql = "DELETE FROM accounts\r\n"
						+ "WHERE id = ? RETURNING true;";			
				
				statement = conn.prepareStatement(sql);
				
				statement.setInt(1, account_id);
				
				result = statement.executeQuery();
				
				if(result.next())
					return result.getBoolean(1);		
			}
			
		
		}catch (SQLException e) {
			e.printStackTrace();
		}				
		return false;
	}

	@Override
	public BankAccount findAccountById(int bank_id) {
		
		try(Connection conn = ConnectionUtil.getConnection()){ 
			String sql = "SELECT * FROM accounts WHERE id = ?;";
			PreparedStatement statement = conn.prepareStatement(sql);
			statement.setInt(1, bank_id);
			ResultSet result = statement.executeQuery();
			BankAccount ba = new BankAccount();
			if(result.next()) {
				ba.setId(result.getInt(1));
				ba.setAccount_balance(result.getDouble(2));
				ba.setActive(result.getBoolean(3));
			}
			return ba;
		
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return null;
	}

	@Override
	public double udateAccountBalance(int bank_id, double account_balance) {
		try(Connection conn = ConnectionUtil.getConnection()){ 
			String sql = "UPDATE accounts\r\n"
					+ "SET account_balance = ? WHERE id = ? RETURNING account_balance ;";
			
			PreparedStatement statement = conn.prepareStatement(sql);

			statement.setDouble(1,account_balance);
			statement.setInt(2,bank_id);
			statement.execute();
			
			ResultSet result = statement.executeQuery();
			
			if(result.next())
				return result.getDouble(1);
			
		
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		return -1;
	}

}
