<%-- 
    Document   : usuario
    Created on : 25-jun-2018, 19:07:14
    Author     : Hector
--%>

<%@page import="entity.Tuit"%>
<%@page import="java.util.List"%>
<%@page import="entity.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <%
      String h=(String)session.getAttribute("hola");
    Usuario u=(Usuario)request.getAttribute("user");
    Integer seguidores =(Integer)request.getAttribute("seguidores");
    Integer seguidos =(Integer)request.getAttribute("seguidos");
    Integer gusta =(Integer)request.getAttribute("gusta");
    List<Tuit> lu=(List<Tuit>)request.getAttribute("tuits");
    %>
    <body>
        <h1><%=h%></h1>
        <table style="width:100%">
  <tr>
    <th>nombre</th>
    <th>descripción</th> 
    <th>país</th>
    <th>url</th>
    <th>fecha_ini</th>
    <th>seguidores</th>
    <th>seguidos</th>
    <th>me gusta</th>
  </tr>
  <tr>
    <td><%= u.getNombre()  %></td>
    <td><%= u.getDescripcion()  %></td>
        <td><%= u.getPais().getPaisIsoCode() %></td>

        <td><%= u.getUrl() %></td>

    <td><%= u.getFecha().toString()  %></td>
    <td><%= seguidores.toString() %></td>
    <td><%= seguidos.toString() %></td>
    <td><%= gusta.toString() %></td>
  </tr>
</table>
   <br></br>
    <br></br>
    <h4>Tuits</h4>
    <br></br>
  <%
      for(Tuit t: lu){
  %>

  <%= t.getFecha()+"  "+t.getTexto()+"   "+ t.getUsuario().getNombre()+"  "+t.getUsuarioList().size()+"   "+ t.getUsuarioList1().size()%>
    <form method="post" action="ReServlet">
      <input type="hidden" name="tuitId" value="<%= t.getStatus().toString() %>">
       <input type="hidden" name="usId" value="<%= u.getIdusuario().toString() %>">
  <input type="submit" value="retuitear">
 </form>
   <form method="post" action="GuServlet">
      <input type="hidden" name="tuitId" value="<%= t.getStatus().toString() %>">
       <input type="hidden" name="usId" value="<%= u.getIdusuario().toString() %>">
  <input type="submit" value="meGusta">
 </form>
  <br></br>
    <br></br>

    <%
    }
    %>
     <%
         for(Usuario us : u.getUsuarioList1()){
      for(Tuit t: us.getTuitList2()){
  %>

  <%= t.getFecha()+"  "+t.getTexto()+"   "+ t.getUsuario().getNombre()+"  "+t.getUsuarioList().size()+"   "+ t.getUsuarioList1().size()%>
    <form method="post" action="ReServlet">
      <input type="hidden" name="tuitId" value="<%= t.getStatus().toString() %>">
      <input type="hidden" name="usId" value="<%= us.getIdusuario().toString() %>">
  <input type="submit" value="retuitear">
 </form>
   <form method="post" action="GuServlet">
       <input type="hidden" name="usId" value="<%= us.getIdusuario().toString() %>">
      <input type="hidden" name="tuitId" value="<%= t.getStatus().toString() %>">
  <input type="submit" value="meGusta">
 </form>
  <br></br>
    <br></br>

    <%
        }
     }
    %>
    <h4>Seguidos</h4>
    <br></br>
  <%
      for(Usuario us: u.getUsuarioList1()){
  %>
  
  <%= us.getNombre() %> 
  <br></br>
    <br></br>

    <%
    }
    %>
    </body>
</html>
