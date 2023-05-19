/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;




import java.sql.*;
import config.conexion;
import java.util.*;
import java.util.List;
import Modelo.Wiki;

/**
 *
 * @author user
 */
public class WikisDao {
        conexion cn = new conexion();
        PreparedStatement ps;
        ResultSet rs;
        Connection con ;
        Wiki w = new Wiki();
    public List obtenerWikis() throws SQLException {
        ArrayList<Wiki> list= new ArrayList<>();
        String sql = "select * from wikis";
        try{
            con = cn.getConnection();
            ps= con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Wiki wi = new Wiki();
                wi.setId(rs.getInt("id"));
                wi.setNombre(rs.getString("nombre"));
                list.add(wi);
            }
            
        }catch(Exception e){
            
        }

        
        
        
        
        
        
        return list;
        
    }
}
