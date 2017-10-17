package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.ProdutoDAO;
import br.com.controlchefweb.entidade.Produto;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class ProdutoBean extends CrudBean<Produto, ProdutoDAO> {

    private ProdutoDAO ProdutoDAO;
    
    @Override
    public ProdutoDAO getDao() {
        if(ProdutoDAO == null){
            ProdutoDAO = new ProdutoDAO();
        }
        return ProdutoDAO;
    }

    @Override
    public Produto criarNovaEntidade() {
        return new Produto();
    }

}