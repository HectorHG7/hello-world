<%-- 
    Document   : index
    Created on : 25-jun-2018, 13:40:08
    Author     : Hector
--%>

<%@page import="entity.Category"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    List<Category> lc=(List<Category>)request.getAttribute("categorias");
    %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        
        <h1>Hello World!</h1>
        <form method="post" action="ActoresServlet">
         <% for(Category s: lc)
         {
         %>   
         <input type="checkbox" name="categ" value="<%=s.getCategoryId().toString()%>"><%=s.getName()%><br>
        <%
            }
            %>
            
            <input type="submit" value="enviar">
        </form>
            <a href="peli.jsp">añadir</a>
    </body>
</html>

<!-- jsp:forward page="/NewServlet"/> -->
