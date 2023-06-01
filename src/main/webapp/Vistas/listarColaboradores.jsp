<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>

<%@page import="ModeloDAO.ModificacionesDao"%>
<%@page import="ModeloDAO.usuarios_articulosDao"%>
<%@page import="Modelo.Usuario_articulo"%>
<%@page import="Modelo.Modificacion"%>
<%@page import="ModeloDAO.UsuariosDao"%>
<%@page import="Modelo.Wiki"%>
<%@page import="ModeloDAO.WikisDao"%>
<%@page import="Modelo.Notificacion"%>
<%@page import="ModeloDAO.NotificacionesDao"%>
<%@page import="java.util.*"%>
<%@page import="Modelo.Usuario"%>
<%@page import="java.util.Collections"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="Vistas/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilosPropios.css">
        <link rel="stylesheet" type="text/css" href="Vistas/css/estilosPropios.css">
        <title>Gestor - Gestion Wikis</title>
    </head>
    <body>
        <nav>
            <%
                String rolDirigido = request.getParameter("rol");

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

            <div  class="alinear-centro">
                <a href="supervisor_wikis.jsp" class="btn btn-primary">Volver</a>
            </div>
            <div  class="alinear-derecha">
                <button><a href="../ControlIU?accion=cerrarsesion">Cerrar Sesion</a></button>

            </div>

        </nav>

        <div class="notificaciones-contenedor">
            <a href="../ControladorNotificaciones?accion=ascenso" class="pedirAscenso">Pedir Ascenso</a>
            <h4 class="text-center text-light">Notificaciones</h4>
            <hr>
            <%
                UsuariosDao usuarioDao = new UsuariosDao();
                NotificacionesDao notificacionDao = new NotificacionesDao();
                Modificacion modificacion = new Modificacion();
                List<Notificacion> listaNotificaciones = notificacionDao.listarNotificaciones(rol, cedula);
                Iterator<Notificacion> iteradorNotificacion = listaNotificaciones.iterator();
                Collections.reverse(listaNotificaciones);

                Notificacion notificacion = null;

                String estado = "";
                String asunto = "";

                Usuario_articulo usuarioArticulo = new Usuario_articulo();
                usuarios_articulosDao usuariosArticulosDao = new usuarios_articulosDao();
                ModificacionesDao modificacionDao = new ModificacionesDao();

                List<Usuario_articulo> listArticulos = usuariosArticulosDao.consultarUsuario(cedula);
                List<Modificacion> listaModificaciones = modificacionDao.consultarModificacion();

                List< Notificacion> notificacionesMostradas = new ArrayList<>();
                int rolNotificacion;
                while (iteradorNotificacion.hasNext()) {
                    notificacion = iteradorNotificacion.next();
                    nombre = usuarioDao.consultarNombre(notificacion.getCedula_usuario());
                    rolNotificacion = usuarioDao.consultarRol(notificacion.getCedula_usuario());

                    if (notificacion.getEstado() == 0) {
                        estado = "Pendiente";
                    } else if (notificacion.getEstado() == 1) {
                        estado = "Aceptado";
                    } else if (notificacion.getEstado() == 2) {
                        estado = "Rechazado";
                    }

                    if (notificacion.getAsunto().equals("Ascenso") || notificacion.getAsunto().equals("Nuevo Usuario")) {
                        asunto = "ascenso";
                        notificacionesMostradas.add(notificacion);
                        
                    } else if (notificacion.getAsunto().equals("Modificacion Articulo")) {
                        asunto = "modificacion";

                        for (Usuario_articulo articuloAcceso : listArticulos) {
                            boolean notificacionAgregada = false;  

                            for (Modificacion recorrerModificacion : listaModificaciones) {
                                int idModificacion = recorrerModificacion.getId();
                                int idArticuloModificacion = recorrerModificacion.getId_Articulo();
                                int idArticuloPermiso = articuloAcceso.getId_Articulo();
                                int cedulaArticulos = articuloAcceso.getCedula_usuario();

                                if (cedula == cedulaArticulos && idArticuloPermiso == idArticuloModificacion && !notificacionAgregada) {
                                    notificacionesMostradas.add(notificacion);
                                    notificacionAgregada = true;  
                                }
                            }
                        }

                    }
                } %>

            <% for (Notificacion notificacionMostrada : notificacionesMostradas) {
                    estado = (notificacionMostrada.getEstado() == 0) ? "Pendiente" : (notificacionMostrada.getEstado() == 1) ? "Aceptado" : "Rechazado";
                    asunto = (notificacionMostrada.getAsunto().equals("Ascenso") || notificacionMostrada.getAsunto().equals("Nuevo Usuario")) ? "ascenso" : "modificacion";
            %>

            <div class="notificaciones">
                <label class="notificacion-estado-<%=estado%>"><%=estado%></label><br>
                <label class="color-asunto">Asunto - <%= notificacionMostrada.getAsunto()%></label>
                <p class="text-light"><%= notificacionMostrada.getMensaje()%></p>

                <% if (estado.equals("Pendiente")) {%>
                <a href="../ControladorNotificaciones?accion=<%=asunto + "Aceptar"%>&id=<%= notificacionMostrada.getId()%>&cedula=<%=notificacionMostrada.getCedula_usuario()%>&modificacion=<%= notificacionMostrada.getId_modificacion()%>" class="btn btn-success">Aceptar</a>
                <a href="../ControladorNotificaciones?accion=<%=asunto + "Rechazar"%>&id=<%= notificacionMostrada.getId()%>" class="btn btn-danger">Rechazar</a>
                <a href="../ControladorNotificaciones?accion=<%=asunto + "Descargar"%>&id=<%= notificacionMostrada.getId()%>" class="btn btn-primary">Descargar</a>
                <% }%>
            </div>
            <% }%>
        </div>

        <section class="wiki-contenedor">
            <table class="table border">
                <thead class="table-light">
                <td>Cedula</td>
                <td>Nombre</td>
                <td>Rol</td>
                <td>Acciones</td>
                </thead>    
                <tbody>
                    <%
                      
                        String rolListado;
                        String idArticulo = request.getParameter("idArticulo");

                        UsuariosDao dao = new UsuariosDao();

                        List<Usuario> lista = dao.listarSupervisores(4);

                        Iterator<Usuario> iter = lista.iterator();

                        Usuario usuario = null;
                        while (iter.hasNext()) {

                            usuario = iter.next();
                            if (usuario.getId_rol() == 3) {
                                rolListado = "Supervisor";
                            } else {
                                rolListado = "Colaborador";
                                
                            }
                    %>
                    <tr>
                        <td><%= usuario.getCedula()%></td>
                        <td><%= usuario.getNombre()%></td>
                        <td><%= rolListado%></td>
                        <td>
                            <a class="btn btn-danger" href="../ControladorArticulos?accion=removerColaborador&cedula=<%= usuario.getCedula()%>&idArticulo=<%=idArticulo%>&rolRedireccion=<%=rol%>">Remover</a>
                        </td>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
            </table>
        </section>

        <script src="js/bootstrap.min.js"></script>
    </body>
</html>