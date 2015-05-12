package messengerserver;
import java.io.IOException;
import shared.Client;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Manage {
    
    List<Client> list = new ArrayList();
    
    public Manage(){}
    public Manage(List<Client> list){this.list=list;}
    
    public void add(Client client) {list.add(client);}
    public String del(ObjectOutputStream out,ObjectInputStream in) {
        String login = new String();
        for (int i = 0;i<list.size();i++){
            if(out==list.get(i).getOutputStream())
            login = list.get(i).getLogin();
            
            try{
                out.close();
                in.close();
      //          list.get(i).getSocket().close();
            }catch(IOException e){
            e.printStackTrace();}
            list.remove(list.get(i));
        }
        return login;
    }
    
    public boolean test(String client) {
        boolean test = true;
        for (Client list1 : list) {
            if (list1.getLogin().equals(client)) {
                test =false; break;
            }
        }
        return test;
    }
    public void setStream(String client, ObjectOutputStream outputStream,ObjectInputStream inputStream,Socket socket){
        list.stream().filter((list1) -> (list1.getLogin().equals(client))).forEach((Client list1) -> {
            list1.setOutputStream(outputStream);
            list1.setInputStream(inputStream);
            list1.setSocket(socket);
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
        for (Client list1 : list) {
            text += list1 +"\n";
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
