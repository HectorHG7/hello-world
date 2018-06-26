<%-- 
    Document   : buscar
    Created on : 5-mei-2018, 11:51:04
    Author     : Elise
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="app.entity.Movimiento"%>

<%
    int buscar = (Integer)request.getAttribute("buscar");
%>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>
    <body>
        <table style="width:100%">
            <tr>
              <td>
                <table style="width:100%">
                <form action="BuscarServlet" method="post">
                  <tr>
                    <td> <p align="right">Codigo</p> </td>
                    <td> <input type="text" name="codigo"> </td>
                  </tr>
                  <tr>
                    <td> <p align="right">Desde</p> </td>
                    <td> <input id="desdeDate" type="date" name="fecha1" default=""> </td>
                  </tr>
                  <tr>
                    <td> <p align="right">Hasta</p> </td>
                    <td> <input id="hastaDate" type="date" name="fecha2" default=""> </td>
                  </tr>
                  <tr>
                        <td></td>
                    <td><input type="submit" class="btn btn-primary" value="Buscar"></td>
                  </tr>
                 </form>
                </table>
              </td>
              <td>
                  <table style="width:100%">
                  <tr>
                    <th>Código</th>
                    <th>Fecha</th>
                    <th>Entidad Origen</th> 
                    <th>Entidad Destino</th>
                    <th>Concepto</th>
                    <th>Cantidad</th>
                  </tr>
                  <% if(buscar==1) {
                          List<Movimiento> listaResultados = (List)request.getAttribute("ListaMovLimit");
                          for (Movimiento mov : listaResultados) { %>
                    <tr>
                      <td><%= mov.getCodigomov() %></td>
                      <td><%= mov.getFecha() %></td>
                      <% if (mov.getTipo()==1) { %>
                      <td><%= mov.getEntidad() %></td> 
                      <td>Mi cuenta</td>
                      <% } else {%>
                      <td>Mi cuenta</td> 
                      <td><%= mov.getEntidad() %></td>
                      <% } %>
                      <td><%= mov.getConcepto() %></td>
                      <td><%= mov.getCantidad() %></td>
                    </tr>
                    <% } } %>
                </table>
              </td>
            </tr>
        </table>
    </body>
</html>