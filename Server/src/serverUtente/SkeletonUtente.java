package serverUtente;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import interfacce.IControllerUtente;

public abstract class SkeletonUtente implements IControllerUtente {
	
	private ServerSocket server;
	private final int port = 7777;
	private Socket utente_socket;
	
public void avviaSkeletonUtente () {
		
		try {
			server = new ServerSocket(port);
			
			while(true) {
				
				utente_socket = server.accept();
				System.out.println("[1] - Connessione stabilita con il client ");
				
				Thread t = new SkeletonThreadUtente(this,utente_socket);
				t.start();
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}

}
