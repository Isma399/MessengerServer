package view;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class ViewServer extends JFrame implements ActionListener{

private final int screenWidth = 800;
private final int screenHeight;
private static final JTextArea chatRead = new JTextArea();
private static final JTextArea displayInfo = new JTextArea();
private static final JTextArea userList = new JTextArea();


public ViewServer(){

    //Initial Setup
    super("MessengerServer");
        this.screenHeight = 600;
    setResizable(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setSize(screenWidth,screenHeight);
    
    //Main Panels
    JPanel window = new JPanel(new BorderLayout());
    JPanel center = new JPanel(new BorderLayout());
    
    //Center Panel. InfoDisplay Part
    JPanel display = new JPanel( new BorderLayout());
    
    displayInfo.setEditable(false);
    displayInfo.setBackground(Color.BLACK);
    displayInfo.setForeground(Color.WHITE);
    display.add(displayInfo);
    display.setBackground(Color.LIGHT_GRAY);
    center.add(display, BorderLayout.CENTER);
    
    //Center Panel - ChatReader
    
    JPanel chat = new JPanel();
    chat.setLayout(new BoxLayout(chat, BoxLayout.Y_AXIS));
    chat.setPreferredSize(new Dimension(600, 400));
    Font font;
    font = new Font("Verdana",Font.ITALIC, 50);
    chat.setFont(font);
    chatRead.setBackground(Color.yellow);
    chatRead.setForeground(Color.gray);
     
    chatRead.setEditable(false);
    chat.add(chatRead);
    chat.setBorder(BorderFactory.createMatteBorder(3, 0, 0, 10,Color.pink));
    
    center.add(chat, BorderLayout.SOUTH);
    
    //Right Panel : UserList
    JPanel right = new JPanel(new BorderLayout());
    JPanel users = new JPanel(new BorderLayout());
    users.setPreferredSize(new Dimension(200, 800));
    
    //JScrollPane userList = new JScrollPane();
    
    userList.setEditable(false);
    userList.setBackground(Color.DARK_GRAY);
    userList.setForeground(Color.white);
    users.add(userList);
    right.add(users);
    
    window.add(right, BorderLayout.EAST);
    window.add(center, BorderLayout.CENTER);
   
    add(window);
    setVisible(true);
}
@Override
public void actionPerformed(ActionEvent e) {
    chatRead.setText("");
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