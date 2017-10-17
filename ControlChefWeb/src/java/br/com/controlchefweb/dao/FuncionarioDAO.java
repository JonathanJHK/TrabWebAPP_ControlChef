package br.com.controlchefweb.dao;

import br.com.controlchefweb.entidade.Produto;
import br.com.controlchefweb.entidade.Funcionario;
import br.com.controlchefweb.util.FabricaConexao;
import br.com.controlchefweb.util.exception.ErroSistema;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FuncionarioDAO implements CrudDAO<Funcionario>{

    @Override
    public void salvar(Funcionario entidade) throws ErroSistema {
        try {
            Connection conexao = FabricaConexao.getConexao();
            PreparedStatement ps;
            if(entidade.getId() == null){
                ps = conexao.prepareStatement("INSERT INTO funcionario (login,senha) VALUES (?,?)");
            } else {
                ps = conexao.prepareStatement("update funcionario set login=?, senha=? where id=?");
                ps.setInt(3, entidade.getId());
            }
            ps.setString(1, entidade.getLogin());
            ps.setString(2, entidade.getSenha());
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
            PreparedStatement ps  = conexao.prepareStatement("delete from funcionario where id = ?");
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
            while(resultSet.next()){
                Funcionario usuario = new Funcionario();
                usuario.setId(resultSet.getInt("id"));
                usuario.setLogin(resultSet.getString("login"));
                usuario.setSenha(resultSet.getString("senha"));
                usuarios.add(usuario);
            }
            FabricaConexao.fecharConexao();
            return usuarios;
            
        } catch (SQLException ex) {
            throw new ErroSistema("Erro ao buscar os funcionarios!",ex);
        }
    }
    
}
