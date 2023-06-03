package ModeloDAO;

import Modelo.Articulo;
import Modelo.Notificacion;
import config.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacionesDao {

    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;

    public void enviarNotificacionAscenso(Notificacion notificacion) {
        String sql = "INSERT INTO notificaciones (estado, cedula_Usuario, id_Rol, asunto, mensaje) VALUES ('" + notificacion.getEstado() + "','" + notificacion.getCedula_usuario() + "','" + notificacion.getId_Rol() + "', '" + notificacion.getAsunto() + "','" + notificacion.getMensaje() + "')";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List listarNotificaciones(int id_Rol, int cedula) {
        ArrayList<Notificacion> listaNotificaciones = new ArrayList<>();
        String sql = "SELECT * FROM notificaciones WHERE id_Rol=" + id_Rol + "OR cedula_Usuario=" + cedula;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion();

                notificacion.setId(rs.getInt("id"));
                notificacion.setEstado(rs.getInt("estado"));
                notificacion.setId_modificacion(rs.getInt("id_modificacion"));
                notificacion.setCedula_usuario(rs.getInt("cedula_Usuario"));
                notificacion.setId_Rol(rs.getInt("id_Rol"));
                notificacion.setAsunto(rs.getString("asunto"));
                notificacion.setMensaje(rs.getString("mensaje"));

                listaNotificaciones.add(notificacion);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return listaNotificaciones;
    }

    public void cambiarEstadoNotificacion(int idNotificacion, int estado) {
        String sql = "UPDATE notificaciones SET estado = " + estado + " WHERE id = " + idNotificacion;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List RecibirNotificaciones(int cedula) {
        ArrayList<Notificacion> listaNotificaciones = new ArrayList<>();
        String sql = "SELECT * FROM notificaciones WHERE cedula_Usuario=" + cedula;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion();

                notificacion.setId(rs.getInt("id"));
                notificacion.setEstado(rs.getInt("estado"));
                notificacion.setId_modificacion(rs.getInt("id_modificacion"));
                notificacion.setCedula_usuario(rs.getInt("cedula_Usuario"));
                notificacion.setId_Rol(rs.getInt("id_Rol"));
                notificacion.setAsunto(rs.getString("asunto"));
                notificacion.setMensaje(rs.getString("mensaje"));

                listaNotificaciones.add(notificacion);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listaNotificaciones;
    }

    public List listarWikisAcceso(int cedula) {
        ArrayList<Articulo> listaArticulosAcceso = new ArrayList<>();
        String sql = "SELECT * FROM wikis_usuarios WHERE cedula_usuario = " + cedula + "AND estado = 'activo'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setId(rs.getInt("id_wiki"));
                articulo.setTitulo("estado");

                listaArticulosAcceso.add(articulo);
            }

        } catch (SQLException e) {
            System.out.println(e);
        }
        return listaArticulosAcceso;
    }

    public List NotificacionesSupervisor(String asunto, int cedula) {
        ArrayList<Notificacion> NotificacionesSupervisor = new ArrayList<>();
        String sql = "SELECT * FROM notificaciones WHERE asunto='Modificacion Articulo' OR cedula_Usuario=" + cedula ;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion();

                notificacion.setId(rs.getInt("id"));
                notificacion.setEstado(rs.getInt("estado"));
                notificacion.setId_modificacion(rs.getInt("id_modificacion"));
                notificacion.setCedula_usuario(rs.getInt("cedula_Usuario"));
                notificacion.setId_Rol(rs.getInt("id_Rol"));
                notificacion.setAsunto(rs.getString("asunto"));
                notificacion.setMensaje(rs.getString("mensaje"));

                NotificacionesSupervisor.add(notificacion);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return NotificacionesSupervisor;
    }

    public void notificacionModificacion(Notificacion notificacion) {
        String sql = "INSERT INTO notificaciones (estado, cedula_Usuario, id_Modificacion, id_Rol, asunto, mensaje) VALUES ('" + notificacion.getEstado() + "','" + notificacion.getCedula_usuario() + "','" + notificacion.getId_modificacion() + "','" + notificacion.getId_Rol() + "', '" + notificacion.getAsunto() + "','" + notificacion.getMensaje() + "')";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
        } catch (SQLException e) {
            System.out.println("Error en notificacion de modificacion" + e);
        }
    }
    
    public List notificacionesGestor(){
        ArrayList<Notificacion> NotificacionesGestor = new ArrayList<>();
        String sql = "SELECT * FROM notificaciones WHERE asunto='Ascenso' OR asunto='Nuevo Usuario'" ;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Notificacion notificacion = new Notificacion();

                notificacion.setId(rs.getInt("id"));
                notificacion.setEstado(rs.getInt("estado"));
                notificacion.setId_modificacion(rs.getInt("id_modificacion"));
                notificacion.setCedula_usuario(rs.getInt("cedula_Usuario"));
                notificacion.setId_Rol(rs.getInt("id_Rol"));
                notificacion.setAsunto(rs.getString("asunto"));
                notificacion.setMensaje(rs.getString("mensaje"));

                NotificacionesGestor.add(notificacion);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }

        return NotificacionesGestor;
    }
}
