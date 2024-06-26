    <%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.*"%>
<%@page import="Modelo.Wiki"%>
<%@page import="ModeloDAO.WikisDao"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="Vistas/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="Vistas/css/estilosPropios.css">
        <link rel="stylesheet" href="css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilosPropios.css">
        <title>Portal Wikis</title>
    </head>
    <body>
        <nav>
            <div>
                <label>SIN CUENTA</label>
            </div>

            <div class= "alinear-centro">
                <a class="btn btn-primary" href="Documetacion/index.html">Documentacion </a>
            </div>

            <div  class="alinear-derecha">
                <button><a href="crearUsuario.jsp">Registrarse</a></button>
                <button><a href="inicioSesion.jsp">Iniciar Sesion</a></button>
            </div>
        </nav>

        <div class="notificaciones-contenedor">
            <h4 class="text-center text-light">Notificaciones</h4>
            <div class="notificaciones">
                <label class="notificacion-estado"></label><br>
                <label class="color-asunto"></label>
                <p class="text-light"></p>

                <img src="" alt="">
                <img src="" alt="">
            </div>

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
                         <td><a href="../ControladorArticulos?valorEnviado=<%=  String.valueOf(wik.getId())%>&accion=vistaSC"><%= wik.getNombre()%></a></td>

                    </tr>
                    <%}%>
                </tbody>
        </section>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>