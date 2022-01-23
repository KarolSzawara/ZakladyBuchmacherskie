package View;

import Models.User;
import database.MatchHistory;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class MatchWindow {
    MatchWindow(User user){
        this.user=user;
        initComponents();
    }
    User user;
    JTable matchTable;
    JButton showbetButton;
    JButton newbetButton;
    JLabel matchLabel;
     Vector<String> vectorColumns=new Vector<>();

    public void initComponents(){
        JPanel panel=new JPanel();
        panel.setLayout(null);
        JFrame frame=new JFrame();
        frame.setTitle("Mecze");

        frame.add(panel);
        frame.setSize(new Dimension(800,600));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //add Button which show with bets for this match
        showbetButton=new JButton("Pokaż zakłady");
        showbetButton.setBounds(615,100,150,25);
        showbetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(matchTable.getSelectedRow()!=-1) {//check user select match
                    frame.dispose();
                    AvailableBetsWindows availableBetsWindows=new AvailableBetsWindows(user,matchTable.getSelectedRow()+1);
                }else JOptionPane.showMessageDialog(null,"Wybierz zaklad który chcesz zobaczyć");
            }
        });
        panel.add(showbetButton);
        //add Button which show new bets windows
        newbetButton=new JButton("Złóż zakład");
        newbetButton.setBounds(615,150,150,25);
        newbetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(matchTable.getSelectedRow()!=-1) {//check user select match
                    if(MatchHistory.comperDate(matchTable.getSelectedRow()+1)) {//check match not been played earlier
                        if (MatchHistory.countBets(user, matchTable.getSelectedRow() + 1) < 3) { //check the user has too many bets
                            frame.dispose();
                            int b = MatchHistory.countBets(user, matchTable.getSelectedRow() + 1);
                            NewBetWindow newBetWindow = new NewBetWindow(user, matchTable.getSelectedRow() + 1);
                        } else JOptionPane.showMessageDialog(null, "Maksymalna ilosc zakladow na jeden mecz to 3");
                    }else JOptionPane.showMessageDialog(null, "Mecz juz sie odbył");
                    }

                else JOptionPane.showMessageDialog(null,"Wybierz zakład który chcesz obstawić");
            }
        });
        panel.add(newbetButton);
        //add table with math
        addColumns();
        TableModel tableModel=new DefaultTableModel(MatchHistory.getMatchHistory(),vectorColumns);


        matchTable=new JTable(tableModel);


        matchTable.getSelectionModel().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        matchTable.setVisible(true);
        matchTable.setEnabled(true);
        matchTable.setDefaultEditor(Object.class,null);
        JScrollPane pane=new JScrollPane(matchTable);
        matchTable.setFillsViewportHeight(true);
        pane.setBounds(0,0,600,600);
        panel.add(pane);
        frame.show();

    }
    void addColumns(){
        vectorColumns.add("Data Meczu");
        vectorColumns.add("Miasto");
        vectorColumns.add("Drużyny");
    }
}
