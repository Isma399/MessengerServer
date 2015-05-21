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
    private Message message=new Message();
    
    public ActionClient(Socket socket, String login){this.socket = socket;this.login=login;}
    
    @Override
    public void run(){
        
        try {
            
            ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
            Manage.user.setStream(login,socket,out);
            
            Message welcome =new Message();
            welcome.setClient(MessengerServer.server);
            welcome.setText(Manage.user.toString());
                        
            for (int i = 0; i< Manage.user.size();i++){ //envoi de l'userList
                Manage.user.get(i).getOutputStream().writeObject(welcome);
                Manage.user.get(i).getOutputStream().flush();
                Manage.user.get(i).getOutputStream().reset();
            }
                welcome.setClient(MessengerServer.welcomeServer);
                //Envoi du message de bienvenu.
            for (int i = 0; i< Manage.user.size();i++){
                if (Manage.user.get(i).getLogin().equals(login)){
                    welcome.setText("Bienvenue " + login + " sur NFP Messenger.");
                    Manage.user.get(i).getOutputStream().writeObject(welcome);
                    }
                else{
                    
                    welcome.setText(login + " s'est connecté(e).");
                Manage.user.get(i).getOutputStream().writeObject(welcome);
                Manage.user.get(i).getOutputStream().flush();
                }
            }
            
            Thread thread4 = new Thread(new Reception(socket,in,out,login));
            thread4.start();
        } catch (IOException e){ 
            Manage.user.del(login);
            view.ViewServer.setList(Manage.user.toString());
            view.ViewServer.appendInfo("Action. Déconnexion de " + login + ".\n");
           // System.err.println(login + " s'est deconnecté.");
           // e.printStackTrace();
            }
    }
}
