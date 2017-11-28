/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.ItemPedido;
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
public class ItemPedidoDAOTest {
    
    public ItemPedidoDAOTest() {
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
     * Test of addPedidoItem method, of class ItemPedidoDAO.
     */
    @Test
    public void testAddPedidoItem() throws Exception {
        System.out.println("addPedidoItem");
        Integer pedido = null;
        Integer item = null;
        ItemPedidoDAO instance = new ItemPedidoDAO();
        instance.addPedidoItem(pedido, item);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvar method, of class ItemPedidoDAO.
     */
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        ItemPedido entidade = null;
        ItemPedidoDAO instance = new ItemPedidoDAO();
        instance.salvar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class ItemPedidoDAO.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        ItemPedido entidade = null;
        ItemPedidoDAO instance = new ItemPedidoDAO();
        instance.deletar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletarPedidoItem method, of class ItemPedidoDAO.
     */
    @Test
    public void testDeletarPedidoItem() throws Exception {
        System.out.println("deletarPedidoItem");
        ItemPedido entidade = null;
        ItemPedidoDAO instance = new ItemPedidoDAO();
        instance.deletarPedidoItem(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class ItemPedidoDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        ItemPedidoDAO instance = new ItemPedidoDAO();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.buscar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPronto method, of class ItemPedidoDAO.
     */
    @Test
    public void testBuscarPronto() throws Exception {
        System.out.println("buscarPronto");
        ItemPedidoDAO instance = new ItemPedidoDAO();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.buscarPronto();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
