package dao;


import java.sql.Connection;
import java.sql.DriverManager;

public class DBConection {
    Connection conn = null;
    public static Connection conDB()
    {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost/gestionEcole", "root", "root");
            System.out.println("connecté avec succès à la dase de données");
            return conn;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;

        }
    }
}