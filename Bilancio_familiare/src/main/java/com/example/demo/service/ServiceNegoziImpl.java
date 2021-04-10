package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.model.Negozi;
import com.example.demo.repositories.RepositoryNegozi;

@Service
public class ServiceNegoziImpl implements ServiceNegozi {

	@Autowired
	public RepositoryNegozi repo;
	
	@Override
	public ResponseEntity<List<Negozi>> getAll() {
		List<Negozi> lista=repo.findAll(Sort.by(Sort.Direction.ASC, "nome"));
		return new ResponseEntity<List<Negozi>>(lista, HttpStatus.OK);
	}
	
	
	@Override
	public ResponseEntity<List<Negozi>> getByTipFra(Integer idTipologia, Integer idFranchising) {
		List<Negozi> lista=repo.findByTipFra(idTipologia, idFranchising);
		if (lista.size()>0)
			return new ResponseEntity<List<Negozi>>(lista, HttpStatus.OK);
		return new ResponseEntity<List<Negozi>>(lista, HttpStatus.BAD_REQUEST);
	}
	
	@Override
	public ResponseEntity<String> aggiungi(Negozi negozio, DUPLICATE duplicate) {
		
		if (duplicate==DUPLICATE.CHECK) {
			if (repo.existsNegozioIndirizzo(negozio.getIndirizzo())) 
				return new ResponseEntity<String>("duplicatoNegozio", HttpStatus.BAD_REQUEST);
		}

		if (repo.saveAndFlush(negozio)!=null)
			return new ResponseEntity<String>("recordSalvato", HttpStatus.OK);
		
		return new ResponseEntity<String>("recordNonSalvato", HttpStatus.BAD_REQUEST);
	}
	
}
