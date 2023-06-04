/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import java.sql.*;
import config.conexion;

import Modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuariosDao {

    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Usuario usua = new Usuario();
    Usuario usuario = new Usuario();

    NotificacionesDao notificacionDao = new NotificacionesDao();

    public int Obtenerusuario(int CEDULA) {
        String sql = "SELECT * FROM usuarios WHERE cedula = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, CEDULA); // Establecer el valor de la cédula en la consulta
            rs = ps.executeQuery();
            if (rs.next()) {
                usua.setCedula(rs.getInt("cedula"));
                usua.setNombre(rs.getString("nombre"));
                usua.setApellido(rs.getString("apellido"));
                usua.setId_rol(rs.getInt("id_Rol"));

                return usua.getId_rol();

            }
        } catch (Exception e) {
            // Manejar la excepción apropiadamente
        }
        return 0;
    }

    public Usuario MostrarUsuario(int cedula) {

        String sql = "SELECT * FROM usuarios WHERE cedula=" + cedula;
        Usuario usuario = new Usuario();
        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                if (cedula == rs.getInt("cedula")) {
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setCedula(rs.getInt("cedula"));
                    usuario.setApellido(rs.getString("apellido"));
                    usuario.setId_rol(rs.getInt("id_Rol"));
                }
            }

        } catch (Exception e) {
            // Manejar la excepción apropiadamente
        }
        return usuario;
    }

    public int consultarRol(int cedula) {
        int id = 0;
        String consultarRol = "SELECT * FROM usuarios WHERE cedula=" + cedula;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consultarRol);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt("id_Rol");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return id;
    }

    public String consultarNombre(int cedula) {
        String consultarRol = "SELECT * FROM usuarios WHERE cedula=" + cedula;
        String nombre = "";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(consultarRol);
            rs = ps.executeQuery();
            if (rs.next()) {
                nombre = rs.getString("nombre");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return nombre;
    }

    public void ascenderUsuario(int cedula, int idNotificacion) {
        int consultarRol = consultarRol(cedula);
        int rolAscender = --consultarRol;
        notificacionDao.cambiarEstadoNotificacion(idNotificacion, 1);

        String sqlUsuarios = "UPDATE usuarios SET id_Rol = ? WHERE cedula = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sqlUsuarios);
            ps.setInt(1, rolAscender);
            ps.setInt(2, cedula);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }

    }

    public void registrarUsuario(Usuario usuario) {
        String sql = "INSERT INTO usuarios(cedula, nombre, apellido, id_Rol) VALUES ('" + usuario.getCedula() + "','" + usuario.getNombre() + "','" + usuario.getApellido() + "','" + usuario.getId_rol() + "')";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Excepcion en insertar usuario" + e);
        }

    }

    public List listarSupervisores(int rol) {
        ArrayList<Usuario> listaSupervisores = new ArrayList<>();
        String sql = "SELECT * FROM usuarios where id_Rol = " + rol;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario usuario = new Usuario();
                usuario.setCedula(rs.getInt("cedula"));
                usuario.setNombre(rs.getString("nombre"));
                usuario.setId_rol(rs.getInt("id_Rol"));

                listaSupervisores.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return listaSupervisores;
    }

    public void AgregarGestor() {

        try {
           
            // Insertar un registro en la tabla "roles" para el rol "Gestor"
            String insertRoleSQL = "INSERT INTO roles (nombre) VALUES ('Gestor'), ('Coordinador'),('Supervisor'), ('Colaborador'), ('Sin Cuenta')";
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
            String insertUserSQL = "INSERT INTO usuarios (cedula, nombre, apellido, id_Rol) VALUES (123, 'Juan', 'Navarro', 1), (456, 'Albeiro', 'Gonzales', 2 ), (789, 'Laura', 'Cruz', 3)";
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
