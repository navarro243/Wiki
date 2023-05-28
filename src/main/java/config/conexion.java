package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    Connection con;
    public conexion(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://DESKTOP-2CH9L2U:1433;databaseName=basededatosWiki;trustServerCertificate=true;";
            String USER = "navarro";
            String PASSWORD = "123";
            con=DriverManager.getConnection(URL, USER, PASSWORD);
              
        }
        catch (Exception e){
            System.out.println("Error"+e);
}
    }
    public Connection getConnection(){
        return con;
    }
    

    
}
