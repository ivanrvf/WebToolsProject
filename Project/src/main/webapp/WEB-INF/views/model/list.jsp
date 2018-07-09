<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="../css.jsp" %>
    <%@ include file="../login/logout.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<div align="center">
<a href="insertForm.htm" class="btn btn btn-success btn-sm">Add</a>
<c:if test="${requestScope.modelName != null }">
	Successfully deleted ${requestScope.modelName}!
</c:if>
<table class="table-striped">
<tr class="w-50 p-3"><td><b>Title</b></td><td><b>Name</b></td><td><b>Action</b></td></tr>
	<c:forEach items="${requestScope.modelList }" var="model">
		<tr class="w-50 p-3">
			<td>${model.getTitle() }</td>
			<td>${model.getName() }</td>
			<td><form:form action="delete.htm" modelAttribute="model" method="PUT"><input type="hidden" value="${model.getId() }" name="id"/><input type="submit" value="Delete" class="btn btn-primary btn-sm"/>&nbsp <button type="submit" formaction="updateForm.htm" formmethod="POST" class="btn btn-danger btn-sm">Update</button></form:form></td>
		</tr>	
	</c:forEach>
</table>
</div>
</body>
</html>