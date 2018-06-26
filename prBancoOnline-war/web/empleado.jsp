<%-- 
    Document   : empleado
    Created on : 16-apr-2018, 9:46:44
    Author     : Elise
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="tabs.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Empleado</title>
    </head>
    <% 
        String s=request.getParameter("insert");
        if(s==null){
            s="openTab('click','operaciones')";
        }
    %>
    <body onload=<%=s%> >
        <div class="tab">
            <button class="tablinks" onclick="openTab(event, 'nuevoUsuario')">Nuevo Usuario</button>
            <button class="tablinks" onclick="openTab(event, 'editarUsuario')">Editar Usuario</button>
            <button class="tablinks" onclick="openTab(event, 'operaciones')">Operaciones</button>
            <button class="tablinks" onclick="openTab(event, 'editarOperaciones')">Editar Operaciones</button>
        </div>
        
        <div id="nuevoUsuario" class="tabcontent">
            <jsp:include page="nuevoUsuario.jsp" flush="true" />
        </div>
        <div id="editarUsuario" class="tabcontent">
            <jsp:include page="editarUsuario.jsp" flush="true" />
        </div>
        <div id="operaciones" class="tabcontent">
            <jsp:include page="operaciones.jsp" flush="true" />
        </div>
         <div id="editarOperaciones" class="tabcontent">
            <jsp:include page="editarOperaciones.jsp" flush="true" />
        </div>
    </body>
</html>
