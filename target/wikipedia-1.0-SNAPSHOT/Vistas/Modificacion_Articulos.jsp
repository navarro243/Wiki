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
                
                <td>id</td>
                <td>Nombre Wiki</td>
                <td>Acciones</td>
                </thead>    

                <%
                     String idArticuloStr = request.getAttribute("idArticulo").toString();
                    int idArticulo = Integer.parseInt(idArticuloStr);
                    ArticulosDao ArtiDao = new ArticulosDao();
                    Articulo articu = ArtiDao.list(idArticulo);
                    System.out.println("************************************************************");
                    System.out.println(articu.getId());
                    System.out.println(articu.getTitulo());

                %>
                <form action="ControladorArticulos" method="get"> 
                    <tbody>
                        <tr>
                            <td><input type="hidden" name="txtid" value="<%= articu.getId()%>" readonly></td>
                            
                            <td><input type="text" name="cambioTitulo" value="<%= articu.getTitulo() %>"></td>
                            
                            <td><input type="submit" name="accion" value="Actualizar"></td>
                        </tr>
                    </tbody>
                </form>

            </table>
        </section>
        <script src="js/bootstrap.min.js"></script>
    </body>
</html>