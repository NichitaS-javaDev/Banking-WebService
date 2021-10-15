package com.web.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

@Component
public class Data {

	public CallableStatement createStatement() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
			
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mobile_banking", "root", "root");
		    
		    CallableStatement statement = conn.prepareCall("call sp_GetAccounts()");
		    
		    statement.execute();
		    
		    return statement;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	public HashMap<Integer, Account> getData() {
		try {	
			HashMap<Integer, Account> accountMap = new HashMap<>();
			
		    ResultSet result = createStatement().getResultSet();
		    
		    while (result.next()) {
				Account account = new Account(
						result.getString("iban"), result.getInt("balance"),
						result.getString("currency"), result.getString("last_operation_time")
						);
				accountMap.put(result.getInt("id"), account);
			}
		    
		    return accountMap;
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    return null;
	}
}
