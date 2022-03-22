package entities;

import java.io.Serializable;

import dao.PartecipanteDAO;


public class Prenotazione implements Serializable{
	
	private static final long serialVersionUID = -2083442871392597841L;
	
	private long id;
	private double prezzo;
	private long data;
	private long  idUser;
	
	
	
	public Prenotazione() {
	}


	public Prenotazione(long id, long data, double prezzo, long idUser) {
		super();
		this.id = id;
		this.data = data;
		this.prezzo = prezzo;
		this.idUser = idUser;
	}



	@Override
	public String toString() {
		return "Prenotazione [id : " + id + ", prezzo : " + prezzo + ", data : " + data + " idUser : " + idUser +"]";
	}




	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public double getPrezzo() {
		return prezzo;
	}


	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}


	public long getIdUser() {
		return idUser;
	}


	public void setIdUser(long idUser) {
		this.idUser = idUser;
	}
	

	public long getData() {
		return data;
	}


	public void setData(long data) {
		this.data = data;
	}


	
	public boolean aggiungiPartecipante(Partecipante p) {
		// TODO Auto-generated method stub
		boolean esito = false;
		
			esito=PartecipanteDAO.aggiungiPartecipante(p);
		
		return esito;
	}

	
	

}
