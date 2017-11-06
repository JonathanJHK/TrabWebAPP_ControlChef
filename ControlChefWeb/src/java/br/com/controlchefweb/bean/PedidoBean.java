/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.entidade.Produto;
import java.io.Serializable;
import java.util.ArrayList;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author VashJHK
 */
@ManagedBean(name="pedidoBean")
@ViewScoped
public class PedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private Produto itemSelecionado = new Produto();
    private ArrayList<Produto> listaItens  = new ArrayList();

    public Produto getItemSelecionado() {
        return itemSelecionado;
    }

    public void setItemSelecionado(Produto itemSelecionado) {
        this.itemSelecionado = itemSelecionado;
    }

    public ArrayList<Produto> getListaItens() {
        return listaItens;
    }

    public void setListaItens(ArrayList<Produto> listaItens) {
        this.listaItens = listaItens;
    }
    
    
    
    @PostConstruct
    public void init() {
      
    }

    public void novoItem(Produto produto) {
        listaItens.add(produto);
       print();
    }
    
    public void print(){
        for(Produto s:listaItens){
            System.out.println(s.getNome());
        }
    }

}
