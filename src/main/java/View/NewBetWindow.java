package View;

import Models.User;
import database.BetsInformation;
import database.NewBetsInformation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class NewBetWindow {
    JButton addButton;
    User user;
    int idMatch;
    JTextField score1;
    JTextField score2;
    JLabel scoreConnector;
    JLabel matchInfo;
    JLabel userInfo;
    JLabel proposedBet;

    NewBetWindow(User user,int id){
        this.user=user;
        this.idMatch=id;
        initComponents();
    }

    private void initComponents(){
        JPanel panel=new JPanel();
        panel.setLayout(null);
        JFrame frame= new JFrame();
        frame.setTitle("Nowy zakład");
        frame.add(panel);
        frame.setSize(new Dimension(600,300));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add label with user information
        userInfo=new JLabel("Użytkownik:"+user.getLogin());
        userInfo.setBounds(20,20,300,30);
        panel.add(userInfo);
        //add label with match information
        matchInfo=new JLabel(BetsInformation.getMatchinString(idMatch));
        matchInfo.setBounds(20,60,300,30);
        panel.add(matchInfo);
        //add new bet button
        addButton=new JButton("Dodaj zakład");
        addButton.setBounds(225,200,150,25);
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if((!score1.getText().equals(""))&&!(score2.getText().equals(""))) {
                    String temp = "";
                    temp += score1.getText();
                    temp += "-";
                    temp += score2.getText();
                    NewBetsInformation.addBet(temp, user, idMatch);
                    frame.dispose();
                    MatchWindow matchWindow = new MatchWindow(user);
                }
                else JOptionPane.showMessageDialog(null,"Podaj wynik");
            }
        });
        panel.add(addButton);
        //add text field to set score
        score1=new JTextField();
        score1.setBounds(225,100,50,25);
        score1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);


                if(((e.getKeyChar()<'0'|| e.getKeyChar()>'9'))&&(e.getKeyChar()!=KeyEvent.VK_BACK_SPACE)){
                    score1.setEditable(false);
                }
                else {
                    score1.setEditable(true);
                }
            }
        });
        panel.add(score1);
        //add connector
        scoreConnector=new JLabel("-");
        scoreConnector.setFont(new Font("Serif", Font.PLAIN, 18));
        scoreConnector.setBounds(297,100,20,25);
        panel.add(scoreConnector);
        //add text field to set score
        score2=new JTextField();
        score2.setBounds(325,100,50,25);
        score2.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);

                if(((e.getKeyChar()<'0'|| e.getKeyChar()>'9'))&&(e.getKeyChar()!=KeyEvent.VK_BACK_SPACE)){
                    score2.setEditable(false);
                }
                else {
                    score2.setEditable(true);
                }
            }
        });
        panel.add(score2);
        proposedBet=new JLabel("Proponowany wynik:"+ NewBetsInformation.proposedBET(idMatch));
        proposedBet.setBounds(225,150,200,25);
        panel.add(proposedBet);


        frame.show();


    }
}
