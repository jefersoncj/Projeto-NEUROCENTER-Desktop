package br.com.markConsult.dao;

import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;


/**
 *
 * @author Charleston Anjos
 * @version 1.0
 * @date 18/09
 */
public interface interfaceDesktop {
    
    /* methods for class of control */
    public void createFrame(JInternalFrame jinternalframe, JDesktopPane jdesktoppane);
    void centralizeFrame(JInternalFrame jinternalframe, JDesktopPane jdesktoppane);
  
    
}
