/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.CategoriaP;
import br.com.controlchefweb.entidade.Produto;
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
public class ProdutoDAOTest {
    
    public ProdutoDAOTest() {
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
     * Test of getTipos method, of class ProdutoDAO.
     */
    @Test
    public void testGetTipos() throws Exception {
        System.out.println("getTipos");
        ProdutoDAO instance = new ProdutoDAO();
        List<CategoriaP> expResult = null;
        List<CategoriaP> result = instance.getTipos();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of criarTipo method, of class ProdutoDAO.
     */
    @Test
    public void testCriarTipo() throws Exception {
        System.out.println("criarTipo");
        CategoriaP categoriap = null;
        ProdutoDAO instance = new ProdutoDAO();
        instance.criarTipo(categoriap);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletarTipo method, of class ProdutoDAO.
     */
    @Test
    public void testDeletarTipo() throws Exception {
        System.out.println("deletarTipo");
        CategoriaP categoriap = null;
        ProdutoDAO instance = new ProdutoDAO();
        instance.deletarTipo(categoriap);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvar method, of class ProdutoDAO.
     */
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Produto produto = null;
        ProdutoDAO instance = new ProdutoDAO();
        instance.salvar(produto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class ProdutoDAO.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        Produto produto = null;
        ProdutoDAO instance = new ProdutoDAO();
        instance.deletar(produto);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class ProdutoDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        ProdutoDAO instance = new ProdutoDAO();
        List<Produto> expResult = null;
        List<Produto> result = instance.buscar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarID method, of class ProdutoDAO.
     */
    @Test
    public void testBuscarID() throws Exception {
        System.out.println("buscarID");
        Integer id = null;
        ProdutoDAO instance = new ProdutoDAO();
        Produto expResult = null;
        Produto result = instance.buscarID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarNome method, of class ProdutoDAO.
     */
    @Test
    public void testBuscarNome() throws Exception {
        System.out.println("buscarNome");
        String nome = "";
        ProdutoDAO instance = new ProdutoDAO();
        List<Produto> expResult = null;
        List<Produto> result = instance.buscarNome(nome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of verificaProd method, of class ProdutoDAO.
     */
    @Test
    public void testVerificaProd() throws Exception {
        System.out.println("verificaProd");
        String nome = "";
        ProdutoDAO instance = new ProdutoDAO();
        boolean expResult = false;
        boolean result = instance.verificaProd(nome);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
