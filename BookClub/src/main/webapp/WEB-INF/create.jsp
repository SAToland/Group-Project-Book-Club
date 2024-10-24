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
				<h1>Add a Book to Your Shelf!</h1>
				<a href="/home">Back to the shelves</a>
			</div>
		</div>
		<div id="main">
			<form:form class="d-flex flex-column col-6" action="/addBook" modelAttribute="newBook" method="POST">
				<form:label for="title" path="title">Title</form:label>
				<form:input type="text" path="title"/>
				<form:label for="author" path="Author">Author</form:label>
				<form:input type="text" path="Author"/>
				<form:label for="description" path="description">My thoughts</form:label>
				<form:textarea name="description" id="description" path="description" cols="30" rows="10"></form:textarea>
				<form:input type="hidden" value="${cUser.id}" path="user"/>
				<input type="submit" />
			</form:form>
		</div>
	</div>
</body>
</html>