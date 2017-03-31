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
	 * restituisce l'oggetto corso il cui codice è passato come parametro
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
				corso.toString();
				break;
			}
		}
		corso.toString();
		listaStud = cd.getStudentiIscrittiAlCorso(corso);
		
		return listaStud;
	}
	
	/*
	 * riceve come parametro la matricola di uno studente e restituisce
	 * la lista di corsi a cui quello studente è iscritto
	 */
	public List<Corso> cercaCorsiIscrizione(String matricola){
		//chiama il metodo di ricerca corsi sul DAO
		StudenteDAO sd = new StudenteDAO();
		List<Corso> corsiIscritto = new LinkedList<Corso>();
		corsiIscritto = sd.cercaCorsiStudente(matricola);
		
		return corsiIscritto;
	}

}
