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

//	@Override
//	public Transfers sendBucks(Transfers transfer) {
//
//		Transfers sendTransfer = null;
//
//		String SqlSendBucks =  "INSERT into transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
//				"VALUES(?,?,?,?,?)";
//
//		jdbcTemplate.update(SqlSendBucks, transferTypeId,transfertStatusId ,accountFrom, accountTo, amount);
//		SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT * FROM transfers WHERE transfer_type_id = ? AND transfer_status_id = ? AND account_from = ? AND account_to = ? AND amount = ?", transferTypeId,transfertStatusId ,accountFrom, accountTo, amount);
//
//		if(row.next()) {
//			transfer = mapToTransfers(row);
//		}
//
//		return sendTransfer;
//	}
//
//	@Override
//	public Transfers requstBucks(Transfers transfer) {
//		Transfers requestTransfer = null;
//
//		String SqlSendBucks =  "INSERT into transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
//				"VALUES(?,?,?,?,?)";
//
//		jdbcTemplate.update(SqlSendBucks, transferTypeId,transfertStatusId ,accountFrom, accountTo, amount);
//		SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT * FROM transfers WHERE transfer_type_id = ? AND transfer_status_id = ? AND account_from = ? AND account_to = ? AND amount = ?", transferTypeId,transfertStatusId ,accountFrom, accountTo, amount);
//
//		if(row.next()) {
//			transfer = mapToTransfers(row);
//		}
//
//		return requestTransfer;
//	}

	//swap user_id / accountFrom & accountTo
	@Override
	public Transfers createTransfer(int accountTo,double amount, int userId) {
		Transfers transfer = null;

		String SqlCreateTransfer =  "INSERT into transfers(transfer_type_id, transfer_status_id, account_from, account_to, amount)" +
				"VALUES(?,?,?,?,?)";

		jdbcTemplate.update(SqlCreateTransfer, accountTo, amount, userId);
		SqlRowSet row = jdbcTemplate.queryForRowSet("SELECT * FROM transfers WHERE account_to = ? AND amount = ? AND account_from = ?", accountTo, amount, userId);

		if(row.next()) {
			transfer = mapToTransfers(row);
		}
		return transfer;
	}

	private Transfers mapToTransfers(SqlRowSet t) {

		Transfers transfer = new Transfers();

		transfer.setTransferId(t.getLong("transfer_id"));
		transfer.setTransferTypeId(t.getInt("transfer_type_id"));
		transfer.setTransferStatusId(t.getInt("transfer_status_id"));
		transfer.setAccountFrom(t.getInt("account_from"));
		transfer.setAccountTo(t.getInt("account_to"));
		transfer.setAmount(t.getDouble("amount"));
		return transfer;
	}






}
