package br.com.markConsult.dao;
import java.awt.Dimension;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;


/**
 *
 * @author Charleston Anjos
 * @version 1.0
 * @date 13/09
 */
public class Desktop implements interfaceDesktop {
    
    
    /**
     *
     * @param jinternalframe
     * @param jdesktoppane
     */
    @Override
    public void createFrame(JInternalFrame jinternalframe, JDesktopPane jdesktoppane) {
        
        jinternalframe.setVisible(true); 
        jdesktoppane.add(jinternalframe);
        
        try {
            jinternalframe.setSelected(true);
            this.centralizeFrame(jinternalframe, jdesktoppane);
        } catch (java.beans.PropertyVetoException e) { 
            System.out.println(e.getStackTrace());
        }
    }    
    
    /**
     *
     * @param jinternalframe
     * @param jdesktoppane
     */
    @Override
    public void centralizeFrame(JInternalFrame jinternalframe, JDesktopPane jdesktoppane){
        //get dimension of two frames
        Dimension sizeA = jdesktoppane.getSize();
        Dimension sizeB = jinternalframe.getSize();
        
        //calc
        jinternalframe.setLocation((sizeA.width - sizeB.width) / 2, (sizeA.height - sizeB.height) / 2);
    }
    
}
