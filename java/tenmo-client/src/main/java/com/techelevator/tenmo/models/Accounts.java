package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class Accounts {

	private long accountId;
	private long userId;
	private BigDecimal balance;
	
	
	public Accounts() {
		
	}
	
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	@Override
	public String toString() {
		return "Accounts [accountId=" + accountId + ", userId=" + userId + ", balance=" + balance + "]";
	}
}