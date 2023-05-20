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
        <link rel="stylesheet" href="Vistas/css/bootstrap.min.css">
        <link rel="stylesheet" type="text/css" href="css/estilosPropios.css">
        <link rel="stylesheet" type="text/css" href="Vistas/css/estilosPropios.css">
        <title>Gestor - Gestion Wikis</title>
    </head>
    <body>
        <nav>
            <div>

                <label>Nombre y Rol</label>
            </div>

            <div  class="alinear-centro">
                <a href="#" class="btn btn-primary"data-bs-toggle="modal" data-bs-target="#exampleModal">Nueva Wiki</a>

            </div>
            <div  class="alinear-derecha">
                <button>Cerrar Sesion</button>

            </div>

        </nav>

        <div class="notificaciones-contenedor">
            <h4 class="text-center text-light">Notificaciones</h4>
            <div class="notificaciones">
                <label class="notificacion-estado">Resuelto</label><br>
                <label class="color-asunto">Asunto - Propuesta para supervisor</label>
                <p class="text-light">123456783 - Juanes Gonzales quiere ser supervisor del articulo Tecnologia</p>

                <button type="button" class="btn btn-success">Aceptar</button>
                <button type="button" class="btn btn-danger">Rechazar</button>
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
                        <td><a href="gestor_gestionArticulos.jsp"><%= wik.getNombre()%></a></td>
                        <td>
                            <a class="btn btn-warning" href="../ControladorWikis?accion=editar&id=<%= wik.getId()%>">Editar</a>

                            
                            
                            <button type="button" class="btn btn-danger" id="<%= wik.getId()%>">
                                <a href="../ControladorWikis?accion=eliminar&id=<%= wik.getId()%>">eliminar</a>
                            </button>

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