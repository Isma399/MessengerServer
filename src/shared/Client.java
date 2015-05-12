package shared;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.net.Socket;

public class Client implements Serializable{
    private static final long serialVersionUID = 9069032088387632208L;
    private String login;
    private transient ObjectOutputStream outputStream;
    private transient ObjectInputStream inputStream;
    private transient Socket socket;
    public Client(){}
    public Client(String login,ObjectOutputStream outputStream,ObjectInputStream inputStream,Socket socket){
        this.login=login;this.outputStream=outputStream;this.inputStream=inputStream;this.socket=socket;
    }
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    
    public ObjectOutputStream getOutputStream(){return outputStream;}
    public void setOutputStream(ObjectOutputStream outputStream){
        this.outputStream=outputStream;
    }
    public ObjectInputStream getInputStream(){return inputStream;}
    public void setInputStream(ObjectInputStream inputStream){
        this.inputStream=inputStream;
    }
    public Socket getSocket(){return socket; }
    public void setSocket(Socket socket){
        this.socket=socket;
    }
    
    @Override
    public String toString(){return login;}
}
