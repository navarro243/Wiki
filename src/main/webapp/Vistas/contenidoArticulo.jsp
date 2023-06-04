<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil

%>
--%>

<%@page import="java.io.PrintWriter"%>



<%@page import="Modelo.Notificacion"%>
<%@page import="ModeloDAO.NotificacionesDao"%>
<%@page import="ModeloDAO.UsuariosDao"%>

<%@page import="Modelo.Articulo"%>
<%@page import="ModeloDAO.ArticulosDao"%>
<%@page import="java.util.*"%>
<%@page import="Modelo.Wiki"%>
<%@page import="ModeloDAO.WikisDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">

        <link rel="stylesheet" href="Vistas/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="Vistas/css/estilosPropios.css">

        <title>Portal</title>
    </head>
    <body>
        <nav>
            <%
                Cookie[] cookies = request.getCookies();
                int cedula = 0;
                String nombre = "";
                int rol = 0;
                String nombreRol;
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
                        nombreRol = "Gestor";
                        break;
                    case 2:
                        nombreRol = "Coordinador";
                        break;
                    case 3:
                        nombreRol = "Supervisor";
                        break;
                    case 4:
                        nombreRol = "Colaborador";
                        break;
                    default:
                        nombreRol = "Sin cuenta";
                }
            %>
            <div>
                <label name="accion" value="nombreYrol"><%= nombre + " - " + nombreRol%></label>
            </div>

            <div class= "alinear-centro">
                <button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#uploadModal">Subir</button>
            </div>    
                

             <div  class="alinear-derecha">
                <button><a href="ControlIU?accion=cerrarsesion">Cerrar Sesion</a></button>

            </div>
        </nav>

        <div class="notificaciones-contenedor">
            <h4 class="text-center text-light">Notificaciones</h4>

            <%
                UsuariosDao usuarioDao = new UsuariosDao();
                NotificacionesDao notificacionDao = new NotificacionesDao();
                List<Notificacion> listaNotificaciones = notificacionDao.listarNotificaciones(rol, cedula);
                Iterator<Notificacion> iteradorNotificacion = listaNotificaciones.iterator();
                Collections.reverse(listaNotificaciones);
                Notificacion notificacion = null;

                String estado = "";
                String asunto = "";
                int rolnoti =0;
                while (iteradorNotificacion.hasNext()) {
                    notificacion = iteradorNotificacion.next();
                    nombre = usuarioDao.consultarNombre(notificacion.getCedula_usuario());
                    rolnoti = usuarioDao.consultarRol(notificacion.getCedula_usuario());

                    if (notificacion.getEstado() == 0) {
                        estado = "Pendiente";

                    } else if (notificacion.getEstado() == 1) {
                        estado = "Aceptado";

                    } else if (notificacion.getEstado() == 2) {
                        estado = "Rechazado";
                    }

                    if (notificacion.getAsunto().equals("Ascenso") || notificacion.getAsunto().equals("Nuevo Usuario")) {
                        asunto = "ascenso";

                    } else if (notificacion.getAsunto().equals("modificacion")) {
                        asunto = "modificacion";
                    }

            %>
            <div class="notificaciones">
                <label class="notificacion-estado-<%=estado%>"><%=estado%></label><br>
                <label class="color-asunto">Asunto - <%= notificacion.getAsunto()%></label>
                <p class="text-light"><%= notificacion.getMensaje()%> </p> 
            </div>
            <%}%>

        </div>>
        <section class="wiki-contenedor">
            <%
                String idArticuloStr = request.getAttribute("idArticulo").toString();
                int idArticulo = Integer.parseInt(idArticuloStr);
                ArticulosDao ArtiDao = new ArticulosDao();
                Articulo articu = ArtiDao.list(idArticulo);
                if (false) {
            %>
            <%String htmlContent = ArtiDao.readHtmlFile(articu.getContenido());

                // Env�a el contenido como respuesta al cliente
                response.setContentType("text/html");

                out.println(htmlContent);
            %>
        </section>
            <%}%>

        <!-- Modal para subir archivo -->
        <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="uploadModalLabel">Subir archivo HTML</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Aqu� puedes colocar el formulario o el c�digo necesario para subir el archivo -->
                        <form action="ControladorContenidoA?id=<%=articu.getId()%>&rol=<%=rol%>" method="post" enctype="multipart/form-data">
                            <input type="file" name="file">
                            <input type="submit" value="Subir">
                        </form>
                    </div>
                </div>

            </div>

        </div>

            <script src="js/bootstrap.min.js"></script>
            <script src="Vistas/js/bootstrap.min.js"></script>
    </body>
</html>