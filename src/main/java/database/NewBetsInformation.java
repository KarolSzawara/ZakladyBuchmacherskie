package database;

import Models.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class NewBetsInformation {
    /**
     *Method checks the most frequent result given by users
     * @param id Match id
     * @return Proposed Bet
     */
     public static String proposedBET(int id){
        String query="SELECT Wynik FROM zaklady WHERE IDmeczu="+id+" GROUP BY Wynik ORDER BY COUNT(*) DESC LIMIT 1";
        ResultSet resultSet=ExecuteQuery.executeSelect(query);
        String result=null;
        try {
            if(resultSet.next()==true) {
                result = resultSet.getString(1);
            }
            else return "Brak zakladow na ten mecz";
        }catch (SQLException e){
            e.printStackTrace();
        }
        return result;
    }

    /**
     * Add new bet to database
     * @param txt Score
     * @param user User
     * @param id
     */
    public static void addBet(String txt, User user,int id){

        String query="INSERT INTO `zaklady` ( `Wynik`, `DataZakladu`, `IDmeczu`,`IDuzytkownika`) VALUES " +
                "('"+txt+"','"+java.time.LocalDate.now().toString()+"','"+id+"', '"+addUser(user)+"')";
        ExecuteQuery.executeQuery(query);

    }

    /**
     * add new user to data base
     * @param user
     * @return
     */
    public static int addUser(User user){

        String query1="SELECT IDuzytkownika FROM `uzykownicy` WHERE nazwa='"+user.getLogin()+"' AND haslo='"+user.getPin()+"'";
        ResultSet resultSet=ExecuteQuery.executeSelect(query1);
        try {
            if(resultSet.next()==true){
                return resultSet.getInt(1);
            }
            else {

                String query2 = "INSERT INTO `uzykownicy` ( `nazwa`, `haslo`) VALUES ( '" + user.getLogin() + "', '" + user.getPin() + "')";
                ExecuteQuery.executeQuery(query2);
                resultSet=ExecuteQuery.executeSelect(query1);
                resultSet.next();
                return resultSet.getInt(1);
            }


        }
        catch (SQLException e){
            e.printStackTrace();
        }
        return 0;
    }
}

