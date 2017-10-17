<%-- 
    Document   : principal
    Created on : 15/10/2017, 22:44:08
    Author     : VashJHK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2> <a href="/ControlChef/ControllerRedirect?page=cadFuncionario">Cadastrar</a> </h2>
        <h2> <a href="/ControlChef/ControllerRedirect?page=editFuncionario">Editar</a> </h2>
        <h2> <a href="/ControlChef/Controller?classe=ControllerLogicFuncionario&metodo=listar">Listagem</a> </h2>

    </body>
</html>
