

<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>
<%@page import="java.util.Collections"%>
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
        <nav>
            <%
                Cookie[] cookies = request.getCookies();
                int cedula = 0;
                String nombre = "";
                int rol = 0;
                String nombreRol = "";
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
                System.out.println(rol + nombreRol+ "/////////" )  ;
            %>
            <div>
                <label name="accion" value="nombreYrol"><%= nombre + " - " + nombreRol%></label>
            </div>

            <div  class="alinear-centro">
                <a href="#" class="btn btn-primary"data-bs-toggle="modal" data-bs-target="#exampleModal">Nueva Wiki</a>
            </div>
            <div  class="alinear-derecha">
                <button><a href="../ControlIU?accion=cerrarsesion">Cerrar Sesion</a></button>

            </div>

        </nav>
        <div>

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

                        } else if (notificacion.getAsunto().equals("modificacion")) {
                            asunto = "modificacion";
                        }

                %>
                <div class="notificaciones">
                    <label class="notificacion-estado-<%=estado%>"><%=estado%></label><br>
                    <label class="color-asunto">Asunto - <%= notificacion.getAsunto()%></label>
                    <p class="text-light"><%= notificacion.getMensaje()%> </p> 

                    <%
                        if (estado.equals("Pendiente")) {
                    %>
                    <a href="../ControladorNotificaciones?accion=<%=asunto + "Aceptar"%>&id=<%= notificacion.getId()%>&cedula=<%=notificacion.getCedula_usuario()%>" class="btn btn-success">Aceptar</a>
                    <a href="../ControladorNotificaciones?accion=<%=asunto + "Rechazar"%>&id=<%= notificacion.getId()%>" class="btn btn-danger">Rechazar</a>
                     <a href="../ControladorDescargaA?accion=descargar&id=<%= notificacion.getId_modificacion() %>" class="btn btn-primary">Descargar</a>
                    <%}%> 
                </div>
                <%}%>
            </div>
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
                        <td><a href="../ControladorArticulos?valorEnviado=<%=  String.valueOf(wik.getId())%>&accion=vista&rolUsuario=<%=rol%>"><%= wik.getNombre()%></a></td>
                        <td>
                            <a class="btn btn-warning" href="../ControladorWikis?accion=editar&id=<%= wik.getId()%>">Editar</a>
                            <a class="btn btn-danger" href="../ControladorWikis?accion=eliminar&id=<%= wik.getId()%>">Eliminar</a>
                            <a class="btn btn-primary" href="../ControladorWikis?accion=accesoWiki&id=<%= wik.getId()%>&rol=3">Asignar Supervisor</a>
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