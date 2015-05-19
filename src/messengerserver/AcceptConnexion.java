package messengerserver;
import java.io.*;
import java.net.*;

public class AcceptConnexion implements Runnable{
    
    private ServerSocket socketserver = null;
    private Socket socket = null;
    public Thread thread1;
  
    public AcceptConnexion(ServerSocket ss){socketserver = ss;}

    @Override
    public void run(){
        try {
            while(true){
                socket = socketserver.accept();
                view.ViewServer.appendInfo("Un client veut se connecter -> ");
                thread1 = new Thread(new Authentification(socket));
                thread1.start();
            }
        } catch(IOException e){
            System.err.println("Erreur serveur");
        }
    }
}
