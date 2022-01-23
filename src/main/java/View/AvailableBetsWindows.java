package View;

import Models.User;
import database.BetsInformation;
import database.ExecuteQuery;
import database.MatchHistory;
import database.NewBetsInformation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class AvailableBetsWindows {
    JButton backButton;
    JButton newbetsButton;
    JTable matchTable;
    JLabel matchLabel;
    Vector<Vector<String>> rowVector;
    Vector<String> columnVector=new Vector<>();
    User user;
    int matchID;
    AvailableBetsWindows(User user,int id){

        this.user=user;
        this.matchID=id;
        initComponets();
    }
    public void initComponets(){
        JPanel panel=new JPanel();
        panel.setLayout(null);
        JFrame frame=new JFrame();
        frame.setTitle("Zaklady meczu");
        frame.add(panel);
        frame.setSize(new Dimension(800,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add back button
        backButton=new JButton("Wróć");
        backButton.setBounds(615,475,150,25);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                MatchWindow matchWindow=new MatchWindow(user);
            }
        });
        panel.add(backButton);
        //add new bets button
        newbetsButton=new JButton("Złóż zakład");
        newbetsButton.setBounds(615,100,150,25);
        newbetsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(MatchHistory.countBets(user,matchID)<3) {//check count bets for one user is less than 3
                    frame.dispose();
                    NewBetWindow newBetWindow = new NewBetWindow(user, matchID);
                }
                else JOptionPane.showMessageDialog(null,"Maksymalna ilosc zakladow na jeden mecz to 3");
            }
        });
        panel.add(newbetsButton);
        //label with this match information
        String matchINFO=BetsInformation.getMatchinString(matchID);
        matchLabel= new JLabel(matchINFO);
        matchLabel.setBounds(20,0,600,50);
        panel.add(matchLabel);

        //add table with match betting
        addColumns();
        TableModel tableModel=new DefaultTableModel(BetsInformation.getBetsHistory(matchID),columnVector);
        matchTable=new JTable(tableModel);
        matchTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matchTable.setVisible(true);
        matchTable.setEnabled(true);
        matchTable.setDefaultEditor(Object.class,null);
        JScrollPane pane=new JScrollPane(matchTable);
        matchTable.setFillsViewportHeight(true);
        pane.setBounds(0,100,600,500);
        panel.add(pane);
        frame.show();


    }
    private void addColumns(){
        columnVector.add("Nazwa uzytkownika");
        columnVector.add("Podany Wynik");
        columnVector.add("Data zakładu");
    }
}
