/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.gui;

import br.com.markConsult.Tetes.Utils;
import br.com.markConsult.classesMetodos.ArquivosProcedimentosTableModel;
import br.com.markConsult.entidades.ArquivosProcedimento;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author Jeferson
 */
public class VerImagens extends javax.swing.JDialog {

    private final Properties confBanco = new Properties();
    private ArquivosProcedimentosTableModel modelArquivos;
    
    

    /**
     * Creates new form VerImagens
     *
     * @param parent
     * @param modal
     */
    public VerImagens(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        URL url = this.getClass().getResource("/br/com/markConsult/imagens/help.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        modelArquivos = new ArquivosProcedimentosTableModel();
        jT_imagens.setModel(modelArquivos);
        jT_imagens.getColumnModel().getColumn(0).setMaxWidth(100);
        jT_imagens.requestFocus();
        
        try {
            confBanco.load(new FileInputStream("/markconsultas/banco.ini"));
        } catch (IOException ex) {
            Logger.getLogger(VerImagens.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);

        Action escapeAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane6 = new javax.swing.JScrollPane();
        jT_imagens = new javax.swing.JTable();
        bt_proximo = new javax.swing.JButton();
        bt_anterior = new javax.swing.JButton();
        jL_imagens = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jT_imagens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jT_imagens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jT_imagensMousePressed(evt);
            }
        });
        jT_imagens.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_imagensKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(jT_imagens);

        bt_proximo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/navigate-down-icon.png"))); // NOI18N
        bt_proximo.setToolTipText("Proxima imagem");
        bt_proximo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_proximoActionPerformed(evt);
            }
        });

        bt_anterior.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/navigate-up-icon.png"))); // NOI18N
        bt_anterior.setToolTipText("Imagem anterior");
        bt_anterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_anteriorActionPerformed(evt);
            }
        });

        jL_imagens.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(bt_proximo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(bt_anterior, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jL_imagens, javax.swing.GroupLayout.PREFERRED_SIZE, 863, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jL_imagens, javax.swing.GroupLayout.PREFERRED_SIZE, 517, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bt_anterior)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_proximo)
                        .addGap(5, 5, 5))
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_proximoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_proximoActionPerformed
        int imgSele = jT_imagens.getSelectedRow();

        if (imgSele >= 0 && imgSele < jT_imagens.getRowCount() - 1) {
            jT_imagens.getSelectionModel().setSelectionInterval(imgSele + 1, imgSele + 1);
          buscaArquivo();
        }
    }//GEN-LAST:event_bt_proximoActionPerformed

    private void bt_anteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_anteriorActionPerformed
        int imgSele = jT_imagens.getSelectedRow();

        if (imgSele > 0) {
            jT_imagens.getSelectionModel().setSelectionInterval(imgSele - 1, imgSele - 1);
            buscaArquivo();
        }
    }//GEN-LAST:event_bt_anteriorActionPerformed

    private void jT_imagensMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_imagensMousePressed
      buscaArquivo();
    }//GEN-LAST:event_jT_imagensMousePressed

    private void jT_imagensKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_imagensKeyReleased
     buscaArquivo();
    }//GEN-LAST:event_jT_imagensKeyReleased

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VerImagens.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            VerImagens dialog = new VerImagens(new javax.swing.JFrame(), true);
            dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                @Override
                public void windowClosing(java.awt.event.WindowEvent e) {
                    System.exit(0);
                }
            });
            dialog.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_anterior;
    private javax.swing.JButton bt_proximo;
    private javax.swing.JLabel jL_imagens;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JTable jT_imagens;
    // End of variables declaration//GEN-END:variables

// private void buscaImagem() {
//        try {
//            int sele = jT_imagens.getSelectedRow();
//            if (sele < 0) {
//                return;
//            }
//            ArquivosPaciente imp = model.getItem(sele);
//            Album abAlbum = (Album) jC_albuns.getSelectedItem();
//            File arquivo = new File(confBanco.getProperty("ip") + imp.getPaciente().getId()+"/"+abAlbum.getId()+ "/" + imp.getImagem());
//          //  System.out.println(arquivo.getPath());

