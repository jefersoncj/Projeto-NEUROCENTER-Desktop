/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author jeferson
 */
public class Clinica {
    private Integer id;
    private String fantasia;
    private String razao;
    private String email;
    private String numero;
    private String cnpj;
    private String foneFixo;
    private String celular1;
    private String celular2;
    private Cep   cep;    
    private LogoClinica logoClinica;

    public Clinica() {
    }
  
    
    

    public Clinica(Integer id, String fantasia, String razao, String email, String numero, String cnpj, String foneFixo, String celular1, String celular2, Cep cep) {
        this.id = id;
        this.fantasia = fantasia;
        this.razao = razao;
        this.email = email;
        this.numero = numero;
        this.cnpj = cnpj;
        this.foneFixo = foneFixo;
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.cep = cep;
    }

    
    
    public Clinica(Integer id, String fantasia, String razao, String email, String numero, String cnpj, String foneFixo, String celular1, String celular2, Cep cep, LogoClinica logoClinica) {
        this.id = id;
        this.fantasia = fantasia;
        this.razao = razao;
        this.email = email;
        this.numero = numero;
        this.cnpj = cnpj;
        this.foneFixo = foneFixo;
        this.celular1 = celular1;
        this.celular2 = celular2;
        this.cep = cep;
        this.logoClinica = logoClinica;
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

    public Cep getCep() {
        return cep;
    }

    public void setCep(Cep cep) {
        this.cep = cep;
    }

    public LogoClinica getLogoClinica() {
        return logoClinica;
    }

    public void setLogoClinica(LogoClinica logoClinica) {
        this.logoClinica = logoClinica;
    }

    @Override
    public String toString() {
        return fantasia ;
    }

    
    
}
