/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author jeferson
 */
public class CondPagto {
    
    private Integer id;
    private String condPagt;
    
    public CondPagto(){
        
    }

    public CondPagto(Integer id, String condPagt) {
        this.id = id;
        this.condPagt = condPagt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCondPagt() {
        return condPagt;
    }

    public void setCondPagt(String condPagt) {
        this.condPagt = condPagt;
    }
    
}
