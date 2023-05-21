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
    <title>Gestor - Gestion Articulos</title>
</head>
<body>
    <nav>
        <div>
            
            <label>Nombre Y Rol</label>
        </div>

         <div  class="alinear-centro">
                <a href="#" class="btn btn-primary"data-bs-toggle="modal" data-bs-target="#exampleModal">Nueva Wiki</a>

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
                <td>Acciones</td>
            </thead>
             <tbody>
                    <%
               
          
                    int id_wiki_origen = ((Integer) request.getSession().getAttribute("variable")).intValue();

                        ArticulosDao dao = new ArticulosDao();
                        List<Articulo> lista = dao.obtenerArticulos(id_wiki_origen);
                        Iterator<Articulo> Intenerator = lista.iterator();
                        Articulo art = null;  
                        System.out.println("8=================================================================================================================================D");
                        while (Intenerator.hasNext()) {
                            art = Intenerator.next();
                          
                    %>
                    <tr>
                        <td><%= art.getId() %></td>
                        <td><a href="#"><%= art.getTitulo() %></a></td>
                        <td>
                                
                        </td>
                    </tr>
                    <%}%>
                </tbody>
                
        </table>
    </section>
                



 <!-- Modal -->
<!-- Modal Crear--> 
<div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
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
                    </div>
                    <input type="submit" class="btn btn-primary" name="accion" value="agregar">
                </form>
            </div>
        </div>
    </div>
</div>
<script src="js/bootstrap.min.js"></script>
</body>
</html>