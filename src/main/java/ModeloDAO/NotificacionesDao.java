package ModeloDAO;

import Modelo.Notificacion;
import config.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificacionesDao {
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con ;
    
    
    public void enviarNotificacionAscenso(Notificacion notificacion){
        String sql = "INSERT INTO notificaciones (estado, cedula_Usuario, id_Rol, asunto) VALUES ('"+notificacion.getEstado()+"','"+notificacion.getCedula_usuario()+"','"+notificacion.getId_Rol()+"', '"+notificacion.getAsunto()+"')";

        try{
            con = cn.getConnection();
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
        }catch(SQLException e){
            System.out.println(e);
        }
    }
    
    public List listarNotificaciones(int id_Rol){
        ArrayList <Notificacion> listaNotificaciones = new ArrayList<>();
        String sql = "SELECT * FROM notificaciones WHERE id_Rol="+ id_Rol;
        
        try{
            con = cn.getConnection();
            ps= con.prepareStatement(sql);
            rs = ps.executeQuery();
            while(rs.next()){
                Notificacion notificacion = new Notificacion();
                
                notificacion.setId(rs.getInt("id"));
                notificacion.setEstado(rs.getInt("estado"));
                notificacion.setId_modificacion(rs.getInt("id_modificacion"));
                notificacion.setCedula_usuario(rs.getInt("cedula_Usuario"));
                notificacion.setId_Rol(rs.getInt("id_Rol"));
                notificacion.setAsunto(rs.getString("asunto"));
                
                listaNotificaciones.add(notificacion);
            }
        }catch(SQLException e){
            System.out.println(e);
        }
        
        return listaNotificaciones;
    }
    
    
}