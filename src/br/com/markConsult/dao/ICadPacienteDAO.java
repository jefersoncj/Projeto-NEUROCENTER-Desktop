/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.dao;

import br.com.markConsult.entidades.Cep;
import br.com.markConsult.entidades.Paciente;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author jeferson
 */
public interface ICadPacienteDAO {
     

    /**
     *
     * @param paciente
     * @return
     * @throws java.sql.SQLException
     */
    public Integer insePaciente(Paciente paciente)throws SQLException;


    /**
     *
     * @param consulta
     * @return
     */
    public boolean altPaciente(Paciente consulta);

    /**
     *
     * @param id
     * @return 
     */
    public boolean rmPaciente(int id);


    /**
     *
     * @param dado
     * @param tipo
     * @return
     */
    public List<Paciente> buscaPaciente(String dado, char tipo);

 
    /**
     *
     * @param id
     * @return
     */
    public Paciente buscaPacientePorConsulta(String id);
    /**
     *
     * @param id
     * @return
     */
    public Paciente buscaPacientePorId(int id);
    
 
    /**
     *
     * @param id
     * @return
     */
    public Cep buscCep(int id);
    
    public Paciente mostrarUltimo();
    /**
     *
     * @return
     */
    public Paciente mostrarPrimeiro();
    /**
     *
     * @param id
     * @return
     */
    public Paciente mostrarProximo(int id);
    /**
     *
     * @param id
     * @return
     */
    public Paciente mostrarAnterior(int id);

}
