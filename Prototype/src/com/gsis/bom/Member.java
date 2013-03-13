package com.gsis.bom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.gsis.dao.DBConnection;

public class Member {
	
	Connection con = null;
	PreparedStatement pstm = null;
	ResultSet rs = null;
	DBConnection dbc = new DBConnection();
	String sql;
	Statement stm;
	
	private String bp_no;
	private String first_name;
	private String last_name;
	private String email;
	private String place;
	private String question1;
	private String answer1;
	private String question2;
	private String answer2;
	private String iniital_password;
	Questions questions;
	
	 public static int count, random, intOrChar, upperOrLower;
	 public static char myChar;
	 public static String lowerCase, password, finalPasswd;
	    
	public Member() {
		super();
		questions = new Questions();
	}
	public String getBpNumber() {
		return bp_no;
	}
	public void setBpNumber(String bp_no) {
		this.bp_no = bp_no;
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
		return place;
	}
	public void setAddress(String place) {
		this.place = place;
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
	public String getPassword() {
		return iniital_password;
	}
	public void setPassword(String iniital_password) {
		this.iniital_password = iniital_password;
	}
	public boolean checkMemberExist(String bp_no, String fname, String lname){
		try{

			DBConnection dbc = new DBConnection();
			con = dbc.getConnection();

			String query = "SELECT * from tblmember where bp_no = ? and first_name = ? and last_name = ?";

			pstm = con.prepareStatement(query);

			pstm.setString(1, bp_no);
			pstm.setString(2, fname);
			pstm.setString(3, lname);


			rs = pstm.executeQuery();

			if(rs.next()){
				this.setBpNumber(rs.getString("bp_no"));
				this.setFirstName(rs.getString("first_name"));
				this.setLastName(rs.getString("last_name"));
				this.setEmail(rs.getString("email"));
				this.setAddress(rs.getString("place"));

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
				con.close();
			}catch(Exception e){
			}
		}
	}
	
	public boolean checkMemberRegistration(String bp_no){
		try{

			DBConnection dbc = new DBConnection();
			con = dbc.getConnection();

			String query = "SELECT * from tblregistered where bp_no = ?";

			pstm = con.prepareStatement(query);

			pstm.setString(1, bp_no);


			rs = pstm.executeQuery();

			if(rs.next()){
				this.setBpNumber(rs.getString("bp_no"));
				this.setFirstName(rs.getString("first_name"));
				this.setLastName(rs.getString("last_name"));
				this.setEmail(rs.getString("email"));
				this.setAddress(rs.getString("place"));
				this.setPassword(rs.getString("password"));

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
				con.close();
			}catch(Exception e){
			}
		}
	}
	
	public boolean register(String bp_no, String fname, String lname, String eadd, String place, String question1, String answer1, String question2, String answer2){
		try{
			con = dbc.getConnection();
			if (con.isClosed()){
				System.out.println("closed");
			}
			
			String pwd = this.generatePassword();;
			
			sql = "INSERT INTO tblregistered(bp_no, first_name, last_name, email, place, question1, answer1, question2, answer2, password) VALUES( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			pstm = con.prepareStatement(sql);

			pstm.setString(1, bp_no);
			pstm.setString(2, fname);
			pstm.setString(3, lname);
			pstm.setString(4, eadd);
			pstm.setString(5, place);
			pstm.setString(6, question1);
			pstm.setString(7, answer1);
			pstm.setString(8, question2);
			pstm.setString(9, answer2);
			pstm.setString(10, pwd);

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
				con.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

	public boolean getMember(String bp_no){
		try{

			DBConnection dbc = new DBConnection();
			con = dbc.getConnection();

			String query = "SELECT * from tblmember where bp_no = ?";

			pstm = con.prepareStatement(query);

			pstm.setString(1, bp_no);


			rs = pstm.executeQuery();

			if(rs.next()){
				this.setBpNumber(rs.getString("bp_no"));
				this.setFirstName(rs.getString("first_name"));
				this.setLastName(rs.getString("last_name"));
				this.setEmail(rs.getString("email"));
				this.setAddress(rs.getString("place"));

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
				con.close();
			}catch(Exception e){
			}
		}
	}
	
	public String generatePassword(){
		try{
		    while (count < 8) {
		    	 
	            // Integer or character
	            intOrChar = (int) (Math.random() * 2);
	 
	            // if 0 then Integer
	            if (intOrChar == 0) {
	 
	                // Print random number 0-9
	                random = (int) (Math.random() * 9);
	                //System.out.print(random);
	                password = Integer.toString(random) + password;
	 
	                // if 1 then character
	            } else if (intOrChar == 1) {
	 
	                // decide lower or upper case
	                upperOrLower = (int) (Math.random() * 2);
	 
	                // if 0 then upper case
	                if (upperOrLower == 0) {
	 
	                    // Print random Capital char
	                    random = (int) (Math.random() * 74);
	                    myChar = (char) (random + '0');
	                    //System.out.print(myChar);
	                    password = Character.toString(myChar) + password;
	 
	                    // if 1 then lower case
	                } else if (upperOrLower == 1) {
	 
	                    // Print random Small char
	                    random = (int) (Math.random() * 74);
	                    myChar = (char) (random + '0');
	                    lowerCase = Character.toString(myChar).toLowerCase() + password;
	                    //System.out.print(lowerCase);
	                    password = lowerCase;
	 
	                }
	 
	            }
	 
	            count++;
	        }
	        finalPasswd = password.replace("null", "");
	        System.out.println(finalPasswd);
	        
	        return finalPasswd;
		}catch(Exception e){
			e.printStackTrace();
		}
		return finalPasswd;
	}

	public boolean checkMemberExistBP(String bp_no){
		try{

			DBConnection dbc = new DBConnection();
			con = dbc.getConnection();

			String query = "SELECT * from tblmember where bp_no = ?";

			pstm = con.prepareStatement(query);

			pstm.setString(1, bp_no);

			rs = pstm.executeQuery();

			if(rs.next()){
				this.setBpNumber(rs.getString("bp_no"));
				this.setFirstName(rs.getString("first_name"));
				this.setLastName(rs.getString("last_name"));
				this.setEmail(rs.getString("email"));
				this.setAddress(rs.getString("place"));

				return true;
			}

			return false;
		}catch(Exception ex){
			System.out.println(ex.getMessage()+ " checkMemberBPError");
			return false;
		}finally{
			try{
				rs.close();
				pstm.close();
				con.close();
			}catch(Exception e){
			}
		}
	}

	public boolean getRegisteredMember(String bp_no){
		try{

			DBConnection dbc = new DBConnection();
			con = dbc.getConnection();

			String query = "SELECT * from tblregistered where bp_no = ?";

			pstm = con.prepareStatement(query);

			pstm.setString(1, bp_no);


			rs = pstm.executeQuery();

			if(rs.next()){
				this.setBpNumber(rs.getString("bp_no"));
				this.setFirstName(rs.getString("first_name"));
				this.setLastName(rs.getString("last_name"));
				this.setEmail(rs.getString("email"));
				this.setAddress(rs.getString("place"));
				this.setPassword(rs.getString("password"));
				this.setQuestion1(rs.getString("question1"));
				this.setQuestion2(rs.getString("question2"));
				this.setAnswer1(rs.getString("answer1"));
				this.setAnswer2(rs.getString("answer2"));
				return true;
			}

			return false;
		}catch(Exception ex){
			System.out.println(ex.getMessage()+ "checkRegisteredMemberError");
			return false;
		}finally{
			try{
				rs.close();
				pstm.close();
				con.close();
			}catch(Exception e){
			}
		}
	}
	
	public boolean updatePassword(String bp_no){
		try{
			con = dbc.getConnection();
			if (con.isClosed()){
				System.out.println("closed");
			}
			
			String pwd = this.generatePassword();;
			
			sql = "UPDATE tblregistered SET password =? WHERE bp_no = ?";
			
			pstm = con.prepareStatement(sql);

			pstm.setString(1, pwd);
			pstm.setString(2, bp_no);

			int count = pstm.executeUpdate();

			if(count==1){

				//System.out.println(sql);
				return true;

			}else{

				return false;
			}

		}catch(Exception ex){
			System.out.println(ex.getMessage() + " updatePasswordError");
			return false;
		}
		finally{
			try{
				pstm.close();
				con.close();
			}catch(Exception ex){
				ex.printStackTrace();
			}
		}
	}

}
