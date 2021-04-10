var urlHead = "http://localhost:8080/bilancio/settori/intestazione";
var urlBody = "http://localhost:8080/bilancio/settori/settoriPerTipologia/";

function headTable() {
	$.get(urlHead, function(dati, status, jqXHR) {
		if (status == "success") {
			var tbl='<thead><tr scope="row" >';
			$(dati).each(function(i, val) {
				tbl+='<th scope="col" >'+val+'</th>'; 		
			});
			tbl+='</thead></tr>';
			$('#table').append(tbl);							
		}
	});
}

function bodyTable(id) {
	var count = 1;
	$.get(urlBody + id, function(dati, status, jqXHR) {
		if (status == 'success') {
			$('<tbody>').appendTo('#table');
			$(dati).each(function(i, settori) {
				$("<tr/>").appendTo('#table')
					.append($('<th scope="row" />').text(count++))
					.append($('<td />').text(settori.id))
					.append($('<td />').text(settori.nome));
			});
			$('</tbody>').appendTo('#table');
		} 
	}).fail(function() {
		$("#table").remove();
		$("p.error").remove();
		$("<div class='alert alert-danger error'>non ci sono settori per la tipologia</div>").appendTo($("#settorifiltrati"));
	});
}

function createTable(id) {
	$('<table class="table3" id="table" />').appendTo($("#settorifiltrati"));
	headTable();
	bodyTable(id);
};


$(document).ready(
	function() {
		$('select').change(function() {
			$("#settorifiltrati").empty();
			var id = $(this).val();
			createTable(id);
		});
	});