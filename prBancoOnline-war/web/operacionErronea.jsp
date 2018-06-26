<%-- 
    Document   : operacionErronea
    Created on : 25-may-2018, 19:24:42
    Author     : Hector
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
         <link rel="stylesheet" type="text/css" href="stylesheet.css">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <script type="text/javascript" src="tabs.js"></script>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style type="text/css">
        body{
        background: #91b6f2;
        }
        h3{
         margin-top: 30px;
         text-align: center;
}
h2{
    margin-top: 100px;
    text-align: center;
    text-color: #0062ff;
}
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <%            
           String msg=(String)request.getAttribute("msgError");
            %>
        <h2>Error! <%=msg%></h2>
        <%
        Integer pagRetorno=(Integer)request.getAttribute("pagRetorno");
        if(pagRetorno==0){
        %>
               
        <form name="frm" action="empleado.jsp" method="post"> <input type="hidden" name="insert" value="openTab('click','operaciones')"></form> 
                
                 <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()" >Volver</a></h3>
                 
            <%
               }else if(pagRetorno==1){
            %>
            <form name="frm" action="empleado.jsp" method="post">  <input type="hidden" name="insert" value="openTab('click','nuevoUsuario')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()">Volver</a>
                 </h3>
            <%
                }else if(pagRetorno==2){
            %>
            <form name="frm" action="empleado.jsp" method="post"> <input type="hidden" name="insert" value="openTab('click','editarUsuario')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()"> Volver </a>
                </h3>
            <%
                }else if(pagRetorno==3){
            %>
             <form name="frm" action="empleado.jsp" method="post"> <input type="hidden" name="insert" value="openTab('click','editarOperaciones')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()"> Volver</a>
                </h3>
            <%
                }else if(pagRetorno==4){
           %>
    <form name="frm" action="UsuarioServlet" method="post"> <input type="hidden" name="insert" value="openTab('click','transferencias')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()"> Volver</a>
                </h3>
            <%
                } else if(pagRetorno==5) {
        %>
    <form name="frm" action="UsuarioServlet" method="post"> <input type="hidden" name="insert" value="openTab('click','buscar')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()"> Volver</a>
                </h3>
    <%
        }
    %>
    </body>
</html>
