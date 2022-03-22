package dao;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;


public class AttribuzioneDAO {
	

//____________START Metodo aggiungiAttribuzione_____________________________________________

	public static boolean aggiungiAttribuzione(int idCampo, long idPrenotazione,long idDisponibilita) {
		
		boolean esito = false;
		
		try {
		
			Connection cn=DBManager.getConnection();
			String query="INSERT INTO ATTRIBUZIONI (IDCAMPO, IDPRENOTAZIONE, IDDISPONIBILITA)VALUES(?,?,?)";
		
			PreparedStatement st=cn.prepareStatement(query);
		
			st.setInt(1, idCampo);
			st.setLong(2, idPrenotazione);
			st.setLong(3, idDisponibilita);
		
			st.execute();
		
			esito=true;
		
        
			DBManager.closeConnection();
			
		}catch(SQLException e){
			e.printStackTrace();
		}
		
        return esito;
    }		
//____________END Metodo aggiungiAttribuzione_____________________________________________

	
}
