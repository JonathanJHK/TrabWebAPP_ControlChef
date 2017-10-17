package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.FuncionarioDAO;
import br.com.controlchefweb.entidade.Funcionario;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FuncionarioBean extends CrudBean<Funcionario, FuncionarioDAO>{

    private FuncionarioDAO usuarioDAO;
    
    @Override
    public FuncionarioDAO getDao() {
        if(usuarioDAO == null){
            usuarioDAO = new FuncionarioDAO();
        }
        return usuarioDAO;
    }

    @Override
    public Funcionario criarNovaEntidade() {
        return new Funcionario();
    }
    
}
