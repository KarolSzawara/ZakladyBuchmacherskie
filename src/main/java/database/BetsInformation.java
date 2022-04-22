package database;

import View.Blad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

public class BetsInformation {
    /**
     * Method returns to the user the match to be displayed in the match information window
     * @param id match id
     * @return return Information About this one match
     */
    public static Vector<String> getMatch(int id){
        String query="SELECT * FROM zakladybuchmacherskie.mecze WHERE IDmeczu="+id;
        ResultSet resultSet=ExecuteQuery.executeSelect(query);
        Vector<String> temp=new Vector<>();
        try {
            resultSet.next();
            temp.add(resultSet.getString(2));
            temp.add(resultSet.getString(3));
            temp.add(resultSet.getString(4));
        }catch (SQLException e){
            Blad.error("Blad bazy danych");
        }
        return temp;
    }

    /**
     * Method returns bets on the selected match to the user.
     * @param id match id
     * @return returns all bets
     */
    public static Vector<Vector<String>> getBetsHistory(int id){
        Vector<Vector<String>> betsVector=new Vector<>();
        String query="SELECT * FROM zakladybuchmacherskie.zaklady WHERE IDmeczu="+id;
        ResultSet resultSet=ExecuteQuery.executeSelect(query);
        try{
            while (resultSet.next()){
                Vector<String> temp=new Vector<>();
                temp.add(resultSet.getString(5));
                temp.add(resultSet.getString(3));
                temp.add(resultSet.getString(2));
                betsVector.add(temp);
            }
        }catch (SQLException e){
            Blad.error("Blad bazy danych");
        }
        return betsVector;
    }

    /**
     * Convert Match infromation to String
     * @param id matchID
     * @return String
     */
    public static String getMatchinString(int id){
        Vector<String> vector=getMatch(id);
        String info="";
        for(String temp:vector) {
            info += temp;
            info += " ";
        }
        return info;
    }
}
