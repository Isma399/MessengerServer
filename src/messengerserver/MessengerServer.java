package messengerserver;
import java.io.*;
import java.net.*;
import shared.Client;
import java.util.Set;
import java.util.HashSet;
import java.net.InetAddress;

public class MessengerServer {

    public static  ServerSocket serverSocket = null;
    public static Thread thread;
  
    
    
    public static void main(String[] args) {
       Set<Client> userSet = new HashSet<>();
       try{
       Client test = new Client("test",InetAddress.getLocalHost());
       userSet.add(test);
       }catch(UnknownHostException e){e.printStackTrace();}
       
        
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Ecoute du serveur sur le port : " + serverSocket.getLocalPort());
            thread = new Thread(new AcceptConnexion(serverSocket,userSet));
            thread.start();
        } catch (IOException e) {
            System.err.println("Le port " +  serverSocket.getLocalPort() + " est déjà utilisé.");
        }
    }
}
