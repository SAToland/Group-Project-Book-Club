<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!-- c:out ; c:forEach etc. --> 
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!-- Formatting (dates) --> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"  %>
<!-- form:form -->
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!-- for rendering errors on PUT routes -->
<%@ page isErrorPage="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Home</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
	<div id="container" class="p-4">
		<div id="top-nav" class="d-flex justify-content-between">
			<div>
				<h1>Welcome, ${cUser.name}</h1>
				<p>Books from everyone's shelves:</p>
			</div>
			<div>
		   		<form action="/logout" method="POST">
					<input type="submit" value="LOGOUT"/>
				</form>
				<a href="/create">+ Add to my shelf!</a>
			</div>
		</div>
		<div id="main">
			<table class="col-10">
				<tr>
					<th>ID</th>
					<th>Title</th>
					<th>Author Name</th>
					<th>Posted By</th>
				</tr>
				<c:forEach items="${books}" var="book">
					<tr>
						<td>${book.id}</td>
						<td><a href="/view/${book.id}">${book.title}</a></td>
						<td>${book.author}</td>
						<td>${book.user.name}</td>
					</tr>
				</c:forEach>
			</table>
		</div>
	</div>
</body>
</html>

