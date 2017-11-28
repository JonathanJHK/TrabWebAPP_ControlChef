/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Funcionario;
import java.util.List;
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
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of getTipos method, of class FuncionarioDAO.
     */
    @Test
    public void testGetTipos() {
        System.out.println("getTipos");
        FuncionarioDAO instance = new FuncionarioDAO();
        List<String> expResult = null;
        List<String> result = instance.getTipos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvar method, of class FuncionarioDAO.
     */
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Funcionario entidade = null;
        FuncionarioDAO instance = new FuncionarioDAO();
        instance.salvar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class FuncionarioDAO.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        Funcionario entidade = null;
        FuncionarioDAO instance = new FuncionarioDAO();
        instance.deletar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class FuncionarioDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        FuncionarioDAO instance = new FuncionarioDAO();
        List<Funcionario> expResult = null;
        List<Funcionario> result = instance.buscar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarAunt method, of class FuncionarioDAO.
     */
    @Test
    public void testBuscarAunt() throws Exception {
        System.out.println("buscarAunt");
        String login = "";
        String senha = "";
        FuncionarioDAO instance = new FuncionarioDAO();
        Funcionario expResult = null;
        Funcionario result = instance.buscarAunt(login, senha);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarID method, of class FuncionarioDAO.
     */
    @Test
    public void testBuscarID() throws Exception {
        System.out.println("buscarID");
        Integer id = null;
        FuncionarioDAO instance = new FuncionarioDAO();
        Funcionario expResult = null;
        Funcionario result = instance.buscarID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarLogin method, of class FuncionarioDAO.
     */
    @Test
    public void testBuscarLogin() throws Exception {
        System.out.println("buscarLogin");
        String login = "";
        FuncionarioDAO instance = new FuncionarioDAO();
        Funcionario expResult = null;
        Funcionario result = instance.buscarLogin(login);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
