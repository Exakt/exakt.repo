package com.gsis.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.gsis.bom.Transaction;
import com.gsis.dao.DBConnection;

public class TransactionInformation {
	private int noOfRecords;

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public void setNoOfRecords(int noOfRecords) {
		this.noOfRecords = noOfRecords;
	}

	public ArrayList<Transaction> getTransactions(int offset, int noOfRecords, String stat) {  

		Connection conn = null;
		PreparedStatement stmt = null;      
		String sql;
		
		ArrayList<Transaction> transactionList = new ArrayList<Transaction>();    

		try {       
			DBConnection dbc = new DBConnection();
			conn = dbc.getConnection();

			sql = "Select SQL_CALC_FOUND_ROWS referenceNo, description, dateFiled, status from tms where status = ? limit " + offset + ", " + noOfRecords; 
			
			stmt = conn.prepareStatement(sql);

			stmt.setString(1, stat);
			
			System.out.println(sql);
			ResultSet rs = stmt.executeQuery();  
			
			while(rs.next()){ 
				Transaction transaction = new Transaction();
				transaction.setReferenceNo(rs.getString("referenceNo").trim());
				transaction.setDescription(rs.getString("description").trim());
				transaction.setDateFiled(rs.getString("dateFiled").trim());
				transaction.setStatus(rs.getString("status").trim());
				transactionList.add(transaction);
			}                                                                          

			rs.close();       
			rs = stmt.executeQuery("SELECT FOUND_ROWS()");
			if(rs.next())
				this.noOfRecords = rs.getInt(1);

			stmt.close();                                                              
			stmt = null;                                                               


			conn.close();                                                              
			conn = null;                                                    

		}                                                                
		catch(Exception e){
			e.printStackTrace();
		}                       

		finally {                                                        

			if (stmt != null) {                                             
				try {                                                          
					stmt.close();                                                 
				} catch (SQLException sqlex) {                                 
					// ignore -- as we can't do anything about it here            
				}                                                              

				stmt = null;                                             
			}                                                         

			if (conn != null) {                                       
				try {                                                    
					conn.close();                                           
				} catch (SQLException sqlex) {                           
					// ignore -- as we can't do anything about it here      
				}                                                        

				conn = null;                                             
			}                                                         
		}               

		return transactionList;

	}  
}
