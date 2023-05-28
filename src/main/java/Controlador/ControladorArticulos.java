
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Controlador;

import Modelo.Articulo;
import ModeloDAO.ArticulosDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
@WebServlet(name = "ControladorArticulos", urlPatterns = {"/ControladorArticulos"})
@MultipartConfig

public class ControladorArticulos extends HttpServlet {

    String vistaG = "Vistas/gestor_gestionArticulos.jsp";
    String editarArticulo = "/Vistas/Modificacion_Articulos.jsp";
    String contenido = "Vistas/contenidoArticulo.jsp";
    String noDisponible = "Vistas/noDisponible.jsp";
    String VistaSC= "Vistas/ContenidoSincuenta.jsp";
    String registrar ="Vistas/inicioSesion.jsp";
    Articulo articu = new Articulo();
    ArticulosDao articuDao = new ArticulosDao();
    String valorRecibido = "";
    int valorEntero = 0;
    String nomRol;

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
            out.println("<title>Servlet ControladorArticulos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorArticulos at " + request.getContextPath() + "</h1>");
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
        String action = request.getParameter("accion");

        int id;

        if (action.equalsIgnoreCase("vista")) {
            valorRecibido = request.getParameter("valorEnviado");
            valorEntero = Integer.parseInt(valorRecibido);
            request.setAttribute("valorEntero", valorEntero);
             request.getRequestDispatcher(vistaG).forward(request, response);
            
        }else if(action.equalsIgnoreCase("vistaSC")) {
            valorRecibido = request.getParameter("valorEnviado");
            valorEntero = Integer.parseInt(valorRecibido);
            request.setAttribute("valorEntero", valorEntero);
             request.getRequestDispatcher(VistaSC).forward(request, response);
        }else if(action.equalsIgnoreCase("verificarUsu") ){
            if("Sin cuenta".equals(nomRol)){
                response.sendRedirect(registrar);
            }
        }
        else if (action.equalsIgnoreCase("agregar")) {

            String titulo = request.getParameter("titulo");

            articu.setTitulo(titulo);

            articu.setId_Wiki(valorEntero);

            articuDao.agregarArticulo(articu);

        } else if (action.equalsIgnoreCase("eliminar")) {
            id = Integer.parseInt(request.getParameter("id"));
            articu.setId(id);
            articuDao.eliminar(id);

        } else if (action.equalsIgnoreCase("editar")) {
            request.setAttribute("idArticulo", request.getParameter("id"));
            request.getRequestDispatcher(editarArticulo).forward(request, response);
        } else if (action.equalsIgnoreCase("actualizar")) {
            id = Integer.parseInt(request.getParameter("txtid"));

            String cambioTitulo = request.getParameter("cambioTitulo");

            articu.setId(id);
            articu.setTitulo(cambioTitulo);
            articuDao.editarArticulos(articu);

        } else if (action.equalsIgnoreCase("contenido")) {
            String ruta = "";
            String idArticulo = request.getParameter("id");
            nomRol = request.getParameter("nomrol");
            System.out.println("******************************************");
            System.out.println(nomRol);
            System.out.println(idArticulo);

            int valorArticulo = Integer.parseInt(idArticulo);
            Articulo articulo = articuDao.list(valorArticulo);
            System.out.println(articulo.getContenido());
            if ("Sin cuenta".equals(nomRol)) {
                if (articulo.getContenido() == null) {
                    ruta = noDisponible;
                    request.getRequestDispatcher(ruta).forward(request, response);
                } else {
                    ruta = articulo.getContenido();
                    String htmlContent = articuDao.readHtmlFile(ruta);

                    // Envía el contenido como respuesta al cliente
                    response.setContentType("text/html");
                    try (PrintWriter out = response.getWriter()) {
                        out.println(htmlContent);
                    }
                }
            } else {
                if (articulo.getContenido()== null) {
                    ruta = contenido;
                    request.setAttribute("idArticulo", idArticulo);
                    request.getRequestDispatcher(ruta).forward(request, response);
                } else {
                    ruta = articulo.getContenido();
                    String htmlContent = articuDao.readHtmlFile(ruta);

                    // Envía el contenido como respuesta al cliente
                    response.setContentType("text/html");
                    try (PrintWriter out = response.getWriter()) {
                        out.println(htmlContent);
                    }
                }
            }

        }

    
       
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
