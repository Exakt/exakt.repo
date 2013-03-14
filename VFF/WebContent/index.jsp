<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
<%@ page import="com.gsis.bom.Member" %>   
<%!
	Member member = null;
%>   
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>
	<fieldset style="width:250px">
		<legend >Login</legend>
		<form action="LoginServlet" method="POST" >
			<table border="0" align="center">
				<tr>
					<td width="20%">UserID:</td>
					<td align="right"><input type="text" id="username" name="username"></td>
				</tr>
				<tr>
					<td width="20%">Password:</td>
					<td align="right"><input type="password" id="password" name="password"></td>
				</tr>
				<tr>
					<td colspan="2" align="right"><input type="submit" id="submit" name="submit" value="Submit"></td>
				</tr>
			</table>
		</form>
	</fieldset>
	<br>
	<a href="register.jsp">Register</a>
	<br>
	<%if(session.getAttribute("locked") != null){%>
		<%out.print("Account Locked!");%>
		<a href="forgot.jsp">Unlock</a>
	<%}%> 
</body>
</html>