<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%! 
	String username = "";
%>

<%
	try{
		username = (String)session.getAttribute("username");
	}catch(Exception e){
		username = "";
	}

	if(username.equals("")){
		response.sendRedirect("index.jsp");
	}

%>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>
	<form action="#">
		<p>This should expire in 30secs w/ <%=username %></p>
		<br>
		<input type="submit" id="submit" name="submit" value="Submit">
	</form>
</body>
</html>