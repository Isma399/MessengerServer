package messengerserver;
import shared.Message;
import java.io.*;
import java.util.Scanner;

public class Emission implements Runnable{
  
   public ObjectOutputStream out;
   public String text;
      
   public Emission(ObjectOutputStream out,String text){this.out=out;this.text=text;}

   @Override
    public void run(){
        try{
        //Scanner scanner = new Scanner(System.in);
        //while (true){
            //String text = scanner.nextLine();
            
            Message message = new Message();
            message.setClient(MessengerServer.messServer);
            message.setText(text);
            
          //TODO boucle d'envoi des messages sauf au client sender
           
            System.out.println("\t\t" + message);
            out.writeObject(message);
            out.flush();
//            out2.reset();
          //  }
        }catch(IOException e){e.printStackTrace();}
//        finally{
//        try{ 
//            out2.close();
//        }catch(IOException e){e.printStackTrace();}}
}
}
