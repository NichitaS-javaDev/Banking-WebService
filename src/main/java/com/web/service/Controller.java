package com.web.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Controller {
	@Autowired
	Data data;
	
	@SuppressWarnings("unchecked")
	@GetMapping("/account")
	public String getAccount(@RequestParam(value = "id", required = true) int id) {
		Account account = data.getData().get(id);
		
		JSONObject acc = new JSONObject();
		acc.put("iban", account.getIban());
		acc.put("balance", account.getBalance() + " " + account.getCurrency());
		acc.put("last operation time", account.getLastOperationTime());
		
		return acc.toString();
	}
	
	@SuppressWarnings("unchecked")
	@GetMapping("/accounts")
	public String getAccounts() {
		JSONArray account_list = new JSONArray();
		
		for(Account acc : data.getData().values()) {
			JSONObject account = new JSONObject();
			account.put("iban", acc.getIban());
			account.put("balance", acc.getBalance() + " " + acc.getCurrency());
			account.put("last operation time", acc.getLastOperationTime());
			
			account_list.add(account);
		}
		return account_list.toString();
	}

}
