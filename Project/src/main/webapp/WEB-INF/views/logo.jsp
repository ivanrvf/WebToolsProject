<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
</head>
<body>
<div style="width:100%;position:relative;height: 100px">
	<div align="left" style="display: inline;">
	<!-- <img class="navbar-brand" style="width:70px;position:absolute" src="http://localhost:8080/project/resources/images/1f6d2.png"> -->
</div>

<div align="right" style="padding-right: 10px">
 	<c:if test="${sessionScope.loggedInUser != null}">
 		<a href="/project/logout.htm" class="btn btn-danger btn-sm">Logout</a>
 	</c:if>
 	</div>
	</div>


</body>
</html>