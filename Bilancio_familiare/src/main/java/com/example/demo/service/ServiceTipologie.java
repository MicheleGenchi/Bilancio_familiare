package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Tipologie;

public interface ServiceTipologie {
	
	ResponseEntity<List<Tipologie>> getAll();
	ResponseEntity<String> aggiungi(Tipologie tipologia); 
}
