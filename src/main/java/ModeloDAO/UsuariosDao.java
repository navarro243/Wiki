/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import java.sql.*;
import config.conexion;

import Modelo.Usuario;
import java.util.ArrayList;

public class UsuariosDao {

    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    Usuario usua = new Usuario();
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

        String sql = "SELECT * FROM usuarios  ";
        Usuario u = new Usuario();
        try {

            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            rs = ps.executeQuery();
            while (rs.next()) {
                if (cedula == rs.getInt("cedula")) {
                    u.setCedula(rs.getInt("cedula"));
                    u.setNombre(rs.getString("nombre"));
                    u.setApellido(rs.getString("apellido"));
                    u.setId_rol(rs.getInt("id_Rol"));
                }
            }

        } catch (Exception e) {
            // Manejar la excepción apropiadamente
        }
        return u;
    }

    public int consultarRol(int cedula) {
        int id = 0;
        String consultarRol = "SELECT TOP 1 * FROM usuarios WHERE cedula=" + cedula;
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
        String consultarRol = "SELECT TOP 1 * FROM usuarios WHERE cedula=" + cedula;
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

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
        }catch(SQLException e){
            System.out.println("Excepcion en insertar usuario" + e);
        }

    }

}
