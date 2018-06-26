<%-- 
    Document   : newjsp
    Created on : 28-mei-2018, 0:41:18
    Author     : Elise
--%>

<%@page import="java.util.List"%>
<%@page import="app.entity.Movimiento"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    List<Movimiento> lista = (List)request.getAttribute("ListaMovLimit");
%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <ul>
        <%
        for(Movimiento m : lista) {
        %>
        <li><%= m.getCodigomov() %></li>
        <%
            }
        %>
        </ul>
    </body>
</html>
