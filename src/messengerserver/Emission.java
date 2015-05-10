package messengerserver;
import shared.Message;
import java.io.*;

public class Emission implements Runnable{
  
   public ObjectOutputStream out;
   public Message message,mess;
         
   public Emission(ObjectOutputStream out, Message message){this.out=out;this.message=message;}

   @Override
    public void run(){
        try{
                    
            if (out!=null){
            for (int i=0;i<Manage.user.size();i++){
                if (Manage.user.get(i).getLogin().equals(message.getClient().getLogin())){
                    System.out.println(message.getClient().getSocket() + " ne devrait pas recevoir.");
                }
            }
            System.out.println("Emission de : " + message.getClient().getLogin() + " vers : " + Manage.user);
            out.writeObject(message);
            out.flush();
            }else{System.err.println("Socket puante");}
        }catch(IOException e){e.printStackTrace();}
    }
}
