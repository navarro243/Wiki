<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
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
    <title>Articulos</title>
</head>
<body>
    <nav>
        <div>
            
        </div>

       
        
        <div  class="alinear-derecha">
            <button><a href="../Controlador?accion=cerrarsesion">Cerrar Sesion</a></button>
        </div>
        
    </nav>
    
    <div class="notificaciones-contenedor">
        <h4 class="text-center text-light">Notificaciones</h4>
        <div class="notificaciones">
            <label class="notificacion-estado">Resuelto</label>
            <br>
            <label class="color-asunto">Asunto - Propuesta para supervisor</label>
            <p class="text-light">123456783 - Juanes Gonzales quiere ser supervisor del articulo Tecnologia</p>

            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">Aceptar</button>
            <button type="button" class="btn btn-danger">Rechazar</button>

        </div>
        <button class="pedirAscenso">Pedir Ascenso</button>
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