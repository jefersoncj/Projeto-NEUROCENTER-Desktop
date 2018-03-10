/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

import java.util.Objects;

/**
 *
 * @author jeferson
 */
public class Procedimento {
    private Integer id;
    private Boolean selecao;
    private String dsProcedimento;
    
    public Procedimento(){
        
    }

    public Procedimento(Integer id) {
        this.id = id;
    }

    public Procedimento(Integer id, String dsProcedimento) {
        this.id = id;
        this.dsProcedimento = dsProcedimento;
    }

    public Procedimento(Integer id, Boolean selecao, String dsProcedimento) {
        this.id = id;
        this.selecao = selecao;
        this.dsProcedimento = dsProcedimento;
      
    } 
    public Procedimento(Integer id, String dsProcedimento, double valorProced) {
        this.id = id;
        this.dsProcedimento = dsProcedimento;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean isSelecao() {
        return selecao;
    }

    public void setSelecao(Boolean selecao) {
        this.selecao = selecao;
    }

    public String getDsProcedimento() {
        return dsProcedimento;
    }

    public void setDsProcedimento(String dsProcedimento) {
        this.dsProcedimento = dsProcedimento;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Procedimento other = (Procedimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
  
}
