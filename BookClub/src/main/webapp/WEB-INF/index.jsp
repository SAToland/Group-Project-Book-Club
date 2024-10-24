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
    <title>Login and Register</title>
    <link rel="stylesheet" href="/webjars/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="/css/main.css"> <!-- change to match your file/naming structure -->
    <script src="/webjars/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="/js/app.js"></script><!-- change to match your file/naming structure -->
</head>
<body>
   <div id="container" class="p-3">
		<h1>Welcome!</h1>
		<p>Join our growing community.</p>
		<div class="d-flex">
			<div id="RegForm" class="d-flex flex-column col-5 me-4">
				<h2>Register</h2>
				<form:form class="d-flex flex-column" action="/register" modelAttribute="newUser" method="POST">
					<form:label for="username" path="name">Name:</form:label>
					<form:input type="text" path="name"/>
					<form:errors path="name"/>
					<form:label for="email" path="email">Email:</form:label>
					<form:input type="text" path="email"/>
					<form:errors path="email"/>
					<form:label for="password" path="password">Password:</form:label>
					<form:input type="password" path="password"/>
					<form:errors path="password"/>
					<form:label for="confirmPass" path="confirmPass">Confirm PW:</form:label>
					<form:input type="password" path="confirmPass"/>
					<form:errors path="confirmPass"/>
					<input type="submit"/>
				</form:form>
			</div>
			<div id="LoginForm" class="col-5">
				<h2>Login</h2>
				<form:form class="d-flex flex-column" action="/login" modelAttribute="newLogin" method="POST">
					<form:label for="email" path="email">Email:</form:label>
					<form:input type="text" path="email"/>
					<form:errors path="email"/>
					<form:label for="password" path="password">Password:</form:label>
					<form:input type="password" path="password"/>
					<form:errors path="password"/>
					<input type="submit"/>
				</form:form>
			</div> 
		</div> 
   </div>
</body>
</html>