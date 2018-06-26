<%-- 
    Document   : operacionCompletada
    Created on : 24-may-2018, 23:25:16
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
         h1{
            margin-top: 100px;
            text-align: center;
            text-color: #0062ff;
        }
        h2{
            text-align: center;
            text-color: #0062ff;
        }
        h3{
            margin-top: 10px;
         text-align: center;
         text-color: #0062ff;
}
        </style>
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Operacion completada con Exito</h1>
        </br>
        <%
        Integer pagRetorno=(Integer)request.getAttribute("pagRetorno");
        Integer nCuenta=(Integer)request.getAttribute("cuenta");
        
        if(pagRetorno==0){
        %>
               
        <form name="frm" action="empleado.jsp" method="post"> <input type="hidden" name="insert" value="openTab('click','operaciones')"></form> 
                
                 <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()" >Continuar</a></h3>
                 
                 <%
                     }else if(pagRetorno==1){
        %>
            <h2>Su numero de cuenta es: <%=nCuenta.toString()%> </h2>
          
         <form name="frm" action="empleado.jsp" method="post">  <input type="hidden" name="insert" value="openTab('click','nuevoUsuario')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()">Continuar</a>
                 </h3>
         <%
          }else if(pagRetorno==2){
            %>
            <form name="frm" action="empleado.jsp" method="post"> <input type="hidden" name="insert" value="openTab('click','editarUsuario')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()"> Continuar </a>
                </h3>
            <%
                }else if(pagRetorno==3){
            %>
             <form name="frm" action="empleado.jsp" method="post"> <input type="hidden" name="insert" value="openTab('click','editarOperaciones')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()"> Continuar</a>
                </h3>
           <%

                }else if(pagRetorno==4){
                    %>
    <form name="frm" action="UsuarioServlet" method="post"> <input type="hidden" name="insert" value="openTab('click','transferencias')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()">Continuar</a>
                </h3>
            <%

                }else if(pagRetorno==5){
                    %>
    <form name="frm" action="UsuarioServlet" method="post"> <input type="hidden" name="insert" value="openTab('click','buscar')"></form> 
             <h3><a class="btn btn-primary"  href="#" onclick="document.frm.submit()">Continuar</a>
                </h3>
            <%
                }
        %>
    
    </body>
</html>