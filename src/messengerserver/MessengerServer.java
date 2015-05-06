package messengerserver;
import java.io.*;
import java.net.*;
import shared.Client;
import java.net.InetAddress;

public class MessengerServer {

    public static  ServerSocket serverSocket = null;
    public static Thread thread;
    public static Manager user;
    public static Client messServer;
  
    public static void main(String[] args) {
       
        try{
       messServer = new Client("messServer",InetAddress.getLocalHost());
        }catch(UnknownHostException e){e.printStackTrace();}
        try {
            serverSocket = new ServerSocket(5000);
            System.out.println("Ecoute du serveur sur le port : " + serverSocket.getLocalPort());
            thread = new Thread(new AcceptConnexion(serverSocket));
            thread.start();
        }catch (IOException e) {
            System.err.println("Le port " +  serverSocket.getLocalPort() + " est déjà utilisé.");
        }
    }
}
