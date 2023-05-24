/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import ModeloDAO.UsuariosDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author vamil
 */
@WebServlet(name = "ControladorUsuarios", urlPatterns = {"/ControladorUsuarios"})
public class ControladorUsuarios extends HttpServlet {
    UsuariosDao usuarioDao = new UsuariosDao();
    Usuario usuario =new Usuario();
     
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorUsuarios</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorUsuarios at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
        String action = request.getParameter("accion");
        
        if(action.equalsIgnoreCase("Registrar")){
            int cedula;
            String nombre ;
            String apellido;
            
            String cedulaURL = request.getParameter("cedula");
            nombre = request.getParameter("nombre");
            apellido = request.getParameter("apellido");
            
            cedula = Integer.parseInt(cedulaURL);
            
            usuario.setCedula(cedula);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setId_rol(4);
            
            
        }
                
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
