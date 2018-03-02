/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.JDialog;
import javax.swing.JProgressBar;

/**
 *
 * @author jeferson
 */
public class Progress extends JDialog{
     public Progress() {  
        this.setTitle("Aguarde...");  
        URL url = this.getClass().getResource("/br/com/markConsult/imagens/help.png");  
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);  
        this.setIconImage(imagemTitulo); 
        setModal(false); 
        JProgressBar progress = new JProgressBar();  
        progress.setIndeterminate(true);  
        progress.setSize(200,20);  
        getContentPane().add(progress);  
        pack();  
        Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();  
        this.setLocation((tela.width - this.getSize().width) / 2,  
             (tela.height - this.getSize().height) / 2);  
    }
}
