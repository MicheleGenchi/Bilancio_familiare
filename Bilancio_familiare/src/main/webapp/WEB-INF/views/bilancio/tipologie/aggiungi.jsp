<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiungi una tipologia</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
</head>
<link rel="stylesheet" href="/resources/css/custom.css">
<body>

	<div class="container">
		<hr />
		<div class="row title">Aggiungi tipologia</div>
		<hr />
		
		<form:form action="aggiungi" modelAttribute="newtipologia" method="POST">
			<div class="form-group">
				<label class="etichetta" for="id">ID</label>
				<form:input class="border-0 id" path="id" id="id" readonly="true" value="${lastId}" />
			</div>

			<div class="form-group">
				<label class="etichetta" for="nome">NOME : </label>
				<form:input path="nome" type="text" class="form-control" id="nome"
					placeholder="Enter nome" />
				<form:errors path="nome" cssClass="error" />	
			</div>
			<hr />
			
			<form:input class="btn btn-outline-secondary" path="" type="submit"
				value="AGGIUNGI" />
			<a href="http://localhost:8080/bilancio" 
				class="btn btn-outline-secondary">RITORNA AL MENU</a>
				
		</form:form>
	</div>

</body>
</html>