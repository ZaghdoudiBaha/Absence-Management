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

import com.gestionProduit.Model.Matiere;
import com.gestionProduit.repository.MatiereRepository;

@Controller
@Transactional
public class MatiereController {
	@Autowired
	private MatiereRepository matiereRepo;

	
	  //Liste des etudiants
	  
	  @GetMapping(value ="/matiere") 
	  public String matiere( Model model,
	  
	  @RequestParam(name = "page", defaultValue = "0") int p,
	  
	  @RequestParam(name = "size", defaultValue = "5") int s,
	  
	  @RequestParam(name = "motCle", defaultValue = "") String motCle){
	  
	  Pageable pageable = PageRequest.of(p, s); 
	  Page<Matiere> pageMatiere= matiereRepo.chercher("%"+motCle+"%", pageable);
	  
	  model.addAttribute("matieres", pageMatiere.getContent()); int[] pages = new
	  int[pageMatiere.getTotalPages()]; model.addAttribute("pages", pages);
	  model.addAttribute("size", s); model.addAttribute("pageCourante", p);
	  model.addAttribute("motCle", motCle);
	  
	  return "matiere"; }
	 
	
	//Ajouter une matiere
	@GetMapping(value ="/ajouterMatiere")
	public String ajouterMatiere( Matiere matiere, Model model) {

		return "matiereForm";
	}
	
	
	  @PostMapping(value ="/ajouterMatiere") 
	  public String ajouterMat( @Valid Matiere matiere,BindingResult bindingResult, Model model) {
 
	  if(!bindingResult.hasErrors()) {	
		  matiereRepo.save(matiere);
	  	  return "redirect:/matiere";}
	  else
		  return "matiereForm"; }
	 
	
	
	  // Modifier une matiere
	  
	  @RequestMapping(value ="/modifierMatiere") 
	  public String modifierMatiere(Model model, Long id) { 

		  Optional<Matiere> matiere = matiereRepo.findById(id); 
		  model.addAttribute("matiere", matiere.get()); 
		  return "modifMatForm"; 
		  }
	  
	  //Supprimer une matiere
	  
	  @RequestMapping(value ="/supprimerMatiere") 
	  public String supprimerMatiere(Model model, Long id, int page, int size) {
	  
		  matiereRepo.deleteById(id);
	  
	  return "redirect:/matiere?page="+page+"&size="+size; }
	 

}
