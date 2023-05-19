<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="Controlador.Controlador" %>

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
            
            <label></label>
        </div>

        <div class= "alinear-centro">
            <img  src="" alt="">
        </div>
        
        <div  class="alinear-derecha">
            
            <button><a href="../Controlador?accion=iniciosesion">Iniciar Sesion</a></button>
            
            
            
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
        <table class="table border">
            <thead class="table-light">

                <td>Id Wiki</td>
                <td>Nombre Wiki</td>
                <td>Acciones</td>
            </thead>
            <tr>
                <td>1</td>
                <td>Tecnologia</td>
                <td>
                    
                </td>
            </tr>
    </section>
<script src="js/bootstrap.min.js"></script>
</body>
</html>