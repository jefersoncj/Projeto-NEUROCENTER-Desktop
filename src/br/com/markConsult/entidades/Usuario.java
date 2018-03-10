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
    private String crm;
    private String nomeMedico;
    private Especialidade especialidade;
    private boolean heAdm;
    private boolean heMedico;
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

    public Usuario(Integer id, String nome, String senha, String crm, String nomeMedico, Especialidade especialidade, boolean heAdm, boolean heMedico, boolean heAtendente, boolean resetSenha, boolean escBarra, Integer codTema) {
        this.id = id;
        this.nome = nome;
        this.senha = senha;
        this.crm = crm;
        this.nomeMedico = nomeMedico;
        this.especialidade = especialidade;
        this.heAdm = heAdm;
        this.heMedico = heMedico;
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

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public boolean isHeAdm() {
        return heAdm;
    }

    public void setHeAdm(boolean heAdm) {
        this.heAdm = heAdm;
    }

    public boolean isHeMedico() {
        return heMedico;
    }

    public void setHeMedico(boolean heMedico) {
        this.heMedico = heMedico;
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
