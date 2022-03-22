package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Utente;



public class UtenteDAO {
	
	
///////////////////////  METODI  \\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\\


		
//____________START Metodo loginUtente____________________________________________	
		
		public static Utente loginUtente(String email,String password){
			
			Utente utente = new Utente ();
			
			
			try {
				Connection cn = DBManager.getConnection();
				String query="SELECT * FROM CLIENTI WHERE EMAIL=? AND PASSWORD=?";
				PreparedStatement st=cn.prepareStatement(query);
				st.setString(1, email);
				st.setString(2, password);
				ResultSet rs=st.executeQuery();
				
				if(rs.next()) {
			
					utente.setId(rs.getLong("Id"));
					utente.setNome(rs.getString("Nome"));
					utente.setCognome(rs.getString("Cognome"));
					System.out.println("[5] - L'utente : " + rs.getString("Nome") + "  " +  rs.getString("Cognome") + "\n" + " nato il  " + rs.getString("DataNascita") + " e con recapito " + rs.getString("Telefono") + " e mail :" + rs.getString("Email") + "\n" + " e' loggato! \n");
				}else
					System.out.println("Error: password o mail non corretti!");
				DBManager.closeConnection();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return utente;
		}
//____________END Metodo loginUtente_______________________________________________
		
	
		

		

		
		
			

	
			
	

		
		
		


}
	