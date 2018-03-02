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
public class Risco {
    
    private Integer id;
    private TipoRisco tipoRisco;
    private String descRisco;
    private Integer idRg;
    

    public Risco() {
    }

    public Risco(Integer id, TipoRisco tipoRisco, String descRisco) {
        this.id = id;
        this.tipoRisco = tipoRisco;
        this.descRisco = descRisco;
    }

    public Risco(Integer id, TipoRisco tipoRisco, String descRisco, Integer idRg) {
        this.id = id;
        this.tipoRisco = tipoRisco;
        this.descRisco = descRisco;
        this.idRg = idRg;
    }
    
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public TipoRisco getTipoRisco() {
        return tipoRisco;
    }

    public void setTipoRisco(TipoRisco tipoRisco) {
        this.tipoRisco = tipoRisco;
    }

    public String getDescRisco() {
        return descRisco;
    }
    public void setDescRisco(String descRisco) {
        this.descRisco = descRisco;
    }

    public Integer getIdRg() {
        return idRg;
    }

    public void setIdRg(Integer idRg) {
        this.idRg = idRg;
    }
    
    
}
