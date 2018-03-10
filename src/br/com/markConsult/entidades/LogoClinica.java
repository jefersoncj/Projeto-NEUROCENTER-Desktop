/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

import java.awt.image.BufferedImage;




/**
 *
 * @author Jeferson1
 */
public class LogoClinica {
    private String nome;
    private byte[]  figura;

    public LogoClinica() {
    }

    public LogoClinica(String nome, byte[] figura) {
        this.nome = nome;
        this.figura = figura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public byte[] getFigura() {
        return figura;
    }

    public void setFigura(byte[] figura) {
        this.figura = figura;
    }

   


   

    
    
    
}
