/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Funcionario;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author VashJHK
 */
public class FuncionarioDAOTest {

    public FuncionarioDAOTest() {
    }

    private static Connection conexao;
    Funcionario func;

    @Before
    public void setUp() {
        try {
            conexao = FabricaConexao.getConexao();
            func = new Funcionario();
            func.setId(1);
            func.setLogin("teste");
            func.setNome("teste");
            func.setSenha("teste");
            func.setTipo("Teste");
        } catch (ErroSistema ex) {
            Logger.getLogger(ClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @After
    public void tearDown() {
        try {
            FabricaConexao.fecharConexao();
        } catch (ErroSistema ex) {
            Logger.getLogger(ClienteDAOTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Test of salvar method, of class FuncionarioDAO.
     */
    @Test
    public void testSalvar() throws Exception {
        int aux = 0;
        FuncionarioDAO instance = new FuncionarioDAO();
        func.setId(null);
        instance.salvar(func);
        
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement("select * from funcionario where login='"+func.getLogin()+"'");
        ResultSet resultSet = ps.executeQuery();
        Funcionario result = new Funcionario();
        if (resultSet.next()) {
            aux = resultSet.getInt("id");
            result.setNome(resultSet.getString("nome"));
            result.setLogin(resultSet.getString("login"));
            result.setSenha(resultSet.getString("senha"));
            result.setTipo(resultSet.getString("tipo"));
        }

        assertEquals(func.getLogin(), result.getLogin());
        func.setId(aux);
        instance.deletar(func);
    }

    /**
     * Test of deletar method, of class FuncionarioDAO.
     */
    @Test
    public void testDeletar() throws Exception {
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO funcionario (id,nome,login,senha,tipo) VALUES ('"+func.getId()+"','"+func.getNome()+"','"+func.getLogin()+"','"+func.getSenha()+"','"+func.getTipo()+"')");
        ps.execute();
        
        FuncionarioDAO instance = new FuncionarioDAO();
        instance.deletar(func);
        
        Funcionario result = instance.buscarLogin(func.getLogin());
        
        assertNull(result);
    }

    /**
     * Test of buscar method, of class FuncionarioDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        boolean verifica = false;
        
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO funcionario (id,nome,login,senha,tipo) VALUES ('"+func.getId()+"','"+func.getNome()+"','"+func.getLogin()+"','"+func.getSenha()+"','"+func.getTipo()+"')");
        ps.execute();
        
        FuncionarioDAO instance = new FuncionarioDAO();

        List<Funcionario> result = instance.buscar();
        for (Funcionario s : result) {
            if (s.getLogin().equals(func.getLogin())) {
                verifica = true;
            }
        }
        assertTrue(verifica);
        instance.deletar(func);
    }

    /**
     * Test of buscarAunt method, of class FuncionarioDAO.
     */
    @Test
    public void testBuscarAunt() throws Exception {
        
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO funcionario (id,nome,login,senha,tipo) VALUES ('"+func.getId()+"','"+func.getNome()+"','"+func.getLogin()+"','"+func.getSenha()+"','"+func.getTipo()+"')");
        ps.execute();
        
        FuncionarioDAO instance = new FuncionarioDAO();

        Funcionario result = instance.buscarAunt(func.getLogin(), func.getSenha());
        
        assertEquals(func, result);
        instance.deletar(func);
    }

    /**
     * Test of buscarID method, of class FuncionarioDAO.
     */
    @Test
    public void testBuscarID() throws Exception {
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO funcionario (id,nome,login,senha,tipo) VALUES ('"+func.getId()+"','"+func.getNome()+"','"+func.getLogin()+"','"+func.getSenha()+"','"+func.getTipo()+"')");
        ps.execute();
        
        FuncionarioDAO instance = new FuncionarioDAO();

        Funcionario result = instance.buscarID(func.getId());
        
        assertEquals(func, result);
        instance.deletar(func);
    }

    /**
     * Test of buscarLogin method, of class FuncionarioDAO.
     */
    @Test
    public void testBuscarLogin() throws Exception {
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO funcionario (id,nome,login,senha,tipo) VALUES ('"+func.getId()+"','"+func.getNome()+"','"+func.getLogin()+"','"+func.getSenha()+"','"+func.getTipo()+"')");
        ps.execute();
        
        FuncionarioDAO instance = new FuncionarioDAO();

        Funcionario result = instance.buscarLogin(func.getLogin());
        
        assertEquals(func, result);
        instance.deletar(func);
    }

}
