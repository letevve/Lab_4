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
	
	/*
	 * restituisce l'oggetto corso il cui codice � passato come parametro
	 */
	/*public Corso trovaCorso(String codCorso){
		CorsoDAO cd = new CorsoDAO();
		Corso ctemp = new Corso(null, 0, null, 0);
		ctemp = cd.getCorso(codCorso);
		
		return ctemp;
	}*/
	
	/*
	 * riceve il codice del corso di cui deve restituire la lista degli iscritti
	 */
	public List<Studente> cercaIscrittiAlCorso(String nomeCorso){
		
		//chiama il metodo cercaIscritti su un oggetto della classe CorsoDAO
		CorsoDAO cd = new CorsoDAO();
		List<Studente> listaStud= new LinkedList<Studente>();
		Corso corso = null;
		for(Corso c : cd.getTuttiICorsi()){
			if(c.getNome().compareTo(nomeCorso)==0){
				corso=c;
				break;
			}
		}
		listaStud = cd.getStudentiIscrittiAlCorso(corso);
		
		return listaStud;
	}

}
