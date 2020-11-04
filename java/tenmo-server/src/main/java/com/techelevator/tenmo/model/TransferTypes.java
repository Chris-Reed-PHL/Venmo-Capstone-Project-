package com.techelevator.tenmo.model;

public class TransferTypes {
	private int transferTypesId;
	private String transferTypeDescription;

	
	public TransferTypes( int transferTypesId, String transferTypeDescription) {
		
		this.transferTypesId = transferTypesId;
		this.transferTypeDescription = transferTypeDescription;
}


	public int getTransferTypesId() {
		return transferTypesId;
	}


	public void setTransferTypesId(int transferTypesId) {
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
