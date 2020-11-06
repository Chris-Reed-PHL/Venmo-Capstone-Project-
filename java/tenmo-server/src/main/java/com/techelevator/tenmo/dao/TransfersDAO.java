package com.techelevator.tenmo.dao;

import com.techelevator.tenmo.model.Transfers;

public interface TransfersDAO {

	
	boolean sendBucks(Transfers transfer);
	
	boolean requestBucks(Transfers transfer);
	
	
	
	
	
	
}
