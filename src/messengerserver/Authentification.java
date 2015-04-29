package messengerserver;
import java.net.*;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.*;

public class Authentification implements Runnable{
    private final Socket socket;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private String login = "test";
    public boolean isAuthenticated = false;
    public Thread thread2;
    
    public Authentification(Socket s){
        socket = s;
    }
    @Override
    public void run(){
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            while(!isAuthenticated){
                out.println("Entrez votre nom : ");
                out.flush();
                login = in.readLine();
                
                if(isValid(login)){
                    
                    out.println("connecte");
                    System.out.println(login + " est connecté.");
                    out.flush();
                    isAuthenticated = true;
                } else {out.println("Erreur Authentification");out.flush();}
            }
            thread2 = new Thread(new Client(socket,login));
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
