<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="/JDBC/login" method="post"
		style="max-width: 400px; margin: auto; border: 1px solid #ccc; padding: 20px; border-radius: 10px; font-family: sans-serif;">
		<c:if test="${alert != null}">
			<h3 class="alert alert-danger"
				style="color: red; text-align: center;">${alert}</h3>
		</c:if>

		<h2 style="text-align: center;">Login</h2>

		<div style="margin-bottom: 15px;">
			<label for="uname"><b>Username</b></label> <input type="text"
				id="uname" name="uname" placeholder="Enter Username" required
				style="width: 100%; padding: 10px; margin-top: 5px; border: 1px solid #ccc; border-radius: 5px;">
		</div>

		<div style="margin-bottom: 15px;">
			<label for="psw"><b>Password</b></label> <input type="password"
				id="psw" name="psw" placeholder="Enter Password" required
				style="width: 100%; padding: 10px; margin-top: 5px; border: 1px solid #ccc; border-radius: 5px;">
		</div>

		<div style="margin-bottom: 15px;">
			<button type="submit"
				style="width: 100%; background: #4CAF50; color: white; padding: 10px; border: none; border-radius: 5px; cursor: pointer;">
				Login</button>
		</div>

		<div style="margin-bottom: 10px;">
			<label> <input type="checkbox" name="remember" checked>
				Remember me
			</label>
		</div>

		<div
			style="text-align: center; background: #f1f1f1; padding: 10px; border-radius: 5px;">
			<button type="button" class="cancelbtn"
				style="background: #f44336; color: white; padding: 8px 16px; border: none; border-radius: 5px; cursor: pointer;">
				Cancel</button>
			<span class="psw" style="margin-left: 10px;">Forgot <a
				href="#">password?</a></span>
		</div>
	</form>


</body>
</html>