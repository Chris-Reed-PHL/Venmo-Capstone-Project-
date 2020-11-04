package com.techelevator.tenmo.models;

public class Accounts {

	private int accountId;
	private int userId;
	private double balance;
	
	
	public Accounts( int accountId, int userId) {
		this.accountId =  accountId;
		this.userId = userId;
		balance = 1000;
	}
	
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Accounts [accountId=" + accountId + ", userId=" + userId + ", balance=" + balance + "]";
	}
	
}
