package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Settori;
import com.example.demo.model.Tipologie;
import com.example.demo.repositories.RepositorySettori;

@Service
public class ServiceSettoriImpl implements ServiceSettori {

	@Autowired
	public RepositorySettori repo;
	
	@Override
	public ResponseEntity<List<Settori>> getAll() {
		return new ResponseEntity<List<Settori>>(repo.findAll(Sort.by(Sort.Direction.ASC, "nome")), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> aggiungi(Settori settore, DUPLICATE duplicate) {
		//cerca settore nella lista
		if (duplicate==DUPLICATE.CHECK) {
			if (repo.existsByNome(settore.getNome())) 
				return new ResponseEntity<String>("duplicatoRecord", HttpStatus.BAD_REQUEST);
		}
		
		if (repo.saveAndFlush(settore)!=null)
			return new ResponseEntity<String>("recordSalvato", HttpStatus.OK);
		
		return new ResponseEntity<String>("recordNonSalvato", HttpStatus.BAD_REQUEST);
	}

	public void aggiungiSettoreTipologia(Settori settore, List<Tipologie> tipologie) {
		repo.update(Example.of(settore) , tipologie);
	}
}
