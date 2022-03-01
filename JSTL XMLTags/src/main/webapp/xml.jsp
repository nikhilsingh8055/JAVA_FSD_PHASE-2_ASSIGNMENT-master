<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
       <%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<c:set var="books">
	<books>
		<book>
			<name>Three Men In a Boat</name>
			<author>Jerome Klapka Jerome</author>
		</book>
		<book>
			<name>MS DHONI THE UNTOLD STORY</name>
			<author>NEERAJ PANDEY</author>
		</book>
	</books>
	<!-- Here i have used different tags available under xml to pass result -->
</c:set>
<x:parse xml="${books }" var="result"></x:parse>
1st Book Name: <x:out select="$result/books/book[1]/name"/><br>
1st Book Author: <x:out select="$result/books/book[1]/author"/><br>

2nd Book Name: <x:out select="$result/books/book[2]/name"/><br>
2nd Book Author: <x:out select="$result/books/book[2]/author"/><br>
</body>
</html>