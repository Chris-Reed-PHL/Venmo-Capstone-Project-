package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.tenmo.model.Accounts;

@Service
public class AccountsSqlDAO implements AccountsDAO {

	   private JdbcTemplate jdbcTemplate;
	   
	   public AccountsSqlDAO(JdbcTemplate jdbcTemplate) {
		   this.jdbcTemplate = jdbcTemplate;
	   }
	
	
	
	
	@Override
	public Accounts viewCurrentBalance(long userId) {
		Accounts account = null;
		
		String SqlViewCurrentBalance = "SELECT a.account_id, a.user_id, a.balance FROM accounts a JOIN users u ON a.user_id = u.user_id WHERE a.user_id= ?";
		
		SqlRowSet result = jdbcTemplate.queryForRowSet(SqlViewCurrentBalance, userId);
		if(result.next()){
			 account = mapToAccounts(result);
			
		}
		
		return account;
	}

	
	private Accounts mapToAccounts(SqlRowSet ars) {
		Accounts account =  new Accounts();
		account.setAccountId(ars.getLong("account_id"));
		account.setUserId(ars.getLong("user_id"));
		account.setBalance(ars.getBigDecimal("balance"));
		return account;
		
		
	}
}
