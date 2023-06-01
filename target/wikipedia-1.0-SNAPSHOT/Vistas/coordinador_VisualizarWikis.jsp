<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>

<%@page import="ModeloDAO.ModificacionesDao"%>
<%@page import="Modelo.Articulo"%>
<%@page import="ModeloDAO.usuarios_articulosDao"%>
<%@page import="Modelo.Usuario_articulo"%>
<%@page import="ModeloDAO.ArticulosDao"%>
<%@page import="Modelo.Modificacion"%>
<%@page import="ModeloDAO.UsuariosDao"%>
<%@page import="java.util.*"%>
<%@page import="Modelo.Wiki"%>
<%@page import="ModeloDAO.WikisDao"%>
<%@page import="Modelo.Notificacion"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.NotificacionesDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilosPropios.css">
        <title>Wikis</title>
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
                switch (rol){
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
                <label name="accion" value="nombreYrol"><%= nombre +" - "+ nombreRol%></label>
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
                ArticulosDao articuloDao = new ArticulosDao();
                

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
                List<Usuario_articulo> listaWikis = usuariosArticulosDao.wikis_usuarios(cedula);

                List< Notificacion> notificacionesMostradas = new ArrayList<>();

                while (iteradorNotificacion.hasNext()) {
                    notificacion = iteradorNotificacion.next();
                    
                    if (notificacion.getAsunto().equals("Ascenso") || notificacion.getAsunto().equals("Nuevo Usuario")) {
                        asunto = "ascenso";
                    } else if (notificacion.getAsunto().equals("Modificacion Articulo")) {
                        asunto = "modificacion";
                        
                        for (Usuario_articulo articuloAcceso : listArticulos) {
                        
                            for (Modificacion recorrerModificacion : listaModificaciones) {
                                int idModificacion = recorrerModificacion.getId();
                                int idArticuloModificacion = recorrerModificacion.getId_Articulo();
                                int idArticuloPermiso = articuloAcceso.getId_Articulo();
                                int cedulaArticulos = articuloAcceso.getCedula_usuario();
                                String articuloEstado = articuloAcceso.getEstado();
                                Articulo idWiki = articuloDao.list(idArticuloPermiso);

                                if (cedula == cedulaArticulos && idArticuloPermiso == idArticuloModificacion && articuloEstado.equals("asignado")) {
                                    notificacionesMostradas.add(notificacion);
                                }
                            }
                        }
                    }
                } %>

            <% for (Notificacion notificacionMostrada : notificacionesMostradas) {
                    if (notificacion.getEstado() == 0) {
                        estado = "Pendiente";
                    } else if (notificacion.getEstado() == 1) {
                        estado = "Aceptado";
                    } else if (notificacion.getEstado() == 2) {
                        estado = "Rechazado";
                    }
            %>

            <div class="notificaciones">
                <label class="notificacion-estado-<%=estado%>"><%=estado%></label><br>
                <label class="color-asunto">Asunto - <%= notificacionMostrada.getAsunto()%></label>
                <p class="text-light"><%= notificacionMostrada.getMensaje()%></p>

                <% if (estado.equals("Pendiente")) {%>
                <a href="../ControladorNotificaciones?accion=<%=asunto + "Aceptar"%>&id=<%= notificacionMostrada.getId()%>&cedula=<%=notificacionMostrada.getCedula_usuario()%>&modificacion=<%= notificacionMostrada.getId_modificacion()%>" class="btn btn-success">Aceptar</a>
                <a href="../ControladorNotificaciones?accion=<%=asunto + "Rechazar"%>&id=<%= notificacionMostrada.getId()%>" class="btn btn-danger">Rechazar</a>
                <a href="../ControladorDescargaA?accion=descargar&id=<%= notificacionMostrada.getId_modificacion() %>" class="btn btn-primary">Descargar</a>
                <% }%>
            </div>
            <% }%>
        </div>

        <section class="wiki-contenedor">
            <table class="table border">
                <thead class="table-light">
                <td>Id Articulos</td>
                <td>Nombre Articulo</td>
              
                </thead>
                <tbody>
                    <%
                        WikisDao dao = new WikisDao();
                        List<Wiki> lista = dao.obtenerWikis();
                        Iterator<Wiki> iter = lista.iterator();
                        Wiki wik = null;
                        while (iter.hasNext()) {
                            wik = iter.next();
                    %>
                    <tr>
                        <td><%= wik.getId()%></td>
                        <td><a href="../ControladorArticulos?valorEnviado=<%=  String.valueOf(wik.getId())%>&accion=vista&rolUsuario=<%=rol%>"><%= wik.getNombre()%></a></td>
                        
                    </tr>
                    <%}%>
                </tbody>
        </section>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>