/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ModeloDAO;

import java.sql.*;
import config.conexion;
import java.util.*;

import Modelo.Articulo;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/**
 *
 * @author user
 */
public class ArticulosDao {

    conexion cn = new conexion();
    PreparedStatement ps;
    ResultSet rs;
    Connection con;
    Articulo Arti = new Articulo();

    public List obtenerArticulos(int idart) {
        ArrayList<Articulo> list = new ArrayList<>();
        String sql = "select * from articulos where id_Wiki = " + idart;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Articulo artuci = new Articulo();

                if (idart == rs.getInt("id_Wiki")) {
                    artuci.setId(rs.getInt("id"));
                    artuci.setTitulo(rs.getString("titulo"));
                    artuci.setContenido(rs.getString("contenido"));
                    artuci.setId_Wiki(rs.getInt("id_Wiki"));

                    list.add(artuci);
                }
            }

        } catch (SQLException e) {
            System.out.println("DAWIDWIAWJDIJ" + e);
        }
        return list;

    }

    public boolean agregarArticulo(Articulo artu) {
        String sql = "INSERT INTO articulos (titulo, id_wiki) values (' " + artu.getTitulo() + " ',' " + artu.getId_Wiki() + " ' )";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();
            return true;
        } catch (SQLException e) {
            System.out.println("Error al agregar articulo" + e);
        }

        return false;
    }

    public boolean eliminar(int id) {
        String sql = "delete from articulos where id=" + id;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (Exception e) {

        }
        return false;

    }

    public Articulo list(int id) {
        String sql = "SELECT * FROM articulos WHERE id=" + id;

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Arti.setId(rs.getInt("id"));
                Arti.setTitulo(rs.getString("titulo"));
                Arti.setContenido(rs.getString("contenido"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return Arti;
    }

    public void editarArticulos(Articulo articulo) {
        String sql = "UPDATE articulos SET titulo = '" + articulo.getTitulo() + "' WHERE id = " + articulo.getId();

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println("*****************************************************************");
            System.out.println(e);

        }
    }

    public boolean agregarRuta(String ruta, int id) {
        String sql = "UPDATE articulos SET contenido = ? WHERE id = ?";

        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, ruta);
            ps.setInt(2, id);
            ps.executeUpdate();

            return true;
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return false;
        }
    }

    public String readHtmlFile(String filePath) throws IOException {
        StringBuilder content = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                content.append(line);
            }
        }
        return content.toString();
    }

    public void cambiarEstadoRespuestaArticulo(String respuesta, int cedula, int idArticulo) {
        String sql = "UPDATE usuarios_articulos SET estado = '" + respuesta + "' WHERE cedula_Usuario=" + cedula + "AND id_Articulo=" + idArticulo;
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();

        }
    }

    public void accesoArticulo(int idArticulo, int cedula_usuario) {
        String sql = "INSERT INTO usuarios_articulos (cedula_usuario, id_Articulo, estado) VALUES ('" + cedula_usuario + "','" + idArticulo + "','Pendiente')";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println("iNGRWSO SONC EXITO");
    }

    public List listarArticulosAcceso(int cedula) {
        ArrayList<Articulo> listaArticulosAcceso = new ArrayList<>();
        String sql = "SELECT * FROM usuarios_articulos WHERE cedula_usuario = " + cedula + "AND estado = 'asignado'";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Articulo articulo = new Articulo();
                articulo.setId(rs.getInt("id_Articulo"));
                articulo.setTitulo("estado");

                listaArticulosAcceso.add(articulo);
            }

        } catch (SQLException e) {
            System.out.println("" + e);
        }
        return listaArticulosAcceso;
    }

    public void actualizarArticulo(int idArticulo, String contenido) {

        StringBuilder stringBuilder = new StringBuilder(contenido);

        for (int i = 0; i < stringBuilder.length(); i++) {
            char letra = stringBuilder.charAt(i);
            if (letra == '\\') {  // Condición para cambiar el carácter
                stringBuilder.setCharAt(i, '/');  // Cambiar el carácter a 'x'
            }
        }

        String nuevoContenido = stringBuilder.toString();
        System.out.println("Nuevo contenido" + nuevoContenido);
        String sql = "UPDATE articulos SET contenido = ? WHERE id = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nuevoContenido);  // Parámetro para la nueva ruta del contenido
            ps.setInt(2, idArticulo);  // Parámetro para el ID del artículo
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("---Error en actualizar Articulo---");
            System.out.println(e);
        }

    }

}
