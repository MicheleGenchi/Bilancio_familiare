<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!doctype html>
<html lang="it">
<head>
<title>Prove datapicker</title>
<link rel="shortcut icon" href="#">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="/resources/css/custom.css">
<link rel="stylesheet" href="/resources/css/tabella.css">
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-3">
				<label for="startdate">INIZIO</label> <input type="date"
					id="startdate" name="startdate" class="form-control"
					ng-model="patient.DateOfBirth" format="dd/mm/yyyy" required="true" />
				<span class="input-group-btn"> <label for="startdate"
					class="btn btn-default" ng-disabled="patient.PatientId"
					ng-click="open()"> </label>
				</span>
			</div>
			<div class="col-lg-3">
				<label for="enddate">FINE</label> <input type="date" id="enddate"
					name="enddate" class="form-control" ng-model="patient.DateOfBirth"
					format="dd/mm/yyyy" required="true" /> <span
					class="input-group-btn"> <label for="enddate"
					class="btn btn-default" ng-disabled="patient.PatientId"
					ng-click="open()"> </label>
				</span>
			</div>

			<div class="col-lg-3">
				<label for="enddate">Linea di comando</label> <input id="commandLine"
					class="form-control" type="text"
					placeholder="selezionato in tabella " />
			</div>
		</div>
		<div class="alert alert-danger" id="alert"></div>
		<hr />
		<div class="row">
			<div class="col-lg-6" id="speseFiltrate">
				Spese filtrate da <span class="etichetta" id="da"></span> a <span
					class="etichetta" id="a"></span>
			</div>
			<div class="col-lg-6">
				<a href="#" onclick="avviaFiltro()" id="caricaTabella"
					class="btn btn-outline-secondary">CARICA TABELLA</a>
			</div>
		</div>
		<hr />
		<div class="row">
			<div class="col-lg-3">
				<div class="span2">
					<a href="http://localhost:8080/bilancio"
						class="btn btn-secondary btn-block">RITORNA AL MENU</a>
				</div>
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script
		src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.bundle.min.js"></script>
	<script type="text/javascript" src="/resources/js/jsSpeseTable.js"></script>

<script>
	var globalCurrent=null;
	var globalId=0;
	var globalCol="";
	var flagDateCorrette = false;
	function getDateFormat(data) {
		var mydate = new Date(data);
		var dd = mydate.getDate();
		var mm = mydate.getMonth() + 1;
		var yyyy = mydate.getFullYear();

		if (dd < 10) {
			dd = '0' + dd;
		}

		if (mm < 10) {
			mm = '0' + mm;
		}
		return dd + "/" + mm + "/" + yyyy;
	}

	function toDate(data) {
		var pattern = "\d{1,2}/\d{1,2}/\d{4}";
		alert("data  = " + data + "\n" + "replace = "
				+ new Date(data.replace(pattern, '$3-$2-$1')));
		return new Date(data.replace(pattern, '$3-$2-$1'));
	}

	$(document).ready(function() {
		$("#alert").hide();
		$("#startdate, #enddate").focus(function() {
			$("#alert").hide()
		});
		$("#startdate").change(function() {
			startD = getDateFormat($(this).val());
			//alert("startdate : " + $(this).val()+ "\nstartD = "+startD);
			$("#caricaTabella").show();
			$("span#da").text(startD);
			$("#enddate").val($(this).val());
		});

		$("#enddate").change(function() {
			endD = getDateFormat($(this).val());
			//alert("enddate : " + $(this).val()+"\nendD = "+endD);
			$("#caricaTabella").show();
			$("span#a").text(endD);
			invia();
		});

	});


	
	function invia() {
		//alert("startD = " + startD + "      endD = " + endD);
		if ($("#enddate").val() > $("#startdate").val()) {
			$("#alert").hide();
			flagDateCorrette = true;
		} else {
			$("#alert").show();
			$("#alert").text("end date é minore di start date");
			flagDateCorrette = false;
		}
	}
</script>
<script>
	function avviaFiltro() {
		var urlHead = "http://localhost:8080/bilancio/spese/intestazione";
		var urlBody = "http://localhost:8080/bilancio/spese/lista/"
				+ $('#startdate').val() + "/" + $('#enddate').val();
		if (flagDateCorrette == true) {
			$("#caricaTabella").hide();
			createTable(urlHead, urlBody);
		}
	}
</script>
</body>
</html>