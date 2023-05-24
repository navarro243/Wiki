<%-- 
    Document   : colaborador
    Created on : 12 may. 2023, 18:28:08
    Author     : vamil
--%>
<<<<<<< Updated upstream

=======
<%@page import="ModeloDAO.UsuariosDao"%>
<%@page import="java.util.*"%>
<%@page import="Modelo.Articulo"%>
<%@page import="ModeloDAO.ArticulosDao"%>

<%@page import="Modelo.Notificacion"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="ModeloDAO.NotificacionesDao"%>
>>>>>>> Stashed changes
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
    <title>Articulos</title>
</head>
<body>
    <nav>
        <div>
=======
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilosPropios.css">
        <link rel="stylesheet" href="Vistas/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="Vistas/css/estilosPropios.css">

        <title>Colaborador - Articulos</title>
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
            </div>
>>>>>>> Stashed changes
            
            <%}%>
            <a href="../ControladorNotificaciones?accion=ascenso" class="pedirAscenso">Pedir Ascenso</a>
        </div>
<<<<<<< Updated upstream

        <div class= "alinear-centro">
            <img  src="" alt="">
        </div>
        
        <div  class="alinear-derecha">
            <button>Iniciar Sesion</button>
            
        </div>
        
    </nav>
    
    <div class="notificaciones-contenedor">
        <h4 class="text-center text-light">Notificaciones</h4>
        <div class="notificaciones">
            <label class="notificacion-estado">Resuelto</label>
            <br>
            <label class="color-asunto">Asunto - Propuesta para supervisor</label>
            <p class="text-light">123456783 - Juanes Gonzales quiere ser supervisor del articulo Tecnologia</p>

            <img src="" alt="">
            <img src="" alt="">

        </div>
        <button class="pedirAscenso">Pedir Ascenso</button>
    </div>
    
    <section class="wiki-contenedor">
        <article class="contenido-Articulo">
            <h1>Hola Articulo</h1>
            
        </article>
    </section>
<script src="js/bootstrap.min.js"></script>
</body>
=======
        <section class="wiki-contenedor">
            <table class="table border">
                <thead class="table-light">
                <td>Id Articulos</td>
                <td>Nombre Articulo</td>
                <td>Acciones</td>
                </thead>
                <tbody>
                    <%--

                        int valorEntero = (int) request.getAttribute("valorEntero");

                        ArticulosDao dao = new ArticulosDao();
                        List<Articulo> lista = dao.obtenerArticulos(valorEntero);
                        Iterator<Articulo> iter = lista.iterator();
                        Articulo art = null;

                        while (iter.hasNext()) {
                            art = iter.next();


                    %>
                    <tr>
                        <td><%= art.getId()%></td>
                        <td><a href="#"><%= art.getTitulo()%></a></td>

                    </tr>
                    <%}--%>
                </tbody>

            </table>
        </section>






        <script src="js/bootstrap.min.js"></script>
        <script src="Vistas/js/bootstrap.min.js"></script>
    </body>
>>>>>>> Stashed changes
</html>