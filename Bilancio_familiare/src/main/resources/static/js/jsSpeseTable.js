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

function update(current, id, tipo) {
	alert("CURRENT = "+current.value+"\nID = "+id+"\nTIPO = "+tipo);
	$(globalCurrent).css("color", "black");
	globalCurrent=current;
	globalId=id;
	$(current).css("color", "red");
	$('#commandLine').attr('type', tipo);
	$('#commandLine').val(current.value);
}

$("#commandLine").change(function() {
	alert("globalCurrent = "+globalCurrent);
	$(globalCurrent).val(this.value);
});

function cancella(id) {
	var risposta=confirm("Sei sicuro di voler rimuovere la spesa con id  "+id+"?");
	if (risposta==true) { 
		//cancella spesa
		alert("spesa cancellata con successo");
		//rendering (ricarica la tabella)
	}
}

function bodyTable(urlBody) {
	var count = 1;
	$.get(urlBody, function(dati, status, jqXHR) {
		if (status == "success") {
			$('<tbody>').appendTo('#table');
			//crea form di input per l'inserimento

			$('</tr>').appendTo('#table');
			$(dati).each(function(i, spesa) {
				$('<tr>').appendTo('#table')
					.append($('<th scope="row" />').text(count++))
					.append($('<td onclick="cancella('+spesa.id+')" />').text(spesa.id))
					.append($('<td><input id="data" value="'+spesa.data+'" onclick="update(this,'+spesa.id+',\'date\')" type="date" readonly/></td>'))
					.append($('<td><input id="descrizione" value="'+spesa.descrizione+'" onclick="update(this,'+spesa.id+',\'text\')" type="text" readonly/></td>'))
					.append($('<td><input id="importo" value="'+spesa.importo+'" onclick="update(this,'+spesa.id+',\'text\')" type="text" readonly/></td>'))
					.append($('<td><input id="negozio" value="'+(spesa.negozi!=null?spesa.negozi.nome:"")+'" onclick="update(this,'+spesa.id+',\'text\')" type="text" readonly/></td>'))
					.append($('<td><input id="entrata" value="'+(spesa.tipoEntrate!=null?spesa.tipoEntrate.descrizione:"")+'" onclick="update(this,'+spesa.id+',\'text\')" type="text" readonly/></td>'))
					.append($('</tr>'));
			});
			$('</tbody>').appendTo('#table');
			return;
		}
	})
	.fail(function() {
		$("#table").remove();
		$("p.error").remove();
		$("<div class='alert alert-danger error' >negozi non trovati per la tipologia ed il franchising</div>").appendTo($("#speseFiltrate"));
	});
}


function createTable(urlHead, urlBody) {
	$("#table").empty();
	$("p.error").remove();
	$('.alert').remove();
	var table = $('<table class="table3" id="table" />').appendTo($("#speseFiltrate"));
	headTable(urlHead);
	bodyTable(urlBody);
};




