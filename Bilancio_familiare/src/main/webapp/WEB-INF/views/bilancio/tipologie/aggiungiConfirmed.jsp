<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Tipologia aggiunta con successo</title>

<meta charset="utf-8">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/custom.css">
</head>
<body>

	<div class="container">
		<div class="row title">Tipologia</div>

		<ul class="list-group list-group-flush">
			<li class="list-group-item"><ul>ID : ${param.id}
				</ul>
				<ul>NOME : ${param.nome}
				</ul></li>
		</ul>
		<br/>
		<div class="${messaggio.tipo}" role="alert">${messaggio.messaggio}</div>
		<ul>
			<a href="http://localhost:8080/bilancio/tipologie/aggiungi"
				class="btn btn-outline-secondary">AGGIUNGI UN'ALTRO</a>
			<a href="http://localhost:8080/bilancio"
				class="btn btn-outline-secondary">RITORNA AL MENU</a>
		</ul>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.bundle.min.js"></script>

</body>
</html>