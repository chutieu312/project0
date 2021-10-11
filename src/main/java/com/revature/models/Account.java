package com.revature.models;

import java.util.List;

public class Account {
	
	private AccountType accountType = null;
	private int account_id;
	private String first_name;
	private String last_name;
	private String user_name;
	private String pass_word;
	private List<BankAccount> bankAccounts;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}
		
	

	public Account(AccountType accountType, int account_id, String first_name, String last_name, String user_name,
			String pass_word, List<BankAccount> bankAccounts) {
		super();
		this.accountType = accountType;
		this.account_id = account_id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.pass_word = pass_word;
		this.bankAccounts = bankAccounts;
	}



	public AccountType getAccountType() {
		return accountType;
	}



	public void setAccountType(AccountType accountType) {
		this.accountType = accountType;
	}



	public int getAccount_id() {
		return account_id;
	}



	public void setAccount_id(int account_id) {
		this.account_id = account_id;
	}



	public String getFirst_name() {
		return first_name;
	}



	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}



	public String getLast_name() {
		return last_name;
	}



	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}



	public String getUser_name() {
		return user_name;
	}



	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}



	public String getPass_word() {
		return pass_word;
	}



	public void setPass_word(String pass_word) {
		this.pass_word = pass_word;
	}



	public List<BankAccount> getBankAccounts() {
		return bankAccounts;
	}



	public void setBankAccounts(List<BankAccount> bankAccounts) {
		this.bankAccounts = bankAccounts;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accountType == null) ? 0 : accountType.hashCode());
		result = prime * result + account_id;
		result = prime * result + ((bankAccounts == null) ? 0 : bankAccounts.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((pass_word == null) ? 0 : pass_word.hashCode());
		result = prime * result + ((user_name == null) ? 0 : user_name.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountType != other.accountType)
			return false;
		if (account_id != other.account_id)
			return false;
		if (bankAccounts == null) {
			if (other.bankAccounts != null)
				return false;
		} else if (!bankAccounts.equals(other.bankAccounts))
			return false;
		if (first_name == null) {
			if (other.first_name != null)
				return false;
		} else if (!first_name.equals(other.first_name))
			return false;
		if (last_name == null) {
			if (other.last_name != null)
				return false;
		} else if (!last_name.equals(other.last_name))
			return false;
		if (pass_word == null) {
			if (other.pass_word != null)
				return false;
		} else if (!pass_word.equals(other.pass_word))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Account [accountType=" + accountType + ", account_id=" + account_id + ", first_name=" + first_name
				+ ", last_name=" + last_name + ", user_name=" + user_name + ", pass_word=" + pass_word
				+ ", bankAccounts=" + bankAccounts + "]";
	}

	
	
	

}
