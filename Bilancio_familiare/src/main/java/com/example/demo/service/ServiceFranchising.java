package com.example.demo.service;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.example.demo.model.Franchising;

public interface ServiceFranchising {

	ResponseEntity<List<Franchising>> getAll();
	ResponseEntity<String> aggiungi(Franchising franchising);
}
