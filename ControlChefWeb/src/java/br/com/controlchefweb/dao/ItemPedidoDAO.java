/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Produto;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VashJHK
 */
public class ItemPedidoDAO implements CrudDAO<ItemPedido>, Serializable{
    
     private static final long serialVersionUID = 1L;
     
     public void addPedidoItem(Integer pedido, Integer item) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            
            ps = conexao.prepareStatement("INSERT INTO `pedido_item` (`id_pedido`,`id_item`) VALUES (?,?)");
            
            ps.setInt(1,pedido);
            ps.setInt(2, item);
    
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar relacionar item ao pedido!", ex);
        }
    }

    @Override
    public void salvar(ItemPedido entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(entidade.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO `itempedido` (`quantidade`,`id_produto`,`valor_item`,`status_pedido`,`mesa`,`entrega`) VALUES (?,?,?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update itempedido set quantidade=?, id_produto=?,valor_item=?,status_pedido=?,mesa=?,entrega=? where id=?");
                ps.setInt(7, entidade.getId());
            }
            ps.setInt(1,entidade.getQuantidade());
            ps.setInt(2, entidade.getProduto().getId());
            ps.setDouble(3,entidade.getValorItem());
            ps.setBoolean(4, entidade.isStatusItem());
            ps.setInt(5,entidade.getMesa());
            ps.setBoolean(6, entidade.isEntrega());
    
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }

    @Override
    public void deletar(ItemPedido entidade) throws ErroSistema {
        try {
            deletarPedidoItem(entidade);
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("delete from itempedido where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar itempedido!", ex);
        }
    }
    
    public void deletarPedidoItem(ItemPedido entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("delete from pedido_item where id_item = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar relacionamento Pedido Item!", ex);
        }
    }

    @Override
    public List<ItemPedido> buscar() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from itempedido where status_pedido = false");
            ResultSet resultSet = ps.executeQuery();
            List<ItemPedido> itens = new ArrayList<>();
            while (resultSet.next()) {
                ItemPedido item = new ItemPedido();
                item.setId(resultSet.getInt("id"));
                ProdutoDAO prodDAO = new ProdutoDAO();
                Produto produt = prodDAO.buscarID(resultSet.getInt("id_produto"));
                item.setProduto(produt);
                item.setQuantidade(resultSet.getInt("quantidade"));
                item.setValorItem(resultSet.getDouble("valor_item"));
                item.setStatusItem(resultSet.getBoolean("status_pedido"));
                item.setEntrega(resultSet.getBoolean("entrega"));
                item.setMesa(resultSet.getInt("mesa"));
                itens.add(item);
            }
            FabricaConexao.fecharConexao();
            return itens;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os item pedido!", ex);
        }
    }
    
     public List<ItemPedido> buscarPronto() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from itempedido where status_pedido = true and entrega = false");
            ResultSet resultSet = ps.executeQuery();
            List<ItemPedido> itens = new ArrayList<>();
            while (resultSet.next()) {
                ItemPedido item = new ItemPedido();
                item.setId(resultSet.getInt("id"));
                ProdutoDAO prodDAO = new ProdutoDAO();
                Produto produt = prodDAO.buscarID(resultSet.getInt("id_produto"));
                item.setProduto(produt);
                item.setQuantidade(resultSet.getInt("quantidade"));
                item.setValorItem(resultSet.getDouble("valor_item"));
                item.setStatusItem(resultSet.getBoolean("status_pedido"));
                item.setEntrega(resultSet.getBoolean("entrega"));
                item.setMesa(resultSet.getInt("mesa"));
                itens.add(item);
            }
            FabricaConexao.fecharConexao();
            return itens;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os item pedido!", ex);
        }
    }
}
