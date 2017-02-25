<%-- 
    Document   : index
    Created on : 25-feb-2017, 17:45:41
    Author     : EdHam
--%>

<%@page import="entidades.clsClienteN"%>
<%@page import="java.util.List"%>
<%@page import="com.clsReglasNegocio"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        
     <%
        for(clsClienteN entidad :clsReglasNegocio.ListarCliente(""))
            out.append(entidad.getNom_cli());
            
         
    %>
    </body>
</html>
