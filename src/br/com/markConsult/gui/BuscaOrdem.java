/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.gui;

import br.com.markConsult.classesMetodos.ExamesResultadoTableModel;
import br.com.markConsult.classesMetodos.OrdemTableModel;
import br.com.markConsult.dao.CadPCMSODAO;
import br.com.markConsult.dao.CadPacienteDAO;
import br.com.markConsult.entidades.MinhaEmpresa;
import br.com.markConsult.entidades.OrdemExames;
import br.com.markConsult.entidades.Paciente;
import br.com.markConsult.entidades.Sessao;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingWorker;

/**
 *
 * @author Jeferson
 */
public class BuscaOrdem extends javax.swing.JInternalFrame {
 String data = (new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(System.currentTimeMillis())));
   
   
    private final OrdemTableModel modelOrdens ;
    private final  ExamesResultadoTableModel modelExames;
    /**
     * Creates new form GeraPCMSO1
     */
    public BuscaOrdem() {
        initComponents();
        modelOrdens = new OrdemTableModel();
        tabela_ordens.setModel(modelOrdens); 
        
        modelExames =  new ExamesResultadoTableModel();
        tabela_exames.setModel(modelExames);
       
        formaTabela();
        
        tabela_ordens.getSelectionModel().setSelectionInterval(0, 0);
  
        jC_paciente.getEditor().addActionListener((ActionEvent e) -> {
            busaPacientes();
            jC_paciente.setSelectedItem(e.getActionCommand().toString());
            
           
        });             
		jC_paciente.addItemListener((ItemEvent e) -> {
                  
        });
                 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        bt_sair = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabela_ordens = new javax.swing.JTable();
        jLabel5 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jC_paciente = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        tf_empresa = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_setor = new javax.swing.JTextField();
        tf_funcao = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabela_exames = new javax.swing.JTable();
        bt_imprimir = new javax.swing.JButton();
        bt_limparCampos = new javax.swing.JButton();

        setClosable(true);
        setIconifiable(true);
        setTitle("Busca Ordem ");
        setFrameIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/help.png"))); // NOI18N

        jLabel6.setText("Exames");

        bt_sair.setText("Fechar");
        bt_sair.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_sairActionPerformed(evt);
            }
        });

        tabela_ordens.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabela_ordens.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabela_ordensMouseClicked(evt);
            }
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tabela_ordensMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tabela_ordens);

        jLabel5.setText("Ordens");

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Busca ordem", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        jLabel3.setText("Paciente:");

        jC_paciente.setEditable(true);
        jC_paciente.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                jC_pacientePopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        jC_paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jC_pacienteActionPerformed(evt);
            }
        });

        jLabel1.setText("Empresa:");

        tf_empresa.setEditable(false);

        jLabel2.setText("Setor:");

        jLabel4.setText("Função");

        tf_setor.setEditable(false);

        tf_funcao.setEditable(false);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jC_paciente, 0, 1, Short.MAX_VALUE)
                    .addComponent(tf_setor))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(tf_empresa)
                    .addComponent(tf_funcao, javax.swing.GroupLayout.DEFAULT_SIZE, 491, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jC_paciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tf_empresa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel4)
                    .addComponent(tf_setor, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_funcao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(12, Short.MAX_VALUE))
        );

        tabela_exames.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tabela_exames.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tabela_examesFocusLost(evt);
            }
        });
        jScrollPane2.setViewportView(tabela_exames);

        bt_imprimir.setText("IMPRIMIR ORDEM");
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        bt_limparCampos.setText("Limpar campos");
        bt_limparCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_limparCamposActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 900, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_limparCampos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bt_sair))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_sair)
                    .addComponent(bt_imprimir)
                    .addComponent(bt_limparCampos))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void bt_sairActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_sairActionPerformed
        dispose();

    }//GEN-LAST:event_bt_sairActionPerformed

    private void tabela_ordensMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_ordensMouseClicked
