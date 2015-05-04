package messengerserver;
import shared.Message;

import java.io.*;
import java.util.Scanner;

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
            Message message = new Message("","");
            message.setText(text);
            message.setLogin("Le serveur dit ");
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
