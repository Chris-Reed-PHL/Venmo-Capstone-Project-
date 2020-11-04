package com.techelevator.tenmo.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;

@RestController
@RequestMapping("accounts")
public class AccountsController {
	
	private AccountsDAO dao;
	
	public AccountsController() {
		this.dao = dao;
		
	}
	
	@RequestMapping(path = "", method = RequestMethod.GET)
	public double viewCurrentBalance(@PathVariable int accountId) {
		return dao.viewCurrentBalance(accountId);
	}

}
