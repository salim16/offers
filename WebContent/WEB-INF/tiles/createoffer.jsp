<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<script type="text/javascript">
$(document).ready(onReady);

function onReady() {
	$("#delete").click(onDeleteClick);
}

function onDeleteClick(event) {
	var performDelete = confirm("Do you really want to delete your Offer");
	
	if(performDelete == false) {
		event.preventDefault();
	}
}
</script>

<sf:form method="get"
	action="${pageContext.request.contextPath}/docreate"
	commandName="offer">
	<sf:input path="id" name="id" type="hidden" />
	
	<table class="formtable">
		<%-- <tr>
				<td class="label">Name:</td>
				<td><sf:input path="user.username" type="text" disabled="true" value="${user.username}"/><br /> 
				</td>
			</tr>
			<tr>
				<td class="label">Email:</td>
				<td><sf:input path="user.email" type="text" disabled="true"/><br />
				</td>
			</tr> --%>
		<tr>
			<td class="label">Your Offer:</td>
			<td><sf:textarea path="text" name="text" rows="10" cols="10"></sf:textarea><br />
				<sf:errors path="text" cssClass="error"></sf:errors></td>
		</tr>
		<tr>
			<td class="label"></td>
			<c:choose>
				<c:when test="${offer.text != null}">
					<td><input class="control" value="Update Advert" type="submit"></td>
					<td><input class="control delete" value="Delete Offer" name="delete" id="delete" type="submit"></td>
				</c:when>
				<c:otherwise>
					<td><input class="control" value="Save Advert" type="submit"></td>
				</c:otherwise>
			</c:choose>
		</tr>
		<%-- <c:if test="${offer.text != null}">
			<tr>
				<td class="label"></td>
				<td>&nbsp;</td>
			</tr>
			<tr>
				<td class="label"></td>
				<td><input class="control" value="Delete Offer" type="submit"></td>
			</tr>
		</c:if> --%>
	</table>
</sf:form>
