package messengerserver;
import shared.Client;
//import java.util.*;
import java.util.ArrayList;
import java.util.List;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class UserManagment {
    List<Client>  userList; 
    
    public UserManagment(UserManagment list){
        this.userList = new ArrayList<>();
        try{
        Client test = new Client ("test", InetAddress.getLocalHost());
        this.userList.add(test);
        }catch (UnknownHostException e){e.printStackTrace();}
        this.userList=list;
    }
    
    public  void addUser(Client client){this.userList.add(client);}
    public  List<Client> delUser(Client client){userList.remove(client);return  userList;}

    public List<Client> getUserList() {
        return userList;
    }

    public void setUserList(List<Client> userList) {
        this.userList = userList;
    }
    
    
    
    

}
