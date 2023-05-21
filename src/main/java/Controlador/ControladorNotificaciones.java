/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Notificacion;
import ModeloDAO.NotificacionesDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControladorNotificaciones", urlPatterns = {"/ControladorNotificaciones"})
public class ControladorNotificaciones extends HttpServlet {
    
    String vistaSupervisor = "Vistas/supervisor_wikis.jsp";
    
    Notificacion notificacion = new Notificacion();
    NotificacionesDao notificacionDao = new NotificacionesDao();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ControladorNotificaciones</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorNotificaciones at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String acceso = "";
        String action = request.getParameter("accion");
            
        if(action.equalsIgnoreCase("ascenso")){
            acceso = vistaSupervisor;
            
            notificacion.setEstado(0);
            notificacion.setAsunto("Propuesta para Supervisor");
            notificacion.setId_modificacion(0);
            notificacion.setCedula_usuario(123);
            notificacion.setId_Rol(1);
            
            notificacionDao.enviarNotificacionAscenso(notificacion);
        }
        
        response.sendRedirect(acceso);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    
    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
