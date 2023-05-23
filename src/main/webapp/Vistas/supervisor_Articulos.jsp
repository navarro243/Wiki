<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>
<%@page import="java.util.*"%>
<%@page import="Modelo.Articulo"%>
<%@page import="ModeloDAO.ArticulosDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
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
        <div>
            
            <label></label>
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

    </div>
    <section class="wiki-contenedor">
        <table class="table border">
            <thead class="table-light">
                <td>Id Articulos</td>
                <td>Nombre Articulo</td>
               
            </thead>
             <tbody>
                    <%
               
          
                    
                      int valorEntero = (int) request.getAttribute("valorEntero");

                        ArticulosDao dao = new ArticulosDao();
                        List<Articulo> lista = dao.obtenerArticulos(valorEntero);
                        Iterator<Articulo> iter = lista.iterator();
                        Articulo art = null;  
        
                        while (iter.hasNext()) {
                            art = iter.next();
                            
                          
                    %>
                    <tr>
                        <td><%= art.getId() %></td>
                        <td><a href="#"><%= art.getTitulo() %></a></td>
                        
                    </tr>
                    <%}%>
                </tbody>
                
        </table>
    </section>
                



 <!-- Modal -->
<!-- Modal Crear--> 


 <script src="js/bootstrap.min.js"></script>
<script src="Vistas/js/bootstrap.min.js"></script>
</body>
</html>