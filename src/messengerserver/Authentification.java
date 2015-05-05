package messengerserver;
import java.net.*;
import java.util.Scanner;
import java.io.*;
import java.util.Set;
import shared.Client;

public class Authentification implements Runnable{
    private final Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String login = "test";
    public boolean isAuthenticated = false;
    public Thread thread2;
    private Set<Client> userSet;
    private UserManagment userManagment;
    private InetAddress inAddress;
    
    public Authentification(Socket s,Set<Client> userSet){
        socket = s;this.userSet=userSet;
    }
    @Override
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            inAddress = socket.getInetAddress();
            System.out.println("(Authentification java a reçu comme inAddress : " + inAddress + ")");
            out = new PrintWriter(socket.getOutputStream());
            
            while(!isAuthenticated){
                out.println("Entrez votre nom : ");
                out.flush();
                login = in.readLine();
                
                if(isValid(login)){
                    
                    out.println("connecte");//envoi du signal
                    //TODO creation de l'objet client, ajout dans le serveur.
                    System.out.println(login + " est connecté.");
                    out.flush();
                    isAuthenticated = true;
                    //UserManagment.
                    Client newClient = new Client (login,inAddress);
                    userSet.add(newClient);
                    System.out.println("UserSet.toString()" + userSet.toString());
                } else {out.println("Erreur Authentification");out.flush();}
            }
            thread2 = new Thread(new ActionClient(socket,login));
            thread2.start();
        } catch (IOException e){
            System.err.println("Erreur : " + login + " ne répond pas. ");
        }
    }
    
    private static boolean isValid(String login){
        boolean connexion = false;
        try {
            Scanner sc = new Scanner(new File("login.txt"));
            while(sc.hasNext()){
                if((sc.nextLine().equals(login))){
                    connexion=false;  
                    System.out.print(login + " est déjà utilisé. ");
                    break;
                }else{
                    connexion = true;
                    //TODO ecrire le nouveau login dans le fichier login.txt
                }
            }
        }catch (FileNotFoundException e){
            System.err.println("Le fichier login.txt n'a pas été trouvé!");
        }
    return connexion;
            
    }
}
