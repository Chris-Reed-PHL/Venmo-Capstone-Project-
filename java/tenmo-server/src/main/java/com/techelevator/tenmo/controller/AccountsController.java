package com.techelevator.tenmo.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountsDAO;
import com.techelevator.tenmo.model.User;
import com.techelevator.tenmo.model.Accounts;
@RestController
@RequestMapping("accounts")
@PreAuthorize("isAuthenticated()")
public class AccountsController {
	
	private AccountsDAO dao;
	
	public AccountsController(AccountsDAO dao) {
		this.dao = dao;
		
	}
	
	@RequestMapping(path = "/{userId}", method = RequestMethod.GET)
	public Accounts viewCurrentBalance(@PathVariable long userId) {
		return dao.viewCurrentBalance(userId);
	}

}
