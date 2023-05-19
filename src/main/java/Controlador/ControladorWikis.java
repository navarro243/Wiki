package Controlador;

import Modelo.Wiki;
import ModeloDAO.WikisDao;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorWikis", urlPatterns = {"/ControladorWikis"})
public class ControladorWikis extends HttpServlet {
    String crearWiki = "Vistas/gestor_GestionWikis.jsp";
    
    Wiki wiki = new Wiki();
    WikisDao wikiDao = new WikisDao();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("accion");
       
        
        if(action.equalsIgnoreCase("agregar")){
            String nombre = request.getParameter("nombre");
            
            wiki.setNombre(nombre);
            wikiDao.agregarWiki(wiki);

            response.sendRedirect(crearWiki);    
            
        }else if(action.equalsIgnoreCase("editar")){
            
            String idPasado = request.getParameter("id");
            int id = Integer.parseInt(idPasado);
                
            String nombreCambio = request.getParameter("cambioNombre");
                
            wiki.setId(id);
            wiki.setNombre(nombreCambio);
            wikiDao.editarWiki(wiki);
            
            response.sendRedirect(crearWiki);
        }
        
        if(action.equalsIgnoreCase("inicioSesion")){
            
        }
    }
    @Override
    
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {
    
}


    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
