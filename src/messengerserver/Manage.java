package messengerserver;
import shared.Client;
import java.util.ArrayList;
import java.util.List;
import java.io.ObjectOutputStream;

public class Manage {
    
    List<Client> list = new ArrayList();
    
    public Manage(){}
    public Manage(List<Client> list){this.list=list;}
    
    public void add(Client client) {list.add(client);}
    public void del(Client client) {list.remove(client);}
    
    public boolean test(String client) {
        boolean test = true;
        for (int i = 0; i<list.size();i++){
            if (list.get(i).getLogin().equals(client)){test =false; break;}
        }
        return test;
    }
    public void setOutput(String client, ObjectOutputStream outputStream){
        for (int i = 0; i<list.size();i++){
            if (list.get(i).getLogin().equals(client)){list.get(i).setOutputStream(outputStream);}
        }
    }
    public int size(){
        return list.size();
    }
    public Client get(int i){
        return list.get(i);
    }
    
    @Override
    public String toString(){
        String text = "";
        for (Client list1 : list) {
            text += list1 +"\n";
        }
        return text;
    }
    public  static Manage user=new Manage();
}
