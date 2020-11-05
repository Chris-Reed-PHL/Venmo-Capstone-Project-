package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Accounts;

public interface AccountsDAO {

	Accounts viewCurrentBalance(long userId);
	
	
}
