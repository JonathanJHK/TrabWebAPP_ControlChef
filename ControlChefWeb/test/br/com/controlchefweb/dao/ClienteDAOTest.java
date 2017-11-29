/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Cliente;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.sql.Connection;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author VashJHK
 */
public class ClienteDAOTest {

    private static Connection conexao;

    public ClienteDAOTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        try {
            conexao = FabricaConexao.getConexao();
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
     * Test of buscarNome method, of class ClienteDAO.
     */
    @Test
    public void testBuscarNome() throws Exception {

        boolean verifica = false;
     

        Cliente cliente = new Cliente();
        cliente.setCpf("11111");
        cliente.setNome("Julia");

        PreparedStatement ps = conexao.prepareStatement("INSERT INTO cliente (nome,cpf) VALUES ('" + cliente.getNome() + "','" + cliente.getCpf() + "')");
        ps.execute();

        ClienteDAO instance = new ClienteDAO();

        List<Cliente> result = instance.buscarNome(cliente.getNome());
        for (Cliente s : result) {
            if (s.getNome().equals(cliente.getNome())) {
                verifica = true;
            }
        }
        assertTrue(verifica);
        instance.deletar(cliente);
    }

    /**
     * Test of buscarCPF method, of class ClienteDAO.
     */
    @Test
    public void testBuscarCPF() throws Exception {
      

        Cliente cliente = new Cliente();
        cliente.setCpf("11111");
        cliente.setNome("Julia");

        PreparedStatement ps = conexao.prepareStatement("INSERT INTO cliente (nome,cpf) VALUES ('" + cliente.getNome() + "','" + cliente.getCpf() + "')");
        ps.execute();

        ClienteDAO instance = new ClienteDAO();

        Cliente result = instance.buscarCPF(cliente.getCpf());

        assertEquals(cliente, result);

        instance.deletar(cliente);
    }

    /**
     * Test of salvar method, of class ClienteDAO.
     */
    @Test
    public void testSalvar() throws Exception {

        Cliente cliente = new Cliente();
        cliente.setCpf("11111");
        cliente.setNome("Julia");
        cliente.setDataNasc(new java.util.Date());

        ClienteDAO instance = new ClienteDAO();

        instance.salvar(cliente);
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement("select * from cliente where cpf=?");
        ps.setString(1, cliente.getCpf());
        ResultSet resultSet = ps.executeQuery();
        Cliente result = new Cliente();
        if (resultSet.next()) {
            result.setCpf(resultSet.getString("cpf"));
            result.setNome(resultSet.getString("nome"));
            result.setDataNasc(resultSet.getDate("dataNasc"));
            result.setDataCad(resultSet.getDate("dataCad"));
        }

        assertEquals(cliente, result);

        instance.deletar(cliente);

    }

    /**
     * Test of update method, of class ClienteDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        Cliente cliente = new Cliente();
        cliente.setCpf("11111");
        cliente.setNome("Julia");
        cliente.setDataNasc(new java.util.Date());
        
        
        ClienteDAO instance = new ClienteDAO();

        instance.salvar(cliente);
        
        cliente.setNome("JuliaNew");
        cliente.setDataCad(new java.util.Date());
        instance.update(cliente);
        
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement("select * from cliente where cpf=?");
        ps.setString(1, cliente.getCpf());
        ResultSet resultSet = ps.executeQuery();
        Cliente result = new Cliente();
        if (resultSet.next()) {
            result.setCpf(resultSet.getString("cpf"));
            result.setNome(resultSet.getString("nome"));
            result.setDataNasc(resultSet.getDate("dataNasc"));
            result.setDataCad(resultSet.getDate("dataCad"));
        }

        assertEquals(cliente, result);

        instance.deletar(cliente);
    }

    /**
     * Test of deletar method, of class ClienteDAO.
     */
    @Test
    public void testDeletar() throws Exception {

  
        Cliente cliente = new Cliente();
        cliente.setCpf("11111");
        cliente.setNome("Julia");

        PreparedStatement ps = conexao.prepareStatement("INSERT INTO cliente (nome,cpf) VALUES ('" + cliente.getNome() + "','" + cliente.getCpf() + "')");
        ps.execute();

        ClienteDAO instance = new ClienteDAO();
        instance.deletar(cliente);
        
        Cliente result = instance.buscarCPF(cliente.getCpf());
        
        assertNull(result);
    }

    /**
     * Test of buscar method, of class ClienteDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        boolean verifica = false;
        List<Cliente> clientes = new ArrayList();

        Cliente cliente = new Cliente();
        cliente.setCpf("11111");
        cliente.setNome("Julia");

        PreparedStatement ps = conexao.prepareStatement("INSERT INTO cliente (nome,cpf) VALUES ('" + cliente.getNome() + "','" + cliente.getCpf() + "')");
        ps.execute();

        ClienteDAO instance = new ClienteDAO();

        List<Cliente> result = instance.buscar();
        for (Cliente s : result) {
            if (s.getNome().equals(cliente.getNome())) {
                verifica = true;
            }
        }
        assertTrue(verifica);
        instance.deletar(cliente);
    }

}
