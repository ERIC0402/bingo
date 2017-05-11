<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix='security' uri='http://www.springframework.org/security/tags' %>
<%@page contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" session="true"%>
<html>
<head>
<title>Login Page</title>
<style>
.error {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #a94442;
	background-color: #f2dede;
	border-color: #ebccd1;
}

.msg {
	padding: 15px;
	margin-bottom: 20px;
	border: 1px solid transparent;
	border-radius: 4px;
	color: #31708f;
	background-color: #d9edf7;
	border-color: #bce8f1;
}

#login-box {
	width: 300px;
	padding: 20px;
	margin: 100px auto;
	background: #fff;
	-webkit-border-radius: 2px;
	-moz-border-radius: 2px;
	border: 1px solid #000;
}
</style>
</head>
<body onload='document.loginForm.username.focus();'>

	<h1>Spring Security Custom Login Form (XML)</h1>

	<div id="login-box">

		<h3>Login with Username and Password</h3>

		
		<div class="msg">${SPRING_SECURITY_LAST_EXCEPTION.message}</div>

		<form name='loginForm' action="<c:url value='/login' />" method='POST'>

			<table>
				<tr>
					<td>User:</td>
					<td><input type='text' name='username' value="${sessionScope['SPRING_SECURITY_LAST_USERNAME']}"></td>
				</tr>
				<tr>
					<td>Password:</td>
					<td><input type='password' name='password' /></td>
				</tr>
				
				<tr>
					<td><input type="text" style="display:inline-block; width:50%" name="code" placeholder="验证码"></td>
					<td>&nbsp;&nbsp; <img src="captcha" onclick="this.src='captcha?t='+new Date()*1" height="30" width="70" /></td>
				</tr>
				
				<tr>
					<td>Remember:</td>
					<td><input id="remember_me" name="remember-me" type="checkbox" value="true"/></td>
				</tr>
				
				<tr>
					<td colspan='2'><input name="submit" type="submit"
						value="submit" /></td>
				</tr>
			</table>

		<!-- 防止跨站提交攻击 -->
		<security:csrfInput/>
		<%-- 	<input type="hidden" name="${_csrf.parameterName}"
				value="${_csrf.token}" /> --%>

		</form>
	</div>

</body>
</html>