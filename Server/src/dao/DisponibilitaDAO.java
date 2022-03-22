package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import entities.Disponibilita;


public class DisponibilitaDAO {

	
//____________START Metodo aggiungiDisponibilita_____________________________________________
	
	public static boolean aggiungiDisponibilita(Disponibilita d){
				
				boolean esito = false;
				
				try {
				
					Connection cn=DBManager.getConnection();
					String query="INSERT INTO DISPONIBILITA (ID, DATA, ORARIOINIZIO, ORARIOFINE, IDCAMPI, STATUS)VALUES(?,?,?,?,?,?)" ;
				
					PreparedStatement st=cn.prepareStatement(query);
					st.setLong(1, d.getId());
					st.setLong(2, d.getData());
					st.setLong(3, d.getOrarioInizio());
					st.setLong(4, d.getOrarioFine());
					st.setLong(5, d.getIdCampi());
					st.setBoolean(6, d.isStatus());
				
			
					st.execute();
		        
					esito=true;
		        
					DBManager.closeConnection();
					
				}catch(SQLException e){
					e.printStackTrace();
				}
				
		        return esito;
	}		
//____________END Metodo aggiungiDisponibilita_____________________________________________
   
	
	
//____________START Metodo readDisponibilita____________________________________
	
	public static Set<Disponibilita> readDisponibilita(long idCampi,long data) throws SQLException {
				Set<Disponibilita> disponibilita = new HashSet<>();
				Connection cn = DBManager.getConnection();
				
				System.out.println("[ArchivioDao - ReadDisponibilita] - idCampi " + idCampi);
				String query = "SELECT * FROM DISPONIBILITA WHERE (IDCAMPI=? AND DATA=?) ORDER BY DISPONIBILITA.ID";
				System.out.println("[ArchivioDao - Readcamp] - query" + query);

				PreparedStatement st = cn.prepareStatement(query);
				st.setLong(1, idCampi);
				st.setLong(2, data);
				ResultSet rs = st.executeQuery();

				//System.out.println("[ArchivioDao - Readcamp] disponiblita " + rs);
				
				while (rs.next() == true) {
					Disponibilita d = new Disponibilita();
					d.setId(rs.getInt("id"));
					d.setOrarioInizio(rs.getLong("orarioInizio"));
					d.setOrarioFine(rs.getLong("orarioFine"));
					d.setStatus(rs.getBoolean("status"));

					
					disponibilita.add(d);
				}

				DBManager.closeConnection();

				return disponibilita;
		}
	//____________END Metodo readDisponibilita________________________________________
	
			

}
