/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

import java.util.List;

/**
 *
 * @author jeferson
 */
public class Empresa {
    private Integer id;
    private String fantasia;
    private String razao;
    private String email;
    private String numero;
    private String cnpj;
    private String inscricaoEstadual;
    private String foneFixo;
    private String celular2;
    private Cep   cep;
    private String contato;
    private List<EmpresaProcedimento> empresaProcedimento;
    
    public Empresa() {
    }

    
    public Empresa(Integer id, String fantasia) {
        this.id = id;
        this.fantasia = fantasia;
    }

    public Empresa(Integer id, String fantasia, String razao, String email, String numero, String cnpj, String inscricaoEstadual, String foneFixo, String celular2, Cep cep, String contato, List<EmpresaProcedimento> empresaProcedimento) {
        this.id = id;
        this.fantasia = fantasia;
        this.razao = razao;
        this.email = email;
        this.numero = numero;
        this.cnpj = cnpj;
        this.inscricaoEstadual = inscricaoEstadual;
        this.foneFixo = foneFixo;
        this.celular2 = celular2;
        this.cep = cep;
        this.contato = contato;
        this.empresaProcedimento = empresaProcedimento;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFantasia() {
        return fantasia;
    }

    public void setFantasia(String fantasia) {
        this.fantasia = fantasia;
    }

    public String getRazao() {
        return razao;
    }

    public void setRazao(String razao) {
        this.razao = razao;
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

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getInscricaoEstadual() {
        return inscricaoEstadual;
    }

    public void setInscricaoEstadual(String inscricaoEstadual) {
        this.inscricaoEstadual = inscricaoEstadual;
    }

    public String getFoneFixo() {
        return foneFixo;
    }

    public void setFoneFixo(String foneFixo) {
        this.foneFixo = foneFixo;
    }

    public String getCelular2() {
        return celular2;
    }

    public void setCelular2(String celular2) {
        this.celular2 = celular2;
    }

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public List<EmpresaProcedimento> getEmpresaProcedimento() {
        return empresaProcedimento;
    }

    public void setEmpresaProcedimento(List<EmpresaProcedimento> empresaProcedimento) {
        this.empresaProcedimento = empresaProcedimento;
    }
  
  
    @Override
    public String toString() {
        return fantasia ;
    }

    
    
}
