<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Aggiungi un settore</title>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<link rel="stylesheet" href="/resources/css/custom.css">
</head>
<body>

	<div class="container">
		<hr />
		<div class="row title">Aggiungi negozio</div>
		<hr />
		
		<form:form action="aggiungi" modelAttribute="newnegozio" method="POST">
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
			
			<div class="form-group">
				<label class="etichetta" for="indirizzo">INDIRIZZO : </label>
				<form:input path="indirizzo" type="text" class="form-control" id="indirizzo"
					placeholder="Enter indirizzo" />
				<form:errors path="indirizzo" cssClass="error" />	
			</div>
			
			<div class="form-group">
				<label class="etichetta" for="tipologie">TIPOLOGIE :</label>				
				<form:select class="form-control" path="tipologie">
						<option value="" label="Scegli una tipologia" selected disabled />
						<form:options items="${listaTipologie}" itemValue="id" itemLabel="nome" />
				</form:select>
			</div>
			
			<div class="form-group">
				<label class="etichetta" for="franchising">FRANCHISING :</label>				
				<form:select class="form-control" path="franchising">
						<option value="" label="Scegli un franchising" selected disabled />
						<form:options items="${listaFranchising}" itemValue="id" itemLabel="nome" />
				</form:select>
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