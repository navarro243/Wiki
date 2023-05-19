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