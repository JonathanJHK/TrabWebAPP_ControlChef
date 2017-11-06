/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.entidade.Produto;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author VashJHK
 */

@ManagedBean(name = "listaProdutoView")
@ViewScoped
public class ListaProdutoView implements Serializable{
     private static final long serialVersionUID = 1L;
    
    public void chooseProd() {
        Map<String,Object> options = new HashMap();
        options.put("resizable", false);
        options.put("modal", true);
        options.put("contentWidth",1000);
        RequestContext.getCurrentInstance().openDialog("listaPedido", options, null);
    }
     
    public void onProdChosen(SelectEvent event) {
        Produto prod = (Produto) event.getObject();
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Produto selecionado", "Id:" + prod.getId());    
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}
