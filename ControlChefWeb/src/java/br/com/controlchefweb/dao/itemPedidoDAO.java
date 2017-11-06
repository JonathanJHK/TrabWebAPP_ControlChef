/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.itemPedido;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author VashJHK
 */
public class itemPedidoDAO implements CrudDAO<itemPedido>, Serializable{
    
     private static final long serialVersionUID = 1L;

    @Override
    public void salvar(itemPedido entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(entidade.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO `itempedido` (`quantidade`,`id_produto`) VALUES (?,?)");
            } else {
                ps = conexao.prepareStatement("update produto set quantidade=?, id_produto=? where id=?");
                ps.setInt(3, entidade.getId());
            }
            ps.setInt(1,entidade.getQuantidade());
            ps.setInt(2, entidade.getProduto().getId());
    
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }

    @Override
    public void deletar(itemPedido entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<itemPedido> buscar() throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
