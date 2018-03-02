/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author Jeferson
 */
public class Periodicidade {
    
    private Integer id;
    private String descPeriodicidade;

    public Periodicidade() {
    }

    public Periodicidade(Integer id, String descPeriodicidade) {
        this.id = id;
        this.descPeriodicidade = descPeriodicidade;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescPeriodicidade() {
        return descPeriodicidade;
    }

    public void setDescPeriodicidade(String descPeriodicidade) {
        this.descPeriodicidade = descPeriodicidade;
    }

    @Override
    public String toString() {
        return descPeriodicidade;
    }
    
    
    
    
}
