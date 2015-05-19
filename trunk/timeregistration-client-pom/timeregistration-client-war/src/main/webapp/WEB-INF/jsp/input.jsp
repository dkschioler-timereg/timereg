<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<jsp:include page="includes/header.jsp">
	<jsp:param name="page.title" value="Input"  />
</jsp:include>

<body>
<table class="container">
<tr><td><jsp:include page="includes/menu.jsp" /></td></tr>
<tr><td><jsp:include page="includes/status.jsp" /></td></tr>
<!-- ************** -->
<tr><td>
<div>
	
	</div>
	<div>

		<form action="post-registration-event.do" method="post">
			<table class="eventinput">
			<tr class="eventinputheader">
					<td class="eventinputheader">&nbsp;</td>
					<td class="eventinputheader">Registration Item</td>
					<td class="eventinputheader">Timestamp</td>
					<td class="eventinputheader">Comment</td>

				</tr>
				<tr>
					<td class="eventinputsubmit"><input  type="submit" value="Save"></td>
					<td class="eventinputitem"><input class="leftmidcol"  type="text" name="registration-item" /></td>
					<td class="eventinputtime"><input class="rightmidcol" type="text" name="registration-event-timestamp"></input></td>
					<td class="eventinputcomment"><input class="rightcol" type="text" name="registration-event-comment"></td>

				</tr>
				
			</table>
		</form>
	</div>
	<div>
	<jsp:include page="includes/select-clients.jsp" />
	${currentClient}
	</div>
</td></tr>
</table>
</body>
</html>