<%-- 
    Document   : gestor_GestionModificaciones
    Created on : 12 may. 2023, 15:56:34
    Author     : vamil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="css/bootstrap.min.css">
    <link rel="stylesheet" href="css/estilosPropios.css">
    <title>Gestor - Modificaciones</title>
</head>
<body>
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
        <div>
            <label>Nombre Y Rol</label>
        </div>

        <div class= "alinear-centro">
            <img  src="" alt="">
        </div>
        
        <div  class="alinear-derecha">
            <button><a href="../Controlador?accion=cerrarsesion">Cerrar Sesion</a></button>
        </div>
        
    </nav>
    
    <div class="notificaciones-contenedor">
        <h4 class="text-center text-light">Notificaciones</h4>
        <div class="notificaciones">
            <label class="notificacion-estado">Resuelto</label><br>
            <label class="color-asunto">Asunto - Propuesta para supervisor</label>
            <p class="text-light">123456783 - Juanes Gonzales quiere ser supervisor del articulo Tecnologia</p>

            <button type="button" class="btn btn-success" data-bs-toggle="modal" data-bs-target="#exampleModal">Aceptar</button>
            <button type="button" class="btn btn-danger">Rechazar</button>
        </div>

    </div>
    <section class="wiki-contenedor">
        <table class="table border">
            <thead class="table-light">
                <td>Id Modificacion</td>
                <td>Descripcion Cambio</td>
                <td>Nombre y Apellido</td>
                <td>Articulo</td>
                <td>Acciones</td>
            </thead>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td></td>
                <td>
                    <button type="button" class="btn btn-secondary">Descargar</button>
                    <button type="button" class="btn btn-primary">Aceptar</button>
                    <button type="button" class="btn btn-danger">Eliminar</button>
                </td>
            </tr>
    </section>
    <script src="js/bootstrap.min.js"></script>
</body>
</html>