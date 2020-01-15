package com.gestionProduit.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestionProduit.Model.Classe;

public interface ClassesRepository extends JpaRepository<Classe, Long> {
	

	  @Query ("select e from Classe e where e.label like :x")
	  public Page<Classe> chercher (@Param("x") String motCle , Pageable pageable);
	  
	 // public List<Classe> findByMatieres(List<Matiere> matieres);
}
