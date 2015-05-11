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
    public String del(ObjectOutputStream out) {
        String user = "";
        for (int i = 0;i<list.size();i++){
            if(out==list.get(i).getOutputStream())
                user = list.get(i).getLogin();
            list.remove(list.get(i));
        }
        return user;
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
    public void setOutput(String client, ObjectOutputStream outputStream){
        list.stream().filter((list1) -> (list1.getLogin().equals(client))).forEach((list1) -> {
            list1.setOutputStream(outputStream);
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
        String text = "";
        for (Client list1 : list) {
            text += list1 +"\n";
        }
        return text;
    }
    public  static Manage user=new Manage();
}
