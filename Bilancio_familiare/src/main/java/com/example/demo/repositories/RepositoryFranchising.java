package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Franchising;

@Repository
public interface RepositoryFranchising extends JpaRepository<Franchising, Integer>{
	
	//un frinchising non può avere lo stesso nome e gruppo
	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END FROM Franchising f WHERE f.nome = :nome and f.gruppo = :gruppo")
	public boolean existsByNome_Gruppo(@Param("nome") String nome, @Param("gruppo") String gruppo);

}
