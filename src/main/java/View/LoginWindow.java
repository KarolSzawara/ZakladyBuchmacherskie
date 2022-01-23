package View;

import Models.User;
import database.Database;
import database.LoginConfirm;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;

public class LoginWindow {
    private static JLabel loginLabel,pinLabel;
    private static JTextField usernameField;
    private static JButton loginButton;
    private static JPasswordField pinField;
    public LoginWindow(){
        initComponent();
    }
    public void initComponent(){
        JPanel panel=new JPanel();
        panel.setLayout(null);
        JFrame frame=new JFrame();
        frame.setTitle("Logowanie");
        frame.setLocation(new Point(500,300));
        frame.add(panel);
        frame.setSize(new Dimension(400,200));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add login label
        loginLabel = new JLabel("Login");
        loginLabel.setBounds(100, 8, 70, 20);
        panel.add(loginLabel);
        //add username field
        usernameField=new JTextField();
        usernameField.setBounds(100,27,193,28);
        panel.add(usernameField);
        //add pin label
        pinLabel=new JLabel("Pin");
        pinLabel.setBounds(100,55,70,20);
        panel.add(pinLabel);

        pinField=new JPasswordField();
        pinField.addKeyListener(new KeyAdapter() {
                                    @Override
                                    public void keyPressed(KeyEvent e) {
                                        super.keyPressed(e);
                                        String value=pinField.getText();
                                        int l =value.length();
                                        if(((e.getKeyChar()<'0'|| e.getKeyChar()>'9'))&&(e.getKeyChar()!=KeyEvent.VK_BACK_SPACE)){
                                            pinField.setEditable(false);
                                        }
                                        else {
                                            pinField.setEditable(true);
                                        }
                                    }
                                });
        pinField.setBounds(100, 75, 193, 28);
        panel.add(pinField);
        //add button
        loginButton=new JButton("Zaloguj się");
        loginButton.setBounds(130,110,133,25);
        loginButton.setBackground(Color.BLACK);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(!(usernameField.getText().equals(""))&&!(pinField.getText().equals(""))) {//check is space for login and pin has something written on it


                    User user = new User(String.copyValueOf(pinField.getPassword()), usernameField.getText());
                    String temp=LoginConfirm.checkUser(user);
                    if(temp==null) {//check if user exists
                        frame.dispose();
                        MatchWindow matchWindow = new MatchWindow(user);
                    }
                    else if (temp.equals(user.getPin())){//check pin is correct
                        frame.dispose();
                        MatchWindow matchWindow = new MatchWindow(user);
                    }else JOptionPane.showMessageDialog(null,"Nieprawidlowe haslo");
                }else{
                    JOptionPane.showMessageDialog(null,"Brak danych logowanie");
                }
            }
        });
        panel.add(loginButton);

        frame.show();
    }
}
