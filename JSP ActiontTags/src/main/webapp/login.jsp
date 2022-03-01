<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<style>
	h3{
		color:red;
	}
</style>
</head>
<body>
<%
	String name=request.getParameter("name");
	String pass=request.getParameter("pass");
	
	if(pass.equals("1111"))
	{
%>
   <!-- Here we have used JSP forward tags -->
	<jsp:forward page="dashboard.jsp">
		<jsp:param value="<%= name %>" name="user"/>
	</jsp:forward>
<%}
	else{
%>
<!-- Here we have used JSP include tags -->
	<h3>Wrong Password</h3>
	<jsp:include page="index.jsp"></jsp:include>
<%} %>
</body>
</html>