package com.example.demo.repositories;

import java.util .Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.EntrateUscite;

public interface RepositorySpese extends JpaRepository<EntrateUscite, Integer>{

	@Query("SELECT s FROM EntrateUscite s "
			+ "LEFT JOIN s.negozi u "
			+ "LEFT JOIN s.tipoentrate e")  
	public List<EntrateUscite> findAll();
	
	@Query("SELECT s FROM EntrateUscite s "
			+ "LEFT JOIN s.negozi u "
			+ "LEFT JOIN s.tipoentrate "  
			+ "WHERE s.data BETWEEN :startDate AND :endDate")
	public List<EntrateUscite> findByDate(@Param("startDate") Date startDate , @Param("endDate") Date endDate);
}
