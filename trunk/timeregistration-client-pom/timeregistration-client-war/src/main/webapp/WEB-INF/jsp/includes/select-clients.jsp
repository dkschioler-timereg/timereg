
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<form action="setClient.do" method="post">
	<select name="client">
		<option>choose</option>
		<c:if test="${not empty clients}">
			<c:forEach var="listValue" items="${clients}">
				<option value="${listValue.getId()}">${listValue.getName()}</option>
			</c:forEach>
		</c:if>
	</select> <input type="submit" value="set">
</form>
