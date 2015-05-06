package messengerserver;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ActionClient implements Runnable{
    
    private Socket socket = null;
    private String login = "test";
    private Thread thread3, thread4;
    
    public ActionClient(Socket socket, String login){this.socket = socket;this.login=login;}
    
    @Override
    public void run(){
        
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            Thread thread4 = new Thread(new Reception(socket,in));
            thread4.start();
            
//            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
//            Thread thread3 = new Thread(new Emission(out));
//            thread3.start();
// 
        } catch (IOException e){
            System.err.println(login + " s'est deconnecté.");
        }
    }
}
