package com.techelevator.tenmo.models;

import java.math.BigDecimal;

public class Transfers {

	private Integer transferId;
//	private Integer transferTypeId;
//	private Integer transferStatusId;
	private Integer accountFrom;
	private Integer accountTo;
	private BigDecimal amount;
	
	public Transfers () {
//	transferTypeId = 2;
//	transferStatusId = 2;
		
	}

	public int getTransferId() {
		return transferId;
	}

	public void setTransferId(Integer transferId) {
		this.transferId = transferId;
	}

//	public int getTransferTypeId() {
//		return transferTypeId;
//	}
//
//	public void setTransferTypeId(Integer transferTypeId) {
//		this.transferTypeId = transferTypeId;
//	}
//
//	public int getTransferStatusId() {
//		return transferStatusId;
//	}
//
//	public void setTransferStatusId(Integer transferStatusId) {
//		this.transferStatusId = transferStatusId;
//	}

	public int getAccountFrom() {
		return accountFrom;
	}

	public void setAccountFrom(Integer accountFrom) {
		this.accountFrom = accountFrom;
	}

	public int getAccountTo() {
		return accountTo;
	}

	public void setAccountTo(Integer accountTo) {
		this.accountTo = accountTo;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	@Override
	public String toString() {
		return "Transfers [transferId=" +  ", transferTypeId=" +  ", transferStatusId="
				+  ", accountFrom=" + accountFrom + ", accountTo=" + accountTo + ", amount=" + amount
				+ "]";
	}
	
}
