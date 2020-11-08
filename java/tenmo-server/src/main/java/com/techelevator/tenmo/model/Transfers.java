package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfers {
	
	private Long transferId;
	private Integer transferTypeId;
	private Integer transferStatusId;
	private Integer accountFrom;
	private Integer accountTo;
	private BigDecimal amount;
	
	private String userName;
	


	public Transfers () {
	
		
	}

	public long getTransferId() {
		return transferId;
	}

	public void setTransferId(Long transferId) {
		this.transferId = transferId;
	}

	public int getTransferTypeId() {
		return transferTypeId;
	}

	public void setTransferTypeId(Integer transferTypeId) {
		this.transferTypeId = transferTypeId;
	}

	public int getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(Integer transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

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
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Override
	public String toString() {
		return "Transfers [transferId=" + transferId + ", transferTypeId=" + transferTypeId + ", transferStatusId="
				+ transferStatusId + ", accountFrom=" + accountFrom + ", accountTo=" + accountTo + ", amount=" + amount + "userName=" + userName
				+ "]";
	}
	

}
