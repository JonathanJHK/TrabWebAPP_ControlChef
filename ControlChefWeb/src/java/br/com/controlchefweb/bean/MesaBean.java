/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.MesaDAO;
import br.com.controlchefweb.entidade.Mesa;
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
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VashJHK
 */
@ManagedBean(name="mesaBean")
@ViewScoped
public class MesaBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private List<Mesa> men;
    private boolean telaBuscar = true;
    private Mesa mesa;
    
    @ManagedProperty("#{MesaDAO}")
     private MesaDAO mesaDao;
    
    
    @PostConstruct
    public void init() {
        try {
            mesa = new Mesa();
            men = mesaDao.buscar();
        } catch (ErroSistema ex) {
            Logger.getLogger(EditViewF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Mesa> getMen() {
        return men;
    }

    public void setMen(List<Mesa> men) {
        this.men = men;
    }

    public MesaDAO getMesaDao() {
        return mesaDao;
    }

    public void setMesaDao(MesaDAO mesaDao) {
        this.mesaDao = mesaDao;
    }

    public boolean isTelaBuscar() {
        return telaBuscar;
    }

    public void setTelaBuscar(boolean telaBuscar) {
        this.telaBuscar = telaBuscar;
    }

    public Mesa getMesa() {
        return mesa;
    }

    public void setMesa(Mesa mesa) {
        this.mesa = mesa;
    }
    
    public void salvar(){
        try {
            mesaDao.salvar(mesa);
            mesa = new Mesa();
            adicionarMensagem("Sucesso!","Salvo com sucesso.",FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem("Atenção",ex.getMessage(),FacesMessage.SEVERITY_ERROR);
        }
    }
    
    public void onRowEdit(RowEditEvent event) throws ErroSistema {
        mesaDao.update((Mesa) event.getObject());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucessso!","Mesa Editado com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cancelado","Edição cancelada com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public void onRowDelete(Mesa fun) throws ErroSistema {
        mesaDao.deletar(fun);
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucessso!","Mesa foi removido com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    public void telaBusca(){
        this.telaBuscar=true;
    }
    
    public void telaNovo(){
        this.telaBuscar=false;;
    }
    
     public void adicionarMensagem(String mensagem, String mensagem2,FacesMessage.Severity tipoErro){
        FacesMessage fm = new FacesMessage(tipoErro,mensagem, mensagem2);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    
}
