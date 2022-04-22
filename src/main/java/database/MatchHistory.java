package database;

import Models.User;
import View.Blad;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Vector;

public class MatchHistory {
    /**
     *
     * Return from database all match
     * @return Vector of match
     */
    public static Vector<Vector<String>> getMatchHistory(){
        Vector<Vector<String>> vectorMatch=new Vector<>();
        Vector<String> singleVector=new Vector<>();
        ResultSet resultSet=ExecuteQuery.executeSelect("SELECT * FROM zakladybuchmacherskie.mecze ");
        try {
            while (resultSet.next()){
                singleVector=new Vector<>();
                singleVector.add(resultSet.getString(2));
                singleVector.add(resultSet.getString(3));
                singleVector.add(resultSet.getString(4));
                vectorMatch.add(singleVector);

            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return vectorMatch;
    }

    /**
     * Method using to check one user have more than 3
     * @param user
     * @param id
     * @return
     */
    public static int countBets(User user,int id){
        String query="SELECT IDuzytkownika FROM `uzykownicy` WHERE nazwa='"+user.getLogin()+"' AND haslo='"+user.getPin()+"'";
        ResultSet resultSet=ExecuteQuery.executeSelect(query);
        Integer i=null;
        try {
            if(resultSet.next()==true) {
                i = resultSet.getInt(1);
                query = "SELECT COUNT(IDzakladu) FROM zaklady WHERE IDuzytkownika="+i+" AND IDmeczu="+id;
                resultSet = ExecuteQuery.executeSelect(query);
                if (resultSet.next() == true) {

                    return resultSet.getInt(1);
                }
            }
            else return 0;
        }catch (SQLException e){
            e.printStackTrace();
        }
        return i;
    }

    /**
     * Method check date of matche is before today's date
     * @param id
     * @return
     */
    public static boolean comperDate(int id){
        String query="SELECT Data FROM `mecze` WHERE IDmeczu="+id;
        ResultSet resultSet=ExecuteQuery.executeSelect(query);
        try {
            resultSet.next();
            String string=resultSet.getString(1);
            LocalDate localDate=LocalDate.parse(string);
            if(java.time.LocalDate.now().isBefore(localDate))
                return true;
            else return false;

        }catch (SQLException e){
            Blad.error("Blad bazy danych");
        }
        return false;
    }
}
