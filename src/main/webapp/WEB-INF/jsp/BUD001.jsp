<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>(BUD001)Student Search</title>
<jsp:include page="style.jsp" />

</head>
<body class="main_body">

	<jsp:include page="header.jsp" />

	<div id="container">
		<div id="left_menu">
			<!-- menu html code exist the menu function of general.js -->
			<jsp:include page="left-menu.jsp" />
		</div>
		<div id="main_contents">
			<div id="contents">
				<div class="search_form">
					<h3>Student Search Page</h3>
					<form:form
						action="/StudentManagement-JPA-SpringMVC/studentResult"
						method="get" modelAttribute="ssBean">

						<table class="tableForm">
							<tr>
								<td class="tblLabel">Student No.</td>
								<td class="tblInputShort"><form:input type="text"
										path="studentId" class="txt" /></td>
								<td class="tblLabel">Student Name</td>
								<td class="tblInputShort"><form:input type="text"
										path="studentName" class="txt" /></td>
							</tr>
							<tr>
								<td class="tblLabel">Class Name</td>
								<td class="tblInputNormal" colspan="3"><form:input
										type="text" id="companyName" path="className" class="txtlong" /></td>
							</tr>
						</table>

						<br />
						<input type="submit" value="Search" onclick="searchList()"
							class="button" />
						<input type="button" value="Reset"
							onclick="location.href='/StudentManagement-JPA-SpringMVC/studentsearch'" class="button" />
						<br />
						<br />
						<div id="errormsg">
							<label id="message">${ error }${ success }</label>
						</div>
					</form:form>
					<div id="list">
						<form name="listForm">
							<br></br> <br />
							<table class="resultTable">
								<c:if test="${ stuList !=null }">
									<tr class="tblHeader">
										<th width="5%">No</th>
										<th width="10%">Student No</th>
										<th width="25%">Student Name</th>
										<th width="40%">Class Name</th>
										<th width="10%">Registered Date</th>
										<th width="10%">Status</th>
									</tr>
								</c:if>
								<c:set var="index" value="${loop.index}" />
								<c:forEach items="${ stuList }" var="st">
									<c:set var="index" value="${index + 1}" />
									<tr>
										<td><c:out value="${index}" /></td>
										<td>${st.studentId}</td>
										<td><a
											href="/StudentManagement-JPA-SpringMVC/studentUpdateModel/${st.studentId}">${st.studentName}</a></td>
										<td>${st.className}</td>
										<td>${st.registerDate}</td>
										<td>${st.status}</td>
									</tr>
								</c:forEach>

							</table>
							<br />

						</form>
					</div>
				</div>
			</div>
		</div>

	</div>

	<div class="footer">
		<span>Copyright &#169; ACE Inspiration 2016</span>
	</div>
</body>
</html>