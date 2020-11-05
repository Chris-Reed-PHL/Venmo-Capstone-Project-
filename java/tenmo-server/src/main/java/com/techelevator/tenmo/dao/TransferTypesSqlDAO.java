package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;


import com.techelevator.tenmo.model.TransferTypes;

public class TransferTypesSqlDAO implements TransferTypesDAO {

	
	private JdbcTemplate jdbcTemplate;
	   
	   public TransferTypesSqlDAO(JdbcTemplate jdbcTemplate) {
		   this.jdbcTemplate = jdbcTemplate;
	   }
	
	
	@Override
	public TransferTypes sendBucks() {
		
		
		
		return null;
	}

	@Override
	public TransferTypes requestBucks() {
		// TODO Auto-generated method stub
		return null;
	}

	
	private TransferTypes mapToTransferTypes(SqlRowSet tt) {
		TransferTypes transferType =  new TransferTypes();
		transferType.setTransferTypesId(tt.getLong("transfer_type_id"));
		transferType.setTransferTypeDescription(tt.getString("transfer_type_desc"));
		
		return transferType;
		
		
	}
	
}
