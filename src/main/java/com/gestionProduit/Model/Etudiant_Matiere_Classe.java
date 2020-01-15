package com.gestionProduit.Model;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity(name = "emc")

public class Etudiant_Matiere_Classe {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date_seance;
	@Min(value = 1)
	@Max(value = 2)
	private int nbr_seance;
	
	
	@ManyToOne
	private Etudiant etudiant;
	@ManyToOne
	private Matiere matiere;
	@ManyToOne
	private Classe classe;
	
}
