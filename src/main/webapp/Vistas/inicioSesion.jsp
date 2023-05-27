<%-- 
    Document   : inicioSesion
    Created on : 9 may. 2023, 22:51:13
    Author     : vamil
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet"type="text/css" href="Vistas/css/estilosPropios.css">
    <link rel="stylesheet"type="text/css" href="css/estilosPropios.css">
    <title>Iniciar Sesion</title>
</head>
<body>
    <form action="../ControlIU">
        <h1>Iniciar Sesion</h1> 
		<label for="cedula">Cédula:</label>
		<input type="text" id="cedula" name="cedula" required>

                <input type="submit" name="accion" value="iniciosesion">
	</form>
</body>
</html>