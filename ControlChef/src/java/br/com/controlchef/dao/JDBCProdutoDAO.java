package br.com.controlchef.dao;

import br.com.controlchef.model.Funcionario;
import br.com.controlchef.model.Produto;
import br.com.controlchef.util.ConnectionFactory;
import br.com.controlchef.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Danilo Souza Almeida
 */
public class JDBCProdutoDAO implements GenericDAO<Produto>{
    
    Connection conn;
    public JDBCProdutoDAO() throws ErroSistema{
        conn = ConnectionFactory.getConnection();
    }
    
    @Override
    public void inserir(Produto produto) {
        
        try {
            PreparedStatement ps;
      
            ps = conn.prepareStatement("INSERT INTO `produto` (`nome`,`descricao`,`preco`) VALUES (?,?,?,)");
            
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
             throw new RuntimeException("Erro ao inserir produto em JDBCProdutoDAO", ex);
        }
      
    }

    @Override
    public void remover(Produto produto) {
       
        try {
            PreparedStatement ps  = conn.prepareStatement("delete from produto where id = ?");
            ps.setInt(1, produto.getId());
            ps.executeUpdate();
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao remover produto em JDBCProdutoDAO",ex);
        }
        
    }

    @Override
    public List<Produto> listar() {
        
        try {
            PreparedStatement ps = conn.prepareStatement("select * from produto");
            ResultSet resultSet = ps.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while(resultSet.next()){
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produtos.add(produto);
            }
            ps.close();
            conn.close();
            return produtos;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCProdutoDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao listar funcionario em JDBCFuncionarioDAO", ex);
        }
            
 
        

    }

    @Override
    public Produto buscar(int id) {
        try {
            Produto produto = new Produto();
            String sql = "SELECT * FROM produto WHERE id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,id);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            produto.setNome(rs.getString("nome"));
            produto.setDescricao(rs.getString("descricao"));
            produto.setPreco(rs.getDouble("preco"));
            
            return produto;
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao buscar produto em JDBCProdutoDAO", ex);
        }
    }   

    @Override
    public void editar(Produto produto) {
        try {
            String sql = "UPDATE produto SET nome=?, descricao=?, preco=? where id=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco());
            ps.setInt(4, produto.getId());
            ps.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(JDBCFuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RuntimeException("Erro ao editar produto em JDBCProdutoDAO", ex);
        }
    }
}
