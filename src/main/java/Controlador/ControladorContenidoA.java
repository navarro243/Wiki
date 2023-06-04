package Controlador;

import Modelo.Articulo;
import Modelo.Modificacion;
import Modelo.Notificacion;
import java.io.PrintWriter;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import ModeloDAO.ArticulosDao;
import ModeloDAO.ModificacionesDao;
import ModeloDAO.NotificacionesDao;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
@WebServlet(name = "ControladorContenidoA", urlPatterns = {"/ControladorContenidoA"})
@MultipartConfig
public class ControladorContenidoA extends HttpServlet {

    String idTxt = "";
    int id = 0;
    ArticulosDao cargar = new ArticulosDao();
    NotificacionesDao notificacionDao = new NotificacionesDao();
    Notificacion notificacion = new Notificacion();
    ModificacionesDao modificacionDao = new ModificacionesDao();

    Articulo listar = new Articulo();

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
            out.println("<title>Servlet ControladorContenidoA</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ControladorContenidoA at " + request.getContextPath() + "</h1>");
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
        // Ruta del archivo que se va a descargar
        String filePath = "ruta_del_archivo_a_descargar"; // Actualiza con la ruta correcta

        // Obtiene el nombre del archivo a partir de la ruta
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator) + 1);

        // Configura las cabeceras de la respuesta
        response.setContentType("text/html");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");

        // Descarga el archivo
        File file = new File(filePath);
        try (InputStream inputStream = file.toURI().toURL().openStream(); OutputStream outputStream = response.getOutputStream()) {
            byte[] buffer = new byte[4096];
            int bytesRead;
            while ((bytesRead = inputStream.read(buffer)) != -1) {
                outputStream.write(buffer, 0, bytesRead);
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
        //obtener id del articulo
        idTxt = request.getParameter("id");
        id = Integer.parseInt(idTxt);

        listar = cargar.list(id);
        if (listar.getContenido() == null) {
            // Obtiene el archivo enviado en la solicitud
            Part filePart = request.getPart("file");

            // Obtiene el nombre del archivo
            String fileName = filePart.getSubmittedFileName();

            // Ruta de la carpeta de destino donde se guardará el archivo
            String uploadPath = request.getServletContext().getRealPath("Vistas");

            // Guarda el archivo en la carpeta de destino
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = uploadPath + File.separator + fileName;
            System.out.println("filePath: " + filePath);
            try (InputStream inputStream = filePart.getInputStream(); OutputStream outputStream = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            cargar.agregarRuta(filePath, id);

        } else {
            String descripcion = request.getParameter("descripcion");
            String cedula = request.getParameter("cedula");
            int cedulaint = Integer.parseInt(cedula);
            // Obtiene el archivo enviado en la solicitud
            Part filePart = request.getPart("file");

            // Obtiene el nombre del archivo
            String fileName = filePart.getSubmittedFileName();

            // Ruta de la carpeta de destino donde se guardará el archivo
            String uploadPath = request.getServletContext().getRealPath("vistashtml");

            // Guarda el archivo en la carpeta de destino
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }

            String filePath = uploadPath + File.separator + fileName;
            System.out.println("filePath: " + filePath);
            try (InputStream inputStream = filePart.getInputStream(); OutputStream outputStream = new FileOutputStream(filePath)) {
                byte[] buffer = new byte[4096];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, bytesRead);
                }
            }

            Articulo articulo = cargar.list(id);
            modificacionDao.agregarModificacion(filePath, descripcion, cedulaint, id);

            List<Modificacion> listaModificaciones = modificacionDao.consultarModificacion();
            String nombre = articulo.getTitulo();
            System.out.println(nombre);

            String asunto = "Modificacion Articulo";
            String mensaje = cedulaint + " Ha propuesto un cambio en el articulo de " + nombre + "Descripcion: " + descripcion;
            int idModificacion = 0;
            for (Modificacion modi : listaModificaciones) {
                idModificacion = modi.getId();
            }
            System.out.println("Esto es la modificacion de controladorA" + idModificacion);

            notificacion.setEstado(0);

            notificacion.setCedula_usuario(cedulaint);
            notificacion.setId_modificacion(idModificacion);
            notificacion.setId_Rol(3);
            notificacion.setAsunto(asunto);
            notificacion.setMensaje(mensaje);

            notificacionDao.notificacionModificacion(notificacion);
        }
        String referer = request.getHeader("referer");

        // Redirige al usuario a la página anterior
        response.sendRedirect(referer);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
