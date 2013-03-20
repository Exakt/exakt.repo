package com.gsis.bom;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.gsis.dao.ConnectionPoolManager;

public class MemberBean {

	final static long MILLIS_PER_DAY = 24 * 3600 * 1000;

	public static final int OK_ID = 0;
	public static final int EXIST_ID = 1;
	public static final int INVALID_ID = 2;
	public static final int INVALID_EMAIL = 3;

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
	private String questionA;
	private String questionB;
	private String answerA;
	private String answerB;

	public MemberBean(){

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

	public String getQuestionA() {
		return questionA;
	}

	public void setQuestionA(String questionA) {
		this.questionA = questionA;
	}

	public String getQuestionB() {
		return questionB;
	}

	public void setQuestionB(String questionB) {
		this.questionB = questionB;
	}

	public String getAnswerA() {
		return answerA;
	}

	public void setAnswerA(String answerA) {
		this.answerA = answerA;
	}

	public String getAnswerB() {
		return answerB;
	}

	public void setAnswerB(String answerB) {
		this.answerB = answerB;
	}
	
	public boolean login(String username, String password){

		String query = "SELECT * from tblregistered where bp_no = ? and password = ?";

		Connection con = null;
		PreparedStatement pstm1 = null;
		ResultSet rs1 = null;

		try{

			con = ConnectionPoolManager.getConnection();
			pstm1 = con.prepareStatement(query);
			pstm1.setString(1, username);
			pstm1.setString(2, password);

			rs1 = pstm1.executeQuery();

			if(rs1.next()){

				this.bp = rs1.getInt("bp_no");
				this.id = rs1.getInt("id_no");
				this.crn = rs1.getInt("crn_no");

				this.birthdate = rs1.getDate("birthdate").toString();
				this.place = rs1.getString("place");
				this.contactNo = rs1.getString("contact_no");
				this.email = rs1.getString("email");
				this.firstName = rs1.getString("first_name");
				this.lastName = rs1.getString("last_name");
				this.password = rs1.getString("password");

				return true;
			}

		}catch(SQLException e){
			e.printStackTrace();
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs1.close();}catch(Exception e){e.printStackTrace();}
			try{pstm1.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return false;
	}

	public int register(int bp, String fname, String lname){

		String query = "SELECT bp_no from tblregistered where bp_no = ?";

		ResultSet rs = null;
		PreparedStatement pstm1 = null;
		PreparedStatement pstm2 = null;
		Connection con = null;

		try{
			con = ConnectionPoolManager.getConnection();
			pstm1 = con.prepareStatement(query);
			pstm1.setInt(1, bp);

			if(pstm1.executeQuery().next()){
				return MemberBean.EXIST_ID;
			}

			query = "SELECT * from tblmember where bp_no = ? and first_name = ? and last_name = ?";
			pstm2 = con.prepareStatement(query);
			pstm2.setInt(1, bp);
			pstm2.setString(2, fname);
			pstm2.setString(3, lname);

			rs = pstm2.executeQuery();

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
				
				if(!Email.isValidEmailAddress(email)){
					return MemberBean.INVALID_EMAIL;
				}
				
				return MemberBean.OK_ID;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{pstm1.close();}catch(Exception e){e.printStackTrace();}
			try{pstm2.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return MemberBean.INVALID_ID;
	}

	public boolean isLocked(int bp_no){
		
		Connection con = null;
		PreparedStatement pstm = null;
		ResultSet rs = null;
		
		try{
			String query = "SELECT * from tblaudit where bp_no = ? and audit_id = 3 order by date desc limit 1";

			con = ConnectionPoolManager.getConnection();
			pstm = con.prepareStatement(query);
			pstm.setInt(1, bp_no);
			rs = pstm.executeQuery();

			if(rs.next()){

				String lastTimestampStr = rs.getString("date");
				String currentTimestampStr = new Timestamp(Calendar.getInstance().getTimeInMillis()).toString();

				String last = lastTimestampStr.substring(0, rs.getString("date").indexOf(".")-1);
				String current = currentTimestampStr.substring(0, currentTimestampStr.indexOf(".")-1);

				DateTimeFormatter formatter = DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss");

				DateTime lastTimestamp =  formatter.parseDateTime(last);
				DateTime currentTimestamp =  formatter.parseDateTime(current);

				int dateDiff = Days.daysBetween(currentTimestamp, lastTimestamp).getDays();

				if(dateDiff > 0){
					return false;
				}

				return true;
			}

		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try{rs.close();}catch(Exception e){e.printStackTrace();}
			try{pstm.close();}catch(Exception e){e.printStackTrace();}
			try{con.close();}catch(Exception e){e.printStackTrace();}
		}

		return false;
	}

	@SuppressWarnings("finally")
	public boolean insert(){
		
		Connection con = null;
		PreparedStatement pstm = null;
		
		boolean result = false;
		
		String query = "INSERT into tblregistered(bp_no, id_no, crn_no, birthdate, place, email, contact_no, first_name, " +
						"last_name, password, question_1, question_2, answer_1, answer_2) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		try{
			con = ConnectionPoolManager.getConnection();
			pstm = con.prepareStatement(query);
			
			this.password = Security.getRandomPassword();
			System.out.println(password);
			this.password = Security.getMD5Hash(password);
			
			pstm.setInt(1, this.bp);
			pstm.setInt(2, this.id);
			pstm.setInt(3, this.crn);
			pstm.setString(4, this.birthdate);
			pstm.setString(5, this.place);
			pstm.setString(6, this.email);
			pstm.setString(7, this.contactNo);
			pstm.setString(8, this.firstName);
			pstm.setString(9, this.lastName);
			pstm.setString(10, this.password);
			pstm.setString(11, this.questionA);
			pstm.setString(12, this.questionB);
			pstm.setString(13, this.answerA);
			pstm.setString(14, this.answerB);
			
			result = (pstm.executeUpdate() == 1) ? true : false;
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			return result;
		}
	}
}
