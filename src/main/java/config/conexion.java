package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class conexion {

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    public conexion() {
     
        try{
               Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String URL = "jdbc:sqlserver://DESKTOP-2CH9L2U;databaseName=basededatosWiki;trustServerCertificate=true;";
            String USER = "navarro";
            String PASSWORD = "123";
            
          
              con=DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (Exception e) {
            System.out.println("Error" + e);
        }
      
    }

    public Connection getConnection() {
        return con;
    }
public void AgregarGestor() {
    try {
        // Insertar un registro en la tabla "roles" para el rol "Gestor"
        String insertRoleSQL = "INSERT INTO roles (nombre) VALUES ('Gestor')";
        ps = con.prepareStatement(insertRoleSQL);
        int rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Se ha insertado el rol correctamente.");
        } else {
            System.out.println("No se pudo insertar el rol.");
        }

        // Obtener el ID del rol insertado
        String lastInsertIDSQL = "SELECT last_insert_rowid()";
        ps = con.prepareStatement(lastInsertIDSQL);
        rs = ps.executeQuery();
        // Procesar el resultado si es necesario

        // Insertar un registro en la tabla "usuarios" para el usuario "Juan Gonzales"
        String insertUserSQL = "INSERT INTO usuarios (cedula, nombre, apellido, id_Rol) VALUES (123, 'Juan', 'Navarro', 1)";
        ps = con.prepareStatement(insertUserSQL);
        rowsAffected = ps.executeUpdate();
        if (rowsAffected > 0) {
            System.out.println("Se ha insertado el usuario correctamente.");
        } else {
            System.out.println("No se pudo insertar el usuario.");
        }
    } catch (SQLException e) {
        System.err.println("Error al establecer la conexión con la base de datos: " + e.getMessage());
    }
}

 

}
