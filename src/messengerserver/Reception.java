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
    private final ObjectInputStream in; 
    private final ObjectOutputStream out;
    private final Socket socket;
    private final String login;
    
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
                Message goodbye = new Message();
                goodbye.setClient(MessengerServer.welcomeServer);
                goodbye.setText(login + " s'est déconnecté.");
                Message newList = new Message();
                newList.setClient(MessengerServer.server);
                newList.setText(Manage.user.toString());
                for (int i = 0; i< Manage.user.size();i++){ 
                    try{
                    Manage.user.get(i).getOutputStream().writeObject(goodbye);
                    Manage.user.get(i).getOutputStream().writeObject(newList);
                    Manage.user.get(i).getOutputStream().flush();
                    Manage.user.get(i).getOutputStream().reset();
                }catch(IOException ex){ex.printStackTrace();}
                }
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

