package com.example.demo.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.model.Tipoentrate;
import com.example.demo.repositories.RepositoryTipoentrate;

@Service
public class ServiceTipoentrateImpl implements ServiceTipoentrate {

	@Autowired
	public RepositoryTipoentrate repo;
	
	@Override
	public ResponseEntity<List<Tipoentrate>> getAll() {
		return new ResponseEntity<List<Tipoentrate>>(repo.findAll(Sort.by(Sort.Direction.ASC, "descrizione")), HttpStatus.OK);
	}
	
	@Override
	public ResponseEntity<String> aggiungi(Tipoentrate tipoentrate, DUPLICATE duplicate) {
		
		if (duplicate==DUPLICATE.CHECK) {
			if (repo.existsByDescrizione(tipoentrate.getDescrizione())) 
				return new ResponseEntity<String>("duplicatoRecord", HttpStatus.BAD_REQUEST);
		}
		
		if (repo.saveAndFlush(tipoentrate)!=null)
			return new ResponseEntity<String>("recordSalvato", HttpStatus.OK);
		
		return new ResponseEntity<String>("recordNonSalvato", HttpStatus.BAD_REQUEST);
	}


	
}
