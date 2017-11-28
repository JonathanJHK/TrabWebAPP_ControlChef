/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.PagamentoDAO;
import br.com.controlchefweb.entidade.Servico;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.RowEditEvent;

/**
 *
 * @author VashJHK
 */
@ManagedBean(name="servicoBean")
@ViewScoped
public class ServicoBean implements Serializable{
    
    private static final long serialVersionUID = 1L;
    private List<Servico> serv;
    
    private PagamentoDAO pagaDao;
    
    
    @PostConstruct
    public void init() {
        try {
            pagaDao = new PagamentoDAO();
            Servico ser = new Servico();
            ser.setTaxa(pagaDao.getarTaxa());
            ser.setDesconto(pagaDao.getarDesconto());
            serv = new ArrayList();
            serv.add(ser);
            
        } catch (ErroSistema ex) {
            Logger.getLogger(EditViewF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Servico> getServ() {
        return serv;
    }

    public void setServ(List<Servico> serv) {
        this.serv = serv;
    }

    public PagamentoDAO getPagaDao() {
        return pagaDao;
    }

    public void setPagaDao(PagamentoDAO pagaDao) {
        this.pagaDao = pagaDao;
    }
    
       
    public void onRowEdit(RowEditEvent event) throws ErroSistema {
        pagaDao.updateTaxaDesconto((Servico) event.getObject());
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Sucessso!","Serviços Editados com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO,"Cancelado","Edição cancelada com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
    
}
