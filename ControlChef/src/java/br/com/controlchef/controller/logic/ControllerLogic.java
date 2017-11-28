/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchef.controller.logic;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Angelo
 */
public interface ControllerLogic {
    //public void executar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public void inserir(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public void editar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public void editarPopular(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public void remover(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    public void listar(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
    
}

