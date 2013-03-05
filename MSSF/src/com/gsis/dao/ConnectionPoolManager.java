package com.gsis.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/*
 * @author : Johne Altamera
 */

public class ConnectionPoolManager {
	
	private static JDCConnectionDriver jdcDriver;

	private static Properties sqlConfig;
	
	static {
		try{
			
			sqlConfig = new Properties();
			sqlConfig.load(Thread.currentThread().getContextClassLoader().
							getResourceAsStream("config/sql.config.properties"));
			
			jdcDriver = new JDCConnectionDriver(sqlConfig.getProperty("driver"), 
												sqlConfig.getProperty("url").concat(sqlConfig.getProperty("dbName")), 
												sqlConfig.getProperty("username"), 
												sqlConfig.getProperty("password"), 
												sqlConfig);
			
			System.out.println("OK");
			
		}catch(Exception e){
			e.printStackTrace();
		}
	}

	public static Connection getConnection() 
			throws SQLException{
		
		return jdcDriver.connect(
				"jdbc:jdc:jdcpool", null);
	}
}

