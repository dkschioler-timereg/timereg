
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<select>
	<c:if test="${not empty registrationItems}">
		<c:forEach var="listValue" items="${registrationItems}">
			<option>${listValue.getId()}${listValue.getName()}
				${listValue.getClientProjects()}</option>
		</c:forEach>
	</c:if>
</select>
<select>
	<option>choose</option>
	<c:if test="${not empty clients}">
		<c:forEach var="listValue" items="${clients}">
			<option>${listValue.getId()}${listValue.getName()}
				</option>
		</c:forEach>
	</c:if>
</select>

