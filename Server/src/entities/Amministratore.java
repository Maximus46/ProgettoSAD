package entities;



import dao.AttribuzioneDAO;
import dao.CampoDAO;
import dao.DisponibilitaDAO;



public class Amministratore {


	public Amministratore() {
	}
	
	
//____________START aggiungiCampo____________________________________________________	
	
	public boolean aggiungiCampo(Campo ca) {
		// TODO Auto-generated method stub
		boolean esito = false;
		
			esito=CampoDAO.aggiungiCampo(ca);
		
		return esito;
	}
//____________END aggiungiCampo_______________________________________________________
	
	

//____________START aggiungiDisponibilita_____________________________________________	
	
	public boolean aggiungiDisponibilita(Disponibilita d) {
			// TODO Auto-generated method stub
			boolean esito = false;
			
				esito=DisponibilitaDAO.aggiungiDisponibilita(d);
			
			return esito;
	}
//____________END aggiungiCampo________________________________________________________	
	
	
	
//____________START aggiungiAttribuzione_______________________________________________		
	
	public boolean aggiungiAttribuzione(int idCampo, long idPrenotazione,long idDisponibilita) {
		// TODO Auto-generated method stub
		boolean esito = true;
		
		esito=AttribuzioneDAO.aggiungiAttribuzione(idCampo, idPrenotazione, idDisponibilita);
		
		return esito;
	}
//____________END aggiungiAttribuzione__________________________________________________		

			

		
	
}