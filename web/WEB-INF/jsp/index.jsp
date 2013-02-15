<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- esta línea toma el nombre de la aplicación y la asigna como base de
            todos los enlaces, de modo que en las rutas absolutas no será necesario
            empezarlas desde el nombre de la aplicación, sino desde / -->
        <base href="${pageContext.request.contextPath}/" />

        <link rel="icon" href="lib/img/favicon.ico"/>
        <title>Proyecto Películas</title>

        <link rel="stylesheet" type="text/css" media="all" href="lib/css/bootstrap-2.2.1.min.css" />
        <link rel="stylesheet" type="text/css" media="all" href="lib/css/style.css" />

        <script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="lib/js/jquery-1.8.3.min.js"><\/script>')</script>
        <script type="text/javascript" src="lib/js/bootstrap-2.2.1.min.js"></script>
        <script src="lib/js/data.js"></script><script src="lib/js/library.js"></script>
        
    </head>

    <body>

        <div class="navbar navbar-fixed-top">
            <div class="navbar-inner">
                <div class="container-fluid">

                    <a class="brand" href="index.html">Películas CRUD</a>

                    <ul class="nav">
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown">Películas</a>
                            <ul class="dropdown-menu">
                                <li><a href="peliculas/index.html">Ver películas</a></li>
                                <li><a href="peliculas/form.html">Nueva película</a></li>
                            </ul>
                        </li>
                        <li><a href="actores/index.html">Actores</a></li>
                    </ul>
 
                </div>
            </div>
        </div>

        <div id="contenido" class="container-fluid">


            <jsp:include page='<%=(String) request.getAttribute("contenido")%>' />


        </div>

        <div id="footer" class="navbar-fixed-bottom">
            <span class="left">Version 1.0

            </span>
            <span class="right">
                &copy; 20013 <a href="https://github.com/mgalbis/ProyectoPeliculas">María Galbis</a>
            </span>
        </div>
        <script type="text/javascript">
            
        </script>
    </body>
</body>
</html>
