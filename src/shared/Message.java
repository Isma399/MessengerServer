package shared;
import java.io.Serializable;

public class Message implements Serializable {
   // private static final long serialVersionUID = -6500551252073842690L;
    private static String login;
    private static String text;
    public Message(String login, String text){}
    public  void setLogin(String login){Message.login=login;}
    public  void setText(String text){Message.text=text;}
    public String getLogin(){return Message.login;}
    public String getText(){return Message.text;}
}
