/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

/**
 *
 * @author VashJHK
 */
 
import br.com.controlchefweb.dao.FuncionarioDAO;
import br.com.controlchefweb.entidade.Funcionario;
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
import org.primefaces.event.CellEditEvent;
import org.primefaces.event.RowEditEvent;

 
@ManagedBean(name="dtEditViewF")
@ViewScoped
public class EditViewF implements Serializable {
    
    private static final long serialVersionUID = 1L;
     
    private List<Funcionario> func;
    
    @ManagedProperty("#{FuncionarioDAO}")
    private FuncionarioDAO funcionario;
     
    @PostConstruct
    public void init() {
        try {
            func = funcionario.buscar();
        } catch (ErroSistema ex) {
            Logger.getLogger(EditViewF.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<Funcionario> getFunc() {
        return func;
    }

    public void setFunc(List<Funcionario> func) {
        this.func = func;
    }

    public FuncionarioDAO getFuncionario() {
        return funcionario;
    }

    public void setFuncionario(FuncionarioDAO funcionario) {
        this.funcionario = funcionario;
    }
 
   public List<String> getTipos() {
       return funcionario.getTipos();
   }
     
    public void onRowEdit(RowEditEvent event) throws ErroSistema {
        funcionario.salvar((Funcionario) event.getObject());
        FacesMessage msg = new FacesMessage("Sucessso!","Funcionário Editado com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
     
    public void onRowCancel(RowEditEvent event) {
        FacesMessage msg = new FacesMessage("Cancelado","Edição cancelada com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
     public String onRowDelete(Funcionario fun) throws ErroSistema {
        funcionario.deletar(fun);
        FacesMessage msg = new FacesMessage("Sucessso!","Funcionário foi removido com sucesso.");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "gerenciar-funcionario.jsf";
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
