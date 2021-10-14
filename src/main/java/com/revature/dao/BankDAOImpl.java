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
	public List<BankAccount> findAllBankAccounts(int customer_id) {
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

}
