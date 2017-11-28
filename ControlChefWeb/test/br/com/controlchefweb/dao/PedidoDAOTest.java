/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Pedido;
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
public class PedidoDAOTest {
    
    public PedidoDAOTest() {
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
     * Test of criar method, of class PedidoDAO.
     */
    @Test
    public void testCriar() throws Exception {
        System.out.println("criar");
        int mesa = 0;
        PedidoDAO instance = new PedidoDAO();
        instance.criar(mesa);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarPedido method, of class PedidoDAO.
     */
    @Test
    public void testBuscarPedido() throws Exception {
        System.out.println("buscarPedido");
        int mesa = 0;
        PedidoDAO instance = new PedidoDAO();
        Pedido expResult = null;
        Pedido result = instance.buscarPedido(mesa);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarProdutoPedido method, of class PedidoDAO.
     */
    @Test
    public void testBuscarProdutoPedido() throws Exception {
        System.out.println("buscarProdutoPedido");
        int idpedido = 0;
        PedidoDAO instance = new PedidoDAO();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.buscarProdutoPedido(idpedido);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvar method, of class PedidoDAO.
     */
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Pedido entidade = null;
        PedidoDAO instance = new PedidoDAO();
        instance.salvar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class PedidoDAO.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        Pedido entidade = null;
        PedidoDAO instance = new PedidoDAO();
        instance.deletar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class PedidoDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        PedidoDAO instance = new PedidoDAO();
        List<Pedido> expResult = null;
        List<Pedido> result = instance.buscar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
