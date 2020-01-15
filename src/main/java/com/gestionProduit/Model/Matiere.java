package com.gestionProduit.Model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Matiere {

	@Id  @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min=2,max=20)
	private String label;
	@Min(value = 1)
	@Max(value = 60)
	private int nb_heure;
	@Min(value = 1)
	@Max(value = 30)
	private int seuil_abs;
	
	@ManyToMany(mappedBy ="matieres", fetch = FetchType.LAZY,cascade = CascadeType.ALL)	
	private List<Classe> classes;
	
	@OneToMany(mappedBy = "matiere" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private  List<Etudiant_Matiere_Classe> etudiant_matiere_classe;

	
}
