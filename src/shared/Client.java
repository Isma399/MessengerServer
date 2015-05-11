package shared;
import java.io.Serializable;
import java.io.ObjectOutputStream;

public class Client implements Serializable{
    private static final long serialVersionUID = 9069032088387632208L;
    private String login;
    private transient ObjectOutputStream outputStream;
    public Client(){}
    public Client(String login,ObjectOutputStream outputStream){
        this.login=login;this.outputStream=outputStream;
    }
    public String getLogin() {return login;}
    public void setLogin(String login) {this.login = login;}
    
    public ObjectOutputStream getOutputStream(){return outputStream;}
    public void setOutputStream(ObjectOutputStream outputStream){
        this.outputStream=outputStream;
    }
    @Override
    public String toString(){return login;}
}
