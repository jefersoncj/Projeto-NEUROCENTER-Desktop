/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.markConsult.dao;

import br.com.markConsult.entidades.Consulta;
import java.sql.Date;
import java.util.List;


/**
 *
 * @author jeferson
 */
public interface ICadConsultasDAO {
    
    /**
     *
     * @param consulta
     * @return
     */
    public Integer inseConsult(Consulta consulta);

    /**
     *
     * @param consulta
     * @return
     */
    public boolean altConsult(Consulta consulta);
    /**
     *
     * @param consulta
     * @return
     */
    public boolean altStatConsult(List<Consulta> consulta);
    /**
     *
     * @param consulta
     * @return
     */
    public boolean altSeqConsult(List<Consulta> consulta);
    /**
     *
     * @param consulta
     * @return
     */
    public boolean altStatConsult2(Consulta consulta);
  
    /**
     *
     * @param id
     */
    public void rmConsult(int id);

    /**
     *
     * @param dado
     * @param tipo
     * @param status
     * @return
     */
    public List<Consulta> buscaConsultas(String dado, char tipo, String status);
    /**
     *
     * @param coluna
     * @param dados
     * @param status
     * @return
     */
    public List<Consulta> buscaConPstat(int coluna, String dados, String status);

    /**
     *
     * @param data
     * @return
     */
    public List<Consulta> buscConsPdata(Date data);
    
    /**
     *
     * @param data
     * @return
     */
    //public int buscPTurnData(int turno, Date data); 
    
    /**
     *
     * @param idPaciente
     * @return
     */
    public Consulta buscConsPCli(int idPaciente);  
  
}
