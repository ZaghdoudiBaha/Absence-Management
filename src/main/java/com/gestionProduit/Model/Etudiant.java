package com.gestionProduit.Model;



import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor

public class Etudiant {

	@Id  @GeneratedValue(strategy = GenerationType.AUTO)
	private Long matricule;
	@Size(min=3,max=20)
	private String nom;
	@Size(min=3,max=20)
	private String prenom;

	@Email
	private String email;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate date_naiss;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Classe classe=new Classe();
	
	@OneToMany(mappedBy = "etudiant" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private  List<Etudiant_Matiere_Classe> etudiant_matiere_classe;

	
}
