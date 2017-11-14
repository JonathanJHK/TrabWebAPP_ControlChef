/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.entidade;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author VashJHK
 */
public class ItemPedido implements Serializable {
    private Integer id;
    private Integer quantidade;
    private Produto produto;
    private boolean statusItem;
    private double valorItem;
    private int mesa;
    private boolean entrega;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public boolean isStatusItem() {
        return statusItem;
    }

    public void setStatusItem(boolean statusItem) {
        this.statusItem = statusItem;
    }

    public double getValorItem() {
        this.valorItem = this.produto.getPreco()*this.quantidade;
        return valorItem;
    }

    public void setValorItem(double valorItem) {
        this.valorItem = valorItem;
    }

    public int getMesa() {
        return mesa;
    }

    public void setMesa(int mesa) {
        this.mesa = mesa;
    }

    public boolean isEntrega() {
        return entrega;
    }

    public void setEntrega(boolean entrega) {
        this.entrega = entrega;
    }

    
    
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ItemPedido other = (ItemPedido) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    
    
}
