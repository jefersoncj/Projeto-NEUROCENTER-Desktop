/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author Jeferson1
 */
public class Tipo {
    private Integer id;
    private String tipoCon;

    public Tipo() {
    }

    public Tipo(Integer id, String tipoCon) {
        this.id = id;
        this.tipoCon = tipoCon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTipoCon() {
        return tipoCon;
    }

    public void setTipoCon(String tipoCon) {
        this.tipoCon = tipoCon;
    }
    
    
}
