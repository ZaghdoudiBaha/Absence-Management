package com.gestionProduit.Service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gestionProduit.Model.Etudiant;
import com.gestionProduit.Model.Matiere;
import com.gestionProduit.repository.Etudiant_Matiere_ClasseRepository;

@Transactional
@Service
public class AbsenceServiceImp implements AbsenceService {
	
	@Autowired
	Etudiant_Matiere_ClasseRepository emcRepository;
	@Autowired
	SendMailService sendMail;

	@Override
	public Float absenceEtudiantParMatiere(Long matricule, Long id) {
		
		return emcRepository.calculerAbsenceParMatiere(matricule, id);
	}

	@Override
	public Float absenceParMatiere(Long id) {

		return emcRepository.calculerAbsenceGroupeParMatiere(id);
	}
 
	@Override
	public void tousLesAbsence() {
		String message ;String introduction;
		List<Etudiant> etudiantAbsent= emcRepository.tousLesEtudiantAbsent();
		for (int i=0;i < etudiantAbsent.size();i++) {
			 message="";introduction="";
			 introduction="Bonjour, \n Je vous informe Mr/Mlle "+etudiantAbsent.get(i).getNom() +" "+
					etudiantAbsent.get(i).getPrenom()+" que tu es élminer pour les matiéres suivantes : \n ";;
			System.out.println(introduction);
			List<Matiere> matieresDesEtudiantAbsent = etudiantAbsent.get(i).getClasse().getMatieres();
			for (int j=0;j < matieresDesEtudiantAbsent.size();j++) { 
					Float nbr = absenceEtudiantParMatiere(etudiantAbsent.get(i).getMatricule(),matieresDesEtudiantAbsent.get(j).getId());
					if(nbr==null) {nbr=0F;}
					 
					if(nbr >=  matieresDesEtudiantAbsent.get(j).getSeuil_abs()) {
						message = message + matieresDesEtudiantAbsent.get(j).getLabel() +" : "+nbr+". \n";
						System.out.println(message);
					}
				}
			if(message != "") {
				sendMail.sendEmail(etudiantAbsent.get(i).getEmail(), "Avis d'absence", introduction+message);
			}
				
			} 
		
	 }

}
