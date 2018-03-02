/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.gui;

import br.com.markConsult.classesMetodos.FixedLengthDocument;
import br.com.markConsult.classesMetodos.IntegerDocument;
import br.com.markConsult.dao.CadRiscoDAO;
import br.com.markConsult.dao.CadTipoRiscoDAO;
import br.com.markConsult.entidades.Risco;
import br.com.markConsult.entidades.TipoRisco;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;

/**
 *
 * @author jeferson
 */
public class CadRisco extends javax.swing.JInternalFrame {

    int inserir_alterar = 0;
    String endImage;

    /**
     * Creates new form CadCliente
     */
    public CadRisco() {
        initComponents();
        estadoBotoes("inicial");

        tf_codigo.setDocument(new IntegerDocument(10));
        tf_desc_risco.setDocument(new FixedLengthDocument(120));
      

        atualizaTela();
       
        bt_buscar.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel4 = new javax.swing.JPanel();
        jLabel18 = new javax.swing.JLabel();
        tf_codigo = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tf_desc_risco = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        tf_descTipoRisco = new javax.swing.JTextField();
        jL_tipoRisco = new javax.swing.JLabel();
        bt_tipoRisco = new javax.swing.JButton();
        tf_idTipoRisco = new javax.swing.JTextField();
        jPanel1 = new javax.swing.JPanel();
        bt_sair = new javax.swing.JButton();
        bt_editar = new javax.swing.JButton();
        bt_salvar = new javax.swing.JButton();
        bt_novo = new javax.swing.JButton();
        bt_excluir = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        bt_buscar = new javax.swing.JButton();
        bt_ultimo = new javax.swing.JButton();
        bt_avançar = new javax.swing.JButton();
        bt_primeiro = new javax.swing.JButton();
        bt_voltar = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Riscos");
        setToolTipText("");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/help.png"))); // NOI18N

        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cadastro de Risco", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jLabel18.setText("Código:");

        tf_codigo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_codigoFocusLost(evt);
            }
        });
        tf_codigo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_codigoActionPerformed(evt);
            }
        });
        tf_codigo.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_codigoKeyPressed(evt);
            }
        });

        jLabel7.setText("Tipo Risco:");

        jLabel8.setText("Descrição:");

        jL_tipoRisco.setText("*");

        bt_tipoRisco.setText("...");
        bt_tipoRisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_tipoRiscoActionPerformed(evt);
            }
        });

        tf_idTipoRisco.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_idTipoRiscoFocusLost(evt);
            }
        });
        tf_idTipoRisco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_idTipoRiscoActionPerformed(evt);
            }
        });
        tf_idTipoRisco.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_idTipoRiscoKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jL_tipoRisco)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tf_desc_risco, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(tf_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(bt_tipoRisco)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_idTipoRisco, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_descTipoRisco)))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(tf_codigo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tf_descTipoRisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jL_tipoRisco)
                    .addComponent(bt_tipoRisco)
                    .addComponent(tf_idTipoRisco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(tf_desc_risco, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));
        jPanel1.setToolTipText("");

        bt_sair.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/Log-Out-icon.png"))); // NOI18N
        bt_sair.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sair", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sairActionPerformed(evt);
            }
        });

        bt_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/Action-edit-icon.png"))); // NOI18N
        bt_editar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editarActionPerformed(evt);
            }
        });

        bt_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/App-floppy-icon.png"))); // NOI18N
        bt_salvar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salvar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_salvar.setIconTextGap(2);
        bt_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salvarActionPerformed(evt);
            }
        });

        bt_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/new-file-icon.png"))); // NOI18N
        bt_novo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Novo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_novoActionPerformed(evt);
            }
        });

        bt_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/trash-icon.png"))); // NOI18N
        bt_excluir.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Excluir", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_excluir.setIconTextGap(2);
        bt_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirActionPerformed(evt);
            }
        });

        bt_cancelar.setFont(new java.awt.Font("Ubuntu", 0, 15)); // NOI18N
        bt_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/Extras-Close-icon.png"))); // NOI18N
        bt_cancelar.setAutoscrolls(true);
        bt_cancelar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cancelar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_cancelar.setIconTextGap(2);
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        bt_buscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/Search-icon.png"))); // NOI18N
        bt_buscar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Buscar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_buscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscarActionPerformed(evt);
            }
        });
        bt_buscar.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bt_buscarKeyPressed(evt);
            }
        });

        bt_ultimo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/hide-right-icon.png"))); // NOI18N
        bt_ultimo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ultimo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_ultimo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_ultimoActionPerformed(evt);
            }
        });

        bt_avançar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/navigate-right-icon.png"))); // NOI18N
        bt_avançar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Avançar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_avançar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_avançarActionPerformed(evt);
            }
        });

        bt_primeiro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/hide-left-icon.png"))); // NOI18N
        bt_primeiro.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Primeiro", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_primeiro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_primeiroActionPerformed(evt);
            }
        });

        bt_voltar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/navigate-left-icon.png"))); // NOI18N
        bt_voltar.setToolTipText("");
        bt_voltar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Voltar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_voltar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_voltarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(bt_primeiro, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_voltar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_avançar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_ultimo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_buscar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_sair, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bt_buscar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_excluir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_novo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_sair, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_editar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_salvar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_cancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_ultimo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_avançar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_voltar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(bt_primeiro, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void tf_codigoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_codigoActionPerformed

    }//GEN-LAST:event_tf_codigoActionPerformed

    private void tf_codigoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_codigoFocusLost
        if (tf_codigo.isEditable()) {
            String id = tf_codigo.getText();
            if (id.equals("")) {
                atualizaTela();
            } else {
                buscaPorId(id);
            }
        }
    }//GEN-LAST:event_tf_codigoFocusLost

    private void tf_codigoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_codigoKeyPressed
        if (tf_codigo.isEditable()) {
            if (evt.getKeyCode() == KeyEvent.VK_F2) {
                telBuscEmp();

            }
        }

    }//GEN-LAST:event_tf_codigoKeyPressed

    private void bt_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sairActionPerformed
        dispose();
    }//GEN-LAST:event_bt_sairActionPerformed

    private void bt_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editarActionPerformed

        inserir_alterar = 1;
        estadoBotoes("novo");
    }//GEN-LAST:event_bt_editarActionPerformed

    private void bt_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salvarActionPerformed
        if (verificaCampos() == false) {
        String descRisco = tf_desc_risco.getText(); 
        TipoRisco tr = new TipoRisco();
        tr.setId(Integer.parseInt(tf_idTipoRisco.getText()));

        if (inserir_alterar == 0) {

            CadRiscoDAO dao = new CadRiscoDAO();
            Risco risco = new Risco(null,tr, descRisco);

            int id = dao.inseRisco(risco);
            risco.setId(id);
            tf_codigo.setText("" + id);

        }

        if (inserir_alterar == 1) {
            int codigo = Integer.parseInt(tf_codigo.getText());
            CadRiscoDAO dao = new CadRiscoDAO();
            Risco risco = new Risco(codigo,tr, descRisco);
            dao.altRisco(risco);
        }
        inserir_alterar = 0;

        estadoBotoes("salvar");

        }

    }//GEN-LAST:event_bt_salvarActionPerformed

    private void bt_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_novoActionPerformed
       

        tf_codigo.setText("");
        tf_desc_risco.setText("");
        tf_idTipoRisco.setText("");
        tf_descTipoRisco.setText("");
        tf_desc_risco.requestFocus();
        estadoBotoes("novo");
    }//GEN-LAST:event_bt_novoActionPerformed

    private void bt_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirActionPerformed
        int opcao_escolhida = JOptionPane.showConfirmDialog(null, "Excluir " + tf_desc_risco.getText() + "?", "Exclusão ", JOptionPane.YES_NO_OPTION);
        if (opcao_escolhida == JOptionPane.YES_OPTION) {
            int id = Integer.parseInt(tf_codigo.getText());
            CadRiscoDAO dao = new CadRiscoDAO();
            boolean excludo = dao.rmRisco(id);
            if (excludo) {
                atualizaTela();
                estadoBotoes("inicial");
            } else {
                JOptionPane.showMessageDialog(null, "Risco não pode ser excluído porque está sendo utilizado em outra tabela!");
            }

        }
    }//GEN-LAST:event_bt_excluirActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        String nome = "Deseja realmente cancelar?";
        int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Cancelar ", JOptionPane.YES_NO_OPTION);
        if (opcao_escolhida == JOptionPane.YES_OPTION) {
            inserir_alterar = 0;
            estadoBotoes("cancelar");
            jL_tipoRisco.setForeground(Color.black);

        }
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void bt_buscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscarActionPerformed
        telBuscEmp();
    }//GEN-LAST:event_bt_buscarActionPerformed

    private void bt_buscarKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_bt_buscarKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_F2) {
            telBuscEmp();
        }
    }//GEN-LAST:event_bt_buscarKeyPressed

    private void bt_ultimoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_ultimoActionPerformed
        atualizaTela();
    }//GEN-LAST:event_bt_ultimoActionPerformed

    private void bt_avançarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_avançarActionPerformed

        CadRiscoDAO dao = new CadRiscoDAO();
        Risco c = dao.mostrarProximo(Integer.parseInt(tf_codigo.getText()));
        mostrar_dados(c);
    }//GEN-LAST:event_bt_avançarActionPerformed

    private void bt_primeiroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_primeiroActionPerformed
        CadRiscoDAO dao = new CadRiscoDAO();
        Risco c = dao.mostrarPrimeiro();
        mostrar_dados(c);
    }//GEN-LAST:event_bt_primeiroActionPerformed

    private void bt_voltarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_voltarActionPerformed

        CadRiscoDAO dao = new CadRiscoDAO();
        Risco c = dao.mostrarAnterior(Integer.parseInt(tf_codigo.getText()));
        mostrar_dados(c);

    }//GEN-LAST:event_bt_voltarActionPerformed

    private void tf_idTipoRiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_idTipoRiscoActionPerformed
     tf_desc_risco.requestFocus();
    }//GEN-LAST:event_tf_idTipoRiscoActionPerformed

    private void tf_idTipoRiscoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_idTipoRiscoFocusLost
      if (tf_idTipoRisco.isEditable()) {
            if (!tf_idTipoRisco.getText().equals("")) {
                buscaTipoRiscoPorId(tf_idTipoRisco.getText());
            } else {
                tf_descTipoRisco.setText("");
            }
        }
    }//GEN-LAST:event_tf_idTipoRiscoFocusLost

    private void tf_idTipoRiscoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_idTipoRiscoKeyPressed
      if (tf_idTipoRisco.isEditable()) {
            if (evt.getKeyCode() == KeyEvent.VK_F2) {
              telBuscaTipoRisco();

            }
        }
    }//GEN-LAST:event_tf_idTipoRiscoKeyPressed

    private void bt_tipoRiscoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_tipoRiscoActionPerformed
       telBuscaTipoRisco();
    }//GEN-LAST:event_bt_tipoRiscoActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_avançar;
    private javax.swing.JButton bt_buscar;
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_excluir;
    private javax.swing.JButton bt_novo;
    private javax.swing.JButton bt_primeiro;
    private javax.swing.JButton bt_sair;
    private javax.swing.JButton bt_salvar;
    private javax.swing.JButton bt_tipoRisco;
    private javax.swing.JButton bt_ultimo;
    private javax.swing.JButton bt_voltar;
    private javax.swing.JLabel jL_tipoRisco;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JTextField tf_codigo;
    private javax.swing.JTextField tf_descTipoRisco;
    private javax.swing.JTextField tf_desc_risco;
    private javax.swing.JTextField tf_idTipoRisco;
    // End of variables declaration//GEN-END:variables


    private void estadoBotoes(String botao) {
        if ("inicial".equals(botao)) {
            bt_novo.setEnabled(true);
            bt_cancelar.setEnabled(false);
            bt_editar.setEnabled(true);
            bt_buscar.setEnabled(true);
            bt_salvar.setEnabled(false);
            bt_excluir.setEnabled(true);
            bt_primeiro.setEnabled(true);
            bt_voltar.setEnabled(true);
            bt_avançar.setEnabled(true);
            bt_ultimo.setEnabled(true);
            bt_tipoRisco.setEnabled(false);
           

            tf_codigo.setEditable(true);
            tf_idTipoRisco.setEditable(false);
            tf_descTipoRisco.setEditable(false);
            tf_desc_risco.setEditable(false);
          

        }

        if ("novo".equals(botao)) {
            bt_novo.setEnabled(false);
            bt_cancelar.setEnabled(true);
            bt_editar.setEnabled(true);
            bt_buscar.setEnabled(false);
            bt_excluir.setEnabled(false);
            bt_editar.setEnabled(false);
            bt_salvar.setEnabled(true);
            bt_primeiro.setEnabled(false);
            bt_voltar.setEnabled(false);
            bt_avançar.setEnabled(false);
            bt_ultimo.setEnabled(false);
            bt_tipoRisco.setEnabled(true);
        

            tf_codigo.setEditable(false);
            tf_idTipoRisco.setEditable(true);
            tf_descTipoRisco.setEditable(true);
            tf_desc_risco.setEditable(true);
            
           

        }

        if ("cancelar".equals(botao)) {
            atualizaTela();
            estadoBotoes("inicial");
        }
        if ("salvar".equals(botao)) {
            estadoBotoes("inicial");

        }

        if ("buscar".equals(botao)) {
            estadoBotoes("inicial");

        }

    }

   

    private void atualizaTela() {
        CadRiscoDAO dao = new CadRiscoDAO();
        Risco c = dao.mostrarUltimo();
        mostrar_dados(c);
    }

    public void mostrar_dados(Risco rc) {
        
        if (rc != null) {

         
                tf_codigo.setText("" + rc.getId());
                tf_desc_risco.setText(rc.getDescRisco());
                mostrar_risco(rc.getTipoRisco());
        }
    }
    
     public void mostrar_risco(TipoRisco tr) {
      
        if (tr != null) {
              tf_idTipoRisco.setText(tr.getId().toString());
              tf_descTipoRisco.setText(tr.getDescTipoRisco());  
              jL_tipoRisco.setForeground(Color.black);

        }
    }
    

    public void buscaPorId(String id) {
        CadRiscoDAO dao = new CadRiscoDAO();
        Risco risco = dao.buscRiscPid(Integer.parseInt(id));
        if (risco == null) {
            JOptionPane.showMessageDialog(null, "Risco não cadastrada!");
             tf_codigo.requestFocus();
        }
        mostrar_dados(risco);
    }

    public void telBuscEmp() {
        BuscaRisco pcp = new BuscaRisco(null, true);
        pcp.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
        pcp.setVisible(true);

        if (pcp.okselecionado()) {

            Risco emp = pcp.retornEspSele();
            mostrar_dados(emp);
        }

    }
    public void buscaTipoRiscoPorId(String id) {
        CadTipoRiscoDAO dao = new CadTipoRiscoDAO();
        TipoRisco tr = dao.buscEmpPid(Integer.parseInt(id));
        if (tr == null) {
            JOptionPane.showMessageDialog(null, "Tipo de risco não cadastrado!");
             tf_codigo.requestFocus();
        }
        mostrar_risco(tr);
    }
    public boolean verificaCampos() {
        boolean ok = false;
        if (tf_idTipoRisco.getText().equals("")) {
            jL_tipoRisco.setForeground(Color.red);
            ok = true;
        }
      

        return ok;
    }

    private void telBuscaTipoRisco() {
       BuscaTipoRisco pcp = new BuscaTipoRisco(null, true);
        pcp.getRootPane().setWindowDecorationStyle(JRootPane.COLOR_CHOOSER_DIALOG);
        pcp.setVisible(true);

        if (pcp.okselecionado()) {
            TipoRisco tr = pcp.retornEspSele();
            mostrar_risco(tr);
        }
    }
}
