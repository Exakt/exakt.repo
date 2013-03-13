<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.gsis.bom.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prototype - Forgot Password</title>
<%!
	Member member;
	String error;
%>
<%
	try{
		member = (Member) session.getAttribute("member");
		error = request.getParameter("error");
	}catch(Exception e){
		e.printStackTrace();
	}
%>
</head>
<body>
	<form action="CheckServlet" method="get">
		<table>
			<th>Forgot Password</th>
			<%
				if (error != null) {
					if (error.equalsIgnoreCase("exist")) {
						
					} else if (error.equalsIgnoreCase("no")) {
						%>
						<p style="width: 250px; color: red; font-size: 13px">
							<i>Member NOT Registered</i>
						</p>
						<%
					}  else if (error.equalsIgnoreCase("okay")) {
						%>
						<p style="width: 250px; color: green; font-size: 13px">
							<i>Check your email for the new password</i>
						</p>
						<%
					} else if (error.equalsIgnoreCase("not")){
						%>
						<p style="width: 250px; color: red; font-size: 13px">
							<i>Invalid Answer/s</i>
						</p>
						<%
					}
				}
			%>
			<tr>
				<td>BP Number</td>
				<td><input type="text" name="bpNumber" id = "bpNumber" 
				<%if (error != null) {
						if (error.equals("exist")) {%>					
							value="<%out.print(member.getBpNumber()); %>" <%
						} else {%>
							value="" <%
						}
					}%>	
				/></td>
			</tr>
			<tr>
				<td><input type="submit" name="submit" value="Submit"/></td>
				<td><input type="hidden" name="facility" value="forgotPassword"/></td>
			</tr>
		</table>
	</form>

	<form action="ForgotPasswordServlet" method="get">
		<table>
			<tr>
				<td>Secret Question 1</td>
				<td><input type="text" name="question1" id = "question1" 			
					<%if (error != null) {
							if (error.equals("exist")) {%>					
								value="<%out.print(member.getQuestions().getQuestionDesc(member.getQuestion1()));%>" <%
							} else {%>
								value="" <%
							}
						}%>			
				/></td>
			</tr>
			<tr>
				<td>Answer</td>
				<td><input type="text" name="answer1" id = "answer1"/></td>
			</tr>
			<tr>
				<td>Secret Question 2</td>
				<td><input type="text" name="question2" id = "question2"
					<%if (error != null) {
							if (error.equals("exist")) {%>					
								value="<%out.print(member.getQuestions().getQuestionDesc(member.getQuestion2()));%>" <%
							} else {%>
								value="" <%
							}
						}%>	
				/></td>
			</tr>
			<tr>
				<td>Answer</td>
				<td><input type="text" name="answer2" id = "answer2"/></td>
			</tr>
			<tr>
				<td><input type="hidden" name="bpNumber" 
					<%if (error != null) {
							if (error.equals("exist")) {%>					
								value="<%out.print(member.getBpNumber());%>" <%
							} else {%>
								value="" <%
							}
						}%>	
				/></td>
				<td><input type="submit" name="submit" value="Submit"/></td>
			</tr>
		</table>
	</form>
</body>
</html>