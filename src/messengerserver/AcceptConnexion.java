package messengerserver;

import java.io.*;
import java.net.*;
import java.util.Set;
import java.util.HashSet;
import shared.Client;



public class AcceptConnexion implements Runnable{
    
    private ServerSocket socketserver = null;
    private Socket socket = null;
    private Set<Client> userSet;
    
    public Thread thread1;
    public AcceptConnexion(ServerSocket ss,Set<Client> userSet){
        socketserver = ss;this.userSet=userSet;
    }

    public void run(){
        try {
            while(true){
                socket = socketserver.accept();
                System.out.print("Un client veut se connecter -> ");
                thread1 = new Thread(new Authentification(socket,userSet));
                thread1.start();
            }
        } catch(IOException e){
            System.err.println("Erreur serveur");
        }
    }
}
