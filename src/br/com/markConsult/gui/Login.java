/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.gui;

import br.com.markConsult.classesMetodos.FixedLengthDocument;
import br.com.markConsult.dao.CadMinhaEmpresaDAO;
import br.com.markConsult.dao.CadUsuarioDAO;
import br.com.markConsult.entidades.MinhaEmpresa;
import br.com.markConsult.entidades.Sessao;
import br.com.markConsult.entidades.Usuario;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Jeferson1
 */
public class Login extends javax.swing.JFrame {
    List<MinhaEmpresa> e;
    /**
     * Creates new form Login
     */
    public Login() {
        initComponents();
        setSize(300, 168);
         URL url = this.getClass().getResource("/br/com/markConsult/imagens/help.png");  
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);  
        this.setIconImage(imagemTitulo); 
        
        tf_usuario.setDocument(new  FixedLengthDocument(15));
        tf_senha.setDocument(new  FixedLengthDocument(10));
        tf_usuario.requestFocus();
        CadMinhaEmpresaDAO dao = new CadMinhaEmpresaDAO();
         e =  dao.buscaEmpresa("", 'e');
        for (MinhaEmpresa e1 : e) {
            jC_minhaEmpresa.addItem(e1);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        bt_ok = new javax.swing.JButton();
        jC_minhaEmpresa = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tf_senha = new javax.swing.JPasswordField();
        tf_usuario = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Login Mark Consulta");
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jButton2.setForeground(new java.awt.Color(204, 51, 0));
        jButton2.setText("CANCELAR");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 130, -1, -1));

        bt_ok.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        bt_ok.setForeground(new java.awt.Color(51, 255, 51));
        bt_ok.setText("Ok");
        bt_ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_okActionPerformed(evt);
            }
        });
        getContentPane().add(bt_ok, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 90, -1));

        getContentPane().add(jC_minhaEmpresa, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 200, -1));

        jLabel4.setText("Empresa:");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 20));

        jLabel2.setText("Senha:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 45, 20));

        tf_senha.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tf_senhaFocusGained(evt);
            }
        });
        tf_senha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_senhaActionPerformed(evt);
            }
        });
        getContentPane().add(tf_senha, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 70, 200, -1));

        tf_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_usuarioActionPerformed(evt);
            }
        });
        getContentPane().add(tf_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 200, -1));

        jLabel1.setText("Usuário:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, -1, 20));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("NEUROCENTER");
        getContentPane().add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 10, 300, 30));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/login.jpg"))); // NOI18N
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 170));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_okActionPerformed
        okSelcionado();
    }//GEN-LAST:event_bt_okActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tf_senhaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_senhaActionPerformed
        verificaSenha();
    }//GEN-LAST:event_tf_senhaActionPerformed

    private void tf_senhaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_senhaFocusGained
        tf_senha.setSelectionStart(0);
        tf_senha.setSelectionEnd(tf_senha.getPassword().length);
    }//GEN-LAST:event_tf_senhaFocusGained

    private void tf_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_usuarioActionPerformed
        tf_senha.requestFocus();
        verificaUsu();
    }//GEN-LAST:event_tf_usuarioActionPerformed
//
//    /**
//     * @param args the command line arguments
//     */
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(Login.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new Login().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_ok;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox jC_minhaEmpresa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPasswordField tf_senha;
    private javax.swing.JTextField tf_usuario;
    // End of variables declaration//GEN-END:variables
public void verificaUsu(){
     if (tf_usuario.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Digite o nome do usuário!");
            tf_usuario.requestFocus();
        }else{
          
        }
     
    
    
}

public void verificaSenha(){
    
    String senha = "";
    char[] senhaChar = tf_senha.getPassword();
    for (char s : senhaChar) {
    senha = ""+s;
    }
     if (senha.equals("")) { 
            JOptionPane.showMessageDialog(null, "Digite a senha!");
            tf_senha.requestFocus();
        }else{
         okSelcionado();
     
     }
}

public void okSelcionado(){
        CadUsuarioDAO dao = new CadUsuarioDAO();
       
     String usuari = tf_usuario.getText();
        char[] senhaChar = tf_senha.getPassword();
        String senha = "";
        for (char c : senhaChar) {
         
            senha = senha + c;
        }
        
         Usuario us ;
        if (senha.equals(usuari)) {
        String usu = tf_usuario.getText();
         us = dao.login(usu, usu);
        if (us != null) {
          Sessao sessao = Sessao.getInstance();
          sessao.setUsuario(us);
          sessao.setMinhaEmpresa((MinhaEmpresa) jC_minhaEmpresa.getSelectedItem());
            dispose();
            TrocaSenha troca =   new TrocaSenha(null, true);
            troca.setaUsu(usu);
            troca.setVisible(true);  
        }else{
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
            tf_usuario.requestFocus();
        
       }
        }
        else {
          us = dao.login(usuari, senha); 
           if (us != null) {
               Sessao sessao = Sessao.getInstance();
          sessao.setUsuario(us);
          sessao.setMinhaEmpresa((MinhaEmpresa) jC_minhaEmpresa.getSelectedItem());
           dispose();
           TelaPrincipal t =  new TelaPrincipal();
           t.setVisible(true);
           //t.permicao();
           
          
              
            }   
        else{
            JOptionPane.showMessageDialog(null, "Usuário ou senha inválidos!");
            tf_usuario.requestFocus();
        
}
        }
}

 
}
