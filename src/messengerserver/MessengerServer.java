package messengerserver;
import java.io.*;
import java.net.*;
import shared.Client;
import view.ViewServer;
import javax.swing.SwingUtilities;


public class MessengerServer {

    public static  ServerSocket serverSocket = null;
    public static Thread thread;
    public static Client server= new Client("server",null);
    
  
   public static void main(String[] args) {
       
        try {
            serverSocket = new ServerSocket(5000);
            view.ViewServer.appendInfo("Ecoute sur le port : " + serverSocket.getLocalPort() + ".\n");
            thread = new Thread(new AcceptConnexion(serverSocket));
            thread.start();
        }catch (IOException e) {
            System.err.println("Le port " +  serverSocket.getLocalPort() + " est déjà utilisé.");
        }
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new ViewServer();
                }
            });
   }
}

