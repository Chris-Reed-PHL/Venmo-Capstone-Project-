package com.techelevator.tenmo.dao;


import java.util.List;

import com.techelevator.tenmo.model.Transfers;

public interface TransfersDAO {

	
	boolean sendBucks(Transfers transfer);
	
	boolean requestBucks(Transfers transfer);
	
	List<Transfers> viewTransferHistory(Long userId);
	
	
	
	
}
