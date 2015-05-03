package messengerserver;

import shared.Message;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reception implements Runnable{
    public        ObjectInputStream in2; 
    
    public Reception(ObjectInputStream in2){
        this.in2 = in2;
    }

    @Override
    public void run() {
        while (true){
            try{
                Object objectReceived = in2.readObject();
                if (!(objectReceived instanceof Message)){System.out.println("Bad Object.");}else{
                    Message message = (Message)objectReceived;
                    System.out.println(message.getLogin() + " : " + message.getText());
                }                 
            } catch (IOException e){
                //e.printStackTrace();
                System.out.println("IOException!!!!!!!");
            } catch (ClassNotFoundException ex) {
                System.err.println("Class Not found");
                ex.printStackTrace();
               // Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
