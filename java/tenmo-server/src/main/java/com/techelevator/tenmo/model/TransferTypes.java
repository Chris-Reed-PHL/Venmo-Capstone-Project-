package com.techelevator.tenmo.model;

public class TransferTypes {
	private long transferTypesId;
	private String transferTypeDescription;

	
	public TransferTypes() {
		
	
}


	public long getTransferTypesId() {
		return transferTypesId;
	}


	public void setTransferTypesId(long transferTypesId) {
		this.transferTypesId = transferTypesId;
	}


	public String getTransferTypeDescription() {
		return transferTypeDescription;
	}


	public void setTransferTypeDescription(String transferTypeDescription) {
		this.transferTypeDescription = transferTypeDescription;
	}


	@Override
	public String toString() {
		return "TransferTypes [transferTypesId=" + transferTypesId + ", transferTypeDescription="
				+ transferTypeDescription + "]";
	}
	
	

}
