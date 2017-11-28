/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Funcionario;
import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Pedido;
import br.com.controlchefweb.entidade.Produto;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author VashJHK
 */
@ManagedBean(name = "PedidoDAO")
@SessionScoped
public class PedidoDAO implements CrudDAO<Pedido>, Serializable {


    public void criar(int mesa) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            Date hoje = new Date();

            ps = conexao.prepareStatement("INSERT INTO `pedido` (`mesa`,`statusPedido`,`dataCriacao`) VALUES (?,?,?)");

            ps.setInt(1, mesa);
            ps.setBoolean(2, true);
            ps.setDate(3,new java.sql.Date(hoje.getTime()));

            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar criar pedido!", ex);
        }
    }

    public Pedido buscarPedido(int mesa) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from pedido where pedido.mesa=? and pedido.statusPedido=true");
            ps.setInt(1, mesa);
            ResultSet resultSet = ps.executeQuery();
            Pedido pedido = new Pedido();
            Funcionario funcionario;
            FuncionarioDAO funDao = new FuncionarioDAO();

            if (resultSet.next()) {
                pedido.setId(resultSet.getInt("id"));
                funcionario = funDao.buscarID(resultSet.getInt("vendedor"));
                pedido.setVendedor(funcionario);
                pedido.setValorTotal(resultSet.getDouble("valorTotal"));
                pedido.setMesa(resultSet.getInt("mesa"));
                pedido.setStatusPedido(resultSet.getBoolean("statusPedido"));
                pedido.setDataCriacao(resultSet.getDate("dataCriacao"));
                pedido.setItens(buscarProdutoPedido(resultSet.getInt("id")));
            } else {
                return null;
            }
            FabricaConexao.fecharConexao();
            return pedido;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar criar pedido!", ex);
        }
    }

    public List<ItemPedido> buscarProdutoPedido(int idpedido) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT produto.id, produto.nome,produto.preco,produto.descricao,produto.imagem,produto.disponivel,produto.tipo,itempedido.quantidade,itempedido.valor_item,itempedido.status_pedido,itempedido.entrega,itempedido.id as id_pedido FROM produto,itempedido where itempedido.id_produto = produto.id and itempedido.id in (select pedido_item.id_item FROM pedido_item where pedido_item.id_pedido = ?)");
            ps.setInt(1, idpedido);
            ResultSet resultSet = ps.executeQuery();

            List<ItemPedido> itens = new ArrayList<>();
            while (resultSet.next()) {
                ItemPedido itempedido = new ItemPedido();
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setImagem(resultSet.getString("imagem"));
                produto.setDisponivel(resultSet.getBoolean("disponivel"));
                produto.setTipo(resultSet.getString("tipo"));

                itempedido.setId(resultSet.getInt("id_pedido"));
                itempedido.setProduto(produto);
                itempedido.setQuantidade(resultSet.getInt("quantidade"));
                itempedido.setValorItem(resultSet.getDouble("valor_item"));
                itempedido.setStatusItem(resultSet.getBoolean("status_pedido"));
                itempedido.setEntrega(resultSet.getBoolean("entrega"));
                itens.add(itempedido);
            }

            FabricaConexao.fecharConexao();
            return itens;
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os item pedido!", ex);
        }
    }

    @Override
    public void salvar(Pedido entidade) throws ErroSistema {
         try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            
            ps = conexao.prepareStatement("update pedido set vendedor=?,valorTotal=?, statusPedido=? where id=?");
                
          
            ps.setInt(1, entidade.getVendedor().getId());
            ps.setDouble(2, entidade.getValorTotal());
            ps.setBoolean(3, entidade.isStatusPedido());
            ps.setInt(4, entidade.getId());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar funcionario!", ex);
        }
    }

    @Override
    public void deletar(Pedido entidade) throws ErroSistema {
        try {
            ItemPedidoDAO itemDao = new ItemPedidoDAO();
            for(ItemPedido s: entidade.getItens()){
                itemDao.deletar(s);
            }
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("delete from pedido where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar pedido!", ex);
        }
    }

    @Override
    public List<Pedido> buscar() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from pedido where statusPedido = false");
            ResultSet resultSet = ps.executeQuery();
            List<Pedido> pedidos = new ArrayList<>();
            while (resultSet.next()) {
                Pedido pedido = new Pedido();
                pedido.setId(resultSet.getInt("id"));
                pedido.setValorTotal(resultSet.getDouble("valorTotal"));
                pedido.setMesa(resultSet.getInt("mesa"));
                pedido.setStatusPedido(resultSet.getBoolean("statusPedido"));
                pedido.setDataCriacao(resultSet.getDate("dataCriacao"));
                Funcionario funcionario = new Funcionario();
                funcionario.setId(resultSet.getInt("vendedor"));
                pedido.setVendedor(funcionario);
               
                
                
                pedidos.add(pedido);
            }
            FabricaConexao.fecharConexao();
            return pedidos;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar Pedidos!", ex);
        }
    }

}
