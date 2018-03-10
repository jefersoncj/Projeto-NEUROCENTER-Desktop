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
public class Anamnese {
    
    
    private  Integer id;
    private Paciente paciente;
    private Date dataCadastro;
    private String descricao;

    public Anamnese() {
    }

    public Anamnese(Integer id, Paciente paciente, Date dataCadastro, String descricao) {
        this.id = id;
        this.paciente = paciente;
        this.dataCadastro = dataCadastro;
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

   
}
