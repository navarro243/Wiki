<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>
<%@page import="Modelo.Notificacion"%>
<%@page import="ModeloDAO.NotificacionesDao"%>
<%@page import="ModeloDAO.UsuariosDao"%>
<%@page import="java.util.*"%>
<%@page import="Modelo.Articulo"%>
<%@page import="ModeloDAO.ArticulosDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<<<<<<< Updated upstream
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="css/estilosPropios.css">
    <title>Gestor - Gestion Articulos</title>
</head>
<body>
    <nav>
        <div>
            
            <label>Nombre Y Rol</label>
        </div>

         <div  class="alinear-centro">
                <a href="#" class="btn btn-primary"data-bs-toggle="modal" data-bs-target="#ModalArticulos">Nueva Wiki</a>
=======
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilosPropios.css">
        <link rel="stylesheet" href="Vistas/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="Vistas/css/estilosPropios.css">

        <title>Gestor - Gestion Articulos</title>
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
>>>>>>> Stashed changes

            <div>
                <label name="accion" value="nombreYrol"><%= nombre + rol%></label>
            </div>

            <div  class="alinear-centro">
                <a href="#" class="btn btn-primary"data-bs-toggle="modal" data-bs-target="#exampleModal">Nuevo Articulo</a>
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
<<<<<<< Updated upstream
               
          
                    int id_wiki_origen = ((Integer) request.getSession().getAttribute("variable")).intValue();

        ArticulosDao dao = new ArticulosDao();
        List<Articulo> lista = dao.obtenerArticulos(id_wiki_origen);
        Iterator<Articulo> iter = lista.iterator();
        Articulo art = null;  
        System.out.println("8=================================================================================================================================D"+lista);
        while (iter.hasNext()) {
            art = iter.next();
                          
=======
                        int valorEntero = (int) request.getAttribute("valorEntero");

                        ArticulosDao dao = new ArticulosDao();
                        List<Articulo> lista = dao.obtenerArticulos(valorEntero);
                        Iterator<Articulo> iter = lista.iterator();
                        Articulo art = null;

                        while (iter.hasNext()) {
                            art = iter.next();
>>>>>>> Stashed changes
                    %>
                    <tr>
                        <td><%= art.getId()%></td>
                        <td><a href="#"><%= art.getTitulo()%></a></td>
                        <td>
<<<<<<< Updated upstream
                                <a class="btn btn-warning" href="../ControladorArticulos?accion=editar&id=<%= art.getId()%>">Editar</a>

                            
                            
                           
                                <a class="btn btn-danger" href="../ControladorArticulos?accion=eliminar&id=<%= art.getId()%>">eliminar</a>
=======
                            <a class="btn btn-warning" href="ControladorArticulos?accion=editar&id=<%= art.getId()%>">Editar</a>
                            <a class="btn btn-danger" href="ControladorArticulos?accion=eliminar&id=<%= art.getId()%>">eliminar</a>
>>>>>>> Stashed changes
                        </td>
                    </tr>
                    <%}%>
                </tbody>

            </table>
        </section>

<<<<<<< Updated upstream

 <!-- Modal -->
<!-- Modal Crear--> 
<div class="modal fade" id="ModalArticulos" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h1 class="modal-title fs-5" id="exampleModalLabel">Crear Wiki</h1>
                <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
            </div>
            <div class="modal-body">
                <form action="../ControladorArticulos?accion=agregar" method="get">
                    <div class="mb-3">
                        <label for="titulo">Título</label>
                        <input type="text" class="form-control" id="titulo" name="titulo" required>
                    </div>
                    <div class="mb-3">
                        <label for="contenido">Contenido</label>
                        <textarea class="form-control" id="contenido" name="contenido" rows="5" required></textarea>
=======
        <!-- Modal Crear--> 
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h1 class="modal-title fs-5" id="exampleModalLabel">Crear Articulo</h1>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <form action="ControladorArticulos" method="get">
                            <label>Titulo articulo</label>
                            <input type="text" name="titulo"></input>

                            <input type="submit" name="accion" value="agregar"></input>

                        </form>
>>>>>>> Stashed changes
                    </div>
                    <input type="submit" class="btn btn-primary" name="accion" value="agregar">
                </form>
            </div>
        </div>
<<<<<<< Updated upstream
    </div>
</div>
<script src="js/bootstrap.min.js"></script>
</body>
=======

        <script src="js/bootstrap.min.js"></script>
    </body>
>>>>>>> Stashed changes
</html>