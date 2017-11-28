/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchef.controller.logic;


import br.com.controlchef.dao.JDBCFuncionarioDAO;
import br.com.controlchef.model.Funcionario;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.controlchef.dao.GenericDAO;
import br.com.controlchef.util.exception.ErroSistema;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Angelo
 */
public class ControllerLogicFuncionario implements ControllerLogic {

    @Override
    public void inserir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Funcionario funcionario = new Funcionario();
            
            funcionario.setLogin(request.getParameter("login"));
            funcionario.setSenha(request.getParameter("senha"));
            
            //request.getSession().setAttribute("pessoa", pessoa);
            
            GenericDAO f = new JDBCFuncionarioDAO();
            f.inserir(funcionario);
            
            request.setAttribute("msg", "Gravado com sucesso!"); 
            request.getRequestDispatcher("login.jsp").forward(request, response);
        } catch (ErroSistema ex) {
            Logger.getLogger(ControllerLogicFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Funcionario funcionario = new Funcionario();
            
            funcionario.setId(Integer.parseInt(request.getParameter("id")));
            funcionario.setLogin(request.getParameter("login"));
            funcionario.setSenha(request.getParameter("senha"));
            
            /*
            request.getSession().setAttribute("pessoa", pessoa);
            */
            GenericDAO f = new JDBCFuncionarioDAO();
            f.editar(funcionario);
            request.setAttribute("msg", "Editado com sucesso!"); 
            request.getRequestDispatcher("/ControlChef/Controller?classe=ControllerLogicFuncionario&metodo=listar").forward(request, response);
        } catch (ErroSistema ex) {
            Logger.getLogger(ControllerLogicFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void editarPopular(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            GenericDAO f = new JDBCFuncionarioDAO();
            
            Funcionario p = (Funcionario) f.buscar(id);
            
            request.setAttribute("funcionario", p);
            request.getRequestDispatcher("editFuncionario").forward(request, response);
        } catch (ErroSistema ex) {
            Logger.getLogger(ControllerLogicFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public void remover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(request.getParameter("id"));
            
            GenericDAO f = new JDBCFuncionarioDAO();
            f.remover(id);
            request.getRequestDispatcher("/ControlChef/Controller?classe=ControllerLogicFuncionario&metodo=listar").forward(request, response);
        } catch (ErroSistema ex) {
            Logger.getLogger(ControllerLogicFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            GenericDAO f = new JDBCFuncionarioDAO();
            
            request.setAttribute("funcionarios", f.listar());
            request.getRequestDispatcher("listaFuncionario").forward(request, response);
        } catch (ErroSistema ex) {
            Logger.getLogger(ControllerLogicFuncionario.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
