/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author Jeferson
 */
public class Album {

    private Integer id;
    Paciente paciente;
    private String descAlbum;

    public Album() {
    }

    public Album(Integer id, String descAlbum) {
        this.id = id;
        this.descAlbum = descAlbum;
    }

    
    public Album(Integer id, Paciente paciente, String descAlbum) {
        this.id = id;
        this.paciente = paciente;
        this.descAlbum = descAlbum;
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescAlbum() {
        return descAlbum;
    }

    public void setDescAlbum(String descAlbum) {
        this.descAlbum = descAlbum;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }
    
    

    @Override
    public String toString() {
        return descAlbum;
    }

     
}
