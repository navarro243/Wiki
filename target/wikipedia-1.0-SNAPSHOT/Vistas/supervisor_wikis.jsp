<%-- 
    Document   : supervisor_wikis
    Created on : 12 may. 2023, 17:20:19
    Author     : vamil
--%>
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
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" href="css/estilosPropios.css">
        <title>Gestor</title>
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
            <div class="notificaciones">
                <label class="notificacion-estado"></label><br>
                <label class="color-asunto"></label>
                <p class="text-light"></p>

                <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">Aceptar</button>
                <button type="button" class="btn btn-danger">Rechazar</button>
            </div>
            <a href="../ControladorNotificaciones?accion=ascenso" class="pedirAscenso">Pedir Ascenso</a>
        </div>

        <section class="wiki-contenedor">
            <table class="table border">
                <thead class="table-light">
                <td>Id Wiki</td>
                <td>Nombre Wiki</td>


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
                    </tr>
                    <%}%>
                </tbody>
        </section>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>