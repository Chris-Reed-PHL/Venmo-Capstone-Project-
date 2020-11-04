package com.techelevator.tenmo.models;

public class TransferStatuses {

	private int transferStatusId;
	private String transferStatusDescrtiption;
	
	public TransferStatuses( int transferStatusId, String transferStatusDescrtiption) {
		this.transferStatusId =  transferStatusId;
		this.transferStatusDescrtiption = transferStatusDescrtiption;
	}

	public int getTransferStatusId() {
		return transferStatusId;
	}

	public void setTransferStatusId(int transferStatusId) {
		this.transferStatusId = transferStatusId;
	}

	public String getTransferStatusDescrtiption() {
		return transferStatusDescrtiption;
	}

	public void setTransferStatusDescrtiption(String transferStatusDescrtiption) {
		this.transferStatusDescrtiption = transferStatusDescrtiption;
	}

	@Override
	public String toString() {
		return "TransferStatuses [transferStatusId=" + transferStatusId + ", transferStatusDescrtiption="
				+ transferStatusDescrtiption + "]";
	}
	
	
}
