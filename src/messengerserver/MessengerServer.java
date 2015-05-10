package messengerserver;
import java.io.*;
import java.net.*;
import shared.Client;
import java.net.InetAddress;

public class MessengerServer {

    public static  ServerSocket serverSocket = null;
    public static Thread thread;
    public static Client server= new Client("server",null,null);
    
  
    public static void main(String[] args) {
       
        try {
            serverSocket = new ServerSocket(5000);
            server.setSocket(serverSocket.getLocalSocketAddress());
            System.out.println("Ecoute du serveur sur le port : " + serverSocket.getLocalPort());
            thread = new Thread(new AcceptConnexion(serverSocket));
            thread.start();
        }catch (IOException e) {
            System.err.println("Le port " +  serverSocket.getLocalPort() + " est déjà utilisé.");
        }
    }
}
