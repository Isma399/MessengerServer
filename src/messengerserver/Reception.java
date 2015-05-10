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
    public ObjectInputStream in; 
    public ObjectOutputStream out;
    public Socket socket;
    
    public Reception(Socket socket,ObjectInputStream in,ObjectOutputStream out){
        this.in = in;this.socket=socket;this.out=out;
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
                        System.out.print("\t" + message);
                        System.out.println("Expéditeur :  " + message.getClient().getLogin());
                        //message.setClient(MessengerServer.server);
                        
                        if (out!=null){
                        Thread thread3 = new Thread(new Emission(out,message));
                        thread3.start();
                        }else{System.err.println("Socket inexistante!!");
                        }
                    };
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
