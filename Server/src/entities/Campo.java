package entities;


import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dao.DisponibilitaDAO;




public class Campo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3390188700693196881L;
	private long id;
	private String tipologia;
	private int tariffa;
	private String descrizione;
	
	private ArrayList<Disponibilita> disponibilita;
	
	public Campo() {
		
	}
	
	
	public Campo(long id,String tipologia,int tariffa, String descrizione) {
		this.id=id;
		this.tipologia=tipologia;
		this.tariffa=tariffa;
		this.descrizione= descrizione;
		
	}
	
	

	@Override
	public String toString() {
		return "Campo [id : " + id + ", tipologia : " + tipologia + ", tariffa : " + tariffa + " descrizione : " + descrizione + "]";
	}


	public long getId() { 
		return id;
	}

	public void setId(long id) { 
		this.id = id;
	}

	public String getTipologia() { 
		return tipologia;
	}

	public void setTipologia(String tipologia) { 
		this.tipologia = tipologia;
	}

	public int getTariffa() { 
		return tariffa;
	}

	public void setTariffa(int tariffa) { 
		this.tariffa = tariffa;
	}
	
	public String getDescrizione() { 
		return descrizione;
	}

	public void setDescrizione(String descrizione) { 
		this.descrizione = descrizione;
	}
	
	
	
	//____________START getDisponibilita__________________________________________________________	
	
			public ArrayList<Disponibilita> getDisponibilita(long idCampi,long data) {

				
				disponibilita= new ArrayList<Disponibilita>();
				
				Set<Disponibilita> disponobilitadao = new HashSet<>();
				try {
					disponobilitadao = DisponibilitaDAO.readDisponibilita(idCampi,data);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				disponibilita = convertiInListaD(disponobilitadao);
				
				return disponibilita;
			}
	//____________END getDisponibilita_____________________________________________________________	
	
			
			
	//____________START convertiInListaD________________________________________________________	
			
			public ArrayList<Disponibilita> convertiInListaD (Set<Disponibilita> disponibilita){
				ArrayList<Disponibilita> listaDisponibilita=new ArrayList<Disponibilita>();
				
				for(Disponibilita d : disponibilita) {
					listaDisponibilita.add(d);
				}
				
				return listaDisponibilita;
			}
	//____________END convertiInListaD____________________________________________________________	
			

}
