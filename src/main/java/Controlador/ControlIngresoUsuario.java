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
import javax.servlet.http.Cookie;

@WebServlet(name = "ControlIU", urlPatterns = {"/ControlIU"})
public class ControlIngresoUsuario extends HttpServlet {

    String base = "Vistas/vistaGeneral.jsp";

    String gestor = "Vistas/gestor_GestionWikis.jsp";
    String coordinador = "Vistas/coordinador_VisualizarWikis.jsp";
    String supervisor = "Vistas/supervisor_wikis.jsp";
    String colaborador = "Vistas/colaborador.jsp";
    String vistaSinSesion = "Vistas/vistaGeneral.jsp";

    UsuariosDao dao = new UsuariosDao();

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

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String acceso = "";

        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("iniciosesion")) {
            String cedulaTexto = request.getParameter("cedula");

            int cedula = Integer.parseInt(cedulaTexto);
            int rol_vista = dao.Obtenerusuario(cedula);
            String nombre_usuario = dao.consultarNombre(cedula);

            String valorCookie = cedula + ":" + nombre_usuario + ":" + rol_vista;
            Cookie cookie = new Cookie("usuario", valorCookie);

            response.addCookie(cookie);

            switch (rol_vista) {
                case 0:
                    acceso = base;
                    break;
                case 1:
                    acceso = gestor;
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
        }
        if (action.equalsIgnoreCase("cerrarSesion")) {
            Cookie[] cookies = request.getCookies();

            if (cookies != null) {
                for (Cookie cookie : cookies) {
                    if (cookie.getName().equals("usuario")) {
                        // Destruir la cookie estableciendo el tiempo de vida en 0 segundos
                        cookie.setMaxAge(0);
                        response.addCookie(cookie);
                        
                        System.out.println("Se cerro la sesion exitosamente");
                    }
                }
            }
            acceso = vistaSinSesion;
        }

        response.sendRedirect(acceso);
    }

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
