package database;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Database {
    /**
     * The class that connects to the database
     */
    private static String url="jdbc:mysql://localhost:3306/zakladybuchmacherskie";
    private static String user="root";
    private static String password="";

    /**
     * Connecting to database
     * @return
     */
    public static Connection getConnect(){
        Connection connection=null;
        try {
            connection = DriverManager.getConnection(url,user,password);

        }catch (SQLException e){
            JOptionPane.showMessageDialog(null,"Brak poloczenia z baza danych");
        }
        return connection;
    }
}
