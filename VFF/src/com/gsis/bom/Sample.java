package com.gsis.bom;

import java.sql.ResultSet;
import java.sql.Statement;

import com.gsis.dao.ConnectionPoolManager;
import com.gsis.dao.JDCConnection;

public class Sample {

	/**
	 * @param args
	 */
	public static void main(String[] args) throws Exception{
		
		JDCConnection con = (JDCConnection)ConnectionPoolManager.getConnection();
		Statement stm = con.createStatement();
		ResultSet rs = stm.executeQuery("SELECT * from tblmember");
		
		while(rs.next()){
			System.out.println(rs.getString("firstName"));
		}
		
	}

}
