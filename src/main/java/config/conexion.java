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

        try {
            Class.forName("org.sqlite.JDBC");
            // Establecer la URL de conexión para una base de datos en memoria RAM
            String url = "jdbc:sqlite::memory:";

            // Establecer la conexión con la base de datos en memoria RAM
            con = DriverManager.getConnection(url);

            // Habilitar las claves foráneas
            String enableForeignKeysSQL = "PRAGMA foreign_keys = ON;";
            ps = con.prepareStatement(enableForeignKeysSQL);
            ps.execute();

            // Crear la tabla "articulos"
            String createArticulosTableSQL = "CREATE TABLE IF NOT EXISTS articulos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "titulo TEXT NOT NULL,"
                    + "contenido TEXT,"
                    + "id_Wiki INTEGER"
                    + ")";
            ps = con.prepareStatement(createArticulosTableSQL);
            ps.execute();

            // Crear la tabla "modificaciones"
            String createModificacionesTableSQL = "CREATE TABLE IF NOT EXISTS modificaciones ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "descripcionCambio TEXT NOT NULL,"
                    + "contenidoNuevo TEXT NOT NULL,"
                    + "cedula_Usuario INTEGER,"
                    + "id_Articulo INTEGER,"
                    + "FOREIGN KEY (cedula_Usuario) REFERENCES usuarios (cedula),"
                    + "FOREIGN KEY (id_Articulo) REFERENCES articulos (id)"
                    + ")";
            ps = con.prepareStatement(createModificacionesTableSQL);
            ps.execute();

            // Crear la tabla "notificaciones"
            String createNotificacionesTableSQL = "CREATE TABLE IF NOT EXISTS notificaciones ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "estado INTEGER NOT NULL,"
                    + "id_modificacion INTEGER,"
                    + "cedula_Usuario INTEGER,"
                    + "id_Rol INTEGER,"
                    + "asunto TEXT,"
                    + "mensaje TEXT,"
                    + "FOREIGN KEY (id_modificacion) REFERENCES modificaciones (id),"
                    + "FOREIGN KEY (cedula_Usuario) REFERENCES usuarios (cedula),"
                    + "FOREIGN KEY (id_Rol) REFERENCES roles (id)"
                    + ")";
            ps = con.prepareStatement(createNotificacionesTableSQL);
            ps.execute();

            // Crear la tabla "roles"
            String createRolesTableSQL = "CREATE TABLE IF NOT EXISTS roles ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT NOT NULL"
                    + ")";
            ps = con.prepareStatement(createRolesTableSQL);
            ps.execute();

            // Crear la tabla "usuarios"
            String createUsuariosTableSQL = "CREATE TABLE IF NOT EXISTS usuarios ("
                    + "cedula INTEGER PRIMARY KEY,"
                    + "nombre TEXT NOT NULL,"
                    + "apellido TEXT NOT NULL,"
                    + "id_Rol INTEGER,"
                    + "FOREIGN KEY (id_Rol) REFERENCES roles (id)"
                    + ")";
            ps = con.prepareStatement(createUsuariosTableSQL);
            ps.execute();

            // Crear la tabla "usuarios_articulos"
            String createUsuariosArticulosTableSQL = "CREATE TABLE IF NOT EXISTS usuarios_articulos ("
                    + "cedula_Usuario INTEGER,"
                    + "id_Articulo INTEGER,"
                    + "estado TEXT,"
                    + "FOREIGN KEY (cedula_Usuario) REFERENCES usuarios (cedula),"
                    + "FOREIGN KEY (id_Articulo) REFERENCES articulos (id)"
                    + ")";
            ps = con.prepareStatement(createUsuariosArticulosTableSQL);
            ps.execute();

            // Crear la tabla "wikis"
            String createWikisTableSQL = "CREATE TABLE IF NOT EXISTS wikis ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                    + "nombre TEXT,"
                    + "id_Rol INTEGER NULL ,"
                    + "FOREIGN KEY (id_Rol) REFERENCES roles (id)"
                    + ")";
            ps = con.prepareStatement(createWikisTableSQL);
            ps.execute();

            // Crear la tabla "wikis_usuarios"
            String createWikisUsuariosTableSQL = "CREATE TABLE IF NOT EXISTS wikis_usuarios ("
                    + "cedula_usuario INTEGER,"
                    + "id_wiki INTEGER,"
                    + "estado TEXT,"
                    + "FOREIGN KEY (cedula_usuario) REFERENCES usuarios (cedula),"
                    + "FOREIGN KEY (id_wiki) REFERENCES wikis (id)"
                    + ")";
            ps = con.prepareStatement(createWikisUsuariosTableSQL);
            ps.execute();
         
                
            

        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error: " + e.getMessage());
        }

    }

    public Connection getConnection() {
        return con;
    }

    

}
