/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Mesa;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author VashJHK
 */
@ManagedBean(name = "MesaDAO")
@SessionScoped
public class MesaDAO implements CrudDAO<Mesa>,Serializable{

    @Override
    public void salvar(Mesa entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
           
            ps = conexao.prepareStatement("INSERT INTO `mesa` (`status_mesa`,`id`) VALUES (?,?)");
           
            ps.setInt(2,entidade.getId());
            ps.setBoolean(1, entidade.isStatus_mesa());
    
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }
    
    public void update(Mesa entidade) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            
             ps = conexao.prepareStatement("update produto set status_mesa=?, where id=?");
            
            ps.setInt(2,entidade.getId());
            ps.setBoolean(1, entidade.isStatus_mesa());
    
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }

    @Override
    public void deletar(Mesa entidade) throws ErroSistema {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Mesa> buscar() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from mesa");
            ResultSet resultSet = ps.executeQuery();
            List<Mesa> mesas = new ArrayList<>();
            while (resultSet.next()) {
                Mesa mesa = new Mesa();
                mesa.setId(resultSet.getInt("id"));
                mesa.setStatus_mesa(resultSet.getBoolean("status_mesa"));
                mesas.add(mesa);
            }
            FabricaConexao.fecharConexao();
            return mesas;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar as mesas!", ex);
        }
    }
    
}
