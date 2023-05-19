<%@page import="Modelo.Usuario"%>
<%@page import="ModeloDAO.UsuariosDao"%>
<%@page import="java.util.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="ModeloDAO.WikisDao"%>
<%@page import="Modelo.Wiki"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Gestor - Gestion Wikis</title>
    <link rel="stylesheet" href="Vistas/css/bootstrap.min.css">
    <link rel="stylesheet" href="Vistas/css/estilosPropios.css">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/estilosPropios.css">
</head>
<body>
    <nav>
        <div>
            
             
          
        </div>

        <div class="alinear-centro">
            <button type="button" class="btn btn-primary">Nueva Wiki</button>
        </div>

        <div class="alinear-derecha">
           <button><a href="../Controlador?accion=cerrarsesion">Cerrar Sesion</a></button>
        </div>
    </nav>

    <div class="notificaciones-contenedor">
        <h4 class="text-center text-light">Notificaciones</h4>
        <div class="notificaciones">
            <label class="notificacion-estado">Resuelto</label><br>
            <label class="color-asunto">Asunto - Propuesta para supervisor</label>
            <p class="text-light">123456783 - Juanes Gonzales quiere ser supervisor del artículo Tecnología</p>

            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">Aceptar</button>
            <button type="button" class="btn btn-danger">Rechazar</button>
        </div>
    </div>

    <section class="wiki-contenedor">
        <table class="table border">
            <thead class="table-light">
                <tr>
                    <th>Id Wiki</th>
                    <th>Nombre Wiki</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody>
                <%
                    WikisDao dao = new WikisDao();
                    List<Wiki> list = dao.obtenerWikis();
                    Iterator<Wiki> iter =list.iterator();
                    Wiki wik =null;
                    while(iter.hasNext()){
                    wik= iter.next();
                %>
                <tr>
                    <td><%= wik.getId() %></td>
                    <td><a href="gestor_gestionArticulos.jsp"><%= wik.getNombre() %></a></td>
                    <td>
                        <button type="button" class="btn btn-warning" data-bs-toggle="modal" data-bs-target="#exampleModal">Editar</button>
                        <button type="button" class="btn btn-danger">Eliminar</button>
                    </td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </section>

    <script src="js/bootstrap.min.js"></script>
</body>
</html>
