package com.gestionProduit.controller;

import java.util.List;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.gestionProduit.Model.Classe;
import com.gestionProduit.Model.Etudiant;
import com.gestionProduit.repository.ClassesRepository;
import com.gestionProduit.repository.EtudiantRepository;

@Controller
@Transactional
public class EtudiantController {

	@Autowired
	private EtudiantRepository etudiantRepo;
	
	@Autowired
	private ClassesRepository classesRepo;
	


	//Liste des etudiants
	@GetMapping(value ="/etudiant")
	public String etudiant( Model model,
			  @RequestParam(name = "page", defaultValue = "0") int p,
			  @RequestParam(name = "size", defaultValue = "5") int s,
			  @RequestParam(name = "motCle", defaultValue = "") String motCle){
		
		Pageable pageable = PageRequest.of(p, s);
		Page<Etudiant> pageEtudiant= etudiantRepo.chercher("%"+motCle+"%", pageable);
																									
		model.addAttribute("etudiants", pageEtudiant.getContent());
		int[] pages = new int[pageEtudiant.getTotalPages()]; 
		model.addAttribute("pages", pages);
		model.addAttribute("size", s);             
		model.addAttribute("pageCourante", p); 
		model.addAttribute("motCle", motCle);
 
		return "etudiant";
	}
	
	//Ajouter un etudiant
	@GetMapping(value ="/ajouterEtudiant")
	public String ajouterEtudiant( Model model,@Valid Etudiant etudiant) {
		List<Classe> classes =classesRepo.findAll();
		model.addAttribute("classes", classes);
 
		return "etudiantForm";
	}
	
	
	  @PostMapping(value ="/ajouterEtudiant") 
	  public String ajouterEtudiantClasse( Model model,@Valid Etudiant etudiant, BindingResult bindingResult) {
 
	  if(!bindingResult.hasErrors()) {	
		etudiantRepo.save(etudiant);
	  	return "redirect:/etudiant";}
	  else
		return "etudiantForm";
	  
	   }
	 
	
	// Modifier un etudiant
	@GetMapping("/modifierEtudiant")
	public String modifierEtudiant( Model model, Long matricule) {
		List<Classe> classes =classesRepo.findAll();
		model.addAttribute("classes", classes);
		Etudiant e = etudiantRepo.findByMatricule(matricule);
		 model.addAttribute("etudiant", e);

		return "modifEtudForm";
	}
	
	
	  @PostMapping("/modifierEtudiant") 
	  public String save( Model model, Long matricule, @Valid Etudiant etudiant, BindingResult bindingResult) {
	  
	  if(!bindingResult.hasErrors()) { 
		  etudiantRepo.save(etudiant); 
		  return "redirect:/etudiant";}
	  else 
		  return "modifEtudForm";
	  
	  }
	 
	 
	
	
	//Supprimer un etudiant
	@RequestMapping(value ="/supprimerEtudiant")
	public String supprimerEtudiant( Model model, Long matricule, int page, int size) {

		etudiantRepo.deleteByMatricule(matricule);
		
		return "redirect:/etudiant?page="+page+"&size="+size;
	}

	
}
