/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author jeferson
 */
public class Usuario {
    
    private Integer id;
    private String nome;
    private String senha;
    private boolean heAdm;
    private boolean heAtendente;
    private boolean resetSenha;
    private boolean escBarra;
    private Integer codTema;
    
    
    public Usuario(){
    }

    public Usuario(Integer id, boolean escBarra) {
        this.id = id;
        this.escBarra = escBarra;
    }

    public Usuario(Integer id, Integer codTema) {
        this.id = id;
        this.codTema = codTema;
    }
    
    public Usuario(Integer id, String nome) {
        this.id = id;
        this.nome = nome;
    }

    public Usuario(Integer id, String nome, String senha, boolean heAdm, boolean heAtendente, boolean resetSenha, boolean escBarra, Integer codTema) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.heAdm = heAdm;
        this.heAtendente = heAtendente;
        this.resetSenha = resetSenha;
        this.escBarra = escBarra;
        this.codTema = codTema;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isHeAdm() {
        return heAdm;
    }

    public void setHeAdm(boolean heAdm) {
        this.heAdm = heAdm;
    }

    public boolean isHeAtendente() {
        return heAtendente;
    }

    public void setHeAtendente(boolean heAtendente) {
        this.heAtendente = heAtendente;
    }

    public boolean isResetSenha() {
        return resetSenha;
    }

    public void setResetSenha(boolean resetSenha) {
        this.resetSenha = resetSenha;
    }

    public boolean isEscBarra() {
        return escBarra;
    }

    public void setEscBarra(boolean escBarra) {
        this.escBarra = escBarra;
    }

    public Integer getCodTema() {
        return codTema;
    }

    public void setCodTema(Integer codTema) {
        this.codTema = codTema;
    }

    

   
}
