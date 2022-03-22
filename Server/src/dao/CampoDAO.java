package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entities.Campo;

public class CampoDAO {
	
	
	//____________START Metodo readCampo__________________________________________________

	public static Set<Campo> readCampo(String tipologia) throws SQLException {
		Set<Campo> campi = new HashSet<>();
		Connection cn = DBManager.getConnection();

		System.out.println("[ArchivioDao - Readcamp] - tipologia data" + tipologia);
		String query = "SELECT * FROM CAMPI WHERE TIPOLOGIA=?";
		System.out.println("[ArchivioDao - Readcamp] - query" + query);

		
		PreparedStatement st = cn.prepareStatement(query);
		st.setString(1, tipologia);
		ResultSet rs = st.executeQuery();

		while (rs.next() == true) {
			Campo c = new Campo();
			c.setId(rs.getInt("id"));
			c.setTariffa(rs.getInt("tariffa"));
			c.setDescrizione(rs.getString("descrizione"));

			campi.add(c);
		}

		DBManager.closeConnection();

		return campi;
		}
	
	
	
	//____________START Metodo aggiungiCampo___________________________________________
	
			public static boolean aggiungiCampo(Campo c){
				
				boolean esito = false;
				
				try {
				
					Connection cn=DBManager.getConnection();
					String query="INSERT INTO CAMPI (ID, TIPOLOGIA, TARIFFA, DESCRIZIONE)VALUES(?,?,?,?)";
				
					PreparedStatement st=cn.prepareStatement(query);
					st.setLong(1, c.getId());
					st.setString(2, c.getTipologia());
					st.setInt(3, c.getTariffa());
					st.setString(4, c.getDescrizione());

					st.execute();
		        
					esito=true;
		        
					DBManager.closeConnection();
				}catch(SQLException e){
					e.printStackTrace();
				}
				
		        return esito;
		    }		
//____________END Metodo aggiungiCampo________________________________________________
			

}
