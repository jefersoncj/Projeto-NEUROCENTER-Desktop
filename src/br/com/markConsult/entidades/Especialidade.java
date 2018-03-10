/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author jeferson
 */
public class Especialidade {
    
    private Integer id;
    private String especialidade;
    
    public Especialidade(){
        
    }

    public Especialidade(Integer id, String especialidade) {
        this.id = id;
        this.especialidade = especialidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    
}
