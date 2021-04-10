package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.example.demo.model.EntrateUscite;
import com.example.demo.repositories.RepositorySpese;

@Service
public class ServiceSpeseImpl implements ServiceSpese {

	@Autowired
	public RepositorySpese repo;
	
	@Override    
	public List<EntrateUscite> getAll() {
		List<EntrateUscite> lista=repo.findAll(Sort.by(Sort.Direction.ASC, "data"));
		//lista.forEach(System.out::println);
		return lista;
	}

	public List<EntrateUscite> getSpeseByDate(Date startDate, Date endDate) {
		List<EntrateUscite> lista=repo.findByDate(startDate, endDate);
		//lista.forEach(System.out::println);
		return lista;
	}
}
