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
public class EmpresaProcedimento {
    private Integer id;
    private Boolean selecao;
    private Empresa empresa;
    private Procedimento Procedimento;
    private Double valor;

    public EmpresaProcedimento() {
    }

    public EmpresaProcedimento(Integer id, Empresa empresa, Procedimento Procedimento, Double valor) {
        this.id = id;
        this.empresa = empresa;
        this.Procedimento = Procedimento;
        this.valor = valor;
    }

    public EmpresaProcedimento(Empresa empresa, Procedimento Procedimento) {
        this.empresa = empresa;
        this.Procedimento = Procedimento;
    }

    public EmpresaProcedimento(Integer id, Empresa empresa, Procedimento Procedimento) {
        this.id = id;
        this.empresa = empresa;
        this.Procedimento = Procedimento;
    }

    public EmpresaProcedimento(Integer id, Boolean selecao, Empresa empresa, Procedimento Procedimento, Double valor) {
        this.id = id;
        this.selecao = selecao;
        this.empresa = empresa;
        this.Procedimento = Procedimento;
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

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Procedimento getProcedimento() {
        return Procedimento;
    }

    public void setProcedimento(Procedimento Procedimento) {
        this.Procedimento = Procedimento;
    }

    public Double getValor() {
        return valor;
    }

    public void setValor(Double valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return Procedimento.getDsProcedimento() ;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
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
        final EmpresaProcedimento other = (EmpresaProcedimento) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }


    

    
}
