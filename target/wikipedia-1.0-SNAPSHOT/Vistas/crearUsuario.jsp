<%-- 
    Document   : crearUsuario
    Created on : 9 may. 2023, 23:04:04
    Author     : vamil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="css/estilosPropios.css">
        <title>Registrarse</title>
    </head>
    <body>
        <form action="../ControladorNotificaciones" method="post">
            <label for="cedula">Cédula:</label>
            <input type="text" id="cedula" name="cedula" required>

            <label for="nombre">Nombre:</label>
            <input type="text" id="nombre" name="nombre" required>

            <label for="apellido">Apellido:</label>
            <input type="text" id="apellido" name="apellido" required>
            <input type="submit" name="accion" value="solicitarRegistro">
        </form>
    </body>
</html>
