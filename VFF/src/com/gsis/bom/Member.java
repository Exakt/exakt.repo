package com.gsis.bom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.gsis.dao.ConnectionPoolManager;

public class Member {

	private int bp;
	private int id;
	private int crn;
	
	private String birthdate;
	private String place;
	private String email;
	private String contactNo;
	private String firstName;
	private String lastName;
	private String password;
	
	public Member(){
		
	}
	
	public int getBp() {
		return bp;
	}
	
	public void setBp(int bp) {
		this.bp = bp;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getCrn() {
		return crn;
	}
	
	public void setCrn(int crn) {
		this.crn = crn;
	}
	
	public String getBirthdate() {
		return birthdate;
	}
	
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	
	public String getPlace() {
		return place;
	}
	
	public void setPlace(String place) {
		this.place = place;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getContactNo() {
		return contactNo;
	}
	
	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public boolean login(String username, String password){
		
		String query = "SELECT * from tblregistered where bp_no = ? and password = ?";
		
		try{
			
			Connection con = ConnectionPoolManager.getConnection();
			PreparedStatement pstm = con.prepareStatement(query);
			pstm.setString(1, username);
			pstm.setString(2, password);
			
			ResultSet rs = pstm.executeQuery();
			
			if(rs.next()){
				
				this.bp = rs.getInt("bp_no");
				this.id = rs.getInt("id_no");
				this.crn = rs.getInt("crn_no");
				
				this.birthdate = rs.getDate("birthdate").toString();
				this.place = rs.getString("place");
				this.contactNo = rs.getString("contact_no");
				this.email = rs.getString("email");
				this.firstName = rs.getString("first_name");
				this.lastName = rs.getString("last_name");
				this.password = rs.getString("password");
				
				return true;
			}
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}
		
		return false;
	}
}
