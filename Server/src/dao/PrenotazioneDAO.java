package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entities.Prenotazione;


public class PrenotazioneDAO {
	

	public static long aggiungiPrenotazione (Prenotazione p) {
		
		long IdPrenotazione = -1;
		
		
		try {
			Connection cn = DBManager.getConnection();
			String query="INSERT INTO PRENOTAZIONI (DATA,PREZZO,IDUTENTE)VALUES(?,?,?)";
			PreparedStatement st=cn.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			st.setLong(1, p.getData());
			st.setDouble(2, p.getPrezzo());
			st.setLong(3, p.getIdUser());
			
			int affectedRows = st.executeUpdate();
			
			
			if( affectedRows == 0) {
				throw new SQLException("Creating booking failed, no rows affected.");
		
			}
			
			try (ResultSet generatedKeys = st.getGeneratedKeys()) {
	            if (generatedKeys.next()) {
	                IdPrenotazione = generatedKeys.getLong(1);
	            }
	            else {
	                throw new SQLException("Creating user failed, no ID obtained.");
	            }
			}
				
				
			DBManager.closeConnection();
		}catch(SQLException e){
			e.printStackTrace();
		}
		
	    return IdPrenotazione;
	}
}
