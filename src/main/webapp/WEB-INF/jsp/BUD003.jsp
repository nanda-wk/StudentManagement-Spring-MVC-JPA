<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>(BUD003)Class Register</title>
<jsp:include page="style.jsp" />
<script type="text/javascript">
function insert()
{
    var con = confirm("Are you sure to register?");
    if(con)
    {
        document.getElementById('errormsg').innerHTML = "";
    }
    
}
</script>
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
				<h3>Class Register</h3>
				<form:form action="/StudentManagement-JPA-SpringMVC/classRegister"
					method="post" name="registerForm" modelAttribute="cbean">
					<label id="errormsg"> ${ error }<form:errors
							path="*" />
					</label><label id="successmsg">${ success }</label>
					<br />
					<br />
					<br />


					<table class="tableForm">
						<tr>
							<td class="tblSingleLabel">Class ID *</td>
							<td class="tblSingleInput"><form:input type="text"
									id="txtUserId" class="txt_popup" path="id" /></td>
						</tr>
						<tr>
							<td class="tblSingleLabel">Class Name *</td>
							<td class="tblSingleInput"><form:input type="text"
									class="txtlong_popup" id="txtUserName" path="name" /></td>
						</tr>

					</table>
					<br />

					<input type="submit" value="Register" class="button"
						onclick="javascript:insert()" />
				</form:form>

				<br />
				<br />
				<br />
			</div>
		</div>

	</div>

	<div class="footer">
		<span>Copyright &#169; ACE Inspiration 2016</span>
	</div>
</body>
</html>