/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.markConsult.classesMetodos;


import br.com.markConsult.dao.AlteraImagemDAO;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;

/**
 *
 * @author Jeferson1
 */
public class BackGround extends JDesktopPane {    
    BufferedImage image;  
    JDesktopPane jdesktop; 
    
        public BackGround() {    
            try {
                //            try {
//                 java.net.URL caminho = TelaPrincipal.class
//     .getResource("/br/com/markConsult/imagens/fundo2.jpg");
//            img = javax.imageio.ImageIO.read(caminho);
//            } catch (Exception ex) {
//            }
//        }
//
//        @Override
//        public void paintComponent(Graphics g) {
//            super.paintComponent(g);
//            if (img != null) {
//                Dimension dimension = this.getSize();
//                int x = (int)(dimension.getWidth() - img.getWidth(this)) / 2;
//                int y = (int)(dimension.getHeight() - img.getHeight(this)) / 2;
//
//                g.drawImage(img, x, y, img.getWidth(this), img.getHeight(this), this);
//            } else {
//                g.drawString("Imagem nao encontrada", 50, 50);
//            }
//        }
                AlteraImagemDAO dao = new AlteraImagemDAO();
               byte[] imagem = dao.buscImaPid();
                if (imagem != null) {
                    image = ImageIO.read(new ByteArrayInputStream(imagem));
                }else{
                    image = ImageIO.read(new File("imagem/logoMark.png"));
                }
                //Image img = new ImageIcon(imagem).getImage(); 
             
               
            } catch (IOException ex) {
                
            }
            
            
        }
       
        public final  void setImage(String imagem) {
        try {  
            image = ImageIO.read(new File(imagem));
        } catch (IOException ex) {
            Logger.getLogger(BackGround.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
        
        @Override
            public void paintComponent (Graphics g){  
                super.paintComponent(g);  
                if (image != null) {
                     Graphics2D g2d = (Graphics2D) g.create();  
                g2d.drawImage(image,  
                    this.getWidth()/2 - image.getWidth()/2,  
                    this.getHeight()/2 - image.getHeight()/2,  
                    null);  
                g2d.dispose(); 
                
            }else{
                     g.drawString("Imagem nao encontrada", 50, 50);
                    //g.setColor(Color.GRAY);
                }
                
            }; 
        
            
       
        
        
    
}
