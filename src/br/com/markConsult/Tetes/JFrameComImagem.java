/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.Tetes;

/**
 *
 * @author Jeferson1
 */
import java.awt.Graphics;  
import java.awt.Graphics2D;  
import java.awt.image.BufferedImage;  
import java.io.IOException;  
  
import javax.imageio.ImageIO;  
import javax.swing.JFrame;  
import org.ietf.jgss.GSSContext;  
  
@SuppressWarnings("serial")  
public class JFrameComImagem extends JFrame  
{  
          
    private BufferedImage img;   
    public JFrameComImagem(BufferedImage img)   
        {  
        super("JFrame com imagem");  
        setSize(800,600);  
        setLocationRelativeTo(null);  
        this.img = img;       
    }  
      
    @Override  
    public void paint(Graphics g) {  
        Graphics2D g2d = (Graphics2D) g.create();         
        g2d.drawImage(img, 100, 100, null);  
        g.dispose();  
                  
    }  
          
          
        public void gXYZ()  
        {  
        Graphics2D g = img.createGraphics();  
              g.drawLine(50, 25, 30, 40);        
              
              
            
        }  
      
    public static void main(String[] args)   
        {          
        try {  
            BufferedImage img = ImageIO.read(JFrameComImagem.class.getResourceAsStream("report.png"));  
            new JFrameComImagem(img).setVisible(true);  
        } catch (IOException e) {  
            System.out.println("Não foi possível carregar a imagem.");  
        } catch (IllegalArgumentException e) {  
            System.out.println("Imagem não encontrada!");  
        }  
               
               // gXYZ gx = new gXYZ();  
                //Erro....  
              
    }  
          
          
          
          
          
}