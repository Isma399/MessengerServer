package messengerserver;
import shared.Message;
import java.net.*; 
import java.io.*;
import java.util.Scanner;

public class Emission implements Runnable{
   
   public ObjectOutputStream out2;
   public Message message;
    public Emission(ObjectOutputStream out2){
        this.out2=out2;
    }
@Override
public void run(){
    
 
    try{
        Scanner scanner = new Scanner(System.in);
        while (true){
            String text = scanner.nextLine();
            message.setText(text);
            out2.writeObject(message);
            out2.flush();
            out2.reset();
            out2.close();
        }

    }catch(IOException e){System.out.println(e);}
}
}
