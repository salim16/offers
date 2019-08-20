<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
	$(document).ready(function() {
		document.f.username.focus();
	});

</script>
	<h3>Login with User name and Password</h3>

	<c:if test="${param.error != null }">
		<p class="error">Login failed! Check that your user name or
			password are correct</p>

	</c:if>

	<c:url value="login" var="loginUrl" />

	<form name='f' action='${loginUrl}' method='POST'>
		<table class="formtable">
			<tr>
				<td>User:</td>
				<td><input type='text' name='username' /></td>
			</tr>
			<tr>
				<td>Password:</td>
				<td><input type='password' name='password' /></td>
			</tr>
			<tr>
				<td>Remember Me:</td>
				<td><input type='checkbox' name='remember-me' checked="checked" /></td>
			</tr>
			<tr>
				<td colspan='2'><input name="submit" type="submit"
					value="Login" /></td>
			</tr>
		</table>
	</form>

	<p>
		<a href='<c:url value="newaccount" />'>Create new account</a>
	</p>
