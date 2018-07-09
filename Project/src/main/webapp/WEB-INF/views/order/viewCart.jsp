<%@page import="com.spring.project.model.Item"%>
<%@page import="java.util.*"%>
<%@ page import="com.spring.project.model.Order" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../css.jsp" %>
<%@ include file="../logo.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Cart</title>
</head>
<body>
<br/>
<div align="right">
<a href="/project/order/viewOrders.htm" class="btn btn-info btn-sm">All Orders</a>
</div>
	<table class="table" style="margin:20px;">
<tr class="w-50 p-3"><td><b>Name</b></td>
<td><b>Quantity</b></td>
<td><b>Price</b></td>
<td><b>Action</b></td></tr>
	<%
		HttpSession httpSession = request.getSession();
		Order order = (Order)httpSession.getAttribute("order");
		if(order != null){
			Iterator<Item> iterator = order.getItems().iterator();
			while(iterator.hasNext()){
				Item item = iterator.next();
				out.println();
				out.print("<tr><td>"+item.getProduct().getName() + "</td><td> "+ item.getQty() + "</td><td> " + (item.getQty() * item.getProduct().getPriceperunit()) +"</td><td><button onclick='remove("+item.getProduct().getId()+")' class='btn btn-danger btn-sm'>Remove</button></td></tr>");				
			}
			out.println("<button onclick='placeOrder()' class='btn btn-success'>Checkout</button>");
		}
		else{
			out.println("Cart is empty!");
		}
	%>
	</table>
</body>
<script>
	function remove(productId){
		$.ajax({
			  url: "removeCart.htm",
			  data: {
			    id: productId
			  },
			  type:"POST",
			  success: function( result ) {
			    window.reload();
			  }
			});
	}
	function placeOrder(){
		$.ajax({
			  url: "placeOrder.htm",
			  type:"POST",
			  success: function( result ) {
			    alert(result);
			  }
			});
	}
</script>
</html>