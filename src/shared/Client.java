package shared;
import java.io.Serializable;
import java.net.SocketAddress;
import java.io.ObjectOutputStream;

public class Client implements Serializable{
    private String login;
    private SocketAddress clientSocket=null;
    private transient ObjectOutputStream outputStream;
    public Client(){}
    public Client(String login,SocketAddress clientSocket,ObjectOutputStream outputStream){
        this.login=login;this.clientSocket=clientSocket;this.outputStream=outputStream;
    }
    public String getLogin() {
        return login;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public SocketAddress getSocket(){
    return clientSocket;
    }
    public void setSocket(SocketAddress clientSocket){
        this.clientSocket=clientSocket;
    }
    public ObjectOutputStream getOutputStream(){
    return outputStream;
    }
    public void setOutputStream(ObjectOutputStream outputStream){
        this.outputStream=outputStream;
}

    @Override
    public String toString(){
        return login + clientSocket;
    }
            
    
    
}
