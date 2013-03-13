package com.gsis.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

	private Connection con;

	public DBConnection(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
			this.con = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_gsis","root","password");
			
			//Class.forName("oracle.jdbc.driver.OracleDriver");
			//this.con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","tem","tem");
		}catch(Exception ex){
			ex.printStackTrace();
			System.out.println("db error");
		}
	}

	public void setCon(Connection con) {
		this.con = con;
	}

	public Connection getConnection(){

		if(con == null)
			new DBConnection();
		//System.out.println("connected");
		return this.con;
	}

}
