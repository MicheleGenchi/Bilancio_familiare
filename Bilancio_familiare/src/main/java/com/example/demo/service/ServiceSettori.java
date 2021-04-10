package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Settori;

public interface ServiceSettori {
	
	ResponseEntity<List<Settori>> getAll();
	ResponseEntity<String> aggiungi(Settori settore, DUPLICATE duplicate);
	
}
