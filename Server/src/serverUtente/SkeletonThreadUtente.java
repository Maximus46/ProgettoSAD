package serverUtente;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import controller.ControllerUtente;
import entities.Campo;
import entities.Utente;
import entities.Disponibilita;
import entities.Partecipante;
import entities.Prenotazione;
import interfacce.IControllerUtente;

public class SkeletonThreadUtente extends Thread {
	
	IControllerUtente skeletonUtente;
	Socket utente_socket;

	public SkeletonThreadUtente(IControllerUtente skeletonUtente, Socket utente_socket ) {
		// TODO Auto-generated constructor stub
		this.skeletonUtente = skeletonUtente;
		this.utente_socket = utente_socket;
	}
	
	public void run () {
	
		String email = null;
		String password = null;
		String tipologia = null;
		long idCampi=0;
		long data= 0;
		int idCampo=0;
		long idPrenotazione=0;
		long idDisponibilita=0;
		

		
		
		try {
			
			ObjectOutputStream out = new ObjectOutputStream(utente_socket.getOutputStream());
			ObjectInputStream in = new ObjectInputStream (utente_socket.getInputStream());
			ControllerUtente controllerUtente = new ControllerUtente();
			
			System.out.println("[2] - Entra nello skeleton thread utente ");
			
			
			String servizio = null;
			
			servizio = in.readUTF();
			
			switch(servizio) {
		
			
			
			
//____________loginUtente_____________________________________________________
			
			case"loginUtente":{
				
				System.out.println("[3] - Metodo loginUtente");
				
				Utente utente=new Utente();
				
				
				email = in.readUTF();
				password = in.readUTF();
				
				System.out.println("[4] - Email : " + email + " e la password : " + password + " per il login ");
				
				utente= controllerUtente.loginUtente(email, password);
				if(utente.getId()==0 && utente.getNome()==null) {
					utente.setId(-1);
					utente.setNome("");
					utente.setCognome("");
				}
				
				System.out.println("[4] - id : " + utente.getId());	
				out.writeLong(utente.getId());
				out.flush();
				
				System.out.println("[4] - nome : " + utente.getNome());	
				out.writeUTF(utente.getNome() + " " + utente.getCognome());
				out.flush();
				
				
				out.close();
				in.close();
				utente_socket.close();
				
				break;
				
			}			
			
			

			
//____________aggiungiCampo______________________________________________________
			
		case"aggiungiCampo":{
					
					boolean esito = false;
					
					System.out.println("[3] - Metodo aggiungiCampo");
					try {
						int numeroCampi=in.readInt();
						
						System.out.println("[3] - numeroCampi = "+ numeroCampi);

						
						for(int i=0;i<numeroCampi;i++) {
						
						
							Campo c = new Campo();
							System.out.println("[4] -Leggo Campo");
							
							// Ricevo id campo
							c.setId(in.readLong());
							System.out.println("[5] - ID " + c.getId());
							
							
							// Ricevo tipologia campo
							c.setTipologia(in.readUTF());
							System.out.println("[6] - Tipologia " + c.getTipologia());
							
							// Ricevo Tariffa campo
							c.setTariffa(in.readInt());
							
							//System.out.println("[5] - ID" + dispID);
							System.out.println("[7] - Tariffa " + c.getTariffa());
							
							// Ricevo Descrizione campo
							c.setDescrizione(in.readUTF());
							System.out.println("[8] - Descrizione " + c.getDescrizione());
	
							
							esito=controllerUtente.aggiungiCampo(c);
							out.writeBoolean(esito);
							out.flush();
						}	
							
						out.close();
						in.close();
						utente_socket.close();
			
						
					} catch (IOException e3) {
						// TODO Auto-generated catch block
						e3.printStackTrace();
					}		
				break;		
		}		
				
			
			
//____________aggiungiDisponibilita______________________________________________________
			
		case"aggiungiDisponibilita":{
				
				boolean esito = false;
				
				System.out.println("[3] - Metodo aggiungiDisponibilita");
				try {
					int numeroCampi=in.readInt();
					
					System.out.println("[3] - numeroCampi = "+ numeroCampi);

					
					for(int i=0;i<numeroCampi;i++) {

						Disponibilita d = new Disponibilita();
						System.out.println("[4] - Disponibilita : ");

						// RicevoID Disponibilita
						//int dispID = in.readInt();
						//d.setId(dispID);
						
						d.setId(in.readLong());
						//System.out.println("[5] - ID" + dispID);
						System.out.println("[5] - ID " + d.getId());
						
						
						// Ricevo data Disponibilita
						d.setData(in.readLong());
						System.out.println("[6] - Data " + d.getData());
						
						// Ricevo orarioInizio Disponibilita
						d.setOrarioInizio(in.readLong());
						System.out.println("[7] - Orario Inizio " + d.getOrarioInizio());
						
						// Ricevo orarioFine Disponibilita
						d.setOrarioFine(in.readLong());
						System.out.println("[8] - Orario Fine " + d.getOrarioFine());
						
						// RicevoIDCampo Disponibilita
						//int dispID = in.readInt();
						//d.setId(dispID);
						
						d.setIdCampi(in.readLong());
						//System.out.println("[5] - ID" + dispID);
						System.out.println("[9] - IDcampi " + d.getIdCampi());
						
						
						// Ricevo status Disponibilita
						d.setStatus(in.readBoolean());
						System.out.println("[10] - Status " + d.isStatus());
						
						esito=controllerUtente.aggiungiDisponibilita(d);
						out.writeBoolean(esito);
						out.flush();
					}
							
					out.close();
					in.close();
					utente_socket.close();
				
					
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}		
			break;	
		}			
		
		
			
//____________getListaCampi_____________________________________________________
			
			case"getListaCampi":{
				
				System.out.println("[3] - Metodo getListaCampi inizio");

				tipologia= in.readUTF();
				
				ArrayList<Campo> listaCampi=controllerUtente.getListaCampi(tipologia);
				int sizeLista=listaCampi.size();
				out.writeInt(sizeLista);
				System.out.println("[3.5] - sizeLista" + sizeLista);

				out.flush();
				
				System.out.println("[4] - Metodo getListaCampi prima for");

				
				for(int i=0;i<sizeLista;i++) {
						
					try {
				
						Campo c=new Campo();
						c=listaCampi.get(i);
						
						// Invio ID Campo
						out.writeLong(c.getId());
						System.out.println("[5] - ID" + c.getId());
						out.flush();
						
						// Invio Tariffa Campo
						out.writeInt(c.getTariffa());
						System.out.println("[6] - tariffa" + c.getTariffa());
						out.flush();
						
						// Invio Descrizione Campo
						out.writeUTF(c.getDescrizione());
						System.out.println("[7] - Descrizione" + c.getDescrizione());
						out.flush();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					System.out.println("[8] - Metodo getListaCampi fine");

					}
				break;
			}	

			
			
			
//____________aggiungiPrenotazione_____________________________________________________
			
			case"aggiungiPrenotazione":{
				
				long IdPrenotazione = -1;
				
				System.out.println("[3] - Metodo aggiungiPrenotazione");
				
				try {
						
						Prenotazione p = new Prenotazione();
						System.out.println("[4] -Leggo Prenotazione");
						
						p.setId(in.readLong());
						System.out.println("[5] - ID " + p.getId());
						
						p.setPrezzo(in.readDouble());
						System.out.println("[6] - Prezzo " + p.getPrezzo());
						
						p.setData(in.readLong());
						System.out.println("[7] - Data " + p.getData());
						
						p.setIdUser(in.readLong());
						System.out.println("[8] - IdUser " + p.getIdUser());

						
						IdPrenotazione=controllerUtente.aggiungiPrenotazione(p);
					
						System.out.println("[9] - IdPrenotazione inviato " + IdPrenotazione);

						out.writeLong(IdPrenotazione);
						out.flush();
		
					out.close();
					in.close();
					utente_socket.close();

					
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}		
			break;
				
	}		
		

//____________aggiungiAttribuzione_____________________________________________________
			
			case"aggiungiAttribuzione":{
				
				boolean esito = false;
								
				
				
				System.out.println("[3] - Metodo aggiungiAttribuzione");
				try {

						System.out.println("[4] -Leggo Attribuzione");
						
						idCampo=in.readInt();
						idPrenotazione=in.readLong();
						idDisponibilita=in.readLong();
						
	
						esito=controllerUtente.aggiungiAttribuzione( idCampo, idPrenotazione,idDisponibilita);
						System.out.println("[9] - Esito  " + esito);
						
						out.writeBoolean(esito);
						out.flush();
								
					out.close();
					in.close();
					utente_socket.close();
				
					
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}		
			break;
				
	}			
			
			
			
			
//____________getListaDisponibilitaFiltered_____________________________________________________
			
			case"getListaDisponibilitaFiltered":{
				
				System.out.println("[3] - Metodo getListaDisponibilita inizio");

				idCampi= in.readLong();
				data= in.readLong();
				
				ArrayList<Disponibilita> listaDisponibilita=controllerUtente.getListaDisponibilita(idCampi,data);
				int sizeLista=listaDisponibilita.size();
				out.writeInt(sizeLista);
				System.out.println("[3.5] - sizeLista" + sizeLista);

				out.flush();
				
				System.out.println("[4] - Metodo getListaDisponibilitaFiltered prima for");

				
				for(int i=0;i<sizeLista;i++) {
						
					try {

						Disponibilita d=new Disponibilita();
						d=listaDisponibilita.get(i);
						
						
						// Invio ID disponibilita
						out.writeLong(d.getId());
						System.out.println("[5] - ID " + d.getId());
						out.flush();
						
						// Invio orarioInizio
						out.writeLong(d.getOrarioInizio());
						System.out.println("[6] - orario Inizio " + d.getOrarioInizio());
						out.flush();
						
						// Invio orarioFine
						out.writeLong(d.getOrarioFine());
						System.out.println("[7] - orario Fine " + d.getOrarioFine());
						out.flush();
						
						// Invio status
						out.writeBoolean(d.isStatus());
						System.out.println("[8] - status " + d.isStatus());
						out.flush();
						
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					
					System.out.println("[9] - Metodo getListaDisponibilitaFiltered fine \n");

					}
				break;
			}


//____________aggiungiPartecipante_____________________________________________________

			case"aggiungiPartecipante":{
	
				boolean esito = false;

				
				System.out.println("[3] - Metodo aggiungiPrenotazione");
				try {
					int numeroPartecipanti=in.readInt();
		
					System.out.println("[3] - numeroPartecipanti = "+ numeroPartecipanti);

		
					for(int i=0;i<numeroPartecipanti;i++) {
		
		
						Partecipante p = new Partecipante();
						System.out.println("[4] -Leggo Partecipante");
			
			
						p.setId(in.readLong());
						System.out.println("[5] - ID " + p.getId());
			

						p.setIdPrenotazione(in.readLong());
						System.out.println("[6] - idPrenotazione" + p.getIdPrenotazione());
			
		
						p.setNome(in.readUTF());
						System.out.println("[7] - nome " + p.getNome());
			
						// Ricevo Descrizione campo
						p.setCognome(in.readUTF());
		
						System.out.println("[8] - Cognome" + p.geCognome());

			
						esito=controllerUtente.aggiungiPartecipante(p);
		
						out.writeBoolean(esito);
						out.flush();
					}

					out.close();
					in.close();
					utente_socket.close();
	
	
	
				} catch (IOException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}		
				break;
	
			}		
			
			}		
			
		}		
	
		
	
	 catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

	
	 }
  }
	
