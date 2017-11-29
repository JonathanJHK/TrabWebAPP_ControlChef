package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Produto;
import br.com.controlchefweb.entidade.Funcionario;
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

@ManagedBean(name = "FuncionarioDAO")
@SessionScoped
public class FuncionarioDAO implements CrudDAO<Funcionario>, Serializable {

    private static final long serialVersionUID = 1L;

    private final static String[] tipos;

    static {
        tipos = new String[2];
        tipos[0] = "Funcionário";
        tipos[1] = "Gerente";
    }

    public List<String> getTipos() {
        return Arrays.asList(tipos);
    }

    @Override
    public void salvar(Funcionario entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if (entidade.getId() == null) {
                ps = conexao.prepareStatement("INSERT INTO funcionario (nome,login,senha,tipo) VALUES (?,?,?,?)");
            } else {
                ps = conexao.prepareStatement("update funcionario set nome=?,login=?, senha=?, tipo=? where id=?");
                ps.setInt(5, entidade.getId());
            }
            ps.setString(1, entidade.getNome());
            ps.setString(2, entidade.getLogin());
            ps.setString(3, entidade.getSenha());
            ps.setString(4, entidade.getTipo());
            ps.execute();
            FabricaConexao.fecharConexao();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao tentar salvar funcionario!", ex);
        }
    }

    @Override
    public void deletar(Funcionario entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            
            PreparedStatement ps0 = conexao.prepareStatement("update pedido set vendedor=? where vendedor=?");
            ps0.setString(1,null);
            ps0.setInt(2,entidade.getId());
            ps0.execute();
            
            PreparedStatement ps = conexao.prepareStatement("delete from funcionario where id = ?");
            ps.setInt(1, entidade.getId());
            ps.execute();
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao deletar funcionario!", ex);
        }
    }

    @Override
    public List<Funcionario> buscar() throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("select * from funcionario");
            ResultSet resultSet = ps.executeQuery();
            List<Funcionario> usuarios = new ArrayList<>();
            while (resultSet.next()) {
                Funcionario usuario = new Funcionario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setTipo(resultSet.getString("tipo"));
                usuarios.add(usuario);
            }
            FabricaConexao.fecharConexao();
            return usuarios;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os funcionarios!", ex);
        }
    }

    public Funcionario buscarAunt(String login, String senha) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT id,nome, login, senha, tipo FROM funcionario WHERE login=? AND senha=?");
            ps.setString(1, login);
            ps.setString(2, senha);
            ResultSet resultSet = ps.executeQuery();
            Funcionario usuario = new Funcionario();
            if (resultSet.next()) {
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setTipo(resultSet.getString("tipo"));
            } else {
                return null;
            }
            FabricaConexao.fecharConexao();
            return usuario;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os funcionarios!", ex);
        }
    }
    
    public Funcionario buscarID(Integer id) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT id,nome, login, senha, tipo FROM funcionario WHERE id=?");
            ps.setInt(1, id);
            ResultSet resultSet = ps.executeQuery();
            Funcionario usuario = new Funcionario();
            if (resultSet.next()) {
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setTipo(resultSet.getString("tipo"));
            } else {
                return null;
            }
            FabricaConexao.fecharConexao();
            return usuario;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os funcionarios!", ex);
        }
    }
    
    public Funcionario buscarLogin(String login) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps = conexao.prepareStatement("SELECT id,nome, login, senha, tipo FROM funcionario WHERE login=?");
            ps.setString(1, login);
            ResultSet resultSet = ps.executeQuery();
            Funcionario usuario = new Funcionario();
            if (resultSet.next()) {
                usuario.setId(resultSet.getInt("id"));
                usuario.setNome(resultSet.getString("nome"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuario.setTipo(resultSet.getString("tipo"));
                return usuario;
            }
            FabricaConexao.fecharConexao();
            return null;

        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os funcionarios!", ex);
        }
    }

}
