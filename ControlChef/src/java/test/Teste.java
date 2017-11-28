/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import br.com.controlchef.model.Funcionario;
import br.com.controlchef.util.ConnectionFactory;
import br.com.controlchef.util.DAOFactory;
import br.com.controlchef.dao.GenericDAO;
import br.com.controlchef.util.exception.ErroSistema;

/**
 *
 * @author VashJHK
 */
public class Teste {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ErroSistema {
       Funcionario funcionario = new Funcionario();
       funcionario.setLogin("func3");
       funcionario.setSenha("func3");
       GenericDAO fun = DAOFactory.createFuncionarioDAO();
       fun.inserir(funcionario);
    }
    
}
