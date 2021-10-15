package com.web.service;

public class Account {
	private String iban;
	private int balance;
	private String currency;
	private String lastOperationTime;
	
	public Account() {}

	public Account(String iban, int balance, String currency, String lastOperationTime) {
		this.iban = iban;
		this.balance = balance;
		this.currency = currency;
		this.lastOperationTime = lastOperationTime;
	}

	public String getIban() {
		return iban;
	}

	public int getBalance() {
		return balance;
	}

	public String getCurrency() {
		return currency;
	}

	public String getLastOperationTime() {
		return lastOperationTime;
	}
	
}
