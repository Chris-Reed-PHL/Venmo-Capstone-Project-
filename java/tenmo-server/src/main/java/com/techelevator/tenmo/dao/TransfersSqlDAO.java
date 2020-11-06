package com.techelevator.tenmo.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.techelevator.tenmo.model.Transfers;

@Service
public class TransfersSqlDAO implements TransfersDAO{

	private JdbcTemplate jdbcTemplate;

	public TransfersSqlDAO(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}



	//swap user_id / accountFrom & accountTo
	@Override
	public boolean sendBucks(Transfers transfer) {
//		Transfers sendTransfer = null;
		boolean updateResult =  false;
		String SqlSendTransfer =  "BEGIN TRANSACTION;"
				+ " INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount)"
				+ " VALUES(DEFAULT, (SELECT transfer_type_id FROM transfer_types WHERE transfer_type_desc = 'Send'),"
				+ " (SELECT transfer_status_id FROM transfer_statuses WHERE transfer_status_desc = 'Approved'),"
				+ " (SELECT account_id FROM accounts WHERE user_id= ?),"
				+ " (SELECT account_id FROM accounts WHERE user_id= ?), ?);" 
				+ " UPDATE accounts SET balance = balance + ? WHERE account_id = ?;"
				+ " UPDATE accounts SET balance = balance - ? WHERE account_id = ?;"
				+ " COMMIT";
		

		int result = jdbcTemplate.update(SqlSendTransfer,transfer.getAccountFrom(), transfer.getAccountTo(),transfer.getAmount(), transfer.getAmount(),transfer.getAccountTo(),transfer.getAmount(), transfer.getAccountFrom());

		if(result == 0) {
			updateResult = true;
		}
		return updateResult;
		
		
	}
	@Override
	public boolean requestBucks(Transfers transfer) {
//		Transfers requestTransfer = null;
		boolean updateResult =  false;
		String SqlrequestTransfer =  "BEGIN TRANSACTION;"
				+ " INSERT INTO transfers (transfer_id, transfer_type_id, transfer_status_id, account_from, account_to, amount)"
				+ " VALUES(DEFAULT, (SELECT transfer_type_id FROM transfer_types WHERE transfer_type_desc = 'Request'),"
				+ " (SELECT transfer_status_id FROM transfer_statuses WHERE transfer_status_desc = 'Pending'),"
				+ " (SELECT account_id FROM accounts WHERE user_id= ?),"
				+ " (SELECT account_id FROM accounts WHERE user_id= ?), ?);" 
				+ " UPDATE accounts SET balance = balance + ? WHERE account_id = ?;"
				+ " UPDATE accounts SET balance = balance - ? WHERE account_id = ?;"
				+ " COMMIT";

		int result = jdbcTemplate.update(SqlrequestTransfer, transfer.getAccountTo(), transfer.getAccountFrom(), transfer.getAmount(), transfer.getAmount(), transfer.getAccountFrom(), transfer.getAmount(), transfer.getAccountTo());
		
		if(result == 0) {
			updateResult = true;
		}
		return updateResult;
	}
	
	

	private Transfers mapToTransfers(SqlRowSet t) {

		Transfers transfer = new Transfers();

		transfer.setTransferId(t.getLong("transfer_id"));
		transfer.setTransferTypeId(t.getInt("transfer_type_id"));
		transfer.setTransferStatusId(t.getInt("transfer_status_id"));
		transfer.setAccountFrom(t.getInt("account_from"));
		transfer.setAccountTo(t.getInt("account_to"));
		transfer.setAmount(t.getBigDecimal("amount"));
		return transfer;
	}






	








}
