package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Produto;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Danilo Souza Almeida
 */
public class ProdutoDAO implements CrudDAO<Produto>, Serializable{
    
    private static final long serialVersionUID = 1L;
    
    @Override
    public void salvar(Produto produto) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(produto.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO `carro` (`nome`,`descricao`,`preco`,`imagem`, `disponivel` ) VALUES (?,?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update carro set nome=?, descricao=?, preco=?, imagem=?, disponivel=? where id=?");
                ps.setInt(6, produto.getId());
            }
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco());
            ps.setString(4, produto.getImagem());
            ps.setBoolean(5, produto.isDisponivel());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar!", ex);
        }
    }
    
    @Override
    public void deletar(Produto produto) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps  = conexao.prepareStatement("delete from produto where id = ?");
            ps.setInt(1, produto.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar o produto!", ex);
        }
    }
    
    @Override
    public List<Produto> buscar() throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from produto");
            ResultSet resultSet = ps.executeQuery();
            List<Produto> produtos = new ArrayList<>();
            while(resultSet.next()){
                Produto produto = new Produto();
                produto.setId(resultSet.getInt("id"));
                produto.setNome(resultSet.getString("nome"));
                produto.setDescricao(resultSet.getString("descricao"));
                produto.setPreco(resultSet.getDouble("preco"));
                produto.setImagem(resultSet.getString("imagem"));
                produto.setDisponivel(resultSet.getBoolean("disponivel"));
                produtos.add(produto);
            }
            FabricaConexao.fecharConexao();
            return produtos;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os produtos!",ex);
        }
    }
}
