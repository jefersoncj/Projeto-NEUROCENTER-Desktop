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
   private MinhaEmpresa minhaEmpresa;  
  
   private Sessao(){  
   }  
  
   public void setUsuario(Usuario usuario){  
      this.usuario = usuario;  
   }  
   public void setMinhaEmpresa(MinhaEmpresa empresa){  
      this.minhaEmpresa = empresa;  
   }
   public Usuario getUsuario(){  
       return usuario;  
   }  
   public MinhaEmpresa getMinhaEmpresa(){  
       return minhaEmpresa;  
   }  
   public static Sessao getInstance(){  
         if(instance == null){  
               instance = new Sessao();  
         }  
        return instance;  
   }  
}
