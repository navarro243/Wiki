<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>
<%@page import="Modelo.Articulo"%>
<%@page import="ModeloDAO.ArticulosDao"%>
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
    <link rel="stylesheet" type="text/css" href="css/estilosPropios.css">
    <link rel="stylesheet" type="text/css" href="Vistas/css/estilosPropios.css">
    <link rel="stylesheet" type="text/css" href="Vistas/css/bootstrap.min.css">

    <title>Portal Wikis</title>
</head>
<body>
    <nav>
        <div>
            
            <label></label>
        </div>

        <div class= "alinear-centro">
            <div class="d-flex">
                <button type="button" class="btn btn-primary me-2" data-bs-toggle="modal" data-bs-target="#uploadModal">Subir</button>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#downloadModal">Descargar</button>
            </div>

        </div>
        
        <div  class="alinear-derecha">
            <button><a href="../Controlador?accion=cerrarsesion">Cerrar Sesion</a></button>
            
        </div>
        
    </nav>
    
    <div class="notificaciones-contenedor">
        <h4 class="text-center text-light">Notificaciones</h4>
        <div class="notificaciones">
            <label class="notificacion-estado">pendiente</label><br>
            <label class="color-asunto"></label>
            <p class="text-light"></p>

            <img src="" alt="">
            <img src="" alt="">
        </div>

    </div>
    <section class="wiki-contenedor">
       <%
            String idArticuloStr = request.getAttribute("idArticulo").toString();
            int idArticulo = Integer.parseInt(idArticuloStr);
            ArticulosDao ArtiDao = new ArticulosDao();
            Articulo articu = ArtiDao.list(idArticulo);
            if( articu.getContenido()==null ){
   %>
       
    </section>
       <%}%>
  
   <!-- Modal para subir archivo -->
   <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <h5 class="modal-title" id="uploadModalLabel">Subir archivo HTML</h5>
                   <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
                   <!-- Aquí puedes colocar el formulario o el código necesario para subir el archivo -->
                   <form action="ControladorContenidoA" method="post" enctype="multipart/form-data">
                       <input type="file" name="file">
                       <input type="submit" value="Subir">
                   </form>
               </div>
           </div>
       </div>
   </div>

   <!-- Modal para descargar archivo -->
   <div class="modal fade" id="downloadModal" tabindex="-1" aria-labelledby="downloadModalLabel" aria-hidden="true">
       <div class="modal-dialog">
           <div class="modal-content">
               <div class="modal-header">
                   <h5 class="modal-title" id="downloadModalLabel">Descargar archivo HTML</h5>
                   <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
               </div>
               <div class="modal-body">
                   <!-- Aquí puedes colocar el enlace o el código necesario para descargar el archivo -->
                   <a href="ruta_del_archivo.html" download class="btn btn-primary">Descargar</a>
               </div>
           </div>
       </div>
   </div>${htmlContent}
<script src="js/bootstrap.min.js"></script>
<script src="Vistas/js/bootstrap.min.js"></script>
</body>
</html>