package messengerserver;
import shared.Message;

import java.io.*;
import java.net.InetAddress;
import java.util.Scanner;
import shared.Client;

public class Emission implements Runnable{
  
   public ObjectOutputStream out2;
   
   
   
    public Emission(ObjectOutputStream out2){
        this.out2=out2;
    }
@Override
public void run(){
    
//    Scanner scanner = new Scanner (System.in);
//    while(true){
//        String  text = scanner.nextLine();
//        Message.setText(text);
//    } 
    try{
        Scanner scanner = new Scanner(System.in);
        while (true){
            String text = scanner.nextLine();
            
            Message message;
            message = new Message(Reception.sender,text);
          //TODO boucle d'envoi des messages sauf au client sender
           
            System.out.println(message.toString());
            out2.writeObject(message);
            out2.flush();
//            out2.reset();

        }

    }catch(IOException e){e.printStackTrace();}
    finally{
        try{ 
            out2.close();
        }catch(IOException e){e.printStackTrace();}}
}
}
