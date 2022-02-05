<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<div id="header">
	<div id="title">
		<a href="/StudentManagement-JPA-SpringMVC/home">Student
			Registration Assignment</a>
	</div>
	<div id="menuLoginTime">
		<table>
			<tr>
				<td>User</td>
				<td>: ${ userId } ${ userName }</td>
			</tr>
			<tr>
				<td>Current Date</td>
				<td>: <%
				java.util.Date sdate = new java.util.Date();
				java.text.SimpleDateFormat fdate = new java.text.SimpleDateFormat("yyyy/MMMM/dd HH:mm:ss");
				String date = fdate.format(sdate);
				out.print(date);
				%></td>
			</tr>
		</table>
	</div>
	<form:form action="/StudentManagement-JPA-SpringMVC/logout"
		method="get">
		<input id="btn_logout" class="button" type="submit" value="Logout" />
	</form:form>
</div>