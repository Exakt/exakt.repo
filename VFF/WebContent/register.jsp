<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="com.gsis.bom.MemberBean" %>
<%!int result = 4;
	MemberBean member = null;%>
<%
	if(request.getParameter("result") != null){
		result = Integer.parseInt(request.getParameter("result"));		
	}
	
	try{
		member = (MemberBean)session.getAttribute("member");
	}catch(Exception e){
		e.printStackTrace();
	}
%>    
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form action="RegisterServlet" method="POST">
		<h3>Fill up the following:</h3>
		<table> 
			<tr>
				<td>BP Number:</td>
				<td><input type="text" id="bp" name="bp"></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" id="first" name="first"></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" id="last" name="last"></td>
			</tr>
			<tr>
				<td colspan="2" align="right"><input type="submit" id="submit" name="submit" value="Submit"></td>
			</tr>
		</table>
	</form>
	<%
		if(result == 1){
			out.print("Already Exists!");
		}else if(result == 2){
			out.print("BP Number and Name does not match!");
		}else if(result == 3){
			out.print("Invalid E-mail Address!");
		}else{
			if(member != null)
			%>
				<jsp:include page="confirmation.jsp" />
			<%	
		}
	%>
</body>
</html>