package com.revature.models;

public class BankAccount {
	
	private int id;
	private double account_balance;
	private boolean active;
	
	public BankAccount(int account_id, double account_balance, boolean active) {
		super();
		this.id = account_id;
		this.account_balance = account_balance;
		this.active = active;
	}

	public BankAccount() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getAccount_balance() {
		return account_balance;
	}

	public void setAccount_balance(double account_balance) {
		this.account_balance = account_balance;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(account_balance);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + (active ? 1231 : 1237);
		result = prime * result + id;
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
		BankAccount other = (BankAccount) obj;
		if (Double.doubleToLongBits(account_balance) != Double.doubleToLongBits(other.account_balance))
			return false;
		if (active != other.active)
			return false;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Account ID: " + id + ", Balance: " + account_balance + ", Active: " + active ;
	}

	
}
