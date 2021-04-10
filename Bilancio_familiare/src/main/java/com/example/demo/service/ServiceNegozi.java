package com.example.demo.service;

import java.util.List;
import org.springframework.http.ResponseEntity;
import com.example.demo.model.Negozi;

public interface ServiceNegozi {
	
	ResponseEntity<List<Negozi>> getAll();
	ResponseEntity<String> aggiungi(Negozi negozio, DUPLICATE duplicate);
	ResponseEntity<List<Negozi>> getByTipFra(Integer idTipologia, Integer idFranchising);
	
}
