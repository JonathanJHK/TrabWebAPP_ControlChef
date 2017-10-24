package br.com.controlchefweb.autentica;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.controlchefweb.util.SessionUtil;

@RequestScoped 
@ManagedBean (name="autenticadorBean")
public class AutenticadorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	private String login;
	private String senha;

	public String autentica() {
		System.out.println("autentica..");
			
		if (login.equals("admin")&&senha.equals("admin")) {
			System.out.println("Confirmou  usuario e senha ...");		
			
			//ADD USUARIO NA SESSION
			
			Object b = new Object();
			
			SessionUtil.setParam("USUARIOLogado", b);
			
		return "/ControlChefWeb/main.xhtml?faces-redirect=true";

		} else {
			
			return null;

		}

	}

	/**
	 * M�todo que efetua o logout
	 * 
	 * @return
	 */
	public String registraSaida() {

		//REMOVER USUARIO DA SESSION
		
		
		return "/Login?faces-redirect=true";
	}

	// GETTERS E SETTERS


	public String getSenha() {
		return senha;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

}
