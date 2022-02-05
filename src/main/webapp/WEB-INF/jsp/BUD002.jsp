<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>(BUD002)Student Register</title>
<jsp:include page="style.jsp" />
</head>
<body class="main_body">

	<jsp:include page="header.jsp" />

	<div id="container">
		<div id="left_menu">
			<!-- menu html code exist the menu function of general.js -->
			<jsp:include page="left-menu.jsp" />
		</div>
		<form:form action="/StudentManagement-JPA-SpringMVC/studentRegister"
			method="post" modelAttribute="stubean">
			<div id="main_contents">
				<div id="contents">
					<div class="search_form">
						<h3>Student Register</h3>
						<label id="errormsg">${ error }<form:errors
								path="*" /></label><label id="successmsg">${ success }</label> <br /> <br /> <br />
						<table class="tableForm">
							<tr>
								<td class="tblSingleLabel">Student No *</td>
								<td class="tblSingleInput"><form:input type="text"
										class="txtlong" path="studentId" /></td>
							</tr>
							<tr>
								<td class="tblSingleLabel">Student Name *</td>
								<td class="tblSingleInput"><form:input type="text"
										class="txtlong" path="studentName" /></td>
							</tr>
							<tr>
								<td class="tblSingleLabel">Class Name *</td>
								<td class="tblSingleInput"><form:select id="expenseType"
										class="normal_sel" path="className">
										<option></option>
										<c:forEach items="${ classList }" var="cl">
											<option>${ cl.name }</option>
										</c:forEach>
									</form:select></td>
							</tr>
							<tr>
								<td class="tblSingleLabel">Registered Date *</td>
								<td class="tblSingleInput"><form:select id="expenseType"
										class="short_sel" path="year">
										<option>Year</option>
										<c:forEach var="i" begin="2022" end="2025">
											<option>${ i }</option>
										</c:forEach>
									</form:select> <form:select id="expenseType" class="short_sel" path="month">
										<option>Month</option>
										<c:forEach var="i" begin="1" end="12">
											<option>${ i }</option>
										</c:forEach>
									</form:select> <form:select id="expenseType" class="short_sel" path="day">
										<option>Day</option>
										<c:forEach var="i" begin="1" end="31">
											<option>${ i }</option>
										</c:forEach>
									</form:select></td>
							</tr>
							<tr>
								<td class="tblSingleLabel">Status *</td>
								<td class="tblSingleInput"><form:select id="expenseType"
										class="normal_sel" path="status">
										<option></option>
										<option>Attending</option>
										<option>Passed</option>
										<option>Failed</option>
									</form:select></td>
							</tr>


						</table>
						<br />
						<div id="btnGroup">
							<input type="submit" value="Register" class="button"
								onclick="javascript:showMessage()" />
						</div>
					</div>
				</div>
			</div>
		</form:form>
	</div>

	<div class="footer">
		<span>Copyright &#169; ACE Inspiration 2016</span>
	</div>
</body>
</html>