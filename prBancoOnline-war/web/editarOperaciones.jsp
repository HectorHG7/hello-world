<%-- 
    Document   : editarOperaciones
    Created on : 27-may-2018, 11:54:47
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
            <h3>Editar operacion</h3>
            <form name="frm1" method="post" action="EditarOperacionServlet">
                <div class="form-group">
                    <label>Introduce el codigo de la operacion a editar<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="codigo">
                </div>
                <div class="form-group">
                    <select class="form-control" id="sel" name="seleccion"  onChange="comprobarOption1()">
                      <option value="-1">Modificar</option>
                      <option value="1">Borrar</option>
                    </select>
                </div>
                <h4></br>Campos de edicion permitidos</h4>
                <div class="form-group">
                    <label>Concepto</label>
                    <input type="text" id="f1" class="form-control"  size="40" name="concepto">
                </div>
                <div class="form-group">
                    <label>Entidad</label>
                    <input type="text" class="form-control" size="40" name="entidad">
                </div>
                <div class="form-group">
                    <label>Cantidad</label>
                    <input type="text" class="form-control" size="40" name="cantidad">
                </div>
                <div class="form-group">
                    <label>Descripcion</label>
                    <input type="text" class="form-control" size="40" name="descripcion">
                </div>
                <input type="submit" class="btn btn-primary" value="Aceptar">
            </form>
        </div>
    </body>
</html>


