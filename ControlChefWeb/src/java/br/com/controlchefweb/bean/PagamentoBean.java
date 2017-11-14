/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.ClienteDAO;
import br.com.controlchefweb.dao.ItemPedidoDAO;
import br.com.controlchefweb.dao.PagamentoDAO;
import br.com.controlchefweb.dao.PedidoDAO;
import br.com.controlchefweb.entidade.Cliente;
import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Pagamento;
import br.com.controlchefweb.entidade.Pedido;
import br.com.controlchefweb.util.SessionContext;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author VashJHK
 */
@ManagedBean(name = "pagamentoBean")
@SessionScoped
public class PagamentoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int step;
    private Pedido pedidoSelecionado;
    private Cliente clienteSelecionado;
    private Pagamento pagamento;

    private ClienteDAO clien;
    private PedidoDAO ped;
    private ItemPedidoDAO itemp;
    private PagamentoDAO paga;

    private String nome;

    private List<Cliente> clientesFiltrados;

    @PostConstruct
    public void init() {
        try {
            ped = new PedidoDAO();
            itemp = new ItemPedidoDAO();
            paga = new PagamentoDAO();
            clien = new ClienteDAO();
            pedidoSelecionado = new Pedido();
            clienteSelecionado = new Cliente();
            pagamento = new Pagamento();
            clientesFiltrados = new ArrayList();
            step = 1;
            pagamento.setTaxa(paga.getarTaxa());
             pagamento.setDesconto(paga.getarDesconto());
        } catch (ErroSistema ex) {
            Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public PedidoDAO getPed() {
        return ped;
    }

    public void setPed(PedidoDAO ped) {
        this.ped = ped;
    }

    public ItemPedidoDAO getItemp() {
        return itemp;
    }

    public void setItemp(ItemPedidoDAO itemp) {
        this.itemp = itemp;
    }

    public ClienteDAO getClien() {
        return clien;
    }

    public void setClien(ClienteDAO clien) {
        this.clien = clien;
    }

    public PagamentoDAO getPaga() {
        return paga;
    }

    public void setPaga(PagamentoDAO paga) {
        this.paga = paga;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public List<Cliente> getClientesFiltrados() {
        return clientesFiltrados;
    }

    public void setClientesFiltrados(List<Cliente> clientesFiltrados) {
        this.clientesFiltrados = clientesFiltrados;
    }

    public Cliente getClienteSelecionado() {
        return clienteSelecionado;
    }

    public void setClienteSelecionado(Cliente clienteSelecionado) {
        this.clienteSelecionado = clienteSelecionado;
    }

    public Pagamento getPagamento() {
        return pagamento;
    }

    public void setPagamento(Pagamento pagamento) {
        this.pagamento = pagamento;
    }
    
    public void pesquisar() throws ErroSistema {
        clientesFiltrados = clien.buscarNome(nome);
    }

    public void abrirDialogo() {
        Map<String, Object> opcoes = new HashMap<>();
        opcoes.put("modal", true);
        opcoes.put("resizable", false);
        opcoes.put("contentHeight", 470);

        RequestContext.getCurrentInstance().openDialog("SelecaoCliente", opcoes, null);
    }

    public void selecionar(Cliente cliente) {
        RequestContext.getCurrentInstance().closeDialog(cliente);
    }

    public void clienteSeleciona(SelectEvent event) {
        clienteSelecionado = (Cliente) event.getObject();
        adicionarMensagem("Sucesso", "Cliente selecionado com sucesso");
    }
    
    public void clienteCancelar(){
        clienteSelecionado = new Cliente();
        pagamento.setCliente(clienteSelecionado);
        adicionarMensagem("Sucesso", "Cliente removido com sucesso.");
    }

    public void stepPedido(int mesa) {
        try {
            step++;
            pedidoSelecionado = ped.buscarPedido(mesa);
            pedidoSelecionado.setItens(ped.buscarProdutoPedido(pedidoSelecionado.getId()));
            pedidoSelecionado.setVendedor(SessionContext.getInstance().getUsuarioLogado());
            double valorT = 0;

            for (ItemPedido s : pedidoSelecionado.getItens()) {
                valorT += s.getValorItem();
            }
            pedidoSelecionado.setValorTotal(valorT);
            pagamento.setValorT(valorT + pagamento.getTaxa());
            
            
           

        } catch (ErroSistema ex) {
            Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void stepPagamento() throws ErroSistema{
        step++;
        pagamento.setCliente(clienteSelecionado);
        double desconto = calcularDesconto();
        
        pagamento.setValorDesconto(desconto);
        pagamento.setValorT(pedidoSelecionado.getValorTotal() + pagamento.getTaxa() - desconto);
        
        
    }
    
    public void stepFinalizar(){
        switch(pagamento.getFormaPagamento()){
            case "Dinheiro":
                pagamento.setTroco(pagamento.getValorPagamento() - pagamento.getValorT());
                break;
            case "Cartão":
                pagamento.setValorPagamento(pagamento.getValorT());
                pagamento.setTroco(0);
                break;
            case "Cheque":
                pagamento.setTroco(pagamento.getValorPagamento() - pagamento.getValorT());
                break;
        }
        if(pagamento.getValorPagamento() < pagamento.getValorT()){
            adicionarMensagem("Falha", "O valor pago é menor que o exigido.");
            return;
        }else{
            try {
                step++;
                pedidoSelecionado.setStatusPedido(false);
                ped.salvar(pedidoSelecionado);
                paga.criar(pedidoSelecionado, pagamento);
                adicionarMensagem("Sucesso", "O pagamento é finalizado com sucesso.");
            } catch (ErroSistema ex) {
                Logger.getLogger(PagamentoBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        
        
        
        
        
    }
    
    public double calcularDesconto() throws ErroSistema{
        double desconto = 0;
        int porcent = paga.getarDesconto();
        if(pagamento.getCliente().getCpf() != null){
            desconto = (porcent*0.01)*pedidoSelecionado.getValorTotal();
            pagamento.setDesconto(porcent);
        }else{
            pagamento.setDesconto(0);
        }
        
        return desconto;
    }

    public void cancelar() {
        step--;
    }
    public void confirmar(){
        step=1;
    }
    
    public void adicionarMensagem(String mensagem, String mensagem2) {
        FacesMessage fm = new FacesMessage(mensagem, mensagem2);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

}
