package com.web.service;

import org.json.simple.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {
	@Autowired
	private Data data;
	@Autowired
	private Account account;
	
	protected String getJSONAccount(int id) {
		Account acc = data.getAccountById(id);
		
		if(account != null) {
			return account.JSONAccountFactory(acc).toString();
		} else {
			return null;
		}
	}
	
	@SuppressWarnings("unchecked")
	protected String getJSONAccounts() {
		JSONArray accountList = new JSONArray();
		
		if(data.getAllAccounts() != null) {
			for(Account acc : data.getAllAccounts()) {				
				accountList.add(account.JSONAccountFactory(acc));
			}
			
			return accountList.toString();
		} else {
			return null;
		}
	}
}
