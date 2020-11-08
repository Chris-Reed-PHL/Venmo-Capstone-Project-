package com.techelevator.tenmo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.TransfersDAO;
import com.techelevator.tenmo.dao.UserDAO;
import com.techelevator.tenmo.model.Transfers;
import com.techelevator.tenmo.model.User;


@RestController
@RequestMapping("transfers")
@PreAuthorize("isAuthenticated()")
public class TransferController {
	
	private TransfersDAO dao;
	private UserDAO userDao;
	
	public TransferController (TransfersDAO dao, UserDAO userDao ) {
		
		this.dao = dao;
		this.userDao = userDao;
	}
	
	@RequestMapping(path = "/send", method = RequestMethod.POST)
	public void sendTransfer(@Valid @RequestBody Transfers transfer) {
		dao.sendBucks(transfer);

}
	@RequestMapping(path = "/request", method = RequestMethod.POST)
	public boolean requestTransfer(@Valid @RequestBody Transfers transfer) {
		return dao.requestBucks(transfer);
	
}
	@RequestMapping(path="/users", method = RequestMethod.GET)
	public List<User> transferUsers() {
		return userDao.findAll();
	}
	

	
	@RequestMapping(path = "/history/{userId}", method = RequestMethod.GET)
	public List<Transfers> transferHistoryWithUserName(@PathVariable long userId, String userName){
		return dao.viewTransferHistoryWithUserNames(userId, userName);
	}	
	
	@RequestMapping(path ="/details/{transferId}", method = RequestMethod.GET)
	public Transfers transferdetails(@PathVariable long transferId) {
		return dao.details(transferId);
	}
	
	@RequestMapping(path ="/todetails/{transferId}", method = RequestMethod.GET)
	public Transfers toTransferdetails(@PathVariable long transferId) {
		return dao.toDetails(transferId);
	}
}