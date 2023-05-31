package Controlador;

import Modelo.Modificacion;
import Modelo.Usuario;
import Modelo.Notificacion;
import ModeloDAO.ArticulosDao;
import ModeloDAO.ModificacionesDao;
import ModeloDAO.NotificacionesDao;
import ModeloDAO.UsuariosDao;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControladorNotificaciones", urlPatterns = {"/ControladorNotificaciones"})
public class ControladorNotificaciones extends HttpServlet {

    String vistaGestor = "Vistas/gestor_GestionWikis.jsp";
    String vistaGeneral = "Vistas/vistaGeneral.jsp";
    String base = "Vistas/vistaGeneral.jsp";
    String coordinador = "Vistas/coordinador_VisualizarWikis.jsp";
    String supervisor = "Vistas/supervisor_wikis.jsp";
    String colaborador = "Vistas/colaborador.jsp";

    Usuario usuario = new Usuario();
    Notificacion notificacion = new Notificacion();
    NotificacionesDao notificacionDao = new NotificacionesDao();
    UsuariosDao usuarioDao = new UsuariosDao();
    ArticulosDao articuloDao = new ArticulosDao();
    ModificacionesDao modificacionDao = new ModificacionesDao();

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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        int cedula_usuario = 0;
        String acceso = "";
        String mensaje = "";
        String action = request.getParameter("accion");

        Cookie[] cookies = request.getCookies();
        int cedula = 0;
        String nombre = "";
        int rol = 0;
        String rolAscender = "";

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usuario")) {
                    String value = cookie.getValue();
                    String[] values = value.split(":");

                    cedula = Integer.parseInt(values[0]);
                    nombre = values[1];
                    rol = Integer.parseInt(values[2]);
                }
            }
        }

        switch (rol) {

            case 1:
                acceso = vistaGestor;
                break;
            case 2:
                acceso = coordinador;
                rolAscender = "Gestor";
                break;
            case 3:
                acceso = supervisor;
                rolAscender = "Coordinador";
                break;
            case 4:
                acceso = colaborador;
                rolAscender = "Supervisor";
                break;
            case 5:
                acceso = base;
                rolAscender = "Colaborador";
                break;
            default:
                acceso = base;
                break;
        }

        if (action.equalsIgnoreCase("ascenso")) {

            
            mensaje = cedula + " - " + nombre + " Quiere ser " + rolAscender;

            notificacion.setEstado(0);
            notificacion.setAsunto("Ascenso");
            notificacion.setMensaje(mensaje);
            notificacion.setId_modificacion(0);
            notificacion.setCedula_usuario(cedula);
            notificacion.setId_Rol(1);

            notificacionDao.enviarNotificacionAscenso(notificacion);
            
        } else if (action.equalsIgnoreCase("ascensoAceptar")) {
            String idNotificacionURL = request.getParameter("id");
            String cedulaURL = request.getParameter("cedula");

            int idNotificacion = Integer.parseInt(idNotificacionURL);
            cedula = Integer.parseInt(cedulaURL);

            usuarioDao.ascenderUsuario(cedula, idNotificacion);
        } else if (action.equalsIgnoreCase("ascensoRechazar")) {
            String idNotificacionURL = request.getParameter("id");
            int idNotificacion = Integer.parseInt(idNotificacionURL);

            notificacionDao.cambiarEstadoNotificacion(idNotificacion, 2);

        } else if (action.equalsIgnoreCase("modificacionAceptar")) {
            String idNotificacionURL = request.getParameter("id");
            String idModificacionURL = request.getParameter("modificacion");

            int idModificacion = Integer.parseInt(idModificacionURL);
            int idNotificacion = Integer.parseInt(idNotificacionURL);

            Modificacion modificacion = modificacionDao.consultarUnaModificacion(idModificacion);

            int idArticulo = modificacion.getId_Articulo();
            String contenidoNuevo = modificacion.getContenidoNuevo();

            articuloDao.actualizarArticulo(idArticulo, contenidoNuevo);
            notificacionDao.cambiarEstadoNotificacion(idNotificacion, 1);

        } else if (action.equalsIgnoreCase("modificacionRechazar")) {
            String idNotificacionURL = request.getParameter("id");
            int idNotificacion = Integer.parseInt(idNotificacionURL);

            notificacionDao.cambiarEstadoNotificacion(idNotificacion, 2);
            
        } else if (action.equalsIgnoreCase("ascensoRechazar")) {
            String idNotificacionURL = request.getParameter("id");
            int idNotificacion = Integer.parseInt(idNotificacionURL);

            notificacionDao.cambiarEstadoNotificacion(idNotificacion, 2);
            response.sendRedirect(acceso);
        }
        response.sendRedirect(acceso);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String acceso = "";
        int cedula_usuario = 0;
        String action = request.getParameter("accion");

        if (action.equalsIgnoreCase("solicitarRegistro")) {
            acceso = vistaGeneral;

            int cedula;
            String nombre;
            String apellido;

            String cedulaURL = request.getParameter("cedula");
            nombre = request.getParameter("nombre");
            apellido = request.getParameter("apellido");

            cedula = Integer.parseInt(cedulaURL);

            usuario.setCedula(cedula);
            usuario.setNombre(nombre);
            usuario.setApellido(apellido);
            usuario.setId_rol(5);

            String mensaje = cedula + " - " + nombre + apellido + " Quiere ser Colaborador";

            notificacion.setEstado(0);
            notificacion.setAsunto("Nuevo Usuario");
            notificacion.setMensaje(mensaje);
            notificacion.setId_modificacion(0);
            notificacion.setCedula_usuario(cedula);
            notificacion.setId_Rol(1);

            usuarioDao.registrarUsuario(usuario);
            notificacionDao.enviarNotificacionAscenso(notificacion);
        }
        response.sendRedirect(acceso);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
