/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controlchef.dao;

import java.util.List;

/**
 *
 * @author VashJHK
 */
public interface GenericDAO<E> {
    public void inserir(E entidade);
    public void remover(E entidade);
    public List<E> listar();
    public E buscar(int id);
    public void editar(E entidade);
}
