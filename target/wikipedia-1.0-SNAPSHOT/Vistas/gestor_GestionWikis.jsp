
<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>

<%@page import="ModeloDAO.UsuariosDao"%>
<%@page import="Modelo.Wiki"%>
<%@page import="ModeloDAO.WikisDao"%>
<%@page import="Modelo.Notificacion"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.NotificacionesDao"%>

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
        <nav><%
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
        </nav>
        <div>

            <label name="accion" value="nombreYrol"><%= nombre + " - Gestor"%></label>
            <label name="accion" value="nombreYrol"><%= nombre + " - Gestor"%></label>
        </div>

        <div  class="alinear-centro">

            <div class="notificaciones-contenedor">
                <h4 class="text-center text-light">Notificaciones</h4>
                <%
                    UsuariosDao usuarioDao = new UsuariosDao();
                    NotificacionesDao notificacionDao = new NotificacionesDao();
                    List<Notificacion> listaNotificaciones = notificacionDao.listarNotificaciones(rol);

                    Iterator<Notificacion> iteradorNotificacion = listaNotificaciones.iterator();

                    Notificacion notificacion = null;
                    String estado = "";
                    String asunto = "";
                    String mensajeAscenso = "";
                    String rolAscender = "";

                    while (iteradorNotificacion.hasNext()) {
                        notificacion = iteradorNotificacion.next();

                        if (notificacion.getAsunto().equals("Acenso")) {
                            nombre = usuarioDao.consultarNombre(notificacion.getCedula_usuario());
                            rol = usuarioDao.consultarRol(notificacion.getCedula_usuario());
                            switch (rol) {
                                case 2:
                                    rolAscender = "Coordinador";
                                    break;
                                case 3:
                                    rolAscender = "Supervisor";
                                    break;
                                case 4:
                                    rolAscender = "Colaborador";
                                    break;
                            }
                            mensajeAscenso = "Quiere ser  promovido de " + rolAscender;
                            if (notificacion.getAsunto().equals("Acenso")) {
                                asunto = "ascenso";
                            } else if (notificacion.getAsunto().equals("modificacion")) {

                            } else if (notificacion.getAsunto().equals("modificacion")) {
                                asunto = "modificacion";
                            }

                            if (notificacion.getEstado() == 0) {
                                estado = "Pendiente";
                            } else if (notificacion.getEstado() == 1) {
                                estado = "Aceptado";
                                asunto = "resuelto";
                            } else if (notificacion.getEstado() == 2) {
                                estado = "Rechazado";
                                asunto = "resuelto";
                            }


                %>
                <div class="notificaciones">
                    <label class="notificacion-estado"><%=estado%></label><br>
                    <label class="color-asunto">Asunto - <%= notificacion.getAsunto()%></label>
                    <p class="text-light"><%= notificacion.getCedula_usuario() + " - " + notificacion.getAsunto()%></p>

                    <a href="../ControladorNotificaciones?accion=<%=asunto + "Aceptar"%>&id=<%= notificacion.getId()%>&cedula=<%=notificacion.getCedula_usuario()%>" class="btn btn-success">Aceptar</a>
                    <label class="color-asunto">Asunto - <%= notificacion.getAsunto()%></label>
                    <p class="text-light"><%= notificacion.getCedula_usuario() + " - " + nombre + " " + mensajeAscenso%> </p>

                    <a href="../ControladorNotificaciones?accion=<%=asunto + "Rechazar"%>&id=<%= notificacion.getId()%>" class="btn btn-danger">Rechazar</a>
                    <a href="../ControladorNotificaciones?accion=<%=asunto + "Aceptar"%>&id=<%= notificacion.getId()%>&cedula=<%=notificacion.getCedula_usuario()%>" class="btn btn-success">Aceptar</a>
                </div>
                <%}%>

            </div>

            <section class="wiki-contenedor">
                <table class="table border">
                    <thead class="table-light">


                    <td>Id Wiki</td>
                    <td>Nombre Wiki</td>
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
                                <a class="btn btn-danger" href="../ControladorWikis?accion=eliminar&id=<%= wik.getId()%>">eliminar</a>
                            </td>
                        </tr>
                        <%}%>
                    </tbody>

                </table>
            </section>
            <!-- Modal Crear-->

            <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h1 class="modal-title fs-5" id="exampleModalLabel">Crear Wiki</h1>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div class="modal-body">
                            <form action="../ControladorWikis?accion=agregar" method="get">
                                <label>Nombre Wiki</label>
                                <input type="text" name="nombre"></input>

                                <input type="submit" name="accion" value="agregar"></input>

                            </form>
                        </div>

                    </div>
                </div>
            </div>
            <script src="js/bootstrap.min.js"></script>
    </body>
</html>