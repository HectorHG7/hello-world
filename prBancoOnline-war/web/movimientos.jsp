<%-- 
    Document   : movimientos
    Created on : 5-mei-2018, 14:25:28
    Author     : Elise
--%>

<%@page import="java.util.List"%>
<%@page import="app.entity.Movimiento"%>
<%
    List<Movimiento> listaMovimientos;
    listaMovimientos = (List)request.getAttribute("listam");
%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="tab">
            <button class="tablinks2" onclick="openTab2(event, 'balance')">Balance</button>
            <button class="tablinks2" onclick="openTab2(event, 'gastos')">Gastos</button>
            <button class="tablinks2" onclick="openTab2(event, 'ingresos')">Ingresos</button>
        </div>
        
        <div id="balance" class="tabcontent2">
            <table style="width:100%">
                <tr>
                  <th>Fecha</th>
                  <th>Entidad Origen</th> 
                  <th>Entidad Destino</th>
                  <th>Concepto</th>
                  <th>Cantidad</th>
                </tr>
                <% for (Movimiento mov : listaMovimientos) { %>
                <tr>
                  <td><%= mov.getFecha() %></td>
                  <% if (mov.getTipo()==1) { %>
                  <td><%= mov.getEntidad() %></td> 
                  <td>Mi cuenta</td>
                  <% } else {%>
                  <td>Mi cuenta</td> 
                  <td><%= mov.getEntidad() %></td>
                  <% } %>
                  <td><%= mov.getConcepto()%></td>
                  <td><%= mov.getCantidad()%></td>
                </tr>
                <% } %>
            </table>
        </div>
        <div id="gastos" class="tabcontent2">
            <table style="width:100%">
                <tr>
                  <th>Fecha</th>
                  <th>Entidad Origen</th> 
                  <th>Entidad Destino</th>
                  <th>Concepto</th>
                  <th>Cantidad</th>
                </tr>
                <% for (Movimiento mov : listaMovimientos) {
                if (mov.getTipo()==-1) { %>
                <tr>
                  <td><%= mov.getFecha() %></td>
                  <td>Mi cuenta</td> 
                  <td><%= mov.getEntidad() %></td>
                  <td><%= mov.getConcepto()%></td>
                  <td><%= mov.getCantidad()%></td>
                </tr>
                <% }} %>
            </table>
        </div>
        <div id="ingresos" class="tabcontent2">
            <table style="width:100%">
                <tr>
                  <th>Fecha</th>
                  <th>Entidad Origen</th> 
                  <th>Entidad Destino</th>
                  <th>Concepto</th>
                  <th>Cantidad</th>
                </tr>
                <% for (Movimiento mov : listaMovimientos) {
                if (mov.getTipo()==1) { %>
                <tr>
                  <td><%= mov.getFecha() %></td>
                  <td><%= mov.getEntidad() %></td>
                  <td>Mi cuenta</td>
                  <td><%= mov.getConcepto()%></td>
                  <td><%= mov.getCantidad()%></td>
                </tr>
                <% }} %>
            </table>
        </div>
    </body>
</html>
