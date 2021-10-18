package com.web.service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "database")
public class Data {
	private String driver;
	private String url;
	private String username;
	private String password;
	
	public void setDriver(String driver) {
		this.driver = driver;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	@SuppressWarnings("finally")
	private Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName(driver).getDeclaredConstructor().newInstance();
			
			connection = DriverManager.getConnection(url,username,password);
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return connection;
		}
	}

	@SuppressWarnings("finally")
	private CallableStatement createStatement() {
		CallableStatement statement = null;
		try {
		    statement = getConnection().prepareCall("call sp_GetAccounts()");
		    
		    statement.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return statement;
		}
	}
	
	@SuppressWarnings("finally")
	private CallableStatement createStatement(int id) {
		CallableStatement statement = null;
		try {
		    statement = getConnection().prepareCall("call sp_GetAccountById(?)");
		    
		    statement.setInt(1,id);
		    
		    statement.execute();
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return statement;
		}
	}
	
	@SuppressWarnings("finally")
	protected Account getAccountById(int id) {
		Account account = null;
		try {
			ResultSet result = createStatement(id).getResultSet();
			
			if(result.next()) {
				account = new Account(result.getString("iban"), result.getInt("balance"),
						result.getString("currency"), result.getString("last_operation_time")
				);
			}
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			return account;
		}
	}
	
	@SuppressWarnings("finally")
	protected List<Account> getAllAccounts() {
		List<Account> accountList = null;
		try {	
			accountList = new ArrayList();
			
		    ResultSet result = createStatement().getResultSet();
		    if(result.next()) {
		    	do {
		    		Account account = new Account(
							result.getString("iban"), result.getInt("balance"),
							result.getString("currency"), result.getString("last_operation_time")
					);
					accountList.add(account);
		    	} while(result.next());
		    }
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return accountList;
		}
	}
}