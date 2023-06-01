package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexion {
    Connection con;
    public conexion(){
        try{
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://LAPTOP-6FL1177R;databaseName=basededatosWiki;trustServerCertificate=true;";
            String USER = "usuarioSQL";
            String PASSWORD = "321";
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
