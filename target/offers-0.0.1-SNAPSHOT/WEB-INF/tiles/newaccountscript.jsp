<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<script type="text/javascript">
	$(document).ready(onLoad);

	function onLoad() {
		$("#password").keyup(checkPasswordsMatch);
		$("#confirmpass").keyup(checkPasswordsMatch);

		$("#details").submit(canSubmit);
	}

	function checkPasswordsMatch() {
		var password = $("#password").val();
		var confirmPass = $("#confirmpass").val();

		if (password.length < 3 || confirmPass.length < 3) {
			return;
		}

		if (password == confirmPass) {
			$("#matchpass").text("<fmt:message key='MatchedPasswords.user.password' />");
			$("#matchpass").addClass("valid");
			$("#matchpass").removeClass("error");
		} else {
			$("#matchpass").text("<fmt:message key='UnmatchedPasswords.user.password' />");
			$("#matchpass").addClass("error");
			$("#matchpass").removeClass("valid");
		}
	}

	function canSubmit() {
		var password = $("#password").val();
		var confirmPass = $("#confirmpass").val();

		if (password != confirmPass) {
			alert("Password do not match!!")
			return false;
		} else {
			return true;
		}
	}
</script>