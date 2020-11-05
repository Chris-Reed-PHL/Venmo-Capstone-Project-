package com.techelevator.tenmo.controller;
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
	
	@RequestMapping(path = "/", method = RequestMethod.PUT)
	public Transfers createTransfer(@RequestBody )

}
