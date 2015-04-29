package messengerserver;
import java.io.*;
import java.net.*;

public class MessengerServer {

    public static  ServerSocket ss = null;
    public static Thread thread;
    
    public static void main(String[] args) {
        try {
            ss = new ServerSocket(5000);
            System.out.println("Ecoute du serveur sur le port : " + ss.getLocalPort());
            thread = new Thread(new AcceptConnexion(ss));
            thread.start();
        } catch (IOException e) {
            System.err.println("Le port " +  ss.getLocalPort() + " est déjà utilisé.");
        }
    }
}
