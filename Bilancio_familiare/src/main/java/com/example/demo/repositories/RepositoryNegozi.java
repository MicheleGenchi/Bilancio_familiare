package com.example.demo.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Negozi;

@Repository
public interface RepositoryNegozi extends JpaRepository<Negozi, Integer> {

	
	@Query("SELECT n FROM Negozi n "
			+ "left join n.tipologie t "
			+ "left join n.franchising f "
			+ "where t.id = :idTip and f.id = :idFra")
	public List<Negozi> findByTipFra(@Param("idTip") Integer idTipologia, @Param("idFra") Integer idFranchising);
	
	@Query("SELECT CASE WHEN COUNT(*) > 0 THEN 'true' ELSE 'false' END FROM Negozi n "
			+ "Where n.indirizzo = :indirizzo")
	public boolean existsNegozioIndirizzo(@Param("indirizzo") String indirizzo);
	
}
