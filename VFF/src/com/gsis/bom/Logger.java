package com.gsis.bom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import com.gsis.dao.ConnectionPoolManager;

public class Logger {
	
	public static final int LOGIN_ID = 1; 
	public static final int LOGOUT_ID = 2; 
	public static final int LOCKED_ID = 3; 
	
	private String path = "";
	
	protected Properties sqlConfig; //used for initiating SQL Config Properties file 
	
	public Logger(){
		super();
	}
	
	public Logger(String path){
		this.path = path;
	}
	
	public void setPath(String path){
		this.path = path;
	}
	
	public void setPropertiesFile(Properties props){
		
	}
	
	public Properties getPropertiesFile(){
		return new Properties();
	}
	
	public String getPath(){
		return this.path;
	}
	
	public void log(int audit_id, int bp){
		
		String query = "INSERT INTO tblaudit(bp_id, date, audit_id) VALUES(?,?,?)";
		
		Connection con = null;
		PreparedStatement pstm2 = null;
		
		try{
			con = ConnectionPoolManager.getConnection();
			pstm2 = con.prepareStatement(query);
			pstm2.setInt(1, bp);
			pstm2.setTimestamp(2, new Timestamp(Calendar.getInstance().getTimeInMillis()));
			pstm2.setInt(3, audit_id);
			
			if(pstm2.executeUpdate() == 1){
				
				switch(audit_id){
					case 1: System.out.println(bp + " is logged in");
							break;
					case 2: System.out.println(bp + " is logged off");
							break;
					case 3: System.out.println(bp + " is locked out");
							break;
				}
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{pstm2.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}
	}
	
	/*
	 * If the user opt to use log file
	 */
	public void log(int audit_id, int bp, String path){
		
		if(!path.equals("")){
			//TODO code for writing to file
		}
	}
	
}
