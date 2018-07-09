<%@page import="com.spring.project.model.Item"%>
<%@page import="java.util.*"%>
<%@ page import="com.spring.project.model.Order" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ include file="../css.jsp" %>
<%@ include file="../logo.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<script>
	function pageNo(){
		location.href = "excel.htm?pageNo=${requestScope.currentPage}";
	}
</script>
</head>
<body>
Export Excel: <button onclick="pageNo()" class="btn btn-primary btn-sm">Export</button>
<table class="table" style="margin:20px;">
<tr class="w-50 p-3"><td><b>Name</b></td><td><b>Price</b></td><td><b>Quantity</b></td></tr>
	<%
		HttpSession httpSession = request.getSession();
		List<Order> orderList = (List<Order>)request.getAttribute("orderList");
		for(Order order: orderList){
			if(order != null){
				Iterator<Item> iterator = order.getItems().iterator();
				while(iterator.hasNext()){
					Item item = iterator.next();
					out.println();
					out.print("<tr><td>"+item.getProduct().getName() + "</td><td> "+ item.getQty() + "</td><td> " + (item.getQty() * item.getProduct().getPriceperunit()) +"</td></tr>");				
				}
			}
		}
		%>
</table>
<c:forEach begin="1" end="10" var="c">
		<a href="viewAllOrders.htm?pageNo=${c}">${c}</a>
	</c:forEach>
</body>
</html>