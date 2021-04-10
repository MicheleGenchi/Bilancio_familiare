package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Tipologie;
import com.example.demo.repositories.RepositoryTipologie;

@Service
public class ServiceTipologieImpl implements ServiceTipologie {


	@Autowired
	public RepositoryTipologie repo;
	
	@Override
	public ResponseEntity<List<Tipologie>> getAll() {
		if (repo.findAll()!=null)
			return new ResponseEntity<List<Tipologie>>(repo.findAll(Sort.by(Sort.Direction.ASC, "nome")), HttpStatus.OK);
		return null;
	}

	@Override
	public ResponseEntity<String> aggiungi(Tipologie tipologia) {

		if (repo.existsByNome(tipologia.getNome())) 
			return new ResponseEntity<String>("duplicatoRecord", HttpStatus.BAD_REQUEST);
		
		if (repo.saveAndFlush(tipologia)!=null)
			return new ResponseEntity<String>("recordSalvato", HttpStatus.OK);
		
		return new ResponseEntity<String>("recordNonSalvato", HttpStatus.BAD_REQUEST);
	}
}
