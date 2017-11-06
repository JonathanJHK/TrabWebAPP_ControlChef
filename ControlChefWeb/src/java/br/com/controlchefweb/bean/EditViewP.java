/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.ProdutoDAO;
import br.com.controlchefweb.entidade.Produto;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author 201519060360
 */

@ManagedBean(name="dtEditViewP")
@ViewScoped
public class EditViewP implements Serializable{
     private List<Produto> prod;
    
     @ManagedProperty("#{ProdutoDAO}")
     private ProdutoDAO produto;
     
     @PostConstruct
    public void init() {
        try {
            prod = produto.buscar();
        } catch (ErroSistema ex) {
            Logger.getLogger(EditViewF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Produto> getProd() {
        return prod;
    }

    public void setProd(List<Produto> prod) {
        this.prod = prod;
    }

    public ProdutoDAO getProduto() {
        return produto;
    }

    public void setProduto(ProdutoDAO produto) {
        this.produto = produto;
    }
    
    public void selectProdFromDialog(Produto prod) {
        RequestContext.getCurrentInstance().closeDialog(prod);
    }
    
    public List<String> getTipos() {
       return produto.getTipos();
   }
     
     public void onRowEdit(RowEditEvent event) throws ErroSistema {
        produto.salvar((Produto) event.getObject());
        FacesMessage msg = new FacesMessage("Sucessso!","Produto Editado com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado!","Edição cancelada com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public String onRowDelete(Produto pro) throws ErroSistema {
        produto.deletar(pro);
        FacesMessage msg = new FacesMessage("Sucessso!","Produto foi removido com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "gerenciar-produto.jsf";
    }
     
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();
         
        if(newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }
}
