<jsp:include page="/header.jsp"/>
        <form name="formulario" action="/ControlChef/Controller?classe=ControllerLogicFuncionario&metodo=inserir" method="POST">
            <table border="1">
                <thead>
                    <tr>
                        <th colspan="2"> Cadastro de Funcionário </th>                        
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td>Login: </td>
                        <td> <input type="text" name="login" value="" size="30" /> </td>
                    </tr>
                    <tr>
                        <td> Senha: </td>
                        <td> <input type="password" name="senha" value="" size="30" /> </td>
                    </tr>
                    <tr>
                        <td> <input type="submit" value="Enviar" name="enviar" /> </td>
                        <td> <input type="reset" value="Limpar" name="limpar" /> </td>
                    </tr>
                </tbody>
            </table>
           
        </form>
<jsp:include page="/footer.jsp" />