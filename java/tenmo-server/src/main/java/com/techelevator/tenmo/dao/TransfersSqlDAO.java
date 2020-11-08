package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

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



	@Override
	public List<Transfers> viewTransferHistoryWithUserNames(Long userId, String userName) {
		List<Transfers> transactions =  new ArrayList<>();
		Transfers transfer = null;

		String viewTransferHistoryWithUserNames =  "SELECT * FROM transfers t JOIN accounts a ON t.account_from = a.account_id  JOIN users u ON u.user_id = a.user_id WHERE t.account_from = ? OR t.account_to = ?"
				+ " UNION"
				+ " SELECT * FROM transfers t  JOIN accounts a ON t.account_to = a.account_id  JOIN users u ON u.user_id = a.user_id WHERE t.account_to = ? OR t.account_from = ? ORDER BY transfer_id";

		SqlRowSet spending = jdbcTemplate.queryForRowSet(viewTransferHistoryWithUserNames, userName, userId, userName, userId );

		while(spending.next()) {
			transfer = mapToTransfers(spending);
			transactions.add(transfer);
		}

		return transactions;
	}

	@Override
	public Transfers details(Long transferId) {

		Transfers transfer = null;

		String SqltransferDetails = "SELECT * FROM transfers t JOIN accounts a ON t.account_from = a.account_id  JOIN users u ON u.user_id = a.user_id  WHERE transfer_id =?";

		SqlRowSet deets = jdbcTemplate.queryForRowSet(SqltransferDetails, transferId);

		while(deets.next()) {
			transfer = mapToTransfers(deets);
		}
		return transfer;
	}
	@Override
	public Transfers toDetails(Long transferId) {
		Transfers transfer = null;

		String SqltransferDetails = "SELECT * FROM transfers t JOIN accounts a ON t.account_to = a.account_id  JOIN users u ON u.user_id = a.user_id  WHERE transfer_id =?";

		SqlRowSet deets = jdbcTemplate.queryForRowSet(SqltransferDetails, transferId);

		while(deets.next()) {
			transfer = mapToTransfers(deets);
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
		transfer.setAmount(t.getBigDecimal("amount"));
		transfer.setUserName(t.getString("username"));
		return transfer;
	}



}
