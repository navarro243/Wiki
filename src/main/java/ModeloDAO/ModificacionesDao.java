package ModeloDAO;

import Modelo.Modificacion;
import Modelo.Notificacion;
import config.conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ModificacionesDao {

    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    
    NotificacionesDao notificacionDao = new NotificacionesDao();

    public List consultarModificacion() {
        ArrayList<Modificacion> listaModificaciones = new ArrayList<>();
        //Modificacion modificaciones = null;
        String sql = "SELECT * FROM modificaciones";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Modificacion modificacion = new Modificacion();

                modificacion.setId(rs.getInt("id"));
                modificacion.setDescripcionCambio(rs.getString("descripcionCambio"));
                modificacion.setContenidoNuevo(rs.getString("contenidoNuevo"));
                modificacion.setCedula_Usuario(rs.getInt("cedula_Usuario"));
                modificacion.setId_Articulo(rs.getInt("id_Articulo"));

                listaModificaciones.add(modificacion);
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return listaModificaciones;
    }

    public boolean agregarModificacion(String ruta, String descripcion, int cedula, int idArticulo) {
        String sql = "insert into modificaciones (descripcionCambio, contenidoNuevo, cedula_Usuario, id_Articulo) values ('" + descripcion + "','" + ruta + "','" + cedula + "','" + idArticulo + "')";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);

            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }

        
        
    }
    
    public Modificacion consultarUnaModificacion(int idModificacion){
        String sql = "SELECT * FROM modificaciones WHERE id="+ idModificacion;
        Modificacion modificacion = new Modificacion();
        
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                modificacion.setId(rs.getInt("id"));
                modificacion.setDescripcionCambio(rs.getString("descripcionCambio"));
                modificacion.setContenidoNuevo(rs.getString("contenidoNuevo"));
                modificacion.setCedula_Usuario(rs.getInt("cedula_Usuario"));
                modificacion.setId_Articulo(rs.getInt("id_Articulo"));

            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return modificacion;
    }
}
