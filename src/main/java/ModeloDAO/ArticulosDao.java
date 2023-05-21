/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;
import java.sql.*;
import config.conexion;
import java.util.*;

import Modelo.Articulo;
/**
 *
 * @author user
 */
public class ArticulosDao {
       conexion cn = new conexion();
        PreparedStatement ps;
        ResultSet rs;
        Connection con ;
        Articulo Arti = new Articulo();
        
        public List obtenerArticulos(int idart) throws SQLException {
        ArrayList<Articulo> list= new ArrayList<>();
        String sql = "select * from articulos where id_Wiki = "+idart;
        try{
            con = cn.getConnection();
            ps= con.prepareStatement(sql);
            rs=ps.executeQuery();
            while(rs.next()){
                Articulo artuci = new Articulo();
               
             
                    artuci.setId(rs.getInt("id"));
                    artuci.setTitulo(rs.getString("titulo"));
                    artuci.setContenido(rs.getString("contenido"));
                   artuci.setId_Wiki(rs.getInt("id_Wiki"));
               
                list.add(artuci );
            }
            
        }catch(Exception e){
            
        }
        return list;
        
    }
        
        
        public boolean agregarArticulo(Articulo artu){
            String sql = "INSERT INTO articulos (titulo , contenido, id_Wiki) VALUES (' "+ artu.getTitulo() + "', '" +artu.getContenido()+"', '"+artu.getId_Wiki()+"' )";
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
        String sql = "delete from articulos where id="+id;
        try{
            con=cn.getConnection();
            ps=con.prepareStatement(sql);
            ps.executeUpdate();
            
        }catch(Exception e){
        
    }
        return false;

    }
}
