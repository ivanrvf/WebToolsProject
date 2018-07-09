<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../css.jsp" %>
<%@ include file="../logo.jsp" %>
<%@ include file="../login/logout.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Product List</title>
</head>
<body>
</br>
<div align="center">
<c:if test="${requestScope.modelName != null }">
	Successfully Added ${requestScope.productName} to Cart!
</c:if>
<div align="right">
	<a href="viewCart.htm" class="btn btn-info btn-sm">View Cart</a>
</div>
<table class="table" style="margin:20px;">
<tr class="w-50 p-3"><td><b></b></td><td><b>Name</b></td><td><b>Price</b></td><td><b>Quantity</b></td><td><b>Action</b></td></tr>
	<c:forEach items="${requestScope.productList}" var="product">
		<tr class="w-50 p-3">
			<td><img alt="" style="width: 50px;" src="../resources/images/products/${ product.getPhotoPath()}"></td>
			<td>${product.getName() }</td>
			<td>${product.getPriceperunit() }</td>
			<td><input type="number" name="quantity" min="1" max="10" id="qty_${product.getId() }"/></td>
			<td>
				<input type="hidden" value="${product.getId() }" name="id" id="id"/>
				<button onclick="addToCart(${product.getId() })" value="Add To Cart" class="btn btn-primary btn-sm">Add To Cart</button>
			</td>
		</tr>	
	</c:forEach>
</table>
</div>
<script>
	function addToCart(productId){
		$.ajax({
			  url: "addToCart.htm",
			  data: {
			    id: productId,
			    qty: $("#qty_"+productId).val()
			  },
			  type:"POST",
			  success: function( result ) {
			    alert(result);
			  }
			});
	}
</script>
</body>
</html>