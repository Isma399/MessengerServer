package messengerserver;
import java.net.*;
import java.io.*;
import shared.Client;

public class Authentification implements Runnable{
    private final Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String login = "test";
    public boolean isAuthenticated = false;
    public Thread thread2;
    public SocketAddress clientSocket;
    
    public Authentification(Socket s){socket = s;}
    
    @Override
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientSocket = socket.getRemoteSocketAddress();
            System.out.println("(Authentification java a reçu comme socket : " + clientSocket + ")");
            out = new PrintWriter(socket.getOutputStream());
            
            while(!isAuthenticated){
                out.println("Entrez votre nom : ");
                out.flush();
                login = in.readLine();
                
                if(Manage.user.test(login)){
                    
                    out.println("connecte");//envoi du signal
                   
                    System.out.print(login + " connecté. ");
                    out.flush();
                    isAuthenticated = true;
                   
                    Client newClient = new Client (login,clientSocket,null);
                    Manage.user.add(newClient);
                    System.out.println("Un client créé. Dans le serveur, on a : ");
                    System.out.println(Manage.user);
                } else {out.println("Erreur Authentification");out.flush();}
            }
            thread2 = new Thread(new ActionClient(socket,login));
            thread2.start();
        } catch (IOException e){
            System.err.println("Erreur : " + login + " ne répond pas. ");
        }
    }
}
    
   