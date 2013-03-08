<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="com.gsis.bom.*"%>
<%@ page import="java.util.ArrayList"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prototype - Registration</title>
<script type="text/javascript">
	function reload_captcha() {
		var img = document.getElementById('captcha');
		img.src = "<c:url value='simpleCaptcha.jpg' />";
	}
</script>
<%!Member member;
	String error;
	ArrayList<Questions> allQuestions1 = null;
	ArrayList<Questions> allQuestions2 = null;%>
<%
	try {
		member = (Member) session.getAttribute("member");
		error = request.getParameter("error");

		allQuestions1 = member.getQuestions().viewAllQuestions1();
		allQuestions2 = member.getQuestions().viewAllQuestions2();

		if (allQuestions1 == null) {
			allQuestions1 = new ArrayList<Questions>();
		}
		if (allQuestions2 == null) {
			allQuestions2 = new ArrayList<Questions>();
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
</head>
<body>
	<form action="CheckServlet" method="get">
		<table width=550px>
			<th>Registration</th>
			<%
				if (error != null) {
					if (error.equalsIgnoreCase("exist")) {
						
					} else if (error.equalsIgnoreCase("no")) {
						%>
						<p style="width: 250px; color: red; font-size: 13px">
							<i>Member Does NOT Exist</i>
						</p>
						<%
					} else if (error.equalsIgnoreCase("reg")){ %>
						<p style="width: 250px; color: red; font-size: 13px">
							<i>Member Already Registered</i>
						</p>
				<%	}
				}
			%>
			<tr>
				<td>BP Number</td>
				<td><input type="text" id="bpNumber" name="bpNumber"
					placeholder="Enter BP Number"
					<%if (error != null) {
						if (error.equals("exist")) {%>
							value="<%out.print(member.getBpNumber());%>" <%
						} else if (error.equals("reg")) { %>
							value="<%out.print(member.getBpNumber());%>" <%
						} else {%>
							value="<%out.print(member.getBpNumber());%>" <%
						}
					}%> /></td>
			</tr>
			<tr>
				<td>First Name</td>
				<td><input type="text" id="firstname" name="firstname"
					placeholder="Firstname"
					<%if (error != null) {
				//out.print(member.getBpNumber());
				if (error.equals("exist")) {%>
					value="<%out.print(member.getFirstName());%>" <%} else {%>
					value="<%out.print(member.getFirstName());%>" <%}
			}%> /></td>
			</tr>
			<tr>
				<td>Last Name</td>
				<td><input type="text" id="lastname" name="lastname"
					placeholder="Lastname"
					<%if (error != null) {
				//out.print(member.getBpNumber());
				if (error.equals("exist")) {%>
					value="<%out.print(member.getLastName());%>" <%} else {%>
					value="<%out.print(member.getLastName());%>" <%}
			}%> /></td>
			</tr>
			<tr colspan=2>
				<td><input type="submit" name="register" value="Register Me!" /></td>

			</tr>
		</table>
	</form>
	<form action="RegistrationServlet" method="get">
		<table>
			<tr colspan=2>
				<td>
				<td>
					<input type="hidden"
						value="<%if (error != null) {
									if (error.equals("exist")) {
										out.print(member.getBpNumber());
									}
								}%>" name="bp" />
				</td>
				</td>


			</tr>
			<tr>
				<td>Email Address</td>
				<td><input type="text" id="emailAddress" name="emailAddress"
					readonly="readonly"
					<%if (error != null) {
						if (error.equals("exist")) {%>
							value="<%out.print(member.getEmail());%>" <%
						} else if (error.equals("reg")) { %>
							value="" <%
						} else{ %>
							value="<%out.print(member.getEmail());%>" <%
						}
			}%> /></td>
			</tr>
			<tr>
				<td>Home Address</td>
				<td><input type="text" id="address" name="address"
					readonly="readonly"
					<%if (error != null) {
						if (error.equals("exist")) {%>
							value="<%out.print(member.getAddress());%>" <%
						} else if (error.equals("reg")) {
							
						} else {%>
							value="<%out.print(member.getAddress());%>" <%
						}
			}%> /></td>
			</tr>
			<tr>
				<td>Secret Question 1</td>
				<td><select name="question1">
						<% if (error != null) {
								for (int x = 0; x < allQuestions1.size(); x++) {
									if (error.equals("exist")) { %>
										<option value=" <%out.print(((Questions) allQuestions1.get(x)).getQuestionID());%> ">
											<%out.print(((Questions) allQuestions1.get(x)).getQuestions());%>
										</option> <%
								 	}else{ %>
										<option value=" <%out.print(((Questions) allQuestions1.get(x)).getQuestionID());%> ">
											<%out.print(((Questions) allQuestions1.get(x)).getQuestions());%>
										</option>
									<%	}
									}
						     } else { %>
								 <option>Secret Question</option><%						
							 }%>						
				</select></td>
			</tr>
			<tr>
				<td>Answer 1</td>
				<td><input type="text" id="answer1" name="answer1" /></td>
			</tr>
			<tr>
				<td>Secret Question 2</td>
				<td><select name="question2">
						<% if (error != null) {
								for (int x = 0; x < allQuestions2.size(); x++) {
									if (error.equals("exist")) { %>
										<option value=" <%out.print(((Questions) allQuestions2.get(x)).getQuestionID());%> ">
											<%out.print(((Questions) allQuestions2.get(x)).getQuestions());%>
										</option> <%
								 	}else{ %>
										<option value=" <%out.print(((Questions) allQuestions2.get(x)).getQuestionID());%> ">
											<%out.print(((Questions) allQuestions2.get(x)).getQuestions());%>
										</option>
									<%	}
									}
						     } else { %>
								 <option>Secret Question</option><%						
							 }%>
				</select></td>
			</tr>
			<tr>
				<td>Answer 2</td>
				<td><input type="text" id="answer2" name="answer2" /></td>
			</tr>
			<tr colspan=2>
				<td align="center"><img id="captcha" src="<c:url value="simpleCaptcha.jpg" />" width="200"></td>
				<td>
					<input type="text" name="answer" /><input type=button onClick="reload_captcha()" value="Refresh" /></td>
			</tr>
			<tr colspan=2>
				<td>
					<input type="submit" name="proceed" value="Proceed" />
				</td>
			</tr>
		</table>
	</form>
</body>
</html>