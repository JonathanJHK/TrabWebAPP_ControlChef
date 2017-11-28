package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.FuncionarioDAO;
import br.com.controlchefweb.entidade.Funcionario;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean(name = "funcionarioBean")
@ViewScoped
public class FuncionarioBean extends CrudBean<Funcionario, FuncionarioDAO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private FuncionarioDAO usuarioDAO;

    @Override
    public FuncionarioDAO getDao() {
        if (usuarioDAO == null) {
            usuarioDAO = new FuncionarioDAO();
        }
        return usuarioDAO;
    }

    @Override
    public Funcionario criarNovaEntidade() {
        return new Funcionario();
    }

    @Override
    public boolean verificaEntidade() {
       
        try {
            Funcionario func = getDao().buscarLogin(entidade.getLogin());
            if (func != null) {
                adicionarMensagem("Atenção", "Funcionário de mesmo login já existe no sistema.",FacesMessage.SEVERITY_WARN);
                return false;
            }
            return true;
        } catch (ErroSistema ex) {
            Logger.getLogger(FuncionarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
       
    }

}
