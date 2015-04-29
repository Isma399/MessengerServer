package messengerserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class Client implements Runnable{
    
    private Socket socket = null;
    private BufferedReader in = null;
    private PrintWriter out = null;
    private String login = "test";
    private Thread thread3, thread4;
    
    public Client(Socket socket, String login){
        this.socket = socket;this.login=login;
    }
    @Override
    public void run(){
        
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = new PrintWriter(socket.getOutputStream());
            
            Thread thread3 = new Thread(new Reception(in,login));
            thread3.start();
            Thread thread4 = new Thread(new Emission(out));
            thread4.start();
        } catch (IOException e){
            System.err.println(login + " s'est deconnecté.");
        }
    }
}
