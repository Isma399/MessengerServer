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
            Message welcome =new Message();
            welcome.setClient(MessengerServer.server);
            welcome.setText("Bienvenue, les utilisateurs connectés : " + Manage.user.toStringWelcome());
            out.writeObject(welcome);
            out.flush();
            Manage.user.setStream(login,socket,out);
            Thread thread4 = new Thread(new Reception(socket,in,out,login));
            thread4.start();
        } catch (IOException e){ 
            Manage.user.del(login);
            view.ViewServer.setList(Manage.user.toString());
            view.ViewServer.appendInfo("Déconnexion de " + login + ".\n");
           // System.err.println(login + " s'est deconnecté.");
           // e.printStackTrace();
            }
    }
}
