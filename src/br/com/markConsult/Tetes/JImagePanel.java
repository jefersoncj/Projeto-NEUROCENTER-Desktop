/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.Tetes;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author Jeferson1
 */
public class JImagePanel extends JPanel {  
    Image background = null;  
      
    public JImagePanel(Image img) {  
        if (img == null)  
            throw new NullPointerException("Sem imagem para processar!");  
        this.background = img;  
    }  
      
    public JImagePanel(File imgSrc) throws IOException {  
        this(ImageIO.read(imgSrc));  
    }  
  
    public JImagePanel(String fileName) throws IOException {  
        this(new File(fileName));  
    }  
      
    protected void paintComponent(Graphics g) {          
    super.paintComponent(g);  
        Graphics2D g2d = (Graphics2D)g.create();  
        g2d.drawImage(background, 0, 0, this.getWidth(), this.getHeight(), null);  
        g2d.dispose();          
    }  
 
}
