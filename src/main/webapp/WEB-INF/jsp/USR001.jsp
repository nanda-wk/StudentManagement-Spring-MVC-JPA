<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>(USR001) User Search</title>
<jsp:include page="style.jsp" />
<script type="text/javascript">
	function checkDelete() {
		var c = confirm("Are you sure you want to delete!");
		if (c) {
			document.getElementById("deleteuser").submit();
		}
	}
</script>
</head>
<body class="main_body">

	<jsp:include page="header.jsp" />

	<div id="container">
		<div id="left_menu">
			<!-- menu html code exist the menu function of general.js -->
			<jsp:include page="left-menu.jsp"/>
		</div>
		<div id="main_contents">
			<div id="contents">
				<div class="search_form">
					<h3>User Search</h3>
					<form:form action="/StudentManagement-JPA-SpringMVC/searchUser" method="get" modelAttribute="usBean">
						<table class="tableForm">
							<tr>
								<td class="tblLabel"><label>User ID</label></td>
								<td class="tblInputNormal"><form:input type="text"
									id="txtUserId" class="txt" path="userId"/></td>

								<td class="tblLabel">User Name</td>
								<td class="tblInputNormal"><form:input type="text"
									id="txtUserName" class="txt" path="userName"/></td>

							</tr>
						</table>
						<br /> <input type="submit" value="Search" class="button"
							onclick="searchList()" /> <a href="/StudentManagement-JPA-SpringMVC/userRegister"> <input
							type="button" value="Add" class="button" id="userInsert" />
						</a> <input type="button" value="Reset" class="button"
							onclick="location.href='/StudentManagement-JPA-SpringMVC/usermanagement'" />
					</form:form>
					<br /> <br />
					<div id="errormsg">
						<label id="message">${ error }${ success }</label>
					</div>
				</div>

				<br /> <br /> <br />
				<div id="list">
					<table class="resultTable">
						<c:if test="${userList !=null}">
							<tr class="tblHeader">
								<th width="1%">Delete</th>
								<th width="1%">Update</th>
								<th width="12%">User ID</th>
								<th width="24%">User Name</th>

							</tr>
						</c:if>
						<c:forEach items="${userList}" var="ul">
							<tr>
								<td><a href="deleteUser/${ul.id}"><input type="button" value="Delete" id="deleteuser" class="button"
											onclick="javascript:checkDelete()" /></a>
									</td>
								<td><a href="/StudentManagement-JPA-SpringMVC/updateUserModel/${ul.id}"> <input
										type="button" value="Update" class="button" id="updateUser" />
								</a></td>

								<td>${ul.id}</td>
								<td>${ul.name}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
			</div>
		</div>

	</div>
	<div class="footer">
		<span>Copyright &#169; ACE Inspiration 2016</span>
	</div>

</body>
</html>