//        if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
//
//            dispose();
//        }
//        int s = tabela_riscos.getSelectedRow();
    }//GEN-LAST:event_tabela_ordensMouseClicked

    private void jC_pacientePopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_jC_pacientePopupMenuWillBecomeInvisible
        if (jC_paciente.getSelectedIndex() >= 0) {
            Paciente paciente = (Paciente) jC_paciente.getSelectedItem();
            tf_empresa.setText(paciente.getEmpresa().getFantasia());
            tf_setor.setText(paciente.getSetor().getDescSetor());
            tf_funcao.setText(paciente.getFuncao().getDescFuncao());

            CadPCMSODAO dao = new CadPCMSODAO();
            modelOrdens.listar(dao.buscaOrdemPaciente(paciente));
        }else{
            limpaTudo();
        }
    }//GEN-LAST:event_jC_pacientePopupMenuWillBecomeInvisible

    private void jC_pacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jC_pacienteActionPerformed
        abre();
    }//GEN-LAST:event_jC_pacienteActionPerformed

    private void tabela_ordensMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabela_ordensMousePressed
        int ordemSele = tabela_ordens.getSelectedRow();
        if (ordemSele >= 0) {
            OrdemExames or = modelOrdens.getItem(ordemSele);
            CadPCMSODAO dao = new CadPCMSODAO();
            modelExames.listar(dao.buscExamePorOrdem(or));
        }
    }//GEN-LAST:event_tabela_ordensMousePressed

    private void tabela_examesFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tabela_examesFocusLost
        
    }//GEN-LAST:event_tabela_examesFocusLost

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed
        int ordemSele = tabela_ordens.getSelectedRow();
        
        if (ordemSele >= 0) {

            OrdemExames odExames  = modelOrdens.getItem(ordemSele);
            if (odExames != null) {
                gerarRel(odExames.getId());
            } else {
                JOptionPane.showMessageDialog(null, "Selecione uma ordem!");
            }
        }
    }//GEN-LAST:event_bt_imprimirActionPerformed

    private void bt_limparCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_limparCamposActionPerformed
        limpaTudo();
        jC_paciente.removeAllItems();
        jC_paciente.setPopupVisible(false);
    }//GEN-LAST:event_bt_limparCamposActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton bt_limparCampos;
    private javax.swing.JButton bt_sair;
    private javax.swing.JComboBox jC_paciente;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable tabela_exames;
    private javax.swing.JTable tabela_ordens;
    private javax.swing.JTextField tf_empresa;
    private javax.swing.JTextField tf_funcao;
    private javax.swing.JTextField tf_setor;
    // End of variables declaration//GEN-END:variables
public final java.util.Date converte(String dataConsul) throws ParseException {
        DateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date dat = new java.sql.Date(forma.parse(dataConsul).getTime());

        return dat;
    }
   
    
    private void formaTabela(){
        
        
        tabela_ordens.getColumnModel().getColumn(1).setPreferredWidth(150);
        
        tabela_exames.getColumnModel().getColumn(0).setPreferredWidth(300);
        tabela_exames.getColumnModel().getColumn(1).setPreferredWidth(10);
        tabela_exames.getColumnModel().getColumn(2).setPreferredWidth(300);
      
    }    
    
    public void gerarRel(int id_ordem){
        final Progress p = new Progress();
        p.setVisible(true);
        SwingWorker worker = new SwingWorker(){
            @Override
            protected Object doInBackground() throws Exception {
                     
            
             CadPacienteDAO dao = new CadPacienteDAO();
                MinhaEmpresa me = Sessao.getInstance().getMinhaEmpresa();
             dao.ConectRelatorio(id_ordem,me.getId());
    
                return null;
            }
            @Override
            protected void done() {
                p.setVisible(false);
            }
        };
        worker.execute();
    }
    
    private void limpaTudo(){
        tf_empresa.setText("");
        tf_setor.setText("");
        tf_funcao.setText("");
        modelOrdens.limpaLista();
        modelExames.limpaLista();
       
    }
    
     private void abre(){
        jC_paciente.showPopup();  
    }
    private void busaPacientes(){
       
        Object digito =  jC_paciente.getEditor().getItem();
        jC_paciente.removeAllItems();
        String toUpperCase = digito.toString().toUpperCase();
        
        if (!toUpperCase.equals("")) {
            
        
        CadPacienteDAO dao2 = new CadPacienteDAO();
        List<Paciente> listaPacientes = dao2.buscaPaciente(toUpperCase, 'e');
           
        
           for (Paciente paciente : listaPacientes) {
               
               if (listaPacientes.isEmpty()) {
                   limpaTudo();
               }else{
               
               jC_paciente.addItem(paciente);
               }            
            
        }
        }
    }
    
    
}