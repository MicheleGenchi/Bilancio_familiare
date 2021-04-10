package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Settori;
import com.example.demo.model.Tipologie;


@Repository
public interface RepositorySettori extends JpaRepository<Settori, Integer>{

	//un settore non può avere lo stesso nome
	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END FROM Settori s WHERE s.nome = :nome")
	public boolean existsByNome(@Param("nome") String nome);

	//dato un settore di riferimento Example<Settori>, lo cerco nel DB per aggiornare le sue
	//tipologie
	default public void update(Example<Settori> settore, List<Tipologie> tipologies) {
		Settori tempSettore=this.findOne(settore).get();
		tempSettore.setTipologies(tipologies);
		this.saveAndFlush(tempSettore);
	}

}
