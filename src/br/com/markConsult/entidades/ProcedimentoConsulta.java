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
public class ProcedimentoConsulta {
    private Consulta consulta;
    private Procedimento procedimento;
    private Double valor;

    public ProcedimentoConsulta() {
    }
    public ProcedimentoConsulta(Consulta consulta, Procedimento procedimento, Double valor) {
        this.consulta = consulta;
        this.procedimento = procedimento;
        this.valor = valor;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public Procedimento getProcedimento() {
        return procedimento;
    }

    public void setProcedimento(Procedimento procedimento) {
        this.procedimento = procedimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }
}
