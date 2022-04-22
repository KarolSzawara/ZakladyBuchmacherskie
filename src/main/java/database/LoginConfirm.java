package database;

import Models.User;
import View.Blad;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginConfirm {
    /**
     * Checks if the login is free if not returns the PIN to check if the password is correct
     * @param user
     * @return password or null
     */
    public static String checkUser(User user){
        String query="SELECT haslo FROM `uzykownicy` WHERE nazwa='"+user.getLogin()+"'";

        try {
            ResultSet resultSet=ExecuteQuery.executeSelect(query);
            if(resultSet!=null){
                resultSet.next();
                return resultSet.getString(1);
            }else return null;
        }catch (SQLException e){
            Blad.error("Blad poloczenia");
        }
        return null;
    }

}
