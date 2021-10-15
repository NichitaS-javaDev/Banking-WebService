package com.web.service;

import java.io.FileReader;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.HashMap;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.stereotype.Component;

@Component
public class Data {
	
	Connection getConnectionFromConfigFile() {
		try {
			Object object = new JSONParser().parse(new FileReader("DBConfig.json"));
			
			JSONObject connection_obj = (JSONObject) object;
			
			Class.forName(connection_obj.get("driver manager class").toString()).getDeclaredConstructor().newInstance();
			
			return DriverManager.getConnection(
					connection_obj.get("url").toString(), connection_obj.get("user").toString(), 
					connection_obj.get("password").toString()
					);
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

	CallableStatement createStatement() {
		try {		    
		    CallableStatement statement = getConnectionFromConfigFile().prepareCall("call sp_GetAccounts()");
		    
		    statement.execute();
		    
		    return statement;
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	protected HashMap<Integer, Account> getData() {
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
