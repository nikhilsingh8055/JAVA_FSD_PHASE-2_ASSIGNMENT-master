<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<!--  Here we have used different tags available under core tags  -->
Value: <c:out value="${40*5 }"/> <br>
<c:set var="income" value="${6*400 }"></c:set>
value: <c:out value="${income }"/> <br>

<c:catch var="error">
	<% int x=10/0; %>
</c:catch>
<c:if test="${error!=null }">
	${error }<br>
</c:if>
<c:choose>
	<c:when test="${income>=2460 }">
		Can manage everything Easily
	</c:when>
	<c:otherwise>
		Difficult to manage
	</c:otherwise>
</c:choose>
</body>
</html>