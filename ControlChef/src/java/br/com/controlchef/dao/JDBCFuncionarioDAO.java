/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchef.dao;

import br.com.controlchef.model.Funcionario;
import br.com.controlchef.util.ConnectionFactory;
import br.com.controlchef.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VashJHK
 */
public class JDBCFuncionarioDAO implements GenericDAO<Funcionario>{
    
    Connection conn;
    public JDBCFuncionarioDAO() throws ErroSistema{
        conn = ConnectionFactory.getConnection();
    }

    @Override
    public void inserir(Funcionario funcionario) {
        try {
            String sql = "INSERT INTO funcionario (login,senha) VALUES" + "(?,?)";
            PreparedStatement ps = conn.prepareStatement(sql);
            
            ps.setString(1, funcionario.getLogin());
            ps.setString(2, funcionario.getSenha());
            
            ps.executeUpdate();
            ps.close();
            conn.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao inserir funcionario em JDBCFuncionarioDAO", ex);
        }
    }

    @Override
    public void remover(Funcionario funcionario) {
        try {
            String sql = "DELETE FROM funcionario WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,funcionario.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao remover funcionario em JDBCFuncioanrioDAO",ex);
        }
    }

    @Override
    public List<Funcionario> listar() {
       
        try {
            PreparedStatement ps = conn.prepareStatement("select * from funcionario");
            ResultSet resultSet = ps.executeQuery();
            List<Funcionario> usuarios = new ArrayList<Funcionario>();
            while(resultSet.next()){
                Funcionario usuario = new Funcionario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuarios.add(usuario);
            }
            conn.close();
            return usuarios;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar funcionario em JDBCFuncionarioDAO", ex);
        }
            
            
        
    }

    @Override
    public Funcionario buscar(int id) {
        try {
            Funcionario funcionario = new Funcionario();
            String sql = "SELECT * FROM funcionario WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            funcionario.setId(rs.getInt("id"));
            funcionario.setLogin(rs.getString("login"));
            funcionario.setSenha(rs.getString("senha"));
            
            return funcionario;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return null;
    }   

    @Override
    public void editar(Funcionario funcionario) {
        try {
            String sql = "UPDATE funcionario SET login=?,senha=? WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, funcionario.getLogin());
            ps.setString(2, funcionario.getSenha());
            ps.setInt(3, funcionario.getId());
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao editar funcionario em JDBCFuncionario", ex);
        }
    }
    
}
