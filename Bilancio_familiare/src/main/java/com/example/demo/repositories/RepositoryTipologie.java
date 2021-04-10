package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tipologie;


@Repository
public interface RepositoryTipologie extends JpaRepository<Tipologie, Integer>{

	//un settore non può avere lo stesso nome
	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END FROM Tipologie t WHERE t.nome = :nome")
	public boolean existsByNome(@Param("nome") String nome);
}
