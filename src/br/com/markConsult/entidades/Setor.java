/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author jeferson
 */
public class Setor {
    
    private Integer id;
    private String descSetor;
    
    public Setor(){
    }

    public Setor(Integer id, String descSetor) {
        this.id = id;
        this.descSetor = descSetor;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescSetor() {
        return descSetor;
    }

    public void setDescSetor(String descSetor) {
        this.descSetor = descSetor;
    }

    @Override
    public String toString() {
        return  descSetor;
    }
    
    
    
}
