package messengerserver;
import java.io.IOException;
import shared.Client;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Manage extends Thread {
    
    List<Client> list = new ArrayList();
    
    public Manage(){}
    public Manage(List<Client> list){this.list=list;}
    
    public void add(Client client) {
        //L'accès à la liste est commun à tous les threads Authentification
        synchronized(list){
        list.add(client);}
    }
    public void del(String login) {
        for (int i = 0;i<list.size();i++){
            if(login.equals(list.get(i).getLogin())){
            try{
                if (list.get(i).getSocket()!=null){
                list.get(i).getSocket().close();}
            }catch(IOException e){   e.printStackTrace();}
            list.remove(list.get(i));
            }  
        }
    }
    public boolean test(String client) {
        boolean test = true;
        if(client.equals("welcomeServer") || client.equals("server")){test=false;}
        else{
        for (Client list1 : list) {
            if (list1.getLogin().equals(client)) {
                test =false; break;
            }
        }}
        return test;
    }
    public void setStream(String client, Socket socket,ObjectOutputStream out){
        list.stream().filter((list1) -> (list1.getLogin().equals(client))).forEach((Client list1) -> {
            list1.setSocket(socket);
            list1.setOutputStream(out);
        });
    }
    public int size(){
        return list.size();
    }
    public Client get(int i){
        return list.get(i);
    }
    @Override
    public String toString(){
        String text = new String();
        for (Client client : list) {
            text += client.getLogin() +"\n";
        }
        return text;
    }
    public String toStringWelcome(){
        String text = "";
        for (Client list1 : list) {
            text += list1 + " | ";
        }
        text +="\n";
        return text;
    }
    public  static Manage user=new Manage();
          
}
