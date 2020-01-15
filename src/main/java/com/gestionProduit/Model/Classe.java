package com.gestionProduit.Model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.validation.constraints.Size;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Classe {
	
	@Id  
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Size(min=1,max=20)
	private String label;
	@Size(min=3,max=50)
	private String nom_complet;
	
	@OneToMany(mappedBy = "classe", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private List<Etudiant> etudiants = new ArrayList<>();

	@ManyToMany
	private List<Matiere> matieres = new ArrayList<>();
	
	@OneToMany(mappedBy = "classe" ,fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List <Etudiant_Matiere_Classe> etudiant_matiere_classe;
	
	
}
