package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Produto;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author VashJHK
 */

@ManagedBean(name = "ProdutoDAO")
@SessionScoped
public class ProdutoDAO implements CrudDAO<Produto>, Serializable{
    
    private static final long serialVersionUID = 1L;
    
    private final static String[] tipos;

    static {
        tipos = new String[3];
        tipos[0] = "Comida";
        tipos[1] = "Bebida";
        tipos[2] = "Sobremesa";
    }

    public List<String> getTipos() {
        return Arrays.asList(tipos);
    }
    
    @Override
    public void salvar(Produto produto) throws ErroSistema{
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(produto.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO `produto` (`nome`,`descricao`,`preco`,`imagem`, `disponivel`,`tipo` ) VALUES (?,?,?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update produto set nome=?, descricao=?, preco=?, imagem=?, disponivel=?, tipo=? where id=?");
                ps.setInt(7, produto.getId());
            }
            ps.setString(1, produto.getNome());
            ps.setString(2, produto.getDescricao());
            ps.setDouble(3, produto.getPreco());
            ps.setString(4, produto.getImagem());
            ps.setBoolean(5, produto.isDisponivel());
            ps.setString(6,produto.getTipo());
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
                produto.setTipo(resultSet.getString("tipo"));
                produtos.add(produto);
            }
            FabricaConexao.fecharConexao();
            return produtos;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os produtos!",ex);
        }
    }


}
