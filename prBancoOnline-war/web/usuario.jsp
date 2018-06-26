<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="tabs.js"></script>
    </head>
    
    <%
        String s=(String)request.getAttribute("insert");
        if(s==null){
            s="openTab('click','movimientos')";
        }
    %>
    
    <body onload=<%= s %> >
        <div class="tab">
          <button class="tablinks" onclick="openTab(event, 'movimientos')">Movimientos</button>
          <button class="tablinks" onclick="openTab(event, 'transferencias')">Transferencias</button>
          <button class="tablinks" onclick="openTab(event, 'buscar')">Buscar</button>
        </div>
        <div id="movimientos" class="tabcontent">
            <jsp:include page="movimientos.jsp" flush="true" />
        </div>
        <div id="transferencias" class="tabcontent">  
            <jsp:include page="transferencias.jsp" flush="true" />
        </div>
        <div id="buscar" class="tabcontent">
          <jsp:include page="buscar.jsp" flush="true" />
        </div> 
    </body>
</html>
