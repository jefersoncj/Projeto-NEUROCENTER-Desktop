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
public class ArquivosPaciente {
    
    
    private  Integer id;
    private Paciente paciente;
    private Date dataCadastro;
    private String observacao;
    private String imagem;
    private  Album idAlbum;

    public ArquivosPaciente() {
    }

    public ArquivosPaciente(Integer id, Paciente paciente, Date dataCadastro, String observacao, String imagem, Album idAlbum) {
        this.id = id;
        this.paciente = paciente;
        this.dataCadastro = dataCadastro;
        this.observacao = observacao;
        this.imagem = imagem;
        this.idAlbum = idAlbum;
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

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Album getIdAlbum() {
        return idAlbum;
    }

    public void setIdAlbum(Album idAlbum) {
        this.idAlbum = idAlbum;
    }
    
}
