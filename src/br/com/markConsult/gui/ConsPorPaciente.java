/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.gui;

import br.com.markConsult.classesMetodos.ConsPcliTableModel;
import br.com.markConsult.classesMetodos.FixedLengthDocument;
import br.com.markConsult.classesMetodos.FormatacaoConteudo;
import br.com.markConsult.dao.CadConsultasDAO;
import br.com.markConsult.dao.ICadConsultasDAO;
import br.com.markConsult.entidades.Consulta;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.net.URL;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumnModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author jeferson
 */
public class ConsPorPaciente extends javax.swing.JDialog {
    String data = (new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(System.currentTimeMillis())));
    private final boolean okselecionado = false;
    List<Consulta> consultas;
    private final ConsPcliTableModel model;

    /**
     * Creates new form ConsPorPaciente
     * @param parent
     * @param modal
     */
    public ConsPorPaciente(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        addpopup();
        jTConsultas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        URL url = this.getClass().getResource("/br/com/markConsult/imagens/help.png");  
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);  
        this.setIconImage(imagemTitulo); 
        
        model = new ConsPcliTableModel();
        jTConsultas.setModel(model);

        tf_dado.setDocument(new FixedLengthDocument(70));
        ajusTabela();
        
        FormatacaoConteudo corNomes = new FormatacaoConteudo();
        jTConsultas.getColumnModel().getColumn(7).setCellRenderer(corNomes);
        
        
        jTConsultas.getSelectionModel().setSelectionInterval(0, 0);
        
        
        jTConsultas.setAutoCreateRowSorter(true);
        
        TableRowSorter<ConsPcliTableModel> sorter = new TableRowSorter<>((ConsPcliTableModel) jTConsultas.getModel());  
            jTConsultas.setRowSorter(sorter);
        
        
        JTableHeader header = jTConsultas.getTableHeader();  
        header.addMouseListener(new MouseAdapter() {  
  
            @Override  
            public void mouseClicked(MouseEvent e) {  
                JTable table = ((JTableHeader) e.getSource()).getTable();  
                TableColumnModel colModel = table.getColumnModel();  
                int vColIndex = colModel.getColumnIndexAtX(e.getX());  
                int mColIndex = table.convertColumnIndexToModel(vColIndex);  
                if (vColIndex == -1) {  
                    return;  
                }  
                int ordenacao = mColIndex; //criei esta váriavel ordenacao;  
            }  
        }); 
        KeyStroke escapeKeyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0, false);

        Action escapeAction = new AbstractAction() {

            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        };
        getRootPane().getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(escapeKeyStroke, "ESCAPE");
        getRootPane().getActionMap().put("ESCAPE", escapeAction);
       jC_status.setSelectedIndex(6);
       tf_dado.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPMenu = new javax.swing.JPopupMenu();
        jM_altStatus = new javax.swing.JMenu();
        jMenuIt_Aberta = new javax.swing.JMenuItem();
        jMenuIt_Encerra = new javax.swing.JMenuItem();
        jMenuIt_Cancel = new javax.swing.JMenuItem();
        jMenuIt_Faltou = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        jMenuIt_retorno = new javax.swing.JMenuItem();
        jMenuIt_NovaCon = new javax.swing.JMenuItem();
        jMenuIt_verProcedimentos = new javax.swing.JMenuItem();
        bt_sair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTConsultas = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        cb_campo = new javax.swing.JComboBox();
        bt_busca2 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jC_status = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        tf_dado = new javax.swing.JFormattedTextField();
        cbx_complete = new java.awt.Checkbox();

        jM_altStatus.setText("Alterar status");

        jMenuIt_Aberta.setText("Aberta");
        jMenuIt_Aberta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIt_AbertaActionPerformed(evt);
            }
        });
        jM_altStatus.add(jMenuIt_Aberta);

        jMenuIt_Encerra.setText("Encerrada");
        jMenuIt_Encerra.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIt_EncerraActionPerformed(evt);
            }
        });
        jM_altStatus.add(jMenuIt_Encerra);

        jMenuIt_Cancel.setText("Cancelada");
        jMenuIt_Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIt_CancelActionPerformed(evt);
            }
        });
        jM_altStatus.add(jMenuIt_Cancel);

        jMenuIt_Faltou.setText("Faltou");
        jMenuIt_Faltou.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIt_FaltouActionPerformed(evt);
            }
        });
        jM_altStatus.add(jMenuIt_Faltou);

        jPMenu.add(jM_altStatus);
        jPMenu.add(jSeparator1);

        jMenuIt_retorno.setText("Marcar retorno");
        jMenuIt_retorno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIt_retornoActionPerformed(evt);
            }
        });
        jPMenu.add(jMenuIt_retorno);

        jMenuIt_NovaCon.setText("Nova Consulta");
        jMenuIt_NovaCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIt_NovaConActionPerformed(evt);
            }
        });
        jPMenu.add(jMenuIt_NovaCon);

        jMenuIt_verProcedimentos.setText("Ver procedimento");
        jMenuIt_verProcedimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIt_verProcedimentosActionPerformed(evt);
            }
        });
        jPMenu.add(jMenuIt_verProcedimentos);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Consultas");
        setResizable(false);

        bt_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/Log-Out-icon.png"))); // NOI18N
        bt_sair.setText("Sair");
        bt_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sairActionPerformed(evt);
            }
        });

        jTConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTConsultasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTConsultas);

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Consultas", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        cb_campo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Nome", "Data Consulta" }));
        cb_campo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cb_campoActionPerformed(evt);
            }
        });

        bt_busca2.setText("Buscar");
        bt_busca2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_busca2ActionPerformed(evt);
            }
        });

        jLabel3.setText("Campos:");

        jC_status.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Abertas", "Encerrada", "Canceladas", "Faltou", "Aguardando", "Em consulta", "Todos" }));
        jC_status.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jC_statusPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });

        jLabel4.setText("Status:");

        tf_dado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_dadoActionPerformed(evt);
            }
        });
        tf_dado.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tf_dadoKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(cb_campo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addContainerGap(946, Short.MAX_VALUE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jC_status, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_dado)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_busca2))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(12, 12, 12))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cb_campo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_busca2)
                    .addComponent(jC_status, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_dado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbx_complete.setLabel("Auto Complete");
        cbx_complete.setState(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(cbx_complete, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_sair))
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 262, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_sair)
                    .addComponent(cbx_complete, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sairActionPerformed
        dispose();
    }//GEN-LAST:event_bt_sairActionPerformed

    private void bt_busca2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_busca2ActionPerformed
        pesquisa();
    }//GEN-LAST:event_bt_busca2ActionPerformed

    private void cb_campoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cb_campoActionPerformed
        int sele = cb_campo.getSelectedIndex();
        if (sele == 0) {
            tf_dado.setText("");
            tf_dado.requestFocus();
        }else if (sele == 1) {
            tf_dado.setText("");
            tf_dado.requestFocus();
        } 
    }//GEN-LAST:event_cb_campoActionPerformed

    private void tf_dadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_dadoActionPerformed
       pesquisa();
    }//GEN-LAST:event_tf_dadoActionPerformed

    private void tf_dadoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_dadoKeyReleased
     if (cbx_complete.getState() == true) {
            pesquisa();
        }
    }//GEN-LAST:event_tf_dadoKeyReleased

    private void jTConsultasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTConsultasMousePressed
          if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 1) {
              
            } else if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
               verProcedimento();
 
          }
    }//GEN-LAST:event_jTConsultasMousePressed

    private void jMenuIt_AbertaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIt_AbertaActionPerformed
        popupselsecionado(1);
        pesquisa();
    }//GEN-LAST:event_jMenuIt_AbertaActionPerformed

    private void jMenuIt_EncerraActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIt_EncerraActionPerformed
        popupselsecionado(2);
        pesquisa();
    }//GEN-LAST:event_jMenuIt_EncerraActionPerformed

    private void jMenuIt_CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIt_CancelActionPerformed
        popupselsecionado(3);
        pesquisa();
    }//GEN-LAST:event_jMenuIt_CancelActionPerformed

    private void jMenuIt_FaltouActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIt_FaltouActionPerformed
        popupselsecionado(4);
        pesquisa();
    }//GEN-LAST:event_jMenuIt_FaltouActionPerformed

    private void jMenuIt_retornoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIt_retornoActionPerformed
         int consultaSelecionada = jTConsultas.getSelectedRow();
        if (consultaSelecionada >= 0) {
        consultaSelecionada = jTConsultas.convertRowIndexToModel(consultaSelecionada);
        Consulta con = model.getItem(consultaSelecionada);
        CadRetorno retorno = new CadRetorno(null, true);
        retorno.passaConsulta(con);
        retorno.setNomeClie(con.getPaciente().getNome());
        retorno.setVisible(true);
        pesquisa();
        }
    }//GEN-LAST:event_jMenuIt_retornoActionPerformed

    private void jC_statusPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jC_statusPopupMenuWillBecomeInvisible
        pesqPstatus();
    }//GEN-LAST:event_jC_statusPopupMenuWillBecomeInvisible

    private void jMenuIt_NovaConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIt_NovaConActionPerformed
       try {
           int conSele = jTConsultas.getSelectedRow();
           Consulta c;
           if (conSele >= 0) {
               c = model.getItem(conSele);
               CadConsultaModal telCadCons = new CadConsultaModal(null, true);
               telCadCons.estadoBotoes("novo");
               telCadCons.novoCad(converte(data));
               telCadCons.setPaciente(c.getPaciente());
               telCadCons.buscaPaciente();
               telCadCons.setVisible(true);
               pesquisa();
           }
            
        } catch (ParseException ex) {
           System.out.println("erro ao marcar nova consulta");
        }
    }//GEN-LAST:event_jMenuIt_NovaConActionPerformed

    private void jMenuIt_verProcedimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIt_verProcedimentosActionPerformed
       verProcedimento();
    }//GEN-LAST:event_jMenuIt_verProcedimentosActionPerformed

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
            java.util.logging.Logger.getLogger(ConsPorPaciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                ConsPorPaciente dialog = new ConsPorPaciente(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_busca2;
    private javax.swing.JButton bt_sair;
    private javax.swing.JComboBox cb_campo;
    private java.awt.Checkbox cbx_complete;
    private javax.swing.JComboBox jC_status;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JMenu jM_altStatus;
    private javax.swing.JMenuItem jMenuIt_Aberta;
    private javax.swing.JMenuItem jMenuIt_Cancel;
    private javax.swing.JMenuItem jMenuIt_Encerra;
    private javax.swing.JMenuItem jMenuIt_Faltou;
    private javax.swing.JMenuItem jMenuIt_NovaCon;
    private javax.swing.JMenuItem jMenuIt_retorno;
    private javax.swing.JMenuItem jMenuIt_verProcedimentos;
    private javax.swing.JPopupMenu jPMenu;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JTable jTConsultas;
    private javax.swing.JFormattedTextField tf_dado;
    // End of variables declaration//GEN-END:variables
public Date converte(String dataNasc) throws ParseException {
        DateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date dat = new java.sql.Date(forma.parse(dataNasc).getTime());

        return dat;
    }
    public void buscConsultsPid(int id) {
        ICadConsultasDAO dao = new CadConsultasDAO();
        int status = jC_status.getSelectedIndex();
     
        consultas = dao.buscaConsultas(""+ id, 'a',""+status+1);
        model.listar(consultas);
        FormatacaoConteudo corNomes = new FormatacaoConteudo();
        jTConsultas.getColumnModel().getColumn(3).setCellRenderer(corNomes);
        
    }

    public void retornaiItemSelecionado(int id, String nome) {
        ICadConsultasDAO dao = new CadConsultasDAO();
        tf_dado.setText(nome);
        jC_status.setSelectedIndex(6);
        consultas = dao.buscaConsultas("" + id, 'a',"1,2,3,4,5,6"); 
        model.listar(consultas);
        jTConsultas.getSelectionModel().setSelectionInterval(0, 0);
      
    }

    public boolean okselecionado() {

        return okselecionado;

    }

    public void pesquisa() {
        ICadConsultasDAO dao = new CadConsultasDAO();
        String valor = tf_dado.getText();
        int status = jC_status.getSelectedIndex()+1;
        
        switch (cb_campo.getSelectedIndex()) {
            case 0:
                consultas = dao.buscaConsultas(valor, 'e',""+status);
                break;
            case 1:
                consultas = dao.buscaConsultas(valor, 'i',""+status);
                break;
            case 2:
                consultas = dao.buscaConsultas(valor, 't',""+status);
                break;
            case 3:
                consultas = dao.buscaConsultas(valor, 'a',""+status);
                break;


        }

        model.listar(consultas);
        jTConsultas.getSelectionModel().setSelectionInterval(0, 0);
        int sele = jTConsultas.getSelectedRow();
        ajusTabela();

    }

    /**
     *
     */
    public void pesqPstatus() {
        ICadConsultasDAO dao = new CadConsultasDAO();
        int campo = cb_campo.getSelectedIndex();
        int status = jC_status.getSelectedIndex()+1;
    
          switch (status) {
            case 7:
                consultas = dao.buscaConPstat(campo,tf_dado.getText(),"1,2,3,4,5,6");
                break;
            default:
               consultas = dao.buscaConPstat(campo,tf_dado.getText(),""+status);
        }
                
        model.listar(consultas);
        jTConsultas.getSelectionModel().setSelectionInterval(0, 0);
        int sele = jTConsultas.getSelectedRow();
        ajusTabela();

    }


   
    
    private void ajusTabela(){
        DefaultTableCellRenderer direita = new DefaultTableCellRenderer();
        direita.setHorizontalAlignment(SwingConstants.RIGHT);
        
        jTConsultas.getColumnModel().getColumn(0).setMinWidth(60);
        jTConsultas.getColumnModel().getColumn(0).setMaxWidth(60);
        jTConsultas.getColumnModel().getColumn(1).setPreferredWidth(200);
        jTConsultas.getColumnModel().getColumn(2).setPreferredWidth(140);
        jTConsultas.getColumnModel().getColumn(3).setPreferredWidth(120);
        jTConsultas.getColumnModel().getColumn(4).setPreferredWidth(60);
        jTConsultas.getColumnModel().getColumn(4).setCellRenderer(direita);
        jTConsultas.getColumnModel().getColumn(5).setPreferredWidth(100);
        jTConsultas.getColumnModel().getColumn(6).setPreferredWidth(100);
        jTConsultas.getColumnModel().getColumn(7).setPreferredWidth(100);
        jTConsultas.getColumnModel().getColumn(8).setPreferredWidth(100);
        jTConsultas.getColumnModel().getColumn(9).setPreferredWidth(200);
        jTConsultas.getColumnModel().getColumn(10).setPreferredWidth(1000);
    }
    
    private void addpopup() {
        jTConsultas.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //Verificando se o botão direito foi pressionado  
                if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {
                    jPMenu.show(jTConsultas, e.getX(), e.getY());
                }
            }
        });

    }
    
     public void popupselsecionado(int status) {
        ICadConsultasDAO dao = new CadConsultasDAO();
        List<Consulta> con = new ArrayList<>();
      
                int c = jTConsultas.getSelectedRow();
                if (c < 0) {
                } else {
                    c = jTConsultas.convertRowIndexToModel(c);
                    con.clear();
                    con.add(model.getItem(c));
                    con.get(0).setStatus(status);
                    model.alterar(c, con.get(0));
                    jTConsultas.getSelectionModel().setSelectionInterval(c, c);
                }
        dao.altStatConsult(con);
    }

    private void verProcedimento() {
        VerProcedimentosConsulta1 vpc = new VerProcedimentosConsulta1(null, true);
        int consultaSelecionada = jTConsultas.getSelectedRow();
        vpc.setConsulta(model.getItem(consultaSelecionada));
        vpc.setVisible(true);
    }
}
