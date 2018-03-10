/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.entidades;

/**
 *
 * @author jeferson
 */
public class Sessao {
    private static Sessao instance = null;  
   private Usuario usuario;  
   private Clinica clinica;  
  
   private Sessao(){  
   }  
  
   public void setUsuario(Usuario usuario){  
      this.usuario = usuario;  
   }  
   public void setClinica(Clinica clinica){  
      this.clinica = clinica;  
   }
   public Usuario getUsuario(){  
       return usuario;  
   }  
   public Clinica getClinica(){  
       return clinica;  
   }  
   public static Sessao getInstance(){  
         if(instance == null){  
               instance = new Sessao();  
         }  
        return instance;  
   }  
}
