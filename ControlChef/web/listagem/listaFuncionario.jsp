<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<jsp:include page="/header.jsp"/>
<%--
<jsp:useBean id="pessoa" scope="session" class="br.com.senacrs.bean.Pessoa"/>
--%>


<table border="1">
    <thead>
        <tr>
            <th>ID</th>
            <th>Login</th>
            <th>Senha</th>
            <th colspan="2">Ações</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${funcionario}" var="funcionario">
            
            <tr>
                <td>${funcionario.id}</td>
                
                <td>${funcionario.login}</td>
                <td>${funcionario.senha}</td>
                <td> <a href="Controller?classe=ControllerLogicFuncionario&metodo=editarPopular&id=${funcionario.id}">Editar</a> </td>
                <td> <a href="Controller?classe=ControllerLogicFuncionario&metodo=remover&id=${funcionario.id}">Excluir</a> </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
<jsp:include page="/footer.jsp"/>
