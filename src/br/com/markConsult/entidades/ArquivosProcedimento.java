/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.markConsult.entidades;


import java.sql.Date;

/**
 *
 * @author Jeferson1
 */
public class ArquivosProcedimento {
    
    
    private  Integer id;
    private ConsultaProcedimento consultaProcedimento;
    private Date dataCadastro;
    private String observacao;
    private String arquivo;
    

    public ArquivosProcedimento() {
    }

    public ArquivosProcedimento(Integer id, ConsultaProcedimento consultaProcedimento, Date dataCadastro, String observacao, String arquivo) {
        this.id = id;
        this.consultaProcedimento = consultaProcedimento;
        this.dataCadastro = dataCadastro;
        this.observacao = observacao;
        this.arquivo = arquivo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ConsultaProcedimento getConsultaProcedimento() {
        return consultaProcedimento;
    }

    public void setConsultaProcedimento(ConsultaProcedimento consultaProcedimento) {
        this.consultaProcedimento = consultaProcedimento;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getArquivo() {
        return arquivo;
    }

    public void setArquivo(String arquivo) {
        this.arquivo = arquivo;
    }

  
}
