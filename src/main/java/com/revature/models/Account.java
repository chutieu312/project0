package com.revature.models;

import java.util.List;

public class Account {
	
	private String type;
	private int id;
	private String first_name;
	private String last_name;
	private String user_name;
	private String pass_word;
	private List<BankAccount> bankAccounts = null;
	
	public Account() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Account(String type, int id, String first_name, String last_name, String user_name, String pass_word,
			List<BankAccount> bankAccounts) {
		super();
		this.type = type;
		this.id = id;
		this.first_name = first_name;
		this.last_name = last_name;
		this.user_name = user_name;
		this.pass_word = pass_word;
		this.bankAccounts = bankAccounts;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return first_name;
	}

	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}

	public String getLastName() {
		return last_name;
	}

	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	
	public String getName() {
		return first_name +" "+ last_name;
	}

	public String getUserName() {
		return user_name;
	}

	public void setUserName(String user_name) {
		this.user_name = user_name;
	}

	public String getPassword() {
		return pass_word;
	}

	public void setPassword(String pass_word) {
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
		result = prime * result + ((bankAccounts == null) ? 0 : bankAccounts.hashCode());
		result = prime * result + ((first_name == null) ? 0 : first_name.hashCode());
		result = prime * result + id;
		result = prime * result + ((last_name == null) ? 0 : last_name.hashCode());
		result = prime * result + ((pass_word == null) ? 0 : pass_word.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		if (id != other.id)
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
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
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
		return "Account [type=" + type + ", id=" + id + ", first_name=" + first_name + ", last_name=" + last_name
				+ ", user_name=" + user_name + ", pass_word=" + pass_word + ", bankAccounts=" + bankAccounts + "]";
	}
	
	

}
