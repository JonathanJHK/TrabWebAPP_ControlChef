/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.ClienteDAO;
import br.com.controlchefweb.entidade.Cliente;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VashJHK
 */
@ManagedBean(name = "clienteBean")
@ViewScoped
public class ClienteBean extends CrudBean<Cliente, ClienteDAO> implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private ClienteDAO clienteDAO;
    private List<Cliente> clientes;
    
    
    
    public ClienteBean(){
        try {
            clientes = getDao().buscar();
        } catch (ErroSistema ex) {
            Logger.getLogger(ClienteBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(List<Cliente> clientes) {
        this.clientes = clientes;
    }

    
    
    @Override
    public ClienteDAO getDao() {
       if (clienteDAO == null) {
            clienteDAO = new ClienteDAO();
        }
        return clienteDAO;
    }

    @Override
    public Cliente criarNovaEntidade() {
       return new Cliente();
    }

    @Override
    public boolean verificaEntidade() {
        try {
            Cliente clien = getDao().buscarCPF(entidade.getCpf());
            if (clien != null) {
                adicionarMensagem("Atenção", "Cliente de mesmo cpf já está cadastrado.",FacesMessage.SEVERITY_WARN);
                return false;
            }
            return true;
        } catch (ErroSistema ex) {
            Logger.getLogger(FuncionarioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public void onRowEdit(RowEditEvent event) throws ErroSistema {
        getDao().update((Cliente) event.getObject());
        FacesMessage msg = new FacesMessage("Sucessso!","Cliente Editado com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado","Edição cancelada com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public void onRowDelete(Cliente clien) throws ErroSistema {
        getDao().deletar(clien);
        FacesMessage msg = new FacesMessage("Sucessso!","Cliente foi removido com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
}
