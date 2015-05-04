package shared;
import java.io.Serializable;

public class Message implements Serializable {
    private static final long serialVersionUID = -20897833L;
    private  String login;
    private  String text;
    public Message(String login, String text){}
    public  void setLogin(String login){this.login=login;}
    public  void setText(String text){this.text=text;}
    public String getLogin(){return this.login;}
    public String getText(){return this.text;}
    @Override
    public String toString(){ return this.login + " : " + this.text;}
   
    
}
