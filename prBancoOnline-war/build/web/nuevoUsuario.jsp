<%-- 
    Document   : nuevoUsuario
    Created on : 4-mei-2018, 11:29:13
    Author     : Elise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="form">
            <h3>Crear un nuevo usuario</h3>
            <form method="post" action="CrearUsuarioServlet">
                <div class="form-group">
                    <label>DNI<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="dni">
                </div>
                <div class="form-group">
                    <label>Usuario<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="user">
                </div>
                <div class="form-group">
                    <label>Contraseña<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="pass">
                </div>
                <div class="form-group">
                    <label>Nombre<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="nombre">
                </div>
                <div class="form-group">
                    <label>Apellidos<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="apellidos">
                </div>
                <div class="form-group">
                    <label>Domicilio<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="domicilio">
                </div>
                <div class="form-group">
                    <label>Teléfono<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="telefono">
                </div>
                 <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control" size="40" name="email">
                </div>
                <input type="submit" class="btn btn-primary" value="Crear">
            </form>
        </div>
    </body>
</html>
