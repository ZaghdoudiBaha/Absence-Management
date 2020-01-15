package com.gestionProduit.controller;

import java.util.Optional;

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
import com.gestionProduit.repository.ClassesRepository;
import com.gestionProduit.repository.MatiereRepository;
@Transactional
@Controller
public class ClasseController {

	@Autowired
	private ClassesRepository classesRepo;
	@Autowired
	private MatiereRepository matiereRepo;
	


	//Liste des classe
	@RequestMapping(value ="/classe")
	public String classe( Model model,
			  @RequestParam(name = "page", defaultValue = "0") int p,
			  @RequestParam(name = "size", defaultValue = "5") int s,
			  @RequestParam(name = "motCle", defaultValue = "") String motCle){
		
		Pageable pageable = PageRequest.of(p, s);
		Page<Classe> pageClasse= classesRepo.chercher("%"+motCle+"%", pageable);
																									
		model.addAttribute("classes", pageClasse.getContent());
		  int[] pages = new int[pageClasse.getTotalPages()]; 
		  model.addAttribute("pages", pages);
		  model.addAttribute("size", s);             
		  model.addAttribute("pageCourante", p); 
		  model.addAttribute("motCle", motCle);
 
		return "classe";
	}
	
	
	  //Ajouter un classe
	  
	  @GetMapping(value ="/ajouterClasse") 
	  public String displayFormClasse(Model model,@Valid Classe classe, BindingResult bindingResult) {
		  
		  
		  model.addAttribute("matiers",matiereRepo.findAll());
		  return "classeForm";   
	  }
	  
	  
	  @PostMapping(value ="/ajouterClasse") 
	  public String ajouterClasse(Model model,@Valid Classe classe, BindingResult bindingResult) {

	  if(!bindingResult.hasErrors()) { 
		  classesRepo.save(classe);
	  	  return "redirect:/classe";}
	  	  else 
	  		return "classeForm";
	  
	  }
	  
	
	
	  // Modifier une classe
	  
	  @GetMapping(value ="/modifierClasse") 
	  public String modifierClasse(Model model, Long id) { 
		  Optional<Classe> classe = classesRepo.findById(id); 
		  model.addAttribute("classe",classe.getClass()); 
		  model.addAttribute("matiers",matiereRepo.findAll());
		  
		  return "modifClasseForm"; 
		  }
	 
	  
	  //Supprimer un etudiant
	  
	  @RequestMapping(value ="/supprimerClasse") 
	  public String supprimerClasse(Model model, Long id, int page, int size,String motCle) {
	  
		  classesRepo.deleteById(id);
	  
	  return "redirect:/classe?page="+page+"&size="+size; }
	 
	 
	
}