//
//        } catch (IOException ex) {
//            try {
//                URL url = this.getClass().getResource("/br/com/markconsult/imagens/naoEncontada.png");
//                BufferedImage imagem = ImageIO.read(url);
//                mostraImagem(imagem);
//            } catch (IOException ex1) {
//
//            }
//        }
//    }
//
//    public void mostraImagens(List<ArquivosPaciente> imp, int imgSele, String nomeImg) {
//        if (!imp.isEmpty()) {
//             ArrayList<ArquivosPaciente> imagens = new ArrayList<>();
//             imp.forEach((arquivosPaciente) -> {
//                 File f = new File(arquivosPaciente.getImagem());
//                 String ext = Utils.getExtension(f);
//                if (!ext.equals("pdf") && !ext.equals("3gp")) {
//                    imagens.add(arquivosPaciente);
//                }
//            });
//            model.listar(imagens);
//            
//            List<ArquivosPaciente> i =  model.getLista();
//            for (int j = 0; j < i.size(); j++) {
//                 if (i.get(j).getImagem().equals(nomeImg)) {
//                       jT_imagens.getSelectionModel().setSelectionInterval(j, j);
//            }
//        }
//            
//           
//            if (imgSele >= 0 && imgSele <= jT_imagens.getRowCount() - 1) {
//                jT_imagens.getSelectionModel().setSelectionInterval(imgSele, imgSele);
//            }
//            buscaImagem();
//        }else{
//            model.limpaLista();
//            jL_imagens.setIcon(null);
//        }
//    }
//

//
//     public void setAbuns(List<Album> albuns, int selecionado){
//       if (!albuns.isEmpty()) {
//           jC_albuns.removeAllItems();
//           albuns.stream().forEach((albun) -> {
//               jC_albuns.addItem(albun);
//           });
//       }jC_albuns.setSelectedIndex(selecionado - 1);
//   }
//     
//       private void buscaImagensPaciente(){
//        
//        if (jC_albuns.getSelectedIndex() >= 0 && jC_albuns.getSelectedItem() != null) {
//            CadImagensPacienteDAO dao = new CadImagensPacienteDAO();
//            Album a = (Album) jC_albuns.getSelectedItem();
//            List<ArquivosPaciente> ap = dao.buscaImagensPaciente(a);
//            mostraImagens(ap, 0, "");
//        } else {
//            model.limpaLista();
//            jL_imagens.setIcon(null);
//        }
//
//    }
    
    public void setaArquivos(List<ArquivosProcedimento> arquivos,ArquivosProcedimento arquivoSelecionado){
        if (!arquivos.isEmpty()) {
              ArrayList<ArquivosProcedimento> arquivosOK = new ArrayList<>();
             arquivos.forEach((arquivosPaciente) -> {
                 File f = new File(arquivosPaciente.getArquivo());
                 String ext = Utils.getExtension(f);
                if (!ext.equals("pdf") && !ext.equals("3gp")) {
                    arquivosOK.add(arquivosPaciente);
                }
            });
           modelArquivos.listar(arquivosOK);
        }
        
          List<ArquivosProcedimento> i =  modelArquivos.getProcedimentos();
            for (int j = 0; j < i.size(); j++) {
                 if (i.get(j).equals(arquivoSelecionado)) {
                       jT_imagens.getSelectionModel().setSelectionInterval(j, j);
                       buscaArquivo();
            }
          }
     
    }
    
    
        private void buscaArquivo() {
        try {
            int sele = jT_imagens.getSelectedRow();
            if (sele < 0) {
                return;
            }
            ArquivosProcedimento imp = modelArquivos.getItem(sele);
            File arquivo = new File(confBanco.getProperty("ip") + imp.getConsultaProcedimento().getId() + "/" + imp.getArquivo());
            if(Files.exists(arquivo.toPath())){
            BufferedImage imagem = ImageIO.read(arquivo); //carrega a imagem real num buffer
            mostraImagem(imagem);
            }else{
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
            }
           
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro abrindo arquivo");
        }
    }
        
    private void mostraImagem(BufferedImage imagem) {
        if (imagem != null) {
            jL_imagens.setIcon(new ImageIcon(imagem));
        }else{
            jL_imagens.setIcon(null);
        }

    }
}