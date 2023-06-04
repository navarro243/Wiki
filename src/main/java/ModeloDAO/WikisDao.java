package ModeloDAO;

import Modelo.Usuario;
import java.sql.*;
import config.conexion;
import java.util.*;
import java.util.List;
import Modelo.Wiki;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.sqlite.SQLiteException;

public class WikisDao {

    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con = cn.getConnection();;
    Wiki wiki = new Wiki();
    Usuario usuario = new Usuario();

    public List obtenerWikis()   {
        ArrayList<Wiki> list = new ArrayList<>();
        String sql = "SELECT * FROM wikis";
        try {
            
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Wiki wiki1 = new Wiki();
                wiki1.setId(rs.getInt("id"));
                wiki1.setNombre(rs.getString("nombre"));
                list.add(wiki1);
                System.out.println(list.toString());
            }

        } catch (Exception e) {
            System.out.println("Error en obtenerWikis");
        }   
        return list;

    }

    public void agregarWiki(String nombre)  {
        String sql = "INSERT INTO wikis (nombre) values ('"+nombre+"') " ;
        try {
           
            ps = con.prepareStatement(sql);
            int rowsAffected = ps.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Se ha insertado la wiki correctamente.");
            } else {
                System.out.println("No se pudo insertar el rol.");
            }
        } catch (SQLiteException e) {
            System.out.println("Error en Insertar Wikis"+ e);

        } catch (SQLException ex) {
            Logger.getLogger(WikisDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }

    public Wiki list(int id) {
        String sql = "SELECT * FROM wikis WHERE id=" + id;

        try {
          
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                wiki.setId(rs.getInt("id"));
                wiki.setNombre(rs.getString("nombre"));
                wiki.setId_Rol(rs.getInt("id_Rol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return wiki;
    }

    public void editarWiki(Wiki wiki) {
        String sql = "UPDATE wikis SET nombre = '" + wiki.getNombre() + "' WHERE id = " + wiki.getId();

        try {

            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public boolean eliminar(int id) {
        String sql = "delete from wikis where id=" + id;
        try {
          
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
        }
        return false;

    }

    public void accesoWiki(int idWiki, int cedula_usuario) {
        String sql = "INSERT INTO wikis_usuarios (cedula_usuario, id_wiki, estado) VALUES ('" + cedula_usuario + "','" + idWiki + "','Pendiente')";
        try {
        
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List listarWikisAcceso(int cedula) {
        ArrayList<Wiki> listaWikisAcceso = new ArrayList<>();
        String sql = "SELECT * FROM wikis_usuarios WHERE cedula_usuario = " + cedula + "AND estado = 'asignado'";
        
        try {
          
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Wiki wiki = new Wiki();
                wiki.setId(rs.getInt("id_wiki"));
                
                System.out.println("Hola");
                listaWikisAcceso.add(wiki);
            }

        } catch (SQLException e) {
            System.out.println( e);
        }
        return listaWikisAcceso;
    }

    public Wiki obtenerWikiAcceso(int idWiki) {
        Wiki wiki = null;
        String sql = "SELECT * FROM wikis WHERE id = ?";
        try {

            ps = con.prepareStatement(sql);
            ps.setInt(1, idWiki);
            rs = ps.executeQuery();
            if (rs.next()) {
                wiki = new Wiki();
                wiki.setId(rs.getInt("id"));
                wiki.setNombre(rs.getString("nombre"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return wiki;
    }

    public void cambiarEstadoRespuesta(String respuesta, int cedula_usuario, int idWiki) {
        String sql = "UPDATE wikis_usuarios SET estado = '"+respuesta+"' WHERE cedula_Usuario="+ cedula_usuario + "AND id_wiki="+ idWiki;
        try {
   
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }
    
    public List consultarRespuesta(){
        String sql = "SELECT * FROM wikis_usuarios";
        ArrayList <Wiki> listaRespuesta = new ArrayList();
        try {

            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                wiki.setNombre(rs.getString("respuesta"));
                wiki.setId(rs.getInt("id_wiki"));
                listaRespuesta.add(wiki);
            }

        } catch (SQLException e) {
            System.out.println( e);
        }
        return listaRespuesta;
    }
}
