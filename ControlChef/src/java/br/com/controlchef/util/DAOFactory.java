/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchef.util;

import br.com.controlchef.dao.JDBCFuncionarioDAO;
import br.com.controlchef.dao.GenericDAO;
import br.com.controlchef.dao.JDBCProdutoDAO;
import br.com.controlchef.util.exception.ErroSistema;


/**
 *
 * @author VashJHK
 */
public class DAOFactory {
    public static GenericDAO createFuncionarioDAO() throws ErroSistema{
        return new JDBCFuncionarioDAO();
    }
    
    public static GenericDAO createProdutoDAO() throws ErroSistema{
        return new JDBCProdutoDAO();
    }
}
