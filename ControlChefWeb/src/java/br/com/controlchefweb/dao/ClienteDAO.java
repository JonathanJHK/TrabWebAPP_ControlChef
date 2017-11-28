/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Cliente;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author VashJHK
 */
public class ClienteDAO implements CrudDAO<Cliente>, Serializable {
    
    private static final long serialVersionUID = 1L;
    
    public List<Cliente> buscarNome(String nome) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from cliente where nome like '%"+nome+"%'");
            ResultSet resultSet = ps.executeQuery();
            List<Cliente> clientes = new ArrayList<>();
            while (resultSet.next()) {
                Cliente cliente = new Cliente();
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setDataNasc(resultSet.getDate("dataNasc"));
                cliente.setDataCad(resultSet.getDate("dataCad"));
                clientes.add(cliente);
            }
            FabricaConexao.fecharConexao();
            return clientes;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar o produto filtrado!", ex);
        }
    }
    
    public Cliente buscarCPF(String cpf) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from cliente where cpf=?");
            ps.setString(1, cpf);
            ResultSet resultSet = ps.executeQuery();
            Cliente cliente = new Cliente();
            if (resultSet.next()) {  
                cliente.setCpf(resultSet.getString("cpf"));
                cliente.setNome(resultSet.getString("nome"));
                cliente.setDataNasc(resultSet.getDate("dataNasc"));
                cliente.setDataCad(resultSet.getDate("dataCad"));
                return cliente;
            }
            FabricaConexao.fecharConexao();
            return null;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar o cliente pelo CPF!", ex);
        }
    }

     @Override
    public void salvar(Cliente entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            java.sql.Date dataBanco;
            java.util.Date hoje = new java.util.Date();
            
            ps = conexao.prepareStatement("INSERT INTO cliente (nome,dataNasc,dataCad,cpf) VALUES (?,?,?,?)");
            
            ps.setString(1, entidade.getNome());
            dataBanco = new java.sql.Date(entidade.getDataNasc().getTime());
            ps.setDate(2,dataBanco);
            dataBanco = new java.sql.Date(hoje.getTime());
            ps.setDate(3,dataBanco);
            ps.setString(4, entidade.getCpf());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar Cliente!", ex);
        }
    }
    
    public void update(Cliente entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            java.sql.Date dataBanco;
            
     
            ps = conexao.prepareStatement("update cliente set nome=?, dataNasc=?, dataCad=? where cpf=?");
      
    
            ps.setString(1, entidade.getNome());
            dataBanco = new java.sql.Date(entidade.getDataNasc().getTime());
            ps.setDate(2,dataBanco);
            dataBanco = new java.sql.Date(entidade.getDataCad().getTime());
            ps.setDate(3,dataBanco);
            ps.setString(4, entidade.getCpf());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar atualizar Cliente!", ex);
        }
    }

    @Override
    public void deletar(Cliente entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps0 = conexao.prepareStatement("update pagamento set id_cliente=? where id_cliente=?");
            ps0.setString(1,null);
            ps0.setString(2,entidade.getCpf());
            ps0.execute();
            
            PreparedStatement ps = conexao.prepareStatement("delete from cliente where cpf = ?");
            ps.setString(1, entidade.getCpf());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar Cliente!", ex);
        }
    }

    @Override
    public List<Cliente> buscar() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from cliente");
            ResultSet resultSet = ps.executeQuery();
            List<Cliente> usuarios = new ArrayList<>();
            while (resultSet.next()) {
                Cliente usuario = new Cliente();
                usuario.setCpf(resultSet.getString("cpf"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setDataNasc(resultSet.getDate("dataNasc"));
                usuario.setDataCad(resultSet.getDate("dataCad"));
                usuarios.add(usuario);
            }
            FabricaConexao.fecharConexao();
            return usuarios;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os clientes!", ex);
        }
    }
    
}
