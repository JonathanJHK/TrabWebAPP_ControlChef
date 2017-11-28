/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.bean;

import br.com.controlchefweb.dao.FuncionarioDAO;
import br.com.controlchefweb.dao.ItemPedidoDAO;
import br.com.controlchefweb.dao.PagamentoDAO;
import br.com.controlchefweb.dao.PedidoDAO;
import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Pedido;
import br.com.controlchefweb.entidade.Produto;
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

/**
 *
 * @author VashJHK
 */
@ManagedBean(name = "pedidoBean")
@ViewScoped
public class PedidoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private int quant = 1;
    private Pedido pedidoSelecionado;

    private PedidoDAO ped;
    private ItemPedidoDAO itemp;
    private FuncionarioDAO funcDao;
    private PagamentoDAO pagaDao;

    private List<Pedido> pedidoL;

    @PostConstruct
    public void init() {
        try {
            ped = new PedidoDAO();
            itemp = new ItemPedidoDAO();
            funcDao = new FuncionarioDAO();
            pagaDao = new PagamentoDAO();
            pedidoL = ped.buscar();
            pedidoSelecionado = new Pedido();
        } catch (ErroSistema ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
        }
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

    public int getQuant() {
        return quant;
    }

    public void setQuant(int quant) {
        this.quant = quant;
    }

    public List<Pedido> getPedidoL() {
        return pedidoL;
    }

    public void setPedidoL(List<Pedido> pedidoL) {
        this.pedidoL = pedidoL;
    }

    public FuncionarioDAO getFuncDao() {
        return funcDao;
    }

    public void setFuncDao(FuncionarioDAO funcDao) {
        this.funcDao = funcDao;
    }

    public PagamentoDAO getPagaDao() {
        return pagaDao;
    }

    public void setPagaDao(PagamentoDAO pagaDao) {
        this.pagaDao = pagaDao;
    }

    public Pedido getPedidoSelecionado() {
        return pedidoSelecionado;
    }

    public void setPedidoSelecionado(Pedido pedidoSelecionado) {
        this.pedidoSelecionado = pedidoSelecionado;
    }

    public void criarPedido(int mesa) throws ErroSistema {
        try {
            ped.criar(mesa);
            adicionarMensagem("Sucesso", "Pedido foi aberto com sucesso!",FacesMessage.SEVERITY_INFO);
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem("Atenção", ex.getMessage(),FacesMessage.SEVERITY_ERROR);
        }
    }

    public boolean verifPedido(int mesa) throws ErroSistema {
        return ped.buscarPedido(mesa) != null;
    }

    public void addItemPedido(Produto produto, int mesa) throws Exception {
        try {
            ItemPedido itempedido = new ItemPedido();
            itempedido.setProduto(produto);
            itempedido.setQuantidade(quant);
            itempedido.setMesa(mesa);
            System.out.println(quant);

            itemp.salvar(itempedido);
            List<ItemPedido> itens = new ArrayList<>();
            itens = itemp.buscar();
            itempedido = itens.get(itens.size() - 1);

            if (verifPedido(mesa)) {
                Integer id_ped = ped.buscarPedido(mesa).getId();
                itemp.addPedidoItem(id_ped, itempedido.getId());
                adicionarMensagem("Sucesso", "Item do pedido foi adicionado com sucesso!",FacesMessage.SEVERITY_INFO);
            } else {
                throw new Exception("Erro ao relacionar pedido com o itempedido");
            }
            this.quant = 1;
            
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem("Atenção", ex.getMessage(),FacesMessage.SEVERITY_FATAL);
        }
    }

    public List<ItemPedido> buscarItemPedido(int mesa) throws ErroSistema {
        try {

            return ped.buscarProdutoPedido(ped.buscarPedido(mesa).getId());
        } catch (ErroSistema ex) {
            Logger.getLogger(CrudBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem("Atenção", ex.getMessage(),FacesMessage.SEVERITY_FATAL);
        }
        return null;
    }

    public void onRowDelete(ItemPedido item) throws ErroSistema {
        itemp.deletar(item);
        adicionarMensagem("Sucesso", "O item pedido foi removido com sucesso.",FacesMessage.SEVERITY_INFO);

    }

    public void deletarPedido(int mesa) {
        try {
            
            ped.deletar(ped.buscarPedido(mesa));
            adicionarMensagem("Sucesso", "Pedido cancelado com sucesso.",FacesMessage.SEVERITY_INFO);

        } catch (ErroSistema ex) {
            Logger.getLogger(PedidoBean.class.getName()).log(Level.SEVERE, null, ex);
            adicionarMensagem("Atenção", ex.getMessage(), FacesMessage.SEVERITY_FATAL);
        }

    }

    public List<Pedido> buscarPedidoPago() throws ErroSistema {
        List<Pedido> pedidoLF = new ArrayList();
        for (Pedido s : pedidoL) {
            s.setItens(ped.buscarProdutoPedido(s.getId()));
            s.setVendedor(funcDao.buscarID(s.getVendedor().getId()));
            s.setPagamento(pagaDao.buscarID(s));
            pedidoLF.add(s);
        }
        return pedidoLF;
    }

    public void adicionarMensagem(String mensagem, String mensagem2,FacesMessage.Severity tipoErro) {
        FacesMessage fm = new FacesMessage(tipoErro,mensagem, mensagem2);
        FacesContext.getCurrentInstance().addMessage(null, fm);
    }

}
