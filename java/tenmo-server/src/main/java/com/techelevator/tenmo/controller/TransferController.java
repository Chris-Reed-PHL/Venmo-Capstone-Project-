package com.techelevator.tenmo.controller;

import java.util.List;

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
public class TransferController {
	
	private TransfersDAO dao;
	private UserDAO userDao;
	
	public TransferController (TransfersDAO dao, UserDAO userDao ) {
		
		this.dao = dao;
		this.userDao = userDao;
	}
	
	@RequestMapping(path = "/send", method = RequestMethod.POST)
	public void sendTransfer( @RequestBody Transfers transfer) {
		dao.sendBucks(transfer);

}
	@RequestMapping(path = "/request", method = RequestMethod.POST)
	public boolean requestTransfer(@RequestBody Transfers transfer) {
		return dao.requestBucks(transfer);
	
}
	@RequestMapping(path="/users", method = RequestMethod.GET)
	public List<User> transferUsers() {
		return userDao.findAll();
	}
	
	@RequestMapping(path = "/history/{userId}", method = RequestMethod.GET)
	public List<Transfers> transferHistory(@PathVariable long userId){
		return dao.viewTransferHistory(userId);
	}
}