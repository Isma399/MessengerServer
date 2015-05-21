package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewServer extends JFrame {

private final int screenWidth = 600;
private final int screenHeight;
private static final JTextArea chatRead = new JTextArea();
private static final JTextArea displayInfo = new JTextArea();
private static final JTextArea userList = new JTextArea();


public ViewServer(){

    //Initial Setup
    super("MessengerServer");
    screenHeight = 500;
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setLocation(0, 0);
    setSize(screenWidth,screenHeight);
    
    //Main Panels
    JPanel window = new JPanel(new BorderLayout());
    JPanel center = new JPanel(new BorderLayout());
    
    //Center Panel. InfoDisplay Part
    JPanel display = new JPanel( new BorderLayout());
    
    displayInfo.setEditable(false);
    displayInfo.setBackground(Color.BLACK);
    displayInfo.setBorder(BorderFactory.createMatteBorder(10, 10, 10, 10,Color.black));
    displayInfo.setForeground(Color.WHITE);
   displayInfo.setPreferredSize(new Dimension(300, 400));
    display.add(displayInfo);
    display.setBackground(Color.LIGHT_GRAY);
    center.add(display, BorderLayout.NORTH);
    
    //Center Panel - ChatReader
    
    JPanel chat = new JPanel();
    chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
    //chat.setPreferredSize(new Dimension(200, 400));
    Font font;
    font = new Font("Verdana",Font.ITALIC, 50);
    chat.setFont(font);
    chatRead.setBackground(Color.yellow);
    chatRead.setForeground(Color.gray);
     
    chatRead.setEditable(false);
    chat.add(chatRead);
    chat.setBorder(BorderFactory.createMatteBorder(10, 0, 0, 0,Color.pink));
    
    center.add(chat, BorderLayout.CENTER);
    
    //Right Panel : UserList
    JPanel right = new JPanel(new BorderLayout());
    JPanel users = new JPanel(new BorderLayout());
    users.setPreferredSize(new Dimension(200, 800));
    users.setBorder(BorderFactory.createMatteBorder(0, 10, 0, 0,Color.pink));
        
    userList.setEditable(false);
    userList.setBackground(Color.DARK_GRAY);
    userList.setForeground(Color.white);
    userList.setBorder(BorderFactory.createMatteBorder(10, 10, 0, 0,Color.darkGray));
    users.add(userList);
    right.add(users);
    
    window.add(right, BorderLayout.EAST);
    window.add(center, BorderLayout.CENTER);
   
    add(window);
    setVisible(true);
}

public static void appendToChat(String text){
    chatRead.append(text);
}
public static void setList(String text){
    userList.setText(text);
}
public static void appendInfo(String text){
    displayInfo.append(text);
}
}