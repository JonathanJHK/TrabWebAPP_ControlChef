/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.ItemPedidoDAO;
import br.com.controlchefweb.entidade.ItemPedido;
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

/**
 *
 * @author VashJHK
 */
@ManagedBean(name="itemPedidoBean")
@ViewScoped
public class ItemPedidoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private List<ItemPedido> itens;
    
    private List<ItemPedido> itensPronto;
    
     @ManagedProperty("#{ItemPedidoDAO}")
     private ItemPedidoDAO itempDao;
    
    
    @PostConstruct
    public void init() {
        try {
            itempDao = new ItemPedidoDAO();
            itens = itempDao.buscar();
            itensPronto = itempDao.buscarPronto();
        } catch (ErroSistema ex) {
            Logger.getLogger(EditViewF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<ItemPedido> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedido> itens) {
        this.itens = itens;
    }

    public ItemPedidoDAO getItempDao() {
        return itempDao;
    }

    public void setItempDao(ItemPedidoDAO itempDao) {
        this.itempDao = itempDao;
    }

    public List<ItemPedido> getItensPronto() {
        return itensPronto;
    }

    public void setItensPronto(List<ItemPedido> itensPronto) {
        this.itensPronto = itensPronto;
    }
    
    
    public String itemPronto(ItemPedido item) throws ErroSistema{
        item.setStatusItem(true);
        itempDao.salvar(item);
        adicionarMensagem("Sucesso", "Item pronto para ser entregue",FacesMessage.SEVERITY_INFO);
        return "pedidos-pendente.jsf";
    }
    
    public String itemEntregue(ItemPedido item) throws ErroSistema{
        item.setEntrega(true);
        itempDao.salvar(item);
        adicionarMensagem("Sucesso", "Item entregue",FacesMessage.SEVERITY_INFO);
        return "pedidos-pronto.jsf";
    }
    
    public void adicionarMensagem(String mensagem, String mensagem2,FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro,mensagem, mensagem2);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    
    
}
