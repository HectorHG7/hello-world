<%-- 
    Document   : login
    Created on : 9-apr-2018, 10:03:27
    Author     : Elise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
    </head>
    <body>
        <div id="formLogin">
            <h2>Banca Online</h2>
            <form method="post" action="LoginServlet">
                <div class="form-group">
                    <label>Usuario</label>
                    <input type="text" class="form-control" size="40" name="user"><br/>
                </div>
                <div class="form-group">
                    <label>Contraseña</label>
                    <input type="password" class="form-control" size="40" name="pass"><br/>
                </div>
                <input type="submit" class="btn btn-primary" value="Iniciar sesión">
            </form>
        </div>
    </body>
</html>
