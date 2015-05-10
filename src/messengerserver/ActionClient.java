package messengerserver;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import shared.Message;

public class ActionClient implements Runnable{
    
    private Socket socket = null;
    private String login = "test";
    private Thread thread3, thread4;
    public Message message=new Message();
    
    public ActionClient(Socket socket, String login){this.socket = socket;this.login=login;}
    
    @Override
    public void run(){
        
        try {
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Manage.user.setOutput(login,out);
            for (int i = 0;i<Manage.user.size();i++){
                System.out.println("Output ! " + Manage.user.get(i).getOutputStream());
            }
            Thread thread4 = new Thread(new Reception(socket,in,out));
            thread4.start();
            
         
//           Thread thread3 = new Thread(new Emission(out,message));
//          thread3.start();
 
        } catch (IOException e){
            System.err.println(login + " s'est deconnecté.");
        }
    }
}
