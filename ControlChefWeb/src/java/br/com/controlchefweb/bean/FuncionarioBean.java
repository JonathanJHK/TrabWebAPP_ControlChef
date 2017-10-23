package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.FuncionarioDAO;
import br.com.controlchefweb.entidade.Funcionario;
import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean (name="funcionarioBean")
@ViewScoped
public class FuncionarioBean extends CrudBean<Funcionario, FuncionarioDAO> implements Serializable{

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
