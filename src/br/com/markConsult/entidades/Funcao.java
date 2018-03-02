/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author jeferson
 */
public class Funcao {
    
    private Integer id;
    private String descFuncao;
    
    public Funcao(){
        
    }

    public Funcao(Integer id, String descFuncao) {
        this.id = id;
        this.descFuncao = descFuncao;
    }

    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescFuncao() {
        return descFuncao;
    }

    public void setDescFuncao(String descFuncao) {
        this.descFuncao = descFuncao;
    }

    @Override
    public String toString() {
        return descFuncao;
    }

   
    
}
