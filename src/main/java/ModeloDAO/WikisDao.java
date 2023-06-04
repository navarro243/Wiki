package ModeloDAO;

import Modelo.Usuario;
import java.sql.*;
import config.conexion;
import java.util.*;
import java.util.List;
import Modelo.Wiki;

public class WikisDao {

    private final conexion cn = conexion.getInstance();
    private Connection con;
    private PreparedStatement ps;
    private ResultSet rs;
    Wiki wiki = new Wiki();
    Usuario usuario = new Usuario();

    public List obtenerWikis() throws SQLException {
        ArrayList<Wiki> list = new ArrayList<>();
        String sql = "select * from wikis";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Wiki wiki = new Wiki();
                wiki.setId(rs.getInt("id"));
                wiki.setNombre(rs.getString("nombre"));
                list.add(wiki);
            }

        } catch (SQLException e) {
            System.out.println("Error al obtener las wikis: " + e.getMessage());
        }   
        return list;

    }

    public boolean agregarWiki(Wiki wiki) {
        String sql = "INSERT INTO wikis (nombre) VALUES ('" + wiki.getNombre() + "')";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error en Insertar Wikis"+ e);
        }
        return false;

    }

    public Wiki list(int id) {
        String sql = "SELECT * FROM wikis WHERE id=" + id;

        try {
            con = cn.getConnection();
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
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public boolean eliminar(int id) {
        String sql = "delete from wikis where id=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {
        }
        return false;

    }

    public void accesoWiki(int idWiki, int cedula_usuario) {
        String sql = "INSERT INTO wikis_usuarios (cedula_usuario, id_wiki, estado) VALUES ('" + cedula_usuario + "','" + idWiki + "','Pendiente')";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    public List listarWikisAcceso(int cedula) {
        ArrayList<Wiki> listaWikisAcceso = new ArrayList<>();
        String sql = "SELECT * FROM wikis_usuarios WHERE cedula_usuario = " + cedula + " AND (estado = 'asignado')";
        
        try {
            con = cn.getConnection();
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
            con = cn.getConnection();
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
        String sql = "UPDATE wikis_usuarios SET estado = '"+respuesta+"' WHERE cedula_Usuario="+ cedula_usuario + " AND (id_wiki="+ idWiki + ")";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error al cambiar estado de respuestas wikis");

        }
    }
    
    public List consultarRespuesta(){
        String sql = "SELECT * FROM wikis_usuarios";
        ArrayList <Wiki> listaRespuesta = new ArrayList();
        try {
            con = cn.getConnection();
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
