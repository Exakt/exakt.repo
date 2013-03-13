package com.gsis.bom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.gsis.dao.ConnectionPoolManager;


public class Member {
	
	//Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	//DBConnection dbc = new DBConnection();
	String sql;
	Statement stm;
	
	private String bpNo;
	private String first_name;
	private String last_name;
	private String email;
	private String address;
	private String question1;
	private String answer1;
	private String question2;
	private String answer2;
	Questions questions;
	
	
	public Member() {
		super();
		questions = new Questions();
	}
	public String getBpNumber() {
		return bpNo;
	}
	public void setBpNumber(String bpNo) {
		this.bpNo = bpNo;
	}
	public String getFirstName() {
		return first_name;
	}
	public void setFirstName(String first_name) {
		this.first_name = first_name;
	}
	public String getLastName() {
		return last_name;
	}
	public void setLastName(String last_name) {
		this.last_name = last_name;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getQuestion1() {
		return question1;
	}
	public void setQuestion1(String question1) {
		this.question1 = question1;
	}
	public String getAnswer1() {
		return answer1;
	}
	public void setAnswer1(String answer1) {
		this.answer1 = answer1;
	}
	public String getQuestion2() {
		return question2;
	}
	public void setQuestion2(String question2) {
		this.question2 = question2;
	}
	public String getAnswer2() {
		return answer2;
	}
	public void setAnswer2(String answer2) {
		this.answer2 = answer2;
	}
	public Questions getQuestions() {
		return questions;
	}
	public void setQuestions(Questions questions) {
		this.questions = questions;
	}
	public boolean checkMemberExist(String bpNo, String fname, String lname){
		try{

			Connection con = ConnectionPoolManager.getConnection();
			//con = dbc.getConnection();

			String query = "SELECT * from tblmember where bpNo = ? and first_name = ? and last_name = ?";

			pstm = con.prepareStatement(query);

			pstm.setString(1, bpNo);
			pstm.setString(2, fname);
			pstm.setString(3, lname);


			rs = pstm.executeQuery();

			if(rs.next()){
				this.setBpNumber(rs.getString("bpNo"));
				this.setFirstName(rs.getString("first_name"));
				this.setLastName(rs.getString("last_name"));
				this.setEmail(rs.getString("emailAddress"));
				this.setAddress(rs.getString("address"));

				return true;
			}

			return false;
		}catch(Exception ex){
			System.out.println(ex.getMessage()+ "checkMemberError");
			return false;
		}finally{
			try{
				rs.close();
				pstm.close();
				//con.close();
			}catch(Exception e){
			}
		}
	}
	
	public boolean checkMemberRegistration(String bpNo){
		try{

			Connection con = ConnectionPoolManager.getConnection();
			//con = dbc.getConnection();

			String query = "SELECT * from tblregistered where bpNo = ?";

			pstm = con.prepareStatement(query);

			pstm.setString(1, bpNo);


			rs = pstm.executeQuery();

			if(rs.next()){
				this.setBpNumber(rs.getString("bpNo"));
				this.setFirstName(rs.getString("first_name"));
				this.setLastName(rs.getString("lastname"));
				this.setEmail(rs.getString("emailAddress"));
				this.setAddress(rs.getString("address"));

				return true;
			}

			return false;
		}catch(Exception ex){
			System.out.println(ex.getMessage()+ "checkMemberRegisterError");
			return false;
		}finally{
			try{
				rs.close();
				pstm.close();
				//con.close();
			}catch(Exception e){
			}
		}
	}
	
	public boolean register(String bpNo, String fname, String lname, String eadd, String address, String question1, String answer1, String question2, String answer2){
		try{
			Connection con = ConnectionPoolManager.getConnection();
			//ddcon = dbc.getConnection();
			if (con.isClosed()){
				System.out.println("closed");
			}
			
			sql = "INSERT INTO tblregistered(bpNo, first_name, last_name, emailAddress, address, question1, answer1, question2, answer2) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstm = con.prepareStatement(sql);

			pstm.setString(1, bpNo);
			pstm.setString(2, fname);
			pstm.setString(3, lname);
			pstm.setString(4, eadd);
			pstm.setString(5, address);
			pstm.setString(6, question1);
			pstm.setString(7, answer1);
			pstm.setString(8, question2);
			pstm.setString(9, answer2);

			int count = pstm.executeUpdate();

			if(count==1){

				//System.out.println(sql);
				return true;

			}else{

				return false;
			}

		}catch(Exception ex){
			System.out.println(ex.getMessage() + " registerError");
			return false;
		}
		finally{
			try{
				pstm.close();
				//con.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	public boolean getMember(String bpNo){
		try{

			Connection con = ConnectionPoolManager.getConnection();
			//con = dbc.getConnection();

			String query = "SELECT * from tblmember where bpNo = ?";

			pstm = con.prepareStatement(query);

			pstm.setString(1, bpNo);


			rs = pstm.executeQuery();

			if(rs.next()){
				this.setBpNumber(rs.getString("bpNo"));
				this.setFirstName(rs.getString("first_name"));
				this.setLastName(rs.getString("last_name"));
				this.setEmail(rs.getString("emailAddress"));
				this.setAddress(rs.getString("address"));

				return true;
			}

			return false;
		}catch(Exception ex){
			System.out.println(ex.getMessage()+ "getMemberError");
			return false;
		}finally{
			try{
				rs.close();
				pstm.close();
			//s	con.close();
			}catch(Exception e){
			}
		}
	}
	
}
