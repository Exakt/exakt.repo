<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Prototype - In Process</title>
<link rel="stylesheet" href="/resources/themes/master.css"
	type="text/css" />
<link rel="stylesheet" href="css/jquery-ui.css" type="text/css" />
<script src="js/jquery.js" type="text/javascript"></script>
<script src="js/jquery-ui.min.js" type="text/javascript"></script>
<script src="/resources/scripts/mysamplecode.js" type="text/javascript"></script>

<script type="text/javascript">
	$(document).ready(function() {

		var icons = {
			header : "ui-icon-circle-arrow-e",
			activeHeader : "ui-icon-circle-arrow-s"
		};

		$(function() {
			$(".accordionLayout").accordion({
				icons : icons,
				heightStyle : "content"
			});
		});

	});
</script>

</head>
<body>

 <div id="allContent">
	 <div class="accordionLayout">
			<c:forEach var="transaction" items="${transactionList}">
				<p>${transaction.referenceNo} &nbsp &nbsp &nbsp &nbsp &nbsp <input type="text" style="width: 600px" value=" ${transaction.description} " /> &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp ${transaction.dateFiled} &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp  ${transaction.status} </p>
				<div class="accordionLayout">
				</div>
			</c:forEach>
	 </div>
    <c:if test="${currentPage != 1}">
        <a href="TransactionServlet?page=${currentPage - 1}">Previous</a>
    </c:if>
            <c:forEach begin="1" end="${noOfPages}" var="i">
                <c:choose>
                    <c:when test="${currentPage eq i}">
                        ${i}
                    </c:when>
                    <c:otherwise>
                        <a href="TransactionServlet?page=${i}">${i}</a>   
                    </c:otherwise>
                </c:choose>
            </c:forEach>
     <c:if test="${currentPage lt noOfPages}">
        <a href="TransactionServlet?page=${currentPage + 1}">Next</a>
    </c:if>
 </div>
</body>
</html>