/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

import java.util.Objects;

/**
 *
 * @author Jeferson
 */
public class ConsultaProcedimento {
    private Integer id;
    private Boolean selecao;
    private Consulta consulta;
    private EmpresaProcedimento empresaProcedimento;
    private Double valor;
    private String status; 
    public ConsultaProcedimento() {
    }

    public ConsultaProcedimento(Integer id, Consulta consulta, EmpresaProcedimento empresaProcedimento, Double valor) {
        this.id = id;
        this.consulta = consulta;
        this.empresaProcedimento = empresaProcedimento;
        this.valor = valor;
    }

    public ConsultaProcedimento(Integer id, EmpresaProcedimento empresaProcedimento, Double valor) {
        this.id = id;
        this.empresaProcedimento = empresaProcedimento;
        this.valor = valor;
    }

    public ConsultaProcedimento(Integer id, Boolean selecao, EmpresaProcedimento empresaProcedimento, Double valor, String status) {
        this.id = id;
        this.selecao = selecao;
        this.empresaProcedimento = empresaProcedimento;
        this.valor = valor;
        this.status = status;
    }
    
    

    public ConsultaProcedimento(Integer id, Boolean selecao, Consulta consulta, EmpresaProcedimento empresaProcedimento, Double valor) {
        this.id = id;
        this.selecao = selecao;
        this.consulta = consulta;
        this.empresaProcedimento = empresaProcedimento;
        this.valor = valor;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getSelecao() {
        return selecao;
    }

    public void setSelecao(Boolean selecao) {
        this.selecao = selecao;
    }

    public Consulta getConsulta() {
        return consulta;
    }

    public void setConsulta(Consulta consulta) {
        this.consulta = consulta;
    }

    public EmpresaProcedimento getEmpresaProcedimento() {
        return empresaProcedimento;
    }

    public void setEmpresaProcedimento(EmpresaProcedimento empresaProcedimento) {
        this.empresaProcedimento = empresaProcedimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.id);
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
        final ConsultaProcedimento other = (ConsultaProcedimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }
    

    
}
