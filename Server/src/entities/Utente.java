package entities;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import dao.CampoDAO;
import dao.PrenotazioneDAO;
import dao.UtenteDAO;


public class Utente implements Serializable{

	
	private static final long serialVersionUID = -2083442831392597841L;

	private String nome;
	private String cognome;
	private String cf;
	private String email;
	private String password;
	private String telefono;
	private String datanascita;
	private long id;
	private ArrayList<Campo> campi;	
	
	public Utente() {
		
	}
	
	public Utente(String nome, String cognome, String cf, String email, String password, String telefono, String datanascita,long id) {
		
		this.nome=nome;
		this.cognome=cognome;
		this.cf=cf;
		this.email=email;
		this.password=password;
		this.telefono=telefono;
		this.datanascita=datanascita;
		this.id=id;
	}
	
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getCognome() {
		return cognome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	
	public String getCf() {
		return cf;
	}
	public void setCf(String cf) {
		this.cf = cf;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getDataNascita() {
		return datanascita;
	}
	public void setDataNascita(String datanascita) {
		this.datanascita = datanascita;
	}

	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	//____________loginUtente___________________________________________________________
	
		public Utente loginUtente (String email,String password) {
			 	
				Utente utente= new Utente();
				utente = UtenteDAO.loginUtente(email,password);

				return utente;
		}
	//____________END loginUtente________________________________________________________
		
		
		
	//____________START getCampi____________________________________________________________
		
		public ArrayList<Campo> getCampi(String tipologia) {

			campi= new ArrayList<Campo>();
			
			Set<Campo> campodao = new HashSet<>();
			try {
				campodao = CampoDAO.readCampo(tipologia);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			campi = convertiInLista(campodao);
			
			return campi;
		}
	//____________END getCampi_______________________________________________________________	
		
		
		
	//____________START convertiInLista______________________________________________________	
		
		public ArrayList<Campo> convertiInLista (Set<Campo> campo){
			ArrayList<Campo> listaCampi=new ArrayList<Campo>();
			
			for(Campo c : campo) {
				listaCampi.add(c);
			}
			
			return listaCampi;
		}
	//____________END convertiInLista___________________________________________________________	
		
		
		
	//____________START aggiungiPrenotazione____________________________________________________	
		public long aggiungiPrenotazione(Prenotazione p) {
			// TODO Auto-generated method stub
			long IdPrenotazione = -1;
			
			IdPrenotazione=PrenotazioneDAO.aggiungiPrenotazione(p);
			
			return IdPrenotazione;
		}
	//____________END aggiungiPrenotazione______________________________________________________
		
}
