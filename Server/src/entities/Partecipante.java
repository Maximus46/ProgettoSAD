package entities;

import java.io.Serializable;




public class Partecipante implements Serializable{
	
	private static final long serialVersionUID = -2083442871392597841L;
	
	private long id;
	private long idPrenotazione;
	private String nome;
	private String cognome;
	
	
	
	public Partecipante() {
	}


	public Partecipante(long id, String nome, String cognome, long idPrenotazione) {
		super();
		this.id = id;
		this.nome = nome;
		this.cognome = cognome;
		this.idPrenotazione= idPrenotazione;
		
	}



	@Override
	public String toString() {
		return "Partecipante [id : " + id + ", nome : " + nome + " cognome : " + cognome + ", idPrenotazione : " + idPrenotazione + "]";
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getIdPrenotazione() {
		return idPrenotazione;
	}


	public void setIdPrenotazione(long idPrenotazione) {
		this.idPrenotazione = idPrenotazione;
	}




	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}
	

	public String geCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}



	
	

}
