<jsp:include page="/header.jsp" />
  
<%--
<jsp:useBean id="pessoa" scope="session" class="br.com.senacrs.bean.Pessoa" />
--%>
<form name="formulario" action="/ControlChef/Controller?classe=ControllerLogicFuncionario&metodo=editar" method="POST">
            <table border="0">
                <thead>
                    <tr>
                        <th> Alteração no cadastro de Funcionario ID ${param.id}</th>                        
                    </tr>
                </thead>
                <tbody>
                      <tr>
                        <td>ID: </td>
                        <td> <input type="text" readonly="" name="id" value="${funcionario.id}" size="30" /> </td>
                    </tr>
                    <tr>
                        <td>Login: </td>
                        <td> <input type="text" name="login" value="${funcionario.nome}" size="30" /> </td>
                    </tr>
                    <tr>
                        <td> Senha: </td>
                        <td> <input type="text" name="senha" value="${funcionario.senha}" size="30" /> </td>
                    </tr>
                    <tr>
                        <td> <input type="submit" value="Salvar" name="enviar" /> </td>
                        <td> <input type="reset" value="Limpar" name="limpar" /> </td>
                    </tr>
                </tbody>
            </table>            
            
        </form>

<jsp:include page="/footer.jsp" />