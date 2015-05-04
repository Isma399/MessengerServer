package messengerserver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;


public class ActionClient implements Runnable{
    
    private Socket socket = null;
    private String login = "test";
    private Thread thread3, thread4;
    
    public ActionClient(Socket socket, String login){
        this.socket = socket;this.login=login;
    }
    @Override
    public void run(){
        
        try {
            ObjectOutputStream out2 = new ObjectOutputStream(socket.getOutputStream());
            Thread thread3 = new Thread(new Emission(out2));
            thread3.start();
            
            ObjectInputStream in2 = new ObjectInputStream(socket.getInputStream());
            Thread thread4 = new Thread(new Reception(in2));
            thread4.start();
 
        } catch (IOException e){
            System.err.println(login + " s'est deconnecté.");
        }
    }
}
