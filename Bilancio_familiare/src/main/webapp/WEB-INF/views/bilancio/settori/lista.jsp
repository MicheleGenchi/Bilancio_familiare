<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<title>Lista settori</title>

<meta charset="utf-8">

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/custom.css">
<link rel="stylesheet" href="/resources/css/tabella.css">
</head>
<body>

	<div class="container">

		<div>
			<div class="row title">Lista relazioni tipologie/settori</div>
		</div>
		<hr />
		<div class="bootstrap-select-wrapper">
			<label class="etichetta" for="tipologie">TIPOLOGIA</label> <select
				id="tipologie" title="Scegli una opzione">
				<option value="" selected="selected" disabled>Seleziona una
					tipologia</option>
				<c:forEach items="${settore.tipologies}" var="item">
					<option value="${item.id}" label="${item.nome}" />
				</c:forEach>
			</select>
		</div>
		<hr />
		<div class="table-responsive-sm" id="settorifiltrati" >Settori disponibili per tipologia</div>
		<hr />
		<div>
			<a href="http://localhost:8080/bilancio"
				class="btn btn-outline-secondary">RITORNA AL MENU</a>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="/resources/js/jsSettoriTable.js"></script>
</body>
</html>