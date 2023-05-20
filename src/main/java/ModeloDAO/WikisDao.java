package ModeloDAO;

import java.sql.*;
import config.conexion;
import java.util.*;
import java.util.List;
import Modelo.Wiki;

public class WikisDao {
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con ;
    Wiki wiki = new Wiki();
    
    
        
    public List obtenerWikis() throws SQLException {
        ArrayList<Wiki> list= new ArrayList<>();
        String sql = "select * from wikis";
        try{
            con = cn.getConnection();
            ps= con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Wiki wiki = new Wiki();
                wiki.setId(rs.getInt("id"));
                wiki.setNombre(rs.getString("nombre"));
                list.add(wiki);
            }
            
        }catch(Exception e){
            
        }
        return list;
        
    }
    
    public boolean agregarWiki(Wiki wiki){
        String sql = "INSERT INTO wikis (nombre) VALUES ('"+ wiki.getNombre() + "')";
        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        }catch(SQLException e){
            e.printStackTrace();   
            
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

    
    public void editarWiki( Wiki wiki){
        String sql = "UPDATE wikis SET nombre = '" + wiki.getNombre() + "' WHERE id = " + wiki.getId();

        try{
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            
        }catch(SQLException e){
            e.printStackTrace();   
            
        }
    }

    public boolean eliminar(int id){
        String sql = "delete from wikis where id="+id;
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
            
        }catch(Exception e){
        
    }
        return false;

    }
}