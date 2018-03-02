/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;


import br.com.markConsult.entidades.CondPagto;
import java.util.List;

/**
 *
 * @author jeferson
 */
public interface ICadCondPagtoDAO {
    
    
     /**
     *
     * @param condPagto
     * @return
     */
    public Integer inserir(CondPagto condPagto);
     
     
     /**
     *
     * @param condPagto
     * @return
     */
    public boolean alterar(CondPagto condPagto);
     
     /**
     *
     * @param id
     * @return
     */
    public boolean remover(Integer id);
     
     /**
     *
     * @param id
     * @return
     */
    public CondPagto procuraPorID(Integer id);
     
     /**
     *
     * @param nome
     * @param tipo
     * @return
     */
    public List<CondPagto> BuscaCondPagto(String nome, char tipo); 
         /**
     *
     * @return
     */
    public CondPagto mostrarUltimo();
    /**
     *
     * @return
     */
    public CondPagto mostrarPrimeiro();
    /**
     *
     * @param id
     * @return
     */
    public CondPagto mostrarProximo(int id);
    /**
     *
     * @param id
     * @return
     */
    public CondPagto mostrarAnterior(int id);
    
}
