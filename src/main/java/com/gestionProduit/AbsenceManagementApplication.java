package com.gestionProduit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.gestionProduit.repository.ClassesRepository;
import com.gestionProduit.repository.EtudiantRepository;
@EnableScheduling
@SpringBootApplication
public class AbsenceManagementApplication {

	public static void main(String[] args) {
		ApplicationContext ctx = SpringApplication.run(AbsenceManagementApplication.class, args);
		
		EtudiantRepository er =ctx.getBean(EtudiantRepository.class);
		ClassesRepository cr =ctx.getBean(ClassesRepository.class);
		
		/*
		 * Classe classe1 = new Classe("Tic 1 s", "Tic 1 s", null, null); Classe classe2
		 * = new Classe("GLSI 2 D", "GLSI 2 D", null, null); cr.save(classe1);
		 * cr.save(classe2); Etudiant etud1 =new Etudiant("ayoub", "belgasem",
		 * "ayoub@gmail.com", LocalDate.of(1992,05,30), classe1); Etudiant etud2 =new
		 * Etudiant("Imed", "Imed", "Imed@gmail.com", LocalDate.of(2000,04,25),
		 * classe1); Etudiant etud3 =new Etudiant("imen", "imen", "imen@gmail.com",
		 * LocalDate.of(1998,03,28), classe1); Etudiant etud4 =new Etudiant("med",
		 * "med", "med@gmail.com", LocalDate.of(1980,03,15), classe2); Etudiant etud5
		 * =new Etudiant("ines", "ines", "ines@gmail.com", LocalDate.of(1990,01,30),
		 * classe2); Etudiant etud6 =new Etudiant("ayoub", "belgasem",
		 * "ayoub@gmail.com", LocalDate.of(1992,05,30), classe1); Etudiant etud7 =new
		 * Etudiant("Imed", "Imed", "Imed@gmail.com", LocalDate.of(2000,04,25),
		 * classe1); Etudiant etud8 =new Etudiant("imen", "imen", "imen@gmail.com",
		 * LocalDate.of(1998,03,28), classe1); Etudiant etud9 =new Etudiant("med",
		 * "med", "med@gmail.com", LocalDate.of(1980,03,15), classe2); Etudiant etud10
		 * =new Etudiant("ines", "ines", "ines@gmail.com", LocalDate.of(1990,01,30),
		 * classe2); Etudiant etud11 =new Etudiant("ayoub", "belgasem",
		 * "ayoub@gmail.com", LocalDate.of(1992,05,30), classe1); Etudiant etud12 =new
		 * Etudiant("Imed", "Imed", "Imed@gmail.com", LocalDate.of(2000,04,25),
		 * classe1); Etudiant etud13 =new Etudiant("imen", "imen", "imen@gmail.com",
		 * LocalDate.of(1998,03,28), classe1); Etudiant etud14 =new Etudiant("med",
		 * "med", "med@gmail.com", LocalDate.of(1980,03,15), classe2); Etudiant etud15
		 * =new Etudiant("ines", "ines", "ines@gmail.com", LocalDate.of(1990,01,30),
		 * classe2); er.save(etud1); er.save(etud2); er.save(etud3); er.save(etud4);
		 * er.save(etud5); er.save(etud6); er.save(etud7); er.save(etud8);
		 * er.save(etud9); er.save(etud10); er.save(etud11); er.save(etud12);
		 * er.save(etud13); er.save(etud14); er.save(etud15);
		 */
	}

}
