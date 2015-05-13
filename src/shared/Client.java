package shared;
import java.io.Serializable;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class Client implements Serializable{
    private static final long serialVersionUID = 9069032088387632208L;
    private String login;
    private transient Socket socket;
    private ObjectOutputStream outputStream;
    
    
    public Client(){}
    public Client(String login,Socket socket,ObjectOutputStream outputStream)
    {this.login=login;this.socket=socket;this.outputStream=outputStream;}
    
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
       
    public Socket getSocket(){return socket;}
    public void setSocket(Socket socket){this.socket=socket;}
    
    public ObjectOutputStream getOutputStream(){return outputStream;}
    public void setOutputStream(ObjectOutputStream out){this.outputStream=out;}
    
    @Override
    public String toString(){return login;}
}
