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
 * @author jeferson
 */
public class Consulta {
    
    private Integer id ;
    private Convenio convenio;
    private Paciente paciente;
    private Usuario usuario;
    private Date dataConsulta;
    private Integer status;
    private Integer sequencia;
    private String obs;
    private double valor;
    private CondPagto condPagt;
    private Tipo tipo;
    private List<ConsultaProcedimento> consultaProcedimento;
    private boolean fila;
    private boolean prioritario;
    private String codAutorizacao;
    
    
    
    
    
     public Consulta(){
        
    }

    public Consulta(Date dataConsulta, Integer status, String obs) {
        this.dataConsulta = dataConsulta;
        this.status = status;
        this.obs = obs;
    }

    public Consulta(Integer id, Convenio convenio, Paciente paciente, Usuario usuario, Date dataConsulta, Integer status, Integer sequencia, String obs, double valor, CondPagto condPagt, boolean fila) {
        this.id = id;
        this.convenio = convenio;
        this.paciente = paciente;
        this.usuario = usuario;
        this.dataConsulta = dataConsulta;
        this.status = status;
        this.sequencia = sequencia;
        this.obs = obs;
        this.valor = valor;
        this.condPagt = condPagt;
        this.fila = fila;
    }
    

    public Consulta(Integer id, Convenio convenio, Paciente paciente, Usuario usuario, Date dataConsulta, Integer status, Integer sequencia, String obs, double valor, CondPagto condPagt) {
        this.id = id;
        this.convenio = convenio;
        this.paciente = paciente;
        this.usuario = usuario;
        this.dataConsulta = dataConsulta;
        this.status = status;
        this.sequencia = sequencia;
        this.obs = obs;
        this.valor = valor;
        this.condPagt = condPagt;
    }

    public Consulta(Integer id, Convenio convenio, Paciente paciente, Usuario usuario, Date dataConsulta, Integer status, Integer sequencia, String obs, double valor, CondPagto condPagt, Tipo tipo, List<ConsultaProcedimento>  consultaProcedimento, boolean fila, boolean prioritario, String codAutorizacao) {
        this.id = id;
        this.convenio = convenio;
        this.paciente = paciente;
        this.usuario = usuario;
        this.dataConsulta = dataConsulta;
        this.status = status;
        this.sequencia = sequencia;
        this.obs = obs;
        this.valor = valor;
        this.condPagt = condPagt;
        this.tipo = tipo;
        this.consultaProcedimento = consultaProcedimento;
        this.fila = fila;
        this.prioritario = prioritario;
        this.codAutorizacao = codAutorizacao;
    }
    

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Date getDataConsulta() {
        return dataConsulta;
    }

    public void setDataConsulta(Date dataConsulta) {
        this.dataConsulta = dataConsulta;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getSequencia() {
        return sequencia;
    }

    public void setSequencia(Integer sequencia) {
        this.sequencia = sequencia;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public CondPagto getCondPagt() {
        return condPagt;
    }

    public void setCondPagt(CondPagto condPagt) {
        this.condPagt = condPagt;
    }

    public Tipo getTipo() {
        return tipo;
    }

    public void setTipo(Tipo tipo) {
        this.tipo = tipo;
    }
    
    public List<ConsultaProcedimento> getConsultaProcedimento() {
        return consultaProcedimento;
    }

    public void setConsultaProcedimento(List<ConsultaProcedimento> consultaProcedimento) {
        this.consultaProcedimento = consultaProcedimento;
    }

    public boolean isFila() {
        return fila;
    }

    public void setFila(boolean fila) {
        this.fila = fila;
    }

    public boolean isPrioritario() {
        return prioritario;
    }

    public void setPrioritario(boolean prioritario) {
        this.prioritario = prioritario;
    }

    public String getCodAutorizacao() {
        return codAutorizacao;
    }

    public void setCodAutorizacao(String codAutorizacao) {
        this.codAutorizacao = codAutorizacao;
    }

    
    
}
