<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ include file="../css.jsp" %>
<%@ include file="../logo.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
<style>
.custom-centered{
 margin:0 auto;
 width:300px;
}
</style>
</head>
<body>
		<div class=" h-20 custom-centered">
			<form action="login/verifylogin.htm" method="POST">
			<div class="form-group">
				<label for="usr"><b>Username:</b></label> 
				<input type="text" name="username" id="usr" class="rounded form-control"/>
				</div>
			<div class="form-group">
				<label for="pwd"><b>Password:</b></label>
				<input type="password" name="password" id="pwd" class="rounded form-control"/>
			</div>
				<input type="submit" value="Login" class="btn btn-primary btn-lg btn-block"/>
			</form>
		</div>
</body>
</html>