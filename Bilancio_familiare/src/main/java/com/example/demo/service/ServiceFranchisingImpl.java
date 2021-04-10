package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.example.demo.model.Franchising;
import com.example.demo.repositories.RepositoryFranchising;

@Service
public class ServiceFranchisingImpl implements ServiceFranchising {

	@Autowired
	public RepositoryFranchising repo;
	

	@Override    
	public ResponseEntity<List<Franchising>> getAll() {
		return new ResponseEntity<List<Franchising>>(repo.findAll(Sort.by(Sort.Direction.ASC, "nome")), HttpStatus.OK);
	}

	@Override
	public ResponseEntity<String> aggiungi(Franchising frinchising) {
		//cerca frinchising nella lista
		if (repo.existsByNome_Gruppo(frinchising.getNome(), frinchising.getGruppo())==true) {
			return new ResponseEntity<String>("duplicatoRecord", HttpStatus.BAD_REQUEST);
		}
		if (repo.saveAndFlush(frinchising)!=null)
			return new ResponseEntity<String>("recordSalvato",  HttpStatus.OK);
		return new ResponseEntity<String>("recordNonSalvato", HttpStatus.BAD_REQUEST);
	}

}
