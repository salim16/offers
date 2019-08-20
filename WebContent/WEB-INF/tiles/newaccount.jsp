<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

	<h2>Create New Account</h2>

	<sf:form id="details" method="post"
		action="${pageContext.request.contextPath}/createaccount"
		commandName="user">
		<table class="formtable">
			<tr>
				<td class="label">Username:</td>
				<td><sf:input path="username" name="username" type="text" /><br />
					<div class="error">
						<sf:errors path="username"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Name:</td>
				<td><sf:input path="name" name="name" type="text" /><br />
					<div class="error">
						<sf:errors path="name"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input path="email" name="email" type="text" /><br />
					<div class="error">
						<sf:errors path="email"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Password:</td>
				<td><sf:input id="password" path="password" name="password"
						type="password" /><br />
					<div class="error">
						<sf:errors path="password"></sf:errors>
					</div></td>
			</tr>
			<tr>
				<td class="label">Confirm Password:</td>
				<td><input id="confirmpass" name="confirmpass" type="password" />
					<div id="matchpass"></div></td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input value="Create Account" type="submit"></td>
			</tr>
		</table>
	</sf:form>
