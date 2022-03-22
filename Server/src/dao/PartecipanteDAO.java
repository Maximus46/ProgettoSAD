package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


import entities.Partecipante;


public class PartecipanteDAO {
	public static boolean aggiungiPartecipante (Partecipante p) {
		
		boolean esito = false;
		
		try {
			Connection cn = DBManager.getConnection();
			String query="INSERT INTO PARTECIPANTI (ID,NOME,COGNOME,IDPRENOTAZIONI)VALUES(?,?,?,?)";
			PreparedStatement st=cn.prepareStatement(query);
			st.setLong(1, p.getId());
			st.setString(2, p.getNome());
			st.setString(3, p.geCognome());
			st.setLong(4, p.getIdPrenotazione());
			st.execute();
			
			esito = true;
			DBManager.closeConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	    return esito;
	}
}
