/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author jeferson
 */
public class Convenio {
    
    private Integer id;
    private String dsConvenio;
    private double valorConv;
    
    public Convenio(){
        
    }

    public Convenio(Integer id) {
        this.id = id;
    }

    public Convenio(Integer id, String dsConvenio) {
        this.id = id;
        this.dsConvenio = dsConvenio;
    }

      
    public Convenio(Integer id, String dsConvenio, double valorConv) {
        this.id = id;
        this.dsConvenio = dsConvenio;
        this.valorConv = valorConv;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDsConvenio() {
        return dsConvenio;
    }

    public void setDsConvenio(String dsConvenio) {
        this.dsConvenio = dsConvenio;
    }

    public double getValorConv() {
        return valorConv;
    }

    public void setValorConv(double valorConv) {
        this.valorConv = valorConv;
    }

    
    
   
}
