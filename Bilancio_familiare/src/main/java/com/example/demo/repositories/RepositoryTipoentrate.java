package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Tipoentrate;

@Repository
public interface RepositoryTipoentrate extends JpaRepository<Tipoentrate, Integer>{

	//un'entrata non può avere la stessa descriozne
	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END FROM Tipoentrate t WHERE t.descrizione = :descrizione")
	public boolean existsByDescrizione(@Param("descrizione") String descrizione);

}
