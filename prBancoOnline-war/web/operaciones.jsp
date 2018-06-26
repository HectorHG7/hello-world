<%-- 
    Document   : operaciones
    Created on : 4-mei-2018, 11:34:54
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
            <h3>Ingresar o retirar fondos</h3>
            <form method="post" action="operaciones">
                <div class="form-group">
                    <select class="form-control" name="operacion">
                      <option value="-1">Retirar</option>
                      <option value="1">Ingresar</option>
                    </select>
                </div>
                <div class="form-group">
                    <label>Numero de Cuenta<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="cuenta" placeholder="XXXXXXXXXX 10 digitos">
                </div>
                <div class="form-group">
                    <label>Concepto<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="concepto" placeholder="Nomina, Tarjeta...">
                </div>
                <div class="form-group">
                    <label>Entidad<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="entidad" placeholder="Banco, Particular...">
                </div>
                <div class="form-group">
                    <label>Cantidad<font color=#FF0000> *</font></label>
                    <input type="text" class="form-control" size="40" name="cant" placeholder="0.0">
                </div>
                <div class="form-group">
                    <label>Descripcion</label>
                    <input type="text" class="form-control" size="40" name="descripcion" placeholder="Contenido">
                </div>
                <input type="submit" class="btn btn-primary" value="Hacer operación">
            </form>
        </div>
    </body>
</html>
