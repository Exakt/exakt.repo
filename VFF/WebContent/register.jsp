<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Registration</title>
</head>
<body>
	<form action="RegisterServlet" method="POST">
	
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
				<td colspan="2"><input type="submit" id="submit" name="submit"></td>
			</tr>
		</table>
	</form>
</body>
</html>