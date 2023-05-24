<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>

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
            %>

            <div>
                <label name="accion" value="nombreYrol"><%= nombre + rol%></label>
            </div>

            <div  class="alinear-centro">
                <a href="#" class="btn btn-primary"data-bs-toggle="modal" data-bs-target="#exampleModal">Nueva Wiki</a>
            </div>

            <div  class="alinear-derecha">
                <button><a href="../Controlador?accion=cerrarsesion">Cerrar Sesion</a></button>
            </div>
        </nav>

                <nav>
            <%
                Cookie[] cookies = request.getCookies();
                int cedula = 0;
                String nombre = "";
                int rol = 0;
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


            %>
            <div>
                <label name="accion" value="nombreYrol"><%= nombre + " - Supervisor"%></label>

            </div>

            <div  class="alinear-derecha">
                <button><a href="../Controlador?accion=cerrarsesion">Cerrar Sesion</a></button>

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
               

                while (iteradorNotificacion.hasNext()) {
                    notificacion = iteradorNotificacion.next();
                    nombre = usuarioDao.consultarNombre(notificacion.getCedula_usuario());
                    rol = usuarioDao.consultarRol(notificacion.getCedula_usuario());
                    
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
                <p class="text-light"><%= notificacion.getMensaje() %> </p> 

                <%
                    if(estado.equals("Pendiente")){
                %>
                <a href="../ControladorNotificaciones?accion=<%=asunto + "Aceptar"%>&id=<%= notificacion.getId()%>&cedula=<%=notificacion.getCedula_usuario()%>" class="btn btn-success">Aceptar</a>
                <a href="../ControladorNotificaciones?accion=<%=asunto + "Rechazar"%>&id=<%= notificacion.getId()%>" class="btn btn-danger">Rechazar</a>
                <%}%> 
            </div>
            
            <%}%>
            <a href="../ControladorNotificaciones?accion=ascenso" class="pedirAscenso">Pedir Ascenso</a>
        </div>

        <section class="wiki-contenedor">
            <table class="table border">
                <thead class="table-light">
                <td>Id Articulos</td>
                <td>Nombre Articulo</td>
                <td>Acciones</td>
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
                        <td><a href="gestor_gestionArticulos.jsp"><%= wik.getNombre()%></a></td>
                        <td>
                            <a class="btn btn-warning" href="../ControladorWikis?accion=editar&id=<%= wik.getId()%>">Editar</a>
                        </td>
                    </tr>
                    <%}%>
                </tbody>
        </section>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>