/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.ItemPedido;
import br.com.controlchefweb.entidade.Pedido;
import br.com.controlchefweb.entidade.Produto;
import br.com.controlchefweb.util.FabricaConexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    Pedido ped;
    Produto prod;
    ItemPedido itped;

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ped = new Pedido();
        ped.setId(1);
        ped.setStatusPedido(false);

        prod = new Produto();
        prod.setId(1);
        prod.setNome("teste");
        prod.setDescricao("teste");
        prod.setTipo("teste");
        prod.setPreco(10.0);
        
        itped = new ItemPedido();
        itped.setId(1);
        itped.setProduto(prod);
        
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of addPedidoItem method, of class ItemPedidoDAO.
     */
    @Test
    public void testAddPedidoItem() throws Exception {
        boolean verifica = false;
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO pedido (id) VALUES (" + ped.getId() + ")");
        ps.execute();
        ps = conexao.prepareStatement("INSERT INTO itempedido (id) VALUES (" + itped.getId() + ")");
        ps.execute();

        ItemPedidoDAO instance = new ItemPedidoDAO();
        instance.addPedidoItem(ped.getId(), itped.getId());

        conexao = FabricaConexao.getConexao();
        ps = conexao.prepareStatement("Select * from pedido_item where id_pedido=" + ped.getId() + " and id_item=" + itped.getId());
        ResultSet resultSet = ps.executeQuery();
        if (resultSet.next()) {
            verifica = true;
        }
        assertTrue(verifica);
        instance.deletarPedidoItem(itped);
        conexao = FabricaConexao.getConexao();
        ps = conexao.prepareStatement("delete from pedido where id =" + ped.getId());
        ps.execute();
        ps = conexao.prepareStatement("delete from itempedido where id =" + itped.getId());
        ps.execute();

    }

    /**
     * Test of salvar method, of class ItemPedidoDAO.
     */
    @Test
    public void testSalvar() throws Exception {
        int aux = 0;
        itped.setId(null);
        itped.setEntrega(false);
        itped.setStatusItem(false);
        itped.setMesa(1);
        itped.setQuantidade(1);
        
        Connection conexao = FabricaConexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement("INSERT INTO produto (id,nome,descricao,tipo) VALUES (" +itped.getProduto().getId()+ ",'"+itped.getProduto().getNome()+"','"+itped.getProduto().getDescricao()+"','"+itped.getProduto().getTipo()+"')");
        ps.execute();

        ItemPedidoDAO instance = new ItemPedidoDAO();
        instance.salvar(itped);
        
        conexao = FabricaConexao.getConexao();
        ps = conexao.prepareStatement("select * from itempedido where valor_item="+itped.getValorItem());
        ResultSet resultSet = ps.executeQuery();
        ItemPedido result = new ItemPedido();
        if(resultSet.next()){
            aux = resultSet.getInt("id");
            result.setMesa(resultSet.getInt("mesa"));
        }
        
        assertEquals(itped.getMesa(), result.getMesa());
        
        itped.setId(aux);
        instance.deletar(itped);
        ps = conexao.prepareStatement("delete from produto where id =" +itped.getProduto().getId());
        ps.execute();
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
