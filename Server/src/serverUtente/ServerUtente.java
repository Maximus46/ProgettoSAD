package serverUtente;

import controller.ControllerUtente;

public class ServerUtente extends Thread{

	public ServerUtente() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public void run() {
		System.out.println("[Server Utente] avviato ...");
		ControllerUtente controllerUtente = new ControllerUtente();
		controllerUtente.avviaSkeletonUtente();
		

	}

}
