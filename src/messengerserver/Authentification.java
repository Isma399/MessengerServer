package messengerserver;
import java.net.*;
import java.io.*;
import shared.Client;
//import view.ViewServer;
//import java.awt.event.ActionEvent;
//import java.awt.event.ActionListener;

public class Authentification implements Runnable{
    private final Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String login = "test";
    private boolean isAuthenticated = false;
    private Thread thread2;
    private SocketAddress clientSocket;
    
    
    public Authentification(Socket s){socket = s;}
    
    @Override
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            clientSocket = socket.getRemoteSocketAddress();
            view.ViewServer.appendInfo(clientSocket + "->");
            out = new PrintWriter(socket.getOutputStream());
            
            while(!isAuthenticated){
                //out.println("Entrez votre nom : ");
                //out.flush();
                login = in.readLine();
                
                if(Manage.user.test(login)){
                    out.println("connecte");//envoi du signal
                    view.ViewServer.appendInfo(login + " connecté.\n");
                    out.flush();
                                        
                    isAuthenticated = true;
                    Client newClient = new Client (login,null,null);
                    Manage.user.add(newClient);
                    view.ViewServer.setList(Manage.user.toString());
                } else {
                    out.println("loginAlreadyUsed");
                    out.flush();
                    view.ViewServer.appendInfo(login + " login déjà utilisé. \n");
                    out.close();
                }
            }
            thread2 = new Thread(new ActionClient(socket,login));
            thread2.start();
        } catch (IOException e){
            view.ViewServer.appendInfo("Fermeture socket pour le login : " + login + ".\n");
            Manage.user.del(login);
            //System.err.println("Erreur : " + login + " ne répond pas. ");
            //e.printStackTrace();
        }
    }
}
    
   