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
                if (Manage.user.get(i).getLogin().equals(message.getClient().getLogin())){}
                else{
                    Manage.user.get(i).getOutputStream().writeObject(message);
                    Manage.user.get(i).getOutputStream().flush();
                }
            }
            }else{System.err.println("ObjectOutputStream non reçu pour Emission.java.");}
        }catch(IOException e){e.printStackTrace();}
    }
}
