<%-- 
    Document   : login
    Created on : 15/10/2017, 22:32:48
    Author     : VashJHK
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login ControlChef</title>
    </head>
    <body>
        <form action="/ControlChef/Controller?classe=ControllerLogicFuncionario&metodo=autenticar" method="post">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2">TELA DE LOGIN</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Nome</td>
                        <td><input type="text" name="name" value="" size="20" /></td>
                    </tr>
                    <tr>
                        <td>Senha</td>
                        <td> <input type="password" name="password" value="" size="20" /> </td>
                    </tr>
                </tbody>
            </table>
            <input type="submit" value="Logar" name="logar" />
        </form>
    </body>
</html>
