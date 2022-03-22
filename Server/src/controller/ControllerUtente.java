package controller;

import java.util.ArrayList;
import entities.Amministratore;
import entities.Utente;
import entities.Campo;
import entities.Disponibilita;
import entities.Partecipante;
import entities.Prenotazione;
import serverUtente.SkeletonUtente;


public class ControllerUtente extends SkeletonUtente {
	
	
//____________START Metodo loginUtente____________________________________________
	
	public Utente loginUtente (String email, String password) {
		
		Utente utente = new Utente ();		
		
		
		utente=utente.loginUtente(email,password);
		
		return utente;
	}
//____________END Metodo loginUtente_______________________________________________
	
	

//____________START Metodo getListaCampi___________________________________________

	public ArrayList<Campo> getListaCampi(String tipologia) {
		
		ArrayList<Campo> listaC=new ArrayList<Campo>();
		Utente u = new Utente ();
		
		listaC=u.getCampi(tipologia);
		
		return listaC; 
	}		
//____________END Metodo getListaCampi_____________________________________________
	
	
		
//____________START Metodo aggiungiCampo___________________________________________
	
	public boolean aggiungiCampo(Campo ca) {

		boolean esito = false;
		System.out.println(ca.toString());

		Amministratore a = new Amministratore();
		
		esito= a.aggiungiCampo(ca);

		return esito;
	}
//____________END Metodo aggiungiCampo_______________________________________________
	

	
//____________START Metodo aggiungiDisponibilita_____________________________________
	
	public boolean aggiungiDisponibilita(Disponibilita d) {

			boolean esito = false;
					
			System.out.println(d.toString());			

			Amministratore a = new Amministratore();
			
			esito= a.aggiungiDisponibilita(d);

			return esito;
	}
//____________END Metodo aggiungiCampo_______________________________________________
		
		
		
	
	
//____________START Metodo getListaDisponibilitaFiltered_______________________________

	public ArrayList<Disponibilita> getListaDisponibilita(long idCampi,long data) {
			
			ArrayList<Disponibilita> listaD=new ArrayList<Disponibilita>();
			Campo c = new Campo ();
			
			listaD=c.getDisponibilita(idCampi,data);
			
			return listaD; 
	}		
//____________END Metodo getListaDisponibilita__________________________________________
		
		
		
//____________START Metodo aggiungiPrenotazione_________________________________________
	
	public long aggiungiPrenotazione(Prenotazione p) {
			
			long IdPrenotazione = -1;
			
			System.out.println(p.toString());
			
			Utente u = new Utente();
			
			IdPrenotazione = u.aggiungiPrenotazione(p);
			
			return IdPrenotazione;
	}
//____________ENDMetodo aggiungiPrenotazione_____________________________________________

		

//____________START Metodo aggiungiAttribuzione__________________________________________
	
	public boolean aggiungiAttribuzione(int idCampo, long idPrenotazione,long idDisponibilita) {
				
				boolean esito = false;
				
				
				
				Amministratore a = new Amministratore();
				
				esito= a.aggiungiAttribuzione( idCampo,idPrenotazione,idDisponibilita);

				return esito;
	}
//____________ENDMetodo aggiungiPrenotazione_____________________________________________
	
	
	
//____________START Metodo aggiungiPartecipante__________________________________________

	public boolean aggiungiPartecipante(Partecipante p) {
			
			boolean esito = false;
			
			System.out.println(p.toString());
			
			Prenotazione prenotazione = new Prenotazione();
			
			esito = prenotazione.aggiungiPartecipante(p);
			
			return esito;
	}
//____________END Metodo aggiungiPartecipante____________________________________________
	
}
