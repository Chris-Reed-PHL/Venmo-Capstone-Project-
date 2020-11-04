package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import com.techelevator.tenmo.model.Accounts;


public class AccountsSqlDAO implements AccountsDAO {

	   private JdbcTemplate jdbcTemplate;
	   
	   public AccountsSqlDAO(JdbcTemplate jdbcTemplate) {
		   this.jdbcTemplate = jdbcTemplate;
	   }
	
	
	
	
	@Override
	public double viewCurrentBalance(int accountId) {
		double currentBalance = 0;
		
		String SqlViewCurrentBalance = "SELECT balance FROM accounts WHERE account_id= ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(SqlViewCurrentBalance, accountId);
		while(result.next()){
			Accounts ac = mapToAccounts(result);
			currentBalance = ac.getBalance();
		}
		
		return currentBalance;
	}

	
	private Accounts mapToAccounts(SqlRowSet ars) {
		Accounts ac =  new Accounts();
		ac.setAccountId(ars.getInt("account_id"));
		ac.setUserId(ars.getInt("user_id"));
		ac.setBalance(ars.getDouble("balance"));
		return ac;
		
		
	}
}
