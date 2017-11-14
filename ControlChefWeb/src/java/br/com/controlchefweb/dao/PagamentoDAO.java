/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Pagamento;
import br.com.controlchefweb.entidade.Pedido;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

/**
 *
 * @author VashJHK
 */
public class PagamentoDAO implements CrudDAO<ItemPedido>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public void criar(Pedido entidade, Pagamento entidade2) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            Date hoje = new Date();
            
            ps = conexao.prepareStatement("INSERT INTO `pagamento` (id_pedido,valor,valorDesconto,formaPagamento,troco,taxa,desconto,valorPagamento,id_cliente,dataPagamento) VALUES (?,?,?,?,?,?,?,?,?,?)");
            
            ps.setInt(1,entidade.getId());
            ps.setDouble(2,entidade2.getValorT());
            ps.setDouble(3,entidade2.getValorDesconto());
            ps.setString(4, entidade2.getFormaPagamento());
            ps.setDouble(5, entidade2.getTroco());
            ps.setDouble(6, entidade2.getTaxa());
            ps.setInt(7,entidade2.getDesconto());
            ps.setDouble(8,entidade2.getValorPagamento());
            ps.setString(9, entidade2.getCliente().getCpf());
            ps.setDate(10,new java.sql.Date(hoje.getTime()));
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
    
    public Integer getarDesconto() throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            
            ps = conexao.prepareStatement("Select descontServ from servico where id=1");
            ResultSet resultSet = ps.executeQuery();
            
            if (resultSet.next()) {
                return resultSet.getInt("descontServ");
            }
            FabricaConexao.fecharConexao();
            return 0;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao setar desconto de pagamento!", ex);
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
