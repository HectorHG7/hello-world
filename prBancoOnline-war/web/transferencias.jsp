<%-- 
    Document   : Transferencias
    Created on : 16-abr-2018, 9:32:30
    Author     :
--%>

<%@page import="javax.ejb.EJB"%>
<%@page import="javax.servlet.http.HttpSession"%>
<%@page import="app.ejb.TransferenciaFacade"%>
<%@page import="app.entity.Transferencia"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%
    
    HttpSession miSesion = request.getSession(false);
   List<Transferencia> list = (List<Transferencia>)miSesion.getAttribute("listaT");

%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transferencias</title>
    </head>
    <body>
        <div class="trans">
            <div class="transferencia">
                <h2>Últimas Transferencias</h2>
                <table border="1">
                    <tr>
                        <th>Codigo</th>
                        <th>Cuenta Destino</th>
                        <th>Cantidad</th>
                        <th>Cuenta Origen</th>
                        <th>Fecha</th>
                    </tr>
                    <%
                        for(Transferencia t : list) {
                    %>
                    hola
                    <tr>
                        <td><%= t.getCodigo() %></td>
                        <td><%= t.getUsuarioNCuenta1().getNCuenta() %></td>
                        <td><%= t.getCantidad() %></td>
                        <td><%= t.getUsuarioNCuenta().getNCuenta() %></td>
                        <td><%= t.getFecha() %></td>
                    </tr>
                    <% 
                        } 
                     %>
                </table>
            </div>
            <form class="form-inline" method="post" action="transferencia">
                <div class="form-group">
                    <input type="text" class="form-control" size="30" name="cuentaDest" placeholder="Cuenta Destino">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" size="10" name="cantidad" placeholder="Cantidad">
                </div>
                <input type="submit" class="btn btn-primary" value="Transferencia">
            </form>
        </div>
    </body>
</html>