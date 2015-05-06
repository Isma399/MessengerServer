package messengerserver;

import shared.Message;
import shared.Client;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Reception implements Runnable{
    public        ObjectInputStream in; 
    public  ObjectOutputStream out;
    public Socket socket;
    public static Client sender;
    
    public Reception(Socket socket,ObjectInputStream in){
        this.in = in;this.socket=socket;
    }

    @Override
    public void run() {
        while (true){
            if (in != null){
            try{
                Object objectReceived = in.readObject();
                if (!(objectReceived instanceof Message)){System.out.println("Bad Object. Classe -> " + objectReceived.getClass());}
                else{
                    Message message = (Message)objectReceived;
                    if (message.getText() != null){
                        System.out.print("\t" + message.getClient().getLogin() + "(" +  message.getClient().getIpAddress().getHostAddress() +")");
                        System.out.println(" : " + message.getText());
                        //Test Emission
                        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
                        message.setText(message.getClient().getLogin() + " :" + message.getText());
                        Thread thread3 = new Thread(new Emission(out,message.getText()));
                        thread3.start();
                    }                 
                }
            }catch (IOException e){
                //e.printStackTrace();
                System.out.println("IOException!!!!!!!");
                try{in.close();}catch(IOException ex){ex.printStackTrace();}
            }catch (ClassNotFoundException ex) {
                System.err.println("Class Not found");
                ex.printStackTrace();
               // Logger.getLogger(Reception.class.getName()).log(Level.SEVERE, null, ex);
            } 
//            finally{
//                try{
//                    //in2.reset();
//                    in2.close();
//                }catch(IOException e){e.printStackTrace();}
//            }
        }
    }
}
}
