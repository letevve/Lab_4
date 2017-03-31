package it.polito.tdp.lab04.model;

import java.util.*;

import it.polito.tdp.lab04.DAO.CorsoDAO;
import it.polito.tdp.lab04.DAO.StudenteDAO;

public class Model {
	
	public List<String> getListaCorsi() {
		
		LinkedList<String> listaCodici = new LinkedList<String>();
		CorsoDAO cd = new CorsoDAO();
		for(Corso c : cd.getTuttiICorsi() ){
			listaCodici.add(c.getNome());
		}
		
		return listaCodici;
	}
	
	public Studente cercaStudente(String matricola){
		
		StudenteDAO sd = new StudenteDAO();
		Studente temp = new Studente(0, null, null, null);
		temp = sd.trovaStudente(matricola);
		
		return temp;
	}

}
