<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Multilpe Select Settori</title>
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/css/select2.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/select2/4.0.0/js/select2.min.js"></script>

<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>

</head>
<link rel="stylesheet" href="/resources/css/custom.css">
<body>


	<div class="container-fluid h-100 bg-light text-dark">
		<form:form modelAttribute="settore" action="lista" method="POST">
			<div class="row justify-content-center align-items-center">
				<div class="row title">TIPOLOGIE</div>
			</div>

			<div class="row justify-content-center align-items-center h-100">
				<div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3">
					<div class="form-group">
						<form:select path="tipologies" class="mul-select"
							multiple="multiple">
							<form:options items="${listaTipologie}" itemValue="id"
								itemLabel="nome" />
						</form:select>
					</div>
				</div>
			</div>
			<hr />
			<form:form modelAttribute="tipologia" action="lista" method="POST">
				<div class="row justify-content-center align-items-center">
					<div class="row title">SETTORI</div>
				</div>
				<div class="row justify-content-center align-items-center h-100">
					<div class="col col-sm-6 col-md-6 col-lg-4 col-xl-3">
						<div class="form-group">
							<form:select path="settoris" class="mul-select"
								multiple="multiple">
								<form:options items="${listaSettori}" itemValue="id"
									itemLabel="nome" />
							</form:select>
						</div>
					</div>
				</div>
				<hr />
				<div class="border border-light p-3 mb-4">
					<div class="text-center">
						<input class="btn btn-outline-secondary" type="submit"
							value="SELEZIONA" />
					</div>
				</div>
				<div class="border border-light p-3 mb-4">
					<div class="text-center">

						<a class="btn btn-outline-secondary"
							href="http://localhost:8080/bilancio">RITORNA AL MENU</a>
					</div>
				</div>
			</form:form>
		</form:form>
	</div>

	<script>
		$(document).ready(function() {
			$(".mul-select").select2({
				placeholder : "Seleziona", //placeholder
			});
		})
	</script>
</body>
</html>




