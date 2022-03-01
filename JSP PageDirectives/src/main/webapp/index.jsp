<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!-- Here i have used different page directive available to get the result -->
<%@ page import="java.util.Date" %>
<%@ page info="This is my JSP page for Home page" isELIgnored="false" %>
<%@ page autoFlush="true" %>
<%@ page buffer="8kb" %>
<!-- Above all are different types of attributes used -->
${'Hello Ravikant' }<br>
<%= new Date() %><br>
</body>
</html>