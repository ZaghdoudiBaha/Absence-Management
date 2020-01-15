package com.gestionProduit.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.gestionProduit.Model.Etudiant;
import com.gestionProduit.Model.Etudiant_Matiere_Classe;

public interface Etudiant_Matiere_ClasseRepository extends JpaRepository<Etudiant_Matiere_Classe, Long> {

	@Query("select sum(e.nbr_seance * 1.5) from emc e where etudiant.matricule = :x and matiere.id = :y")
	public Float calculerAbsenceParMatiere(@Param("x")Long matricule , @Param("y") Long id);
	
	@Query("select sum(e.nbr_seance * 1.5) from emc e where matiere.id = :x")
	public Float calculerAbsenceGroupeParMatiere(@Param("x") Long id);
	
	@Query("select distinct etudiant from emc" )
	public List<Etudiant> tousLesEtudiantAbsent();

}
