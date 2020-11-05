package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;

public interface TransfersDAO {

	
//	Transfers sendBucks(Transfers transfer);
//	
//	Transfers requstBucks(Transfers transfer);
	
	Transfers createTransfer(int accountTo, double amount, int userId);
	
	
	
}
