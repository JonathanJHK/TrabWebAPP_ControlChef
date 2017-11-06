/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

/**
 *
 * @author 201519060360
 */
import br.com.controlchefweb.dao.FuncionarioDAO;
import br.com.controlchefweb.entidade.Funcionario;

import br.com.controlchefweb.util.SessionContext;
import br.com.controlchefweb.util.exception.ErroSistema;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


/**
 * Controla o LOGIN e LOGOUT do Usuário
 *
 */
@ManagedBean(name = "usuarioLogaBean")
@SessionScoped
public class UsuarioLogaBean implements Serializable {

    private static final long serialVersionUID = 1L;
    

    @ManagedProperty("#{FuncionarioDAO}")
    private FuncionarioDAO func;

    private String login;
    private String senha;

    public FuncionarioDAO getFunc() {
        return func;
    }

    public void setFunc(FuncionarioDAO func) {
        this.func = func;
    }
    
    
    public Funcionario gUser() {
        return (Funcionario) SessionContext.getInstance().getUsuarioLogado();
    }

    public String doLogin() {
        try {
           
            Funcionario user = func.buscarAunt(login, senha);
            
            if (user == null) {
                FacesMessage msg = new FacesMessage("Login ou Senha errado, tente novamente!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                FacesContext.getCurrentInstance().validationFailed();
                return "";
            }else{
                SessionContext.getInstance().setAttribute("usuarioLogado", user);
                FacesMessage msg = new FacesMessage("Login efetuado com sucesso!");
                FacesContext.getCurrentInstance().addMessage(null, msg);
               
            }
            
           
            return "/view/main.jsf?faces-redirect=true";

        } catch (ErroSistema e) {
            FacesContext.getCurrentInstance().validationFailed();
            return "";
        }

    }

    public String doLogout() {
        SessionContext.getInstance().encerrarSessao();
        FacesMessage msg = new FacesMessage("Logout realizado com sucesso !");
        FacesContext.getCurrentInstance().addMessage(null, msg);
        return "/index.jsf?faces-redirect=true";
    }
    /*
    public void solicitarNovaSenha() {
        try {
            getUserBO().gerarNovaSenha(login, email);
            addInfoMessage("Nova Senha enviada para o email " + email);
        } catch (BOException e) {
            addErrorMessage(e.getMessage());
            FacesContext.getCurrentInstance().validationFailed();
        }
    }*/



    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

}
