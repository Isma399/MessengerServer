package messengerserver;
import shared.Client;
//import java.util.*;
import java.util.ArrayList;
import java.util.Set;
import java.net.InetAddress;
import java.net.UnknownHostException;


public class UserManagment {
    Set<Client>  userSet; 
    
    public UserManagment(){
//        this.userList = new ArrayList<>();
//        try{
//        Client test = new Client ("test", InetAddress.getLocalHost());
//        this.userList.add(test);
//        }catch (UnknownHostException e){e.printStackTrace();}
//        this.userList=list;
    }
    
    public  void addUser(Client client){this.userSet.add(client);}
    public  Set<Client> delUser(Client client){userSet.remove(client);return  userSet;}

    public Set<Client> getUserSet() {
        return userSet;
    }

    public void setUserSet(Set<Client> userSet) {
        this.userSet = userSet;
    }
    
    
    
    

}
