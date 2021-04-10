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
		<div class=" row title">Lista negozi filtrati</div>
		<hr />

		<div class="form-group">
			<label class="etichetta" for="nome">TIPOLOGIA</label> <select
				id="tipologie" title="Scegli una opzione">
				<option value="" selected disabled>Nessuna tipologia</option>
				<c:forEach items="${tipologies}" var="item">
					<option value="${item.id}">${item.nome}</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<label class="etichetta" for="nome">FRANCHISING</label> <select
				id="franchising" title="Scegli una opzione">
				<option value="" selected disabled>Nessun franchising</option>
				<c:forEach items="${franchisings}" var="item">
					<option value="${item.id}">${item.nome}</option>
				</c:forEach>
			</select>
		</div>

		<div>
			<a href="#" onclick="avviaFiltro()" class="btn btn-outline-secondary">CARICA
				I NEGOZI</a>
		</div>
		<hr />

		<div id="negoziFiltrati">
			Negozi filtrati per <span class="label" id="tipologia"></span> della catena <span
				class="label" id="frinchising"></span>
		</div>

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
	<script type="text/javascript" src="/resources/js/jsNegoziTable.js"></script>

	<script>
		var tipologia = "";
		var franchising = "";

		function getSelectionText(sel) {
			return sel.options[sel.selectedIndex].text;
		}
		$(document).ready(function() {
			$('select#tipologie').change(function() {
				//alert(getSelectionText(this));
				tipologia = getSelectionText(this);
				$("span.label#tipologia").text(tipologia);
			});
			$('select#franchising').change(function() {
				//alert(getSelectionText(this));
				franchising = getSelectionText(this);
				$("span.label#frinchising").text(franchising);
			});
		});
	</script>
	<script>
		function avviaFiltro() {
			var urlHead = "http://localhost:8080/bilancio/negozi/intestazione";
			var urlBody = "http://localhost:8080/bilancio/negozi/negoziByTipFra/"
					+ $('select#tipologie').val() + "/" + $('select#franchising').val();

			createTable(urlHead, urlBody);
		}
	</script>

</body>
</html>