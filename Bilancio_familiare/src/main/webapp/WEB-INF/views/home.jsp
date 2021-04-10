<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
	integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm"
	crossorigin="anonymous">
<link rel="stylesheet" href="/resources/css/custom.css">
</head>
<body>

	<div class="container-fluid">
		<div class="row">
	
				<img src="/resources/img/bilancio_familiare.jpg"
					alt="risorsa non trovata" />

			<div class="col-m">
				<h2>Bilancio familiare</h2>
				<h3>data : ${dataCorrente}</h3>
				<h4>programmatore : ${mioNome}</h4>
				<h4>Lingua : ${lingua}</h4>
			</div>
		</div>
	</div>
		<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
			<!-- Brand -->
			<a class="navbar-brand" href="#"> <img
				src="/resources/img/icon.jpg" alt="Logo" style="width: 40px;">
			</a>

			<!-- Links -->
			<ul class="navbar-nav">

				<!-- Settore -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Settore </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/bilancio/settori/aggiungi">Aggiungi</a> <a
							class="dropdown-item" href="/bilancio/settori/seleziona">Seleziona</a> <a
							class="dropdown-item" href="/bilancio/settori/lista">Lista</a>
					</div></li>

				<!-- Tipologia -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Tipologia </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/bilancio/tipologie/aggiungi">Aggiungi</a> <a
							class="dropdown-item" href="/bilancio/tipologie/lista">Lista</a>
					</div></li>

				<!-- Franchising -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Franchising </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/bilancio/franchising/aggiungi">Aggiungi</a> <a
							class="dropdown-item" href="/bilancio/franchising/lista">Lista</a> 
					</div></li>

				<!-- Negozio -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Negozio </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/bilancio/negozi/aggiungi">Aggiungi</a> <a
							class="dropdown-item" href="/bilancio/negozi/lista">Lista</a> <a
							class="dropdown-item" href="/bilancio/negozi/listaFiltrata">Lista filtrata</a> 
					</div></li>
				
				<!-- TipoEntrate -->
				<li class="nav-item dropdown"><a
					class="nav-link dropdown-toggle" href="#" id="navbardrop"
					data-toggle="dropdown"> Entrate </a>
					<div class="dropdown-menu">
						<a class="dropdown-item" href="/bilancio/tipoentrate/aggiungi">Aggiungi</a> <a
							class="dropdown-item" href="/bilancio/tipoentrate/lista">Lista</a>
					</div></li>
			</ul>
		</nav>

		<div class="row">
			<div class="col-m ${errore.tipo}" role="alert">${errore.messaggio}</div>
		</div>
	
	<script src="https://code.jquery.com/jquery-3.2.1.slim.min.js"
		integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN"
		crossorigin="anonymous"></script>
	<script
		src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"
		integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q"
		crossorigin="anonymous"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"
		integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl"
		crossorigin="anonymous"></script>
</body>
</html>