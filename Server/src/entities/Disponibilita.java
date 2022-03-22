package entities;

import java.io.Serializable;

import dao.DisponibilitaDAO;

public class Disponibilita implements Serializable{
	
	private static final long serialVersionUID = -2083442831392597841L;
	
	private long id;
	private long data;
	private long orarioInizio;
	private long orarioFine;
	private long  idCampi;
	private boolean status;
	
	
	public Disponibilita() {
	}


	public Disponibilita(long id, long data, long orarioInizio, long orarioFine, long idCampi, boolean status) {
		super();
		this.id = id;
		this.data = data;
		this.orarioInizio = orarioInizio;
		this.orarioFine = orarioFine;
		this.idCampi = idCampi;
		this.status = status;
	}



	@Override
	public String toString() {
		return "Disponibilita [id : " + id + ", data : " + data + ", orarioInizio : " + orarioInizio + ", orarioFine : " + orarioFine + " idCampi : " + idCampi + " status " + status +"]";
	}

	public boolean isStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}


	public long getId() {
		return id;
	}


	public void setId(long id) {
		this.id = id;
	}


	public long getData() {
		return data;
	}


	public void setData(long data) {
		this.data = data;
	}


	public long getOrarioInizio() {
		return orarioInizio;
	}


	public void setOrarioInizio(long orarioInizio) {
		this.orarioInizio = orarioInizio;
	}

	public long getOrarioFine() {
		return orarioFine;
	}


	public void setOrarioFine(long orarioFine) {
		this.orarioFine = orarioFine;
	}
	
	
	public long getIdCampi() {
		return idCampi;
	}


	public void setIdCampi(long idCampi) {
		this.idCampi = idCampi;
	}


	public boolean aggiungiDisponibilita(Disponibilita d) {
		// TODO Auto-generated method stub
		boolean esito = false;
		
			esito=DisponibilitaDAO.aggiungiDisponibilita(d);
		
		return esito;
	}
	
	

	
}
