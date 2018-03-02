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
public class Exame {
    
    private Integer id;
    private String descExame;
    private double valor;
    private String resultado;
    private Integer idRg;

    public Exame() {
    }

    public Exame(Integer id, String descExame, double valor, Integer idRg) {
        this.id = id;
        this.descExame = descExame;
        this.valor = valor;
        this.idRg = idRg;
    }

    public Exame(Integer id, String descExame, double valor) {
        this.id = id;
        this.descExame = descExame;
        this.valor = valor;
    }
    

    public Exame(Integer id, String descExame, double valor, String resultado) {
        this.id = id;
        this.descExame = descExame;
        this.valor = valor;
        this.resultado = resultado;
    }

    public Exame(Integer id, String descExame, double valor, String resultado, Integer idRg) {
        this.id = id;
        this.descExame = descExame;
        this.valor = valor;
        this.resultado = resultado;
        this.idRg = idRg;
    }    
  

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescExame() {
        return descExame;
    }

    public void setDescExame(String descExame) {
        this.descExame = descExame;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    
    public Integer getIdRg() {
        return idRg;
    }

    public void setIdRg(Integer idRg) {
        this.idRg = idRg;
    }
    
    

    
   
}
