package com.web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	Data data;
	
	@GetMapping("/account")
	public String getAccount(@RequestParam(value = "id", required = true) int id) {
		Account account = data.getData().get(id);
		return "IBAN : " + account.getIban() + " | " + "Balance : " + account.getBalance() + " " + account.getCurrency() + 
				" | " + "Last Operation Time : " + account.getLastOperationTime();
	}
	
	@GetMapping("/accounts")
	public String getAccounts() {
		String accounts_list = "";
		for(Account acc : data.getData().values()) {
			accounts_list = accounts_list.concat(
					" >>>>> IBAN : " + acc.getIban() + " | " + "Balance : " + acc.getBalance() + " " + acc.getCurrency() + 
					" | " + "Last Operation Time : " + acc.getLastOperationTime() + " <<<<< "
					);
		}
		return accounts_list;
	}

}
