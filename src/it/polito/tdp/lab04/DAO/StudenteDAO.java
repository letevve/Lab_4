package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import it.polito.tdp.lab04.model.Corso;
import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente trovaStudente(String matricola){
		final String sql=
				"SELECT matricola,nome,cognome, CDS "+
				"FROM studente "+
				"WHERE matricola=?";
			
			Studente result=null;
			
			try{
				Connection conn= ConnectDB.getConnection();
				PreparedStatement st=conn.prepareStatement(sql);
				
				st.setString(1, matricola);
				ResultSet res= st.executeQuery();
				if(res.next()){
					
					Studente s = new Studente(res.getInt("matricola"), res.getString("nome"), res.getString("cognome"), res.getString("CDS"));
					result= s;
					
				} else{
					result=null;
				}
				//conn.close();
				return result;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}
	
	/*
	 * riceve come parametro la matricola di uno studente e restituisce
	 * la lista di corsi a cui quello studente è iscritto
	 */
	public List<Corso> cercaCorsiStudente(String matricola){
		
		final String sql=
				"SELECT * "+ 
				"FROM corso "+
				"WHERE codins IN ( SELECT DISTINCT codins "+
				"FROM iscrizione "+
				"WHERE matricola = ? )";
			
			List<Corso> corsi = new LinkedList<Corso>();
			
			try{
				Connection conn= ConnectDB.getConnection();
				PreparedStatement st=conn.prepareStatement(sql);
				
				st.setString(1, matricola);
				ResultSet res= st.executeQuery();
				while(res.next()){
					
					Corso c = new Corso(res.getString("codins"), res.getInt("crediti"), res.getString("nome"), res.getInt("pd"));
					corsi.add(c);
					
				}
				//conn.close();
				return corsi;
				
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}

}
