 /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import java.sql.*;
import config.conexion;

import Modelo.Usuario;

import java.util.ArrayList;
import java.util.List;
public class UsuariosDao {
    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con ;

    Usuario usua = new Usuario();

    public int Obtenerusuario(int CEDULA) {
String sql = "SELECT * FROM usuarios WHERE cedula = ?";
try {
    con = cn.getConnection();
    ps = con.prepareStatement(sql);
    ps.setInt(1, CEDULA); // Establecer el valor de la cédula en la consulta
    rs = ps.executeQuery();
    if (rs.next()) {
        usua.setCedula(rs.getInt("cedula"));
        usua.setNombre(rs.getString("nombre"));
        usua.setApellido(rs.getString("apellido"));
        usua.setId_rol(rs.getInt("id_Rol"));
        return usua.getId_rol();
    }
} catch (Exception e) {
    // Manejar la excepción apropiadamente
}
return 0;
}
    public Usuario MostrarUsuario(int cedula) {
            
            String sql = "SELECT * FROM usuarios  ";
             Usuario u = new Usuario();
            try {
               
                con = cn.getConnection();
                ps = con.prepareStatement(sql);

                rs = ps.executeQuery();
                while(rs.next()){
                    if (cedula == rs.getInt("cedula")) {
                        u.setCedula(rs.getInt("cedula"));
                        u.setNombre(rs.getString("nombre"));
                        u.setApellido(rs.getString("apellido"));
                        u.setId_rol(rs.getInt("id_Rol"));
                }
                }
                
            } catch (Exception e) {
                // Manejar la excepción apropiadamente
            }
            return u;
}
    
    public int consultarRol(int cedula){
        int id = 0;
        String consultarRol = "SELECT TOP 1 * FROM usuarios WHERE cedula=" + cedula;
        try{
        con = cn.getConnection();
        ps = con.prepareStatement(consultarRol);
        rs = ps.executeQuery();
        id = rs.getInt("id");
        }catch (SQLException e){
            System.out.println(e);
        }
        
        return id;
    }
    
    public void ascenderUsuario(int cedula) {
        int consultarRol = consultarRol(cedula);
        
        
        String sql = "UPDATE usuarios SET id_Rol = "+ consultarRol;
        
    }
    
    
}
