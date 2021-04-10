package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Tipoentrate;

public interface ServiceTipoentrate {

	ResponseEntity<List<Tipoentrate>> getAll();
	ResponseEntity<String> aggiungi(Tipoentrate tipoentrate, DUPLICATE duplicate); 
}
