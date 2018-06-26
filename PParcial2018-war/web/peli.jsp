<%-- 
    Document   : peli
    Created on : 25-jun-2018, 15:45:35
    Author     : Hector
--%>

<%@page import="entity.Category"%>
<%@page import="entity.Language"%>
<%@page import="entity.Rating"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
   <!-- título, descripción, año, calificación (rating), idioma (language_id) y categorías -->
    <body>
        <%
            List<Rating> lr=(List<Rating>) request.getAttribute("lr");
            List<Language> lg=(List<Language>) request.getAttribute("lg");
            List<Category> lc=(List<Category>) request.getAttribute("lc");
            if(lr==null || lg==null || lc==null){
        %>
        <jsp:forward page="peliculasServlet" />
        <%
            }
        %>
        <br></br>
        <form method="post" action="GuardarServlet">
        Titulo<input type="text" size="50" name="titulo">
        <br></br>
        Descripcion <input type="text" size="50" name="des">
        <br></br>
        Año<input type="text" size="50" name="anio">
        <br></br>
        <select name="categ" multiple>
            <%
                for(Category c: lc){
                    
                
                %>
                <option value="<%=c.getCategoryId()%>" > <%=c.getName() %> </option>
                      <%
                          }
                          %>
        </select>
                 <p>Hold down the Ctrl (windows) / Command (Mac) button to select multiple options.</p>             

         <select name="lang">
            <%
                for(Language l: lg){
                    
                
                %>
                <option value="<%=l.getLanguageId()%>" > <%=l.getName() %> </option>
                      <%
                          }
                          %>
                      
        </select>
                          <br></br>
                           <select name="rat">
            <%
                for(Rating c: lr){
                    
                
                %>
                <option value="<%= c.getRatingId() %>" > <%=c.getDescription() %> </option>
                      <%
                          }
                          %>
                      
        </select>
       <br></br>
       <input type="submit" value="añadir">
       </form>
    </body>
</html>
