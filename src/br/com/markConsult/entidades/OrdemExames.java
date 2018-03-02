/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

import java.sql.Date;
import java.util.List;

/**
 *
 * @author Jeferson
 */
public class OrdemExames {
    
    private Integer id;
    private Date data;
    private Paciente paciente;
    private Empresa empresa; 
    private boolean aso;
    private Periodicidade periodicidade;
    private List<Risco> riscos;
    private List<Exame> exames;

    public OrdemExames() {
    }

    public OrdemExames(Integer id, Date data, Paciente paciente, Empresa empresa, boolean aso, Periodicidade periodicidade, List<Risco> riscos, List<Exame> exames) {
        this.id = id;
        this.data = data;
        this.paciente = paciente;
        this.empresa = empresa;
        this.aso = aso;
        this.periodicidade = periodicidade;
        this.riscos = riscos;
        this.exames = exames;
    }

    public OrdemExames(Integer id, Date data, Empresa empresa, Periodicidade periodicidade) {
        this.id = id;
        this.data = data;
        this.empresa = empresa;
        this.periodicidade = periodicidade;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public boolean isAso() {
        return aso;
    }

    public void setAso(boolean aso) {
        this.aso = aso;
    }

    public Periodicidade getPeriodicidade() {
        return periodicidade;
    }

    public void setPeriodicidade(Periodicidade periodicidade) {
        this.periodicidade = periodicidade;
    }

    public List<Risco> getRiscos() {
        return riscos;
    }

    public void setRiscos(List<Risco> riscos) {
        this.riscos = riscos;
    }

    public List<Exame> getExames() {
        return exames;
    }

    public void setExames(List<Exame> exames) {
        this.exames = exames;
    }
   
    
   
}
