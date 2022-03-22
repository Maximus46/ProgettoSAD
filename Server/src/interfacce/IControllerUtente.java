package interfacce;

import java.util.ArrayList;

import entities.Utente;
import entities.Campo;
import entities.Disponibilita;
import entities.Partecipante;
import entities.Prenotazione;


public interface IControllerUtente {
	
	public ArrayList<Disponibilita> getListaDisponibilita(long idCampi,long data);
	public ArrayList<Campo> getListaCampi(String tipologia);
	public Utente loginUtente (String email, String password);
	public long aggiungiPrenotazione (Prenotazione p);
	public boolean aggiungiAttribuzione (int idCampo, long idPrenotazione,long idDisponibilita);
	public boolean aggiungiPartecipante (Partecipante p);
	public boolean aggiungiCampo(Campo ca);
	boolean aggiungiDisponibilita(Disponibilita d);
}
