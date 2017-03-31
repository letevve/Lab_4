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

public class CorsoDAO {

	/*
	 * Ottengo tutti i corsi salvati nel Db
	 */
	public List<Corso> getTuttiICorsi() {

		final String sql = "SELECT * FROM corso";

		List<Corso> corsi = new LinkedList<Corso>();
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				// Crea un nuovo JAVA Bean Corso
				Corso temp = new Corso(rs.getString("codins"), rs.getInt("crediti"), rs.getString("nome"), rs.getInt("pd"));
				
				// Aggiungi il nuovo Corso alla lista
				corsi.add(temp);
			}
			
			//conn.close();
			return corsi;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

	/*
	 * Dato un codice insegnamento, ottengo il corso
	 */
	public Corso getCorso(String codCorso) {
		
		final String sql = "SELECT * FROM corso"+
							"WHERE codins = ?";

		Corso corso = null;
		

		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);

			st.setString(1, codCorso);
			ResultSet rs = st.executeQuery();

			while (rs.next()) {

				corso.setCodice(rs.getString("codins"));
				corso.setCrediti(rs.getInt("crediti"));
				corso.setNome(rs.getString("nome"));
				corso.setPd(rs.getInt("pd"));
				
			}
			
			//conn.close();
			return corso;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}
	

	/*
	 * Ottengo tutti gli studenti iscritti al Corso
	 */
	public List<Studente> getStudentiIscrittiAlCorso(Corso corso) {
		// cerca tutti gli studenti iscritti al corso passato come parametro
		
		final String sql = "SELECT * "+
							"FROM studente"+
							"WHERE matricola IN ( SELECT DISTINCT matricola"+
							"FROM iscrizione"+
							"WHERE codins= ? )";

		List<Studente> studenti = new LinkedList<Studente>();
		
		
		try {
		Connection conn = ConnectDB.getConnection();
		PreparedStatement st = conn.prepareStatement(sql);
		
		st.setString(1, corso.getCodice());
		ResultSet rs = st.executeQuery();
		
		while (rs.next()) {
		
			Studente stemp = new Studente(rs.getInt("matricola"), rs.getString("cognome"), 
					rs.getString("cognome"), rs.getString("CDS"));
			
			studenti.add(stemp);
			
		}
		
		//conn.close();
		return studenti;
		
		} catch (SQLException e) {
		//e.printStackTrace();
		throw new RuntimeException("Errore Db");
		}
		
		
	}

	/*
	 * Data una matricola ed il codice insegnamento,
	 * iscrivi lo studente al corso.
	 */
	public boolean inscriviStudenteACorso(Studente studente, Corso corso) {
		// TODO
		return false;
	}
}
