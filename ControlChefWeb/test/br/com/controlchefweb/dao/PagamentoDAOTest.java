/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Pagamento;
import br.com.controlchefweb.entidade.Pedido;
import br.com.controlchefweb.entidade.Servico;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.sql.Connection;
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
public class PagamentoDAOTest {
   
    private static Connection conexao;
    
    public PagamentoDAOTest() {
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
     * Test of criar method, of class PagamentoDAO.
     */
    @Test
    public void testCriar() throws Exception {
        System.out.println("criar");
        Pedido entidade = null;
        PagamentoDAO instance = new PagamentoDAO();
        instance.criar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getarTaxa method, of class PagamentoDAO.
     */
    @Test
    public void testGetarTaxa() throws Exception {
        System.out.println("getarTaxa");
        PagamentoDAO instance = new PagamentoDAO();
        double expResult = 0.0;
        double result = instance.getarTaxa();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getarDesconto method, of class PagamentoDAO.
     */
    @Test
    public void testGetarDesconto() throws Exception {
        System.out.println("getarDesconto");
        PagamentoDAO instance = new PagamentoDAO();
        double expResult = 0.0;
        double result = instance.getarDesconto();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateTaxaDesconto method, of class PagamentoDAO.
     */
    @Test
    public void testUpdateTaxaDesconto() throws Exception {
        System.out.println("updateTaxaDesconto");
        Servico entidade = null;
        PagamentoDAO instance = new PagamentoDAO();
        instance.updateTaxaDesconto(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscarID method, of class PagamentoDAO.
     */
    @Test
    public void testBuscarID() throws Exception {
        System.out.println("buscarID");
        Pedido entidade = null;
        PagamentoDAO instance = new PagamentoDAO();
        Pagamento expResult = null;
        Pagamento result = instance.buscarID(entidade);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of salvar method, of class PagamentoDAO.
     */
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        ItemPedido entidade = null;
        PagamentoDAO instance = new PagamentoDAO();
        instance.salvar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class PagamentoDAO.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        ItemPedido entidade = null;
        PagamentoDAO instance = new PagamentoDAO();
        instance.deletar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class PagamentoDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        PagamentoDAO instance = new PagamentoDAO();
        List<ItemPedido> expResult = null;
        List<ItemPedido> result = instance.buscar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
