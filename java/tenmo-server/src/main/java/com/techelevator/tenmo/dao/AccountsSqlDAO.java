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
	public double viewCurrentBalance(int accountId, int userId) {
		double currentBalance = 0;
		
		String SqlViewCurrentBalance = "SELECT balance FROM accounts WHERE account_id= ? AND user_id = ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(SqlViewCurrentBalance, accountId, userId);
		while(result.next()){
			Accounts ac = mapToAccounts(result);
			currentBalance = ac.getBalance();
		}
		
		return currentBalance ;
	}

	
	private Accounts mapToAccounts(SqlRowSet ars) {
		Accounts ac =  new Accounts();
		ac.setAccountId(ars.getInt("accountId"));
		ac.setUserId(ars.getInt("userId"));
		ac.setBalance(ars.getDouble("balance"));
		return ac;
		
		
	}
}
