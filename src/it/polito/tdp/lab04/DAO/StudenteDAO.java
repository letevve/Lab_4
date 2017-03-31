package it.polito.tdp.lab04.DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.lab04.model.Studente;

public class StudenteDAO {

	public Studente trovaStudente(String matricola){
		final String sql = "SELECT nome, cognome, CDS, matricola "
				+ "FROM studente"
				+"WHERE matricola=?";
		
		//List <Studente> studenti = new ArrayList<Studente>();
		
		try {
			Connection conn = ConnectDB.getConnection();
			PreparedStatement st = conn.prepareStatement(sql);
			
			st.setString(1, matricola);

			ResultSet rs = st.executeQuery();
			
			Studente result = null;

			if (rs.next()) {

				// Crea un nuovo JAVA Bean Studente
				Studente temp = new Studente(rs.getString("matricola"), rs.getString("cognome"), rs.getString("nome"), rs.getString("CDS"));
				
				result = temp;
			} else {
				result = null;
			}
			
			//conn.close();
			return result;

		} catch (SQLException e) {
			// e.printStackTrace();
			throw new RuntimeException("Errore Db");
		}
	}

}
