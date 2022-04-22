package database;

import View.Blad;

import javax.swing.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ExecuteQuery {
    /**
     * Execetue sql request select on my datebase
     * @param query
     * @return Result of sql
     */
    public static ResultSet executeSelect(String query){
        try {
            Connection connection = Database.getConnect();
            Statement statement = connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);

            return resultSet;
        }catch (SQLException r){
            Blad.error("Blad poloczenia");
        }
        return null;
    }

    /**
     * Execetue sql request which do not need to return information select on my datebase
     * @param query
     */
    public static void executeQuery(String query){
        try {
            Connection connection=Database.getConnect();
            Statement statement=connection.createStatement();
            statement.execute(query);
        }catch (SQLException e){
            Blad.error("Blad bazy danych");
        }
    }
}
