package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.CrudDAO;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public abstract class CrudBean<E, D extends CrudDAO> {

    private String estadoTela = "buscar";//Inserir, Editar, Buscar
    
    E entidade;
    private List<E> entidades;
    
    @PostConstruct
    public void CrudBean(){
        entidade =  criarNovaEntidade();
        mudarParaBusca();
    }
    
    public void salvar(){
        try {
            if(verificaEntidade()){
                 getDao().salvar(entidade);
                 entidade = criarNovaEntidade();
                 adicionarMensagem("Sucesso!","Salvo com sucesso!",FacesMessage.SEVERITY_INFO);
                 mudarParaBusca();
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem("Atenção",ex.getMessage(),FacesMessage.SEVERITY_FATAL);
        }
    }
    public void editar(E entidade){
        this.entidade = entidade;
        mudarParaEdita();
    }
    public void deletar(E entidade){
        try {
            getDao().deletar(entidade);
            entidades.remove(entidade);
            adicionarMensagem("Sucesso!","Deletado com sucesso!",FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem("Atenção",ex.getMessage(),FacesMessage.SEVERITY_FATAL);
        }
    }
    public void buscar(){
        if(isBusca() == false){
           mudarParaBusca();
           return;
        }
        try {
            entidades = getDao().buscar();
            if(entidades == null || entidades.size() < 1){
                adicionarMensagem("Atenção","Não temos nada cadastrado!",FacesMessage.SEVERITY_WARN);
            }
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem("Atenção",ex.getMessage(),FacesMessage.SEVERITY_FATAL);
        }
    }
    
    
    public void adicionarMensagem(String mensagem, String mensagem2,FacesMessage.Severity tipoErro){
        FacesMessage fm = new FacesMessage(tipoErro,mensagem, mensagem2);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }
    
    //getters e setters
    public E getEntidade() {
        return entidade;
    }

    public void setEntidade(E entidade) {
        this.entidade = entidade;
    }

    public List<E> getEntidades() {
        return entidades;
    }

    public void setEntidades(List<E> entidades) {
        this.entidades = entidades;
    }
    
    //Responsvel por criar os métodos nas classes bean
    public abstract D getDao();
    public abstract E criarNovaEntidade();
    public abstract boolean verificaEntidade();
    
    //Metodos para controle da tela
    public boolean isInseri(){
        return "inserir".equals(estadoTela);
    }
    public boolean isEdita(){
        return "editar".equals(estadoTela);
    }
    public boolean isBusca(){
        return "buscar".equals(estadoTela);
    }
    public void mudarParaInseri(){
        estadoTela = "inserir";
    }
    public void mudarParaEdita(){
        estadoTela = "editar";
    }
    public void mudarParaBusca(){
        estadoTela = "buscar";
    }
    
}
