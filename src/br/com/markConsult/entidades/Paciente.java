/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

import java.sql.Date;

/**
 *
 * @author jeferson
 */
public class Paciente {
    private Integer id;
    private String numConvenio;
    private Convenio convenio;
    private String nome;
    private String email;
    private String numero;
    private String complemento;
    private String cpf;
    private String rg;
    private Date dataNasc;
    private String tipoSangue;
    private String foneFixo;
    private String celular1;
    private String celular2;
    private String celular3;
    private String obs;
    private Cep   cep;
    private boolean desabilitado;
    private Date dataCadastro;
    private Date dataUltConsu;
    private Integer status;
    private String nomeMae;
    private String nomePai;
    private Empresa empresa;
    private Funcao funcao;
            
    public Paciente(){
        
    }

    public Paciente(Integer id, String numConvenio, String nome, Empresa empresa, Funcao funcao ) {
        this.id = id;
        this.numConvenio = numConvenio;
        this.nome = nome;
        this.empresa = empresa;
        this.funcao = funcao;
    }

    public Paciente(Integer id, String nome, Empresa empresa) {
        this.id = id;
        this.nome = nome;
        this.empresa = empresa;
    }

    public Paciente(Integer id, String nome, Empresa empresa, Funcao funcao, Convenio convenio) {
        this.id = id;
        this.nome = nome;
        this.empresa = empresa;
        this.funcao = funcao;
        this.convenio = convenio;
    }   

    
    public Paciente(Integer id, String numConvenio, Convenio convenio, String nome, String email, String numero, String complemento, String cpf, String rg, Date dataNasc, String tipoSangue, String foneFixo, String celular1, String celular2, String celular3, String obs, Cep cep, boolean desabilitado, String nomeMae, String nomePai, Empresa empresa, Funcao funcao) {
        this.id = id;
        this.numConvenio = numConvenio;
        this.convenio = convenio;
        this.nome = nome;
        this.email = email;
        this.numero = numero;
        this.complemento = complemento;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNasc = dataNasc;
        this.tipoSangue = tipoSangue;
        this.foneFixo = foneFixo;
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.celular3 = celular3;
        this.obs = obs;
        this.cep = cep;
        this.desabilitado = desabilitado;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.empresa = empresa;
        this.funcao = funcao;
    }

    

    public Paciente(Integer id, String numConvenio, Convenio convenio, String nome, String email, String numero, String complemento, String cpf, String rg, Date dataNasc, String tipoSangue, String foneFixo, String celular1, String celular2, String celular3, String obs, Cep cep, boolean desabilitado, Date dataCadastro, String nomeMae, String nomePai, Empresa empresa, Funcao funcao) {
        this.id = id;
        this.numConvenio = numConvenio;
        this.convenio = convenio;
        this.nome = nome;
        this.email = email;
        this.numero = numero;
        this.complemento = complemento;
        this.cpf = cpf;
        this.rg = rg;
        this.dataNasc = dataNasc;
        this.tipoSangue = tipoSangue;
        this.foneFixo = foneFixo;
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.celular3 = celular3;
        this.obs = obs;
        this.cep = cep;
        this.desabilitado = desabilitado;
        this.dataCadastro = dataCadastro;
        this.nomeMae = nomeMae;
        this.nomePai = nomePai;
        this.empresa = empresa;
        this.funcao = funcao;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNumConvenio() {
        return numConvenio;
    }

    public void setNumConvenio(String numConvenio) {
        this.numConvenio = numConvenio;
    }

    public Convenio getConvenio() {
        return convenio;
    }

    public void setConvenio(Convenio convenio) {
        this.convenio = convenio;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRg() {
        return rg;
    }

    public void setRg(String rg) {
        this.rg = rg;
    }

    public Date getDataNasc() {
        return dataNasc;
    }

    public void setDataNasc(Date dataNasc) {
        this.dataNasc = dataNasc;
    }

    public String getTipoSangue() {
        return tipoSangue;
    }

    public void setTipoSangue(String tipoSangue) {
        this.tipoSangue = tipoSangue;
    }

    public String getFoneFixo() {
        return foneFixo;
    }

    public void setFoneFixo(String foneFixo) {
        this.foneFixo = foneFixo;
    }

    public String getCelular1() {
        return celular1;
    }

    public void setCelular1(String celular1) {
        this.celular1 = celular1;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public String getCelular3() {
        return celular3;
    }

    public void setCelular3(String celular3) {
        this.celular3 = celular3;
    }

    public String getObs() {
        return obs;
    }

    public void setObs(String obs) {
        this.obs = obs;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

  

    public boolean isDesabilitado() {
        return desabilitado;
    }

    public void setDesabilitado(boolean desabilitado) {
        this.desabilitado = desabilitado;
    }

    public Date getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Date dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public Date getDataUltConsu() {
        return dataUltConsu;
    }

    public void setDataUltConsu(Date dataUltConsu) {
        this.dataUltConsu = dataUltConsu;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getNomeMae() {
        return nomeMae;
    }

    public void setNomeMae(String nomeMae) {
        this.nomeMae = nomeMae;
    }

    public String getNomePai() {
        return nomePai;
    }

    public void setNomePai(String nomePai) {
        this.nomePai = nomePai;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public Funcao getFuncao() {
        return funcao;
    }

    public void setFuncao(Funcao funcao) {
        this.funcao = funcao;
    }
}
