/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ModeloDAO.UsuariosDao;
import javax.servlet.RequestDispatcher;

/**
 *
 * @author user
 */
@WebServlet(name = "ControlIU", urlPatterns = {"/ControlIU"})
public class ControlIngresoUsuario extends HttpServlet {
    String base = "Vistas/vistaGeneral.jsp";

    String gestor = "Vistas/gestor_GestionWikis.jsp";
    String coordinador = "Vistas/coordinador_VisualizarWikis.jsp";
    String supervisor = "Vistas/supervisor_Wikis.jsp";
    String colaborador = "Vistas/colaborador.jsp";

    UsuariosDao dao = new UsuariosDao();



    
    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControlIngresoUsuario</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControlIngresoUsuario at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         String acceso = "";
        String action = request.getParameter("accion");
        String cedulaTexto = request.getParameter("cedula");
        int cedula = Integer.parseInt(cedulaTexto);
        int rol_vista = dao.Obtenerusuario(cedula);
        switch(rol_vista){
            case 0:
                acceso=base;
                break;
            case 1:
                acceso=gestor;
                break;
            case 2:
                acceso = coordinador;
                break;
            case 3:
                acceso = supervisor;
                break;
            case 4:
                acceso = colaborador;
                break;
            default:
                acceso = base;
                break;
            }
        Usuario nombreApellidoRol = dao.MostrarUsuario(cedula);
        String mensaje = nombreApellidoRol.getNombre() + " "+ nombreApellidoRol.getApellido()+ " "+ nombreApellidoRol.getId_rol();
    
        response.sendRedirect(acceso);
       
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
