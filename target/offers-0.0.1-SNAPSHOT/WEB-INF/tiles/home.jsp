<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>

<table class="offers">
	<tr>
		<td class="name">Name</td>
		<td class="contact">Contact</td>
		<td class="text">Offer</td>
	</tr>

	<c:forEach var="offer" items="${offers}">
		<tr>
			<td class="name"><c:out value="${offer.user.name}"></c:out></td>
			<td class="contact"><a
				href="<c:url value='/message?uid=${offer.user.username}' />">Contact</a></td>
			<td class="text"><c:out value="${offer.text}"></c:out></td>
		</tr>
	</c:forEach>
</table>

<br />


<script type="text/javascript">
	$(document).ready(onLoad);

	function onLoad() {
		updatePage();
		window.setInterval(updatePage, 5000);
	}

	function updateMessageLink(data) {
		$("#numberMessages").text(data.number);
	}
	
	function updatePage() {
		$.getJSON("<c:url value='/getmessages' />", updateMessageLink);
	}
</script>
