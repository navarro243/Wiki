<%@page import="Modelo.Articulo"%>
<%@page import="ModeloDAO.ArticulosDao"%>
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
            <%
                Cookie[] cookies = request.getCookies();
                int cedula = 0;
                String nombre = "";
                int rol = 0;
                String nombreRol;
                if (cookies != null) {
                    for (Cookie cookie : cookies) {
                        if (cookie.getName().equals("usuario")) {
                            String value = cookie.getValue();
                            String[] values = value.split(":");

                            cedula = Integer.parseInt(values[0]);
                            nombre = values[1];
                            rol = Integer.parseInt(values[2]);
                        }
                    }
                }

                switch (rol) {
                    case 1:
                        nombreRol = "Gestor";
                        break;
                    case 2:
                        nombreRol = "Coordinador";
                        break;
                    case 3:
                        nombreRol = "Supervisor";
                        break;
                    case 4:
                        nombreRol = "Colaborador";
                        break;
                    default:
                        nombreRol = "Sin cuenta";
                }
            %>
            <div>
                <label name="accion" value="nombreYrol"><%= nombre + " - " + nombreRol%></label>
            </div>

            <div class="alinear-centro">
                <div class="d-flex">
                    <button type="button" class="btn btn-primary me-2" data-bs-toggle="modal"
                            data-bs-target="#uploadModal">Actualizar</button>
                    <button type="button" class="btn btn-primary" data-bs-toggle="modal"
                            data-bs-target="#downloadModal">Descargar</button>
                </div>

            </div>

            <div class="alinear-derecha">
                <button><a href="ControlIU?accion=cerrarsesion">Cerrar Sesion</a></button>

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
        <!-- ************************************************** -->
        <section class="wiki-contenedor">

            <%
                String idArticuloStr = request.getAttribute("idArticulo").toString();
                int idArticulo = Integer.parseInt(idArticuloStr);
                ArticulosDao ArtiDao = new ArticulosDao();
                Articulo articu = ArtiDao.list(idArticulo);
                System.out.println("archivo_ruta==" + articu.getContenido());

                String texto = articu.getContenido();
                StringBuilder stringBuilder = new StringBuilder(texto);

                for (int i = 0; i < stringBuilder.length(); i++) {
                    char letra = stringBuilder.charAt(i);
                    if (letra == '\\') {  // Condición para cambiar el carácter
                        stringBuilder.setCharAt(i, '/');  // Cambiar el carácter a 'x'
                    }
                }

                String textoModificado = stringBuilder.toString();
                System.out.println(textoModificado);
            %>


        </section>
        <!-- **************************************************************** -->




        <!-- Modal para subir archivo -->
        <div class="modal fade" id="uploadModal" tabindex="-1" aria-labelledby="uploadModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="uploadModalLabel">Actualizar archivo HTML</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <!-- Aquí puedes colocar el formulario o el código necesario para subir el archivo -->
                        <form action="ControladorContenidoA?id=<%=articu.getId()%>&cedula=<%=cedula%>" method="post" enctype="multipart/form-data">
                            <input type="text" name="descripcion">
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
                        <a id="downloadLink" href="ControladorDescargaArticulo?archivo=<%=textoModificado%>" download class="btn btn-primary">Descargar</a>
                    </div>
                </div>
            </div>
        </div>

        <script src="js/bootstrap.min.js"></script>
        <script src="Vistas/js/bootstrap.min.js"></script>
    </body>

</html>