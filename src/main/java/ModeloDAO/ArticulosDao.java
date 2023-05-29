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

    public List obtenerArticulos(int idart) throws SQLException {
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

        } catch (Exception e) {

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
            System.out.println("*************************************************************************************************");
            System.out.println(e);
            System.out.println(artu.getId_Wiki());

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
            System.out.println("*****************************************************************");
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

    public boolean agregarModificacion(String ruta, String descripcion, int cedula, int idArticulo) {
        String sql = "insert into modificaciones (descripcionCambio, contenidoNuevo, cedula_Usuario,id_Articulo) values ('"+descripcion+"','"+ruta+"','"+cedula+"','"+idArticulo+"') ";

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

}
