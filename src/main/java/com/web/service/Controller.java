package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	AccountService accountService;
	
	@GetMapping("/account")
	public String getAccount(@RequestParam(value = "id", required = true) int id) {
		if(accountService.getJSONAccount(id) != null) {
			return accountService.getJSONAccount(id);
		} else {
			return "Invalid ID";
		}
	}
	
	@GetMapping("/accounts")
	public String getAccounts() {
		if(accountService.getJSONAccounts() != null) {
			return accountService.getJSONAccounts(); 
		} else {
			return "Sorry... Something Went Wrong";
		}
	}
}
