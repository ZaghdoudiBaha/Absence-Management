package com.gestionProduit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestionProduit.Model.Matiere;

public interface MatiereRepository extends JpaRepository<Matiere, Long>{
	
	  @Query ("select m from Matiere m where m.label like :x")
	  public Page<Matiere> chercher (@Param("x") String motCle , Pageable pageable);

}
