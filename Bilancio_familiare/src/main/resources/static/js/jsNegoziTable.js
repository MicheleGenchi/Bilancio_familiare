function headTable(urlHead) {
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

function bodyTable(urlBody) {
	var count = 1;
	$.get(urlBody, function(dati, status, jqXHR) {
		if (status == "success") {
			$('<tbody>').appendTo('#table');
			$(dati).each(function(i, negozio) {
				$("<tr/>").appendTo('#table')
					.append($('<th scope="row" />').text(count++))
					.append($('<td />').text(negozio.id))
					.append($('<td />').text(negozio.nome))
					.append($('<td />').text(negozio.indirizzo));
			});
			$('</tbody>').appendTo('#table');
			return;
		}
	})
	.fail(function() {
		$("#table").remove();
		$("p.error").remove();
		$("<div class='alert alert-danger error' >negozi non trovati per la tipologia ed il franchising</div>").appendTo($("#negoziFiltrati"));
	});
}


function createTable(urlHead, urlBody) {
	$("#table").empty();
	$("p.error").remove();
	$('.alert').remove();
	var table = $('<table class="table3" id="table" />').appendTo($("#negoziFiltrati"));
	headTable(urlHead);
	bodyTable(urlBody);
};




