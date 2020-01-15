package com.gestionProduit.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestionProduit.Model.Classe;
import com.gestionProduit.Model.Etudiant;
import com.gestionProduit.Model.Etudiant_Matiere_Classe;
import com.gestionProduit.Model.Matiere;
import com.gestionProduit.Service.AbsenceService;
import com.gestionProduit.repository.ClassesRepository;
import com.gestionProduit.repository.EtudiantRepository;
import com.gestionProduit.repository.Etudiant_Matiere_ClasseRepository;
import com.gestionProduit.repository.MatiereRepository;

@Controller
public class GestionAbsController {

	@Autowired
	ClassesRepository classesRepo;
	
	@Autowired
	EtudiantRepository etudiantRepo;
	@Autowired
	MatiereRepository matiereRepo;
	
	@Autowired
	Etudiant_Matiere_ClasseRepository etudiant_Matiere_ClasseRepo;
	
	@Autowired
	AbsenceService absenceService;
	
	Etudiant_Matiere_Classe emc=new Etudiant_Matiere_Classe();
	
	@GetMapping("/absform")
	public String absform(Model model,Classe classe) {
		model.addAttribute("classes", classesRepo.findAll());

		return "absform";	
	}

	@GetMapping("/listEtudiantParClasse")
	public String test2(Model model,Classe classe) {

		List<Etudiant>listetud=new ArrayList<>();
		listetud= etudiantRepo.chercherParClasse(classe.getId());
		model.addAttribute("etudiants",listetud );

		return "listEtudiantParClasse";	
	}
	
	@GetMapping("/AjouterAbsence")
	public String ajouterAbsence(Model model,Etudiant_Matiere_Classe emc,Long matricule,Long id) {
		Optional<Classe> classe = classesRepo.findById(id);
		Etudiant e=etudiantRepo.findByMatricule(matricule);		
		model.addAttribute("classe", classe.get());
		model.addAttribute("etudiant", e);	
		List<Matiere> matieres= classe.get().getMatieres();
		model.addAttribute("matieres", matieres);
		model.addAttribute("emc",emc);

		return "ajouterAbsence";	
	}
	
	@PostMapping("/AjouterAbsence")
	public String ajouterAbs(Model model, @Valid Etudiant_Matiere_Classe emc,Long matricule,Long id ,BindingResult bindingResult) {
		
		if (!bindingResult.hasErrors()) {
			  etudiant_Matiere_ClasseRepo.save(emc);		  
		  return "redirect:/listEtudiantParClasse?classe="+emc.getClasse().getId();
		}

		return "ajouterAbsence";	
	}
	
	
	
	//
	
	@RequestMapping("/nbrAbsEtudiantParMatiere")
	public String nbrAbsEtudiantParMatiere(Model model,Long matricule,Long id, Long matiere) {

		Optional<Classe> classe=classesRepo.findById(id);
		List<Matiere>matieres=classe.get().getMatieres();	
		model.addAttribute("matieres", matieres);
		model.addAttribute("id", id);
		model.addAttribute("matricule", matricule);
		
		
		return "AbsEtudiantParMatiere";	
	}
	
	
	  @PostMapping("/nbrAbsEtudiantParMatiere") 
	  public String nbrAbsEtudiantParMat(Model model,@RequestParam Long matricule,@RequestParam Long id,@RequestParam Long matiere) {

		  System.out.println("****** matiere : "+ matiere);
		  String eliminatoire="";
		  Float nbr_heure = 0F;
		  Float nbr_heure_grope= 0F;
		  
		  nbr_heure =  absenceService.absenceEtudiantParMatiere(matricule, matiere);
		  System.out.println("****** matricule : "+ matricule);
		  System.out.println("****** abs : "+ nbr_heure);
		  nbr_heure_grope= absenceService.absenceParMatiere(matiere);
		  
		  if(nbr_heure_grope == null) {
			  nbr_heure_grope=0F;
		   }
		   
		   if(nbr_heure == null) {
			   nbr_heure=0F;
		   }
		   
		  Optional<Matiere> m= matiereRepo.findById(matiere);
		  if((float)m.get().getSeuil_abs() <= nbr_heure) {
			  eliminatoire="Eliminé pour cette matiére";
		  }else {
			  eliminatoire="Il n'a pas dépassé la seille de cette matiére";  
		  }
		  model.addAttribute("nbr_heure",nbr_heure);
		  model.addAttribute("eliminatoire", eliminatoire);
		  model.addAttribute("nbr_heure_grope", nbr_heure_grope);
		  
		  return "eliminationEtudiantParMatiere"; 
	  }
	 

	
	  @Scheduled(fixedRate = 604800000) 
	  public void mailingAbsence() {
		  absenceService.tousLesAbsence(); 
		  }
	 
}
