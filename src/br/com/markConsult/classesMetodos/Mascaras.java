/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.classesMetodos;

import javax.swing.text.AttributeSet;  
import javax.swing.text.BadLocationException;  
import javax.swing.text.PlainDocument;  
  
  
public class Mascaras extends PlainDocument {  
  
    private int numMaximoDigitos = 30;  
  
    public Mascaras(int numMaximoDigitos) {  
        this.numMaximoDigitos = numMaximoDigitos;  
    }  
  
    @Override  
    public void insertString(int offs, String str, AttributeSet a) throws BadLocationException {  
  
        //Recupera o valor que j� est� no campo  
        String texto = getText(0, getLength());  
  
        //Verifica se o novo elemento � um numero  
        str = str.replace(",", ".");  
        if (str.length() > 1 && str.charAt(str.length() - 1) == '.') {  
            str = str + "0";  
        }  
        if (str.length() > 2 && str.charAt(str.length() - 2) == '.') {  
            str = str + "0";  
        }  
        str = str.replace(".", "");  
  
        for (int i = 0; i < str.length(); i++) {  
            char c = str.charAt(i);  
            if (!Character.isDigit(c)) {  
                return;  
            }  
        }  
  
        //Limita o numero de caracteres  
        if (texto.length() < numMaximoDigitos) {  
  
            //Limpa o campo  
            super.remove(0, getLength());  
  
            //Retira pontos e virgula e acrescenta o novo numero  
            texto = texto.replace(".", "").replace(",", "");  
            StringBuffer s = new StringBuffer(texto + str);  
  
            //Retira os zeros � esquerda  
            if (s.length() > 0 && s.charAt(0) == '0') {  
                s.deleteCharAt(0);  
                if (s.length() > 0 && s.charAt(0) == '0') {  
                    s.deleteCharAt(0);  
                    if (s.length() > 0 && s.charAt(0) == '0') {  
                        s.deleteCharAt(0);  
                    }  
                }  
            }  
  
            //Coloca os zeros � esquerda  
            if (s.length() < 3) {  
                if (s.length() < 1) {  
                    s.insert(0, "000");  
                } else if (s.length() < 2) {  
                    s.insert(0, "00");  
                } else {  
                    s.insert(0, "0");  
                }  
            }  
  
            //Coloca virgula e ponto  
            s.insert(s.length() - 2, ",");  
            if (s.length() > 6) {  
                s.insert(s.length() - 6, ".");  
            }  
            if (s.length() > 10) {  
                s.insert(s.length() - 10, ".");  
            }  
  
            super.insertString(0, s.toString(), a);  
        }  
    }  
  
    @Override  
    public void remove(int offset, int length) throws BadLocationException {  
        super.remove(offset, length);  
        String texto = getText(0, getLength());  
        texto = texto.replace(",", "");  
        texto = texto.replace(".", "");  
        super.remove(0, getLength());  
        insertString(0, texto, null);  
    }  
}  