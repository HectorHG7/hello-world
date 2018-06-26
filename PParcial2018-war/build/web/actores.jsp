<%-- 
    Document   : actores
    Created on : 25-jun-2018, 14:08:57
    Author     : Hector
--%>

<%@page import="entity.Actor"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Actor> la= (List<Actor>)request.getAttribute("actores");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
        <%
        if(!la.isEmpty()){
        for(Actor a: la)
            {
        %>
        <%= a.getFirstName()+" "+a.getLastName()%>
        <br></br>
        <%
            }
        }    
        %>
        
    </body>
</html>
