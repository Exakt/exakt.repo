<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ page import="com.gsis.bom.Member" %>

<%! 
	Member member = null;
%>

<%
	try{
		member = (Member)session.getAttribute("member");
	}catch(Exception e){
		
	}

	if(member == null){
		response.sendRedirect("index.jsp");
	}

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<form action="LogoutServlet" method="POST">
		<p>This should expire in 30minutes : <%="Welcome " + member.getFirstName() + " " + member.getLastName() %></p>
		<br>
		<input type="submit" id="submit" name="submit" value="Logout">
	</form>
</body>
</html>