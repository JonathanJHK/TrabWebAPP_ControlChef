/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Pagamento;
import br.com.controlchefweb.entidade.Pedido;
import br.com.controlchefweb.entidade.Servico;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.sql.Timestamp;

/**
 *
 * @author VashJHK
 */
public class PagamentoDAO implements CrudDAO<ItemPedido>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public void criar(Pedido entidade) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            Timestamp t = new Timestamp(entidade.getPagamento().getDataPagamento().getTime());
            
            ps = conexao.prepareStatement("INSERT INTO `pagamento` (id_pedido,valor,valorDesconto,formaPagamento,troco,taxa,desconto,valorPagamento,id_cliente,dataPagamento,valorTaxa) VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            
            ps.setInt(1,entidade.getId());
            ps.setDouble(2,entidade.getPagamento().getValorT());
            ps.setDouble(3,entidade.getPagamento().getValorDesconto());
            ps.setString(4, entidade.getPagamento().getFormaPagamento());
            ps.setDouble(5, entidade.getPagamento().getTroco());
            ps.setDouble(6, entidade.getPagamento().getTaxa());
            ps.setDouble(7,entidade.getPagamento().getDesconto());
            ps.setDouble(8,entidade.getPagamento().getValorPagamento());
            ps.setString(9, entidade.getPagamento().getCliente().getCpf());
            ps.setTimestamp(10, t);
            ps.setDouble(11,entidade.getPagamento().getValorTaxa());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao criar pagamento!", ex);
        }
    }
    
    public double getarTaxa() throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            
            ps = conexao.prepareStatement("Select taxaServ from servico where id=1");
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getDouble("taxaServ");
            }
            FabricaConexao.fecharConexao();
            return 0;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao setar taxa de pagamento!", ex);
        }
     
    }
    
    public double getarDesconto() throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            
            ps = conexao.prepareStatement("Select descontServ from servico where id=1");
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getDouble("descontServ");
            }
            FabricaConexao.fecharConexao();
            return 0;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao setar desconto de pagamento!", ex);
        }
     
    }
    
    public void updateTaxaDesconto(Servico entidade) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            
            ps = conexao.prepareStatement("update servico set descontServ=?,taxaServ=? where id=1");
            
            ps.setDouble(1, entidade.getDesconto());
            ps.setDouble(2, entidade.getTaxa());
       
            ps.execute();
            FabricaConexao.fecharConexao();
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao atualizar desconto e taxa!", ex);
        }
    }
    
    public Pagamento buscarID(Pedido entidade) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            
            ps = conexao.prepareStatement("Select * from pagamento where id_pedido=?");
            ps.setInt(1,entidade.getId());
            ResultSet resultSet = ps.executeQuery();
            Pagamento pagamento = new Pagamento();
            
            if (resultSet.next()) {
                pagamento.setId(resultSet.getInt("id_pagamento"));
                pagamento.setValorT(resultSet.getDouble("valor"));
                pagamento.setValorDesconto(resultSet.getDouble("valorDesconto"));
                pagamento.setFormaPagamento(resultSet.getString("formaPagamento"));
                pagamento.setTroco(resultSet.getDouble("troco"));
                pagamento.setTaxa(resultSet.getDouble("taxa"));
                pagamento.setValorTaxa(resultSet.getDouble("valorTaxa"));
                pagamento.setDesconto(resultSet.getInt("desconto"));
                pagamento.setValorPagamento(resultSet.getDouble("valorPagamento"));
                pagamento.setDataPagamento(resultSet.getTimestamp("dataPagamento"));
                ClienteDAO clienteDao = new ClienteDAO();
                pagamento.setCliente(clienteDao.buscarCPF(resultSet.getString("id_cliente")));
            }
            FabricaConexao.fecharConexao();
            return pagamento;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar pagamento pelo ID!", ex);
        }
    }

    @Override
    public void salvar(ItemPedido entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deletar(ItemPedido entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<ItemPedido> buscar() throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
