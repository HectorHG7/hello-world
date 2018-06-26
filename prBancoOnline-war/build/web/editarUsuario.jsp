<%-- 
    Document   : editarUsuario
    Created on : 26-may-2018, 14:31:45
    Author     : Hector
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
            <h3>Editar usuario</h3>
            <form name="frm" method="post" action="EditarUsuarioServlet">
                <div class="form-group">
                    <label>Introduce el numero de cuenta del usuario a editar<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="ncuenta">
                </div>
                <div class="form-group">
                    <select class="form-control" name="seleccion"  onChange="comprobarOption()">
                      <option value="-1">Modificar</option>
                      <option value="1">Borrar</option>
                    </select>
                </div>
                <h4></br>Campos de edicion permitidos</h4>
                <div class="form-group">
                    <label>DNI</label>
                    <input type="text" class="form-control" id="dni" size="40" name="dni">
                </div>
                <div class="form-group">
                    <label>Usuario</label>
                    <input type="text" class="form-control" size="40" name="user">
                </div>
                <div class="form-group">
                    <label>Contraseña</label>
                    <input type="text" class="form-control" size="40" name="pass">
                </div>
                <div class="form-group">
                    <label>Nombre</label>
                    <input type="text" class="form-control" size="40" name="nombre">
                </div>
                <div class="form-group">
                    <label>Apellidos</label>
                    <input type="text" class="form-control" size="40" name="apellidos">
                </div>
                <div class="form-group">
                    <label>Domicilio</label>
                    <input type="text" class="form-control" size="40" name="domicilio">
                </div>
                <div class="form-group">
                    <label>Teléfono</label>
                    <input type="text" class="form-control" size="40" name="telefono">
                </div>
                 <div class="form-group">
                    <label>Email</label>
                    <input type="text" class="form-control" size="40" name="email">
                </div>
                <input type="submit" class="btn btn-primary" value="Aceptar">
            </form>
        </div>
    </body>
</html>

