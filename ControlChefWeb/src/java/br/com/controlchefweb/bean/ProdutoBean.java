package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.ProdutoDAO;
import br.com.controlchefweb.entidade.Produto;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

@ManagedBean
@ViewScoped
public class ProdutoBean extends CrudBean<Produto, ProdutoDAO> implements Serializable {

    private static final long serialVersionUID = 1L;

    private ProdutoDAO produtoDAO;

    @Override
    public ProdutoDAO getDao() {
        if (produtoDAO == null) {
            produtoDAO = new ProdutoDAO();
        }
        return produtoDAO;
    }

    @Override
    public Produto criarNovaEntidade() {
        return new Produto();
    }

    @Override
    public boolean verificaEntidade() {
        try {
            if (getDao().verificaProd(entidade.getNome())) {
                adicionarMensagem("Atenção", "Produto de mesmo nome já está cadastrado.",FacesMessage.SEVERITY_WARN);
                return false;
            }
            return true;
        } catch (ErroSistema ex) {
            Logger.getLogger(ProdutoBean.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }

}
