<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../../css.jsp" %>
<%@ include file="../../login/logout.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Update</title>
</head>
<body>
	<center>
	<h1>Model Update Form</h1>
	<form:form action="update.htm" modelAttribute="model" >
		<table>
			<input type="hidden" name="id" value="${requestScope.model.getId() }"/>
			<tr>
				<td> Title: </td>
				<td><input type="text" name="title" value="${requestScope.model.getTitle() }"/></td>
			</tr>
			<tr>
				<td> Name: </td>
				<td><input type="text" name="name" value="${requestScope.model.getName() }"/></td>
			</tr>
			<tr>
				<td><input type="submit" value="submit"/></td>
			</tr>
		</table>
	</form:form>
	</center>
</body>
</html>