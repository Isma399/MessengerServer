package messengerserver;

import shared.Message;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javafx.event.ActionEvent;
import view.ViewServer;
//import java.awt.event.ActionEvent;

public class Reception implements Runnable{
    public ObjectInputStream in; 
    public ObjectOutputStream out;
    public Socket socket;
    private String login;
    
    public Reception(Socket socket,ObjectInputStream in,ObjectOutputStream out,String login){
        this.in = in;this.socket=socket;this.out=out;this.login=login;
    }

    @Override
    public void run() {
        boolean connected = true;          
        while (connected){
            if (in != null){
            try{
                Object objectReceived = in.readObject();
                if (!(objectReceived instanceof Message)){System.out.println("Bad Object. Classe -> " + objectReceived.getClass());}
                else{
                    Message message = (Message)objectReceived;
                    if (message.getText() != null){
                        //System.out.println(message);
                        view.ViewServer.appendToChat(message.getClient().getLogin() + " : " + message.getText() + "\n");
                        if (out!=null){
                            Thread thread3 = new Thread(new Emission(out,message));
                            thread3.start();
                        }else{System.err.println("Socket inexistante!!");}
                    }
                } 
            }    
            catch (IOException e){
                Manage.user.del(login);
                view.ViewServer.setList(Manage.user.toString());
                view.ViewServer.appendInfo("Déconnexion de " + login + ".\n");
                connected=false;
            }
            catch (ClassNotFoundException ex) {
                System.err.println("Class Not found");
                ex.printStackTrace();
            } 
        }
    }
    }
}

