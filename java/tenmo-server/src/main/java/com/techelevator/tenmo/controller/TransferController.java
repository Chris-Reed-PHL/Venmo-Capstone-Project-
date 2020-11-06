package com.techelevator.tenmo.controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TransfersDAO;
import com.techelevator.tenmo.model.Transfers;


@RestController
@RequestMapping("transfers")
public class TransferController {
	
	private TransfersDAO dao;
	
	public TransferController (TransfersDAO dao ) {
		
		this.dao = dao;
	}
	
	@RequestMapping(path = "/send", method = RequestMethod.POST)
	public boolean sendTransfer( @RequestBody Transfers transfer) {
		return dao.sendBucks(transfer);

}
	@RequestMapping(path = "/request", method = RequestMethod.POST)
	public boolean requestTransfer(@RequestBody Transfers transfer) {
		return dao.requestBucks(transfer);
	
}
}