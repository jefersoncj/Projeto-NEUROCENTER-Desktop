/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author Jeferson1
 */
public class TipoRisco {
    private Integer id;
    private String descTipoRisco;

    public TipoRisco() {
    }

    public TipoRisco(Integer id, String descTipoRisco) {
        this.id = id;
        this.descTipoRisco = descTipoRisco;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescTipoRisco() {
        return descTipoRisco;
    }

    public void setDescTipoRisco(String descTipoRisco) {
        this.descTipoRisco = descTipoRisco;
    }

    @Override
    public String toString() {
        return  descTipoRisco;
    }

  
  
}
