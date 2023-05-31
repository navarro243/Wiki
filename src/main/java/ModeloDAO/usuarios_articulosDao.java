
package ModeloDAO;

import Modelo.Usuario_articulo;
import config.conexion;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class usuarios_articulosDao {
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    
    public List consultarUsuario(int cedula){
        String sql = "SELECT * FROM usuarios_articulos WHERE cedula_Usuario="+cedula;
        ArrayList <Usuario_articulo> accesosArticulos = new ArrayList <Usuario_articulo>();
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario_articulo usuarioArticulo = new Usuario_articulo();
                usuarioArticulo.setCedula_usuario(rs.getInt("cedula_usuario"));
                usuarioArticulo.setId_Articulo(rs.getInt("id_Articulo"));
                usuarioArticulo.setEstado(rs.getString("estado"));
                
                accesosArticulos.add(usuarioArticulo) ;
            }
        }catch(SQLException e){
            System.out.println("Error en el consultar permisos de articulos" + e);
        }
        return accesosArticulos;
    }
    
    public List wikis_usuarios(int cedula){
        String sql = "SELECT * FROM wikis_usuarios WHERE cedula_Usuario="+cedula;
        ArrayList <Usuario_articulo> accesosWikis = new ArrayList <Usuario_articulo>();
        
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Usuario_articulo usuarioArticulo = new Usuario_articulo();
                usuarioArticulo.setCedula_usuario(rs.getInt("cedula_usuario"));
                usuarioArticulo.setId_Articulo(rs.getInt("id_wiki"));
                usuarioArticulo.setEstado(rs.getString("estado"));
                
                accesosWikis.add(usuarioArticulo) ;
            }
        }catch(SQLException e){
            System.out.println("Error en el consultar permisos de wikis" + e);
        }
        return accesosWikis;
    }
    
    public Usuario_articulo consultarWiki(int idWiki){
        String sql = "SELECT * FROM wikis_usuarios WHERE id_wiki="+idWiki;
        Usuario_articulo wiki = new Usuario_articulo();
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                wiki.setCedula_usuario(rs.getInt("cedula_usuario"));
                wiki.setId_Articulo(rs.getInt("id_wiki"));
                wiki.setEstado(rs.getString("estado"));
                
                
            }
        }catch(SQLException e){
            System.out.println("Error en el consultar permisos de wikis" + e);
        }
        return wiki;
    }
    
    
}
