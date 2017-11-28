/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Mesa;
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
public class MesaDAOTest {
    
    public MesaDAOTest() {
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
     * Test of salvar method, of class MesaDAO.
     */
    @Test
    public void testSalvar() throws Exception {
        System.out.println("salvar");
        Mesa entidade = null;
        MesaDAO instance = new MesaDAO();
        instance.salvar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class MesaDAO.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Mesa entidade = null;
        MesaDAO instance = new MesaDAO();
        instance.update(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deletar method, of class MesaDAO.
     */
    @Test
    public void testDeletar() throws Exception {
        System.out.println("deletar");
        Mesa entidade = null;
        MesaDAO instance = new MesaDAO();
        instance.deletar(entidade);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of buscar method, of class MesaDAO.
     */
    @Test
    public void testBuscar() throws Exception {
        System.out.println("buscar");
        MesaDAO instance = new MesaDAO();
        List<Mesa> expResult = null;
        List<Mesa> result = instance.buscar();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
