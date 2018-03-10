/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.gui;

import br.com.markConsult.Tetes.Utils;
import br.com.markConsult.classesMetodos.AnamneseTableModel;
import br.com.markConsult.classesMetodos.ArquivosProcedimentosTableModel;
import br.com.markConsult.classesMetodos.ConsultaProcedimentosNoSeleTableModel;
import br.com.markConsult.classesMetodos.DatasConsultasTableModel;
import br.com.markConsult.classesMetodos.FixedLengthDocument;
import br.com.markConsult.classesMetodos.IntegerDocument;
import br.com.markConsult.classesMetodos.ReceitaTableModel;
import br.com.markConsult.dao.CadAnamneseDAO;
import br.com.markConsult.dao.CadConsultasDAO;
import br.com.markConsult.dao.CadImagensPacienteDAO;
import br.com.markConsult.dao.CadPacienteDAO;
import br.com.markConsult.dao.CadReceitaDAO;
import br.com.markConsult.dao.ICadPacienteDAO;
import br.com.markConsult.entidades.Anamnese;
import br.com.markConsult.entidades.ArquivosProcedimento;
import br.com.markConsult.entidades.Clinica;
import br.com.markConsult.entidades.Consulta;
import br.com.markConsult.entidades.ConsultaProcedimento;
import br.com.markConsult.entidades.Paciente;
import br.com.markConsult.entidades.Receita;
import br.com.markConsult.entidades.Sessao;
import br.com.markConsult.entidades.Usuario;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JComponent;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;

/**
 *
 * @author Jeferson
 */
public class FichaModal extends javax.swing.JDialog {
    private final Properties confBanco = new Properties();
    String data = (new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(System.currentTimeMillis())));
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");

    ArrayList<File> imgSelecionadas = new ArrayList<>();

    //List<String> datas_cadastro = new ArrayList<>();
    private int ins_alt = 0;
    private final AnamneseTableModel model;
    private final ReceitaTableModel model2;
    private ConsultaProcedimentosNoSeleTableModel modelProcedimento;
    private final DatasConsultasTableModel modelDatasConsultas;
     private ArquivosProcedimentosTableModel modelArquivos;
     
    Paciente pacienteNaTela = null;

    /**
     * Creates new form FichaModal
     * @param parent
     * @param modal
     */
    public FichaModal(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        URL url = this.getClass().getResource("/br/com/markConsult/imagens/help.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
          jProgressBar1.setIndeterminate(true);
        jProgressBar1.setVisible(false);

        tf_dataAtual.setText(data);
        estadoBotoes("inicial");

        tf_nomClient.setDocument(new FixedLengthDocument(200));
        tf_idPaciente.setDocument(new IntegerDocument(10));

        model = new AnamneseTableModel();
        model2 = new ReceitaTableModel();
        modelProcedimento = new ConsultaProcedimentosNoSeleTableModel();
        modelDatasConsultas = new DatasConsultasTableModel();
        modelArquivos = new ArquivosProcedimentosTableModel();
        
        jT_datas.setModel(model);
        jT_receituario.setModel(model2);
        jT_procedimentos.setModel(modelProcedimento);
        jT_datasConsultas.setModel(modelDatasConsultas);
        tf_idPaciente.requestFocus();
        jT_arquivos.setModel(modelArquivos);
        
        try {
            confBanco.load(new FileInputStream("/markconsultas/banco.ini"));
        } catch (IOException ex) {
            Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
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

        jPanel1 = new javax.swing.JPanel();
        bt_buscCli = new javax.swing.JButton();
        tf_idPaciente = new javax.swing.JTextField();
        tf_nomClient = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        tf_idade = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        tf_fone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        tf_rua = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        tf_cidade = new javax.swing.JTextField();
        tf_dataNasc = new javax.swing.JFormattedTextField();
        tf_dataAtual = new javax.swing.JTextField();
        jProgressBar1 = new javax.swing.JProgressBar();
        jTPanel = new javax.swing.JTabbedPane();
        jPanel_fixa = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tf_descricao = new javax.swing.JTextArea();
        jScrollPane3 = new javax.swing.JScrollPane();
        jT_datas = new javax.swing.JTable();
        jPanel_receita = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tf_descricao1 = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        jT_receituario = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jT_procedimentos = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        jT_datasConsultas = new javax.swing.JTable();
        jScrollPane7 = new javax.swing.JScrollPane();
        jT_arquivos = new javax.swing.JTable();
        jp_botoes = new javax.swing.JPanel();
        bt_salvar = new javax.swing.JButton();
        bt_novo = new javax.swing.JButton();
        bt_excluir = new javax.swing.JButton();
        bt_editar = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bt_cancelar = new javax.swing.JButton();
        bt_imprimir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Dados do Paciente", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP));

        bt_buscCli.setText("...");
        bt_buscCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscCliActionPerformed(evt);
            }
        });

        tf_idPaciente.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                tf_idPacienteFocusLost(evt);
            }
        });
        tf_idPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tf_idPacienteActionPerformed(evt);
            }
        });
        tf_idPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                tf_idPacienteKeyPressed(evt);
            }
        });

        jLabel1.setText("Paciente:");

        jLabel3.setText("Data Nas.:");

        jLabel4.setText("Idade:");

        tf_idade.setEditable(false);

        jLabel5.setText("Fone:");

        tf_fone.setEditable(false);

        jLabel6.setText("Rua:");

        tf_rua.setEditable(false);

        jLabel7.setText("Cidade:");

        tf_cidade.setEditable(false);

        tf_dataNasc.setEditable(false);

        tf_dataAtual.setEditable(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(bt_buscCli)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_idPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_nomClient)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tf_dataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tf_cidade)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tf_dataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, 103, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_idade, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_fone)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tf_rua, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bt_buscCli)
                    .addComponent(tf_idPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_nomClient, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(tf_dataAtual, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(tf_idade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(tf_fone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(tf_rua, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tf_dataNasc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tf_cidade, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
        );

        jTPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTPanelMouseClicked(evt);
            }
        });

        tf_descricao.setColumns(20);
        tf_descricao.setRows(5);
        jScrollPane1.setViewportView(tf_descricao);

        jT_datas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jT_datas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jT_datasMousePressed(evt);
            }
        });
        jT_datas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_datasKeyReleased(evt);
            }
        });
        jScrollPane3.setViewportView(jT_datas);

        javax.swing.GroupLayout jPanel_fixaLayout = new javax.swing.GroupLayout(jPanel_fixa);
        jPanel_fixa.setLayout(jPanel_fixaLayout);
        jPanel_fixaLayout.setHorizontalGroup(
            jPanel_fixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_fixaLayout.createSequentialGroup()
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_fixaLayout.setVerticalGroup(
            jPanel_fixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_fixaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_fixaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTPanel.addTab("Ficha", jPanel_fixa);

        tf_descricao1.setColumns(20);
        tf_descricao1.setRows(5);
        jScrollPane2.setViewportView(tf_descricao1);

        jT_receituario.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jT_receituario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jT_receituarioMousePressed(evt);
            }
        });
        jT_receituario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_receituarioKeyReleased(evt);
            }
        });
        jScrollPane4.setViewportView(jT_receituario);

        javax.swing.GroupLayout jPanel_receitaLayout = new javax.swing.GroupLayout(jPanel_receita);
        jPanel_receita.setLayout(jPanel_receitaLayout);
        jPanel_receitaLayout.setHorizontalGroup(
            jPanel_receitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_receitaLayout.createSequentialGroup()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 814, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel_receitaLayout.setVerticalGroup(
            jPanel_receitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel_receitaLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel_receitaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 324, Short.MAX_VALUE))
                .addContainerGap())
        );

        jTPanel.addTab("Receituário", jPanel_receita);

        jT_procedimentos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jT_procedimentos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jT_procedimentosMousePressed(evt);
            }
        });
        jT_procedimentos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_procedimentosKeyReleased(evt);
            }
        });
        jScrollPane6.setViewportView(jT_procedimentos);

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/picture-search-icon.png"))); // NOI18N
        jButton1.setToolTipText("Imagem maior");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jT_datasConsultas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jT_datasConsultas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jT_datasConsultasMousePressed(evt);
            }
        });
        jT_datasConsultas.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jT_datasConsultasKeyReleased(evt);
            }
        });
        jScrollPane5.setViewportView(jT_datasConsultas);

        jT_arquivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jT_arquivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jT_arquivosMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jT_arquivos);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane6, javax.swing.GroupLayout.PREFERRED_SIZE, 444, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 293, Short.MAX_VALUE)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jTPanel.addTab("Exames / Imagens", jPanel2);

        jp_botoes.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        bt_salvar.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        bt_salvar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/salvaIt.png"))); // NOI18N
        bt_salvar.setToolTipText("");
        bt_salvar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Salvar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Ubuntu", 0, 11))); // NOI18N
        bt_salvar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_salvarActionPerformed(evt);
            }
        });

        bt_novo.setFont(new java.awt.Font("Ubuntu", 0, 10)); // NOI18N
        bt_novo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/new-file-icon (2).png"))); // NOI18N
        bt_novo.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Novo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Ubuntu", 0, 11))); // NOI18N
        bt_novo.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        bt_novo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_novoActionPerformed(evt);
            }
        });

        bt_excluir.setFont(new java.awt.Font("Ubuntu", 0, 8)); // NOI18N
        bt_excluir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/trash-icon (2).png"))); // NOI18N
        bt_excluir.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Excluir", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Ubuntu", 0, 11))); // NOI18N
        bt_excluir.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        bt_excluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_excluirActionPerformed(evt);
            }
        });

        bt_editar.setFont(new java.awt.Font("Ubuntu", 0, 8)); // NOI18N
        bt_editar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/Action-edit-icon (2).png"))); // NOI18N
        bt_editar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Editar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Ubuntu", 0, 11))); // NOI18N
        bt_editar.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        bt_editar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_editarActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/Log-Out-icon (1).png"))); // NOI18N
        jButton2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sair", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Ubuntu", 0, 11))); // NOI18N
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        bt_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/Action-edit-icon (3).png"))); // NOI18N
        bt_cancelar.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Cancelar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM));
        bt_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_cancelarActionPerformed(evt);
            }
        });

        bt_imprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/print.png"))); // NOI18N
        bt_imprimir.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Imprimir", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.BOTTOM, new java.awt.Font("Arial", 0, 11))); // NOI18N
        bt_imprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_imprimirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jp_botoesLayout = new javax.swing.GroupLayout(jp_botoes);
        jp_botoes.setLayout(jp_botoesLayout);
        jp_botoesLayout.setHorizontalGroup(
            jp_botoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_botoesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(bt_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jp_botoesLayout.setVerticalGroup(
            jp_botoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jp_botoesLayout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addGroup(jp_botoesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(bt_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_imprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_editar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_excluir, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_salvar, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bt_novo, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 992, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jTPanel, javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addGap(140, 140, 140)
                            .addComponent(jp_botoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(83, 83, 83)))
                    .addContainerGap()))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 589, Short.MAX_VALUE)
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, 9, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jTPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 374, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addComponent(jp_botoes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void bt_buscCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscCliActionPerformed
        telaBuscaClient();
    }//GEN-LAST:event_bt_buscCliActionPerformed

    private void tf_idPacienteFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tf_idPacienteFocusLost
        if (tf_idPaciente.isEditable()) {
            String id = tf_idPaciente.getText();
            if (id.equals("")) {
                limpPaciente();
            } else {
                buscaClient(id);
            }
        }
    }//GEN-LAST:event_tf_idPacienteFocusLost

    private void tf_idPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tf_idPacienteActionPerformed
        bt_buscCli.requestFocus();
    }//GEN-LAST:event_tf_idPacienteActionPerformed

    private void tf_idPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tf_idPacienteKeyPressed
        if (tf_idPaciente.isEditable()) {
            if (evt.getKeyCode() == KeyEvent.VK_F2) {
                telaBuscaClient();

            }
        }
    }//GEN-LAST:event_tf_idPacienteKeyPressed

    private void jT_datasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_datasMousePressed
        int sele = jT_datas.getSelectedRow();
        mostra_ana(model.getItem(sele));
    }//GEN-LAST:event_jT_datasMousePressed

    private void jT_datasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_datasKeyReleased
        int sele = jT_datas.getSelectedRow();
        mostra_ana(model.getItem(sele));
    }//GEN-LAST:event_jT_datasKeyReleased

    private void jT_receituarioMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_receituarioMousePressed
        int sele = jT_receituario.getSelectedRow();
        mostra_receita(model2.getItem(sele));
    }//GEN-LAST:event_jT_receituarioMousePressed

    private void jT_receituarioKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_receituarioKeyReleased
        int sele = jT_receituario.getSelectedRow();
        mostra_receita(model2.getItem(sele));
    }//GEN-LAST:event_jT_receituarioKeyReleased

    private void jT_procedimentosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_procedimentosMousePressed
        buscaArquivos();

    }//GEN-LAST:event_jT_procedimentosMousePressed

    private void jT_procedimentosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_procedimentosKeyReleased
        buscaArquivos();
    }//GEN-LAST:event_jT_procedimentosKeyReleased

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        int arquivoSelecionado = jT_arquivos.getSelectedRow();
        if (arquivoSelecionado >= 0) {
            ArquivosProcedimento arquivo = modelArquivos.getItem(arquivoSelecionado);
            File f = new File(arquivo.getArquivo());
            String ext = Utils.getExtension(f);
            if (!ext.equals("jpeg") && !ext.equals("jpg") && !ext.equals("png")) {
                abriArquivos();
            } else {
                List<ArquivosProcedimento> arquivos = modelArquivos.getProcedimentos();
                VerImagens vi = new VerImagens(null, true);
                vi.setaArquivos(arquivos, arquivo);
                vi.setVisible(true);
            }
        } else {
            JOptionPane.showMessageDialog(null, "Selecione um arquivo!");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jT_datasConsultasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_datasConsultasMousePressed
        mostraProcedimentos();
    }//GEN-LAST:event_jT_datasConsultasMousePressed

    private void jT_datasConsultasKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jT_datasConsultasKeyReleased
        mostraProcedimentos();
    }//GEN-LAST:event_jT_datasConsultasKeyReleased

    private void jT_arquivosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jT_arquivosMouseClicked
        if (jT_arquivos.isEnabled()) {
            if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 1) {
                //buscaArquivos();
            } else if (evt.getButton() == MouseEvent.BUTTON1 && evt.getClickCount() == 2) {
                abriArquivos();
            }
        }
    }//GEN-LAST:event_jT_arquivosMouseClicked

    private void jTPanelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTPanelMouseClicked
        int panelSelecionado = jTPanel.getSelectedIndex();

        if(panelSelecionado == 2){
            jp_botoes.setVisible(false);
        }else{
            jp_botoes.setVisible(true);
        }

    }//GEN-LAST:event_jTPanelMouseClicked

    private void bt_salvarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_salvarActionPerformed
        if (jTPanel.getSelectedIndex() == 0) {
            String idPaciente = tf_idPaciente.getText();
            if (idPaciente.equals("")) {
                JOptionPane.showMessageDialog(null, "Selecione um paciente!");
            } else if (tf_descricao.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite uma descrição!");
            } else {
                String tf_descriacao = tf_descricao.getText();
                Date dt = null;
                try {
                    dt = (Date) converte(data);
                } catch (ParseException ex) {
                    Logger.getLogger(CadConsultaModal.class.getName()).log(Level.SEVERE, null, ex);
                }
                CadAnamneseDAO dao = new CadAnamneseDAO();

                if (ins_alt == 0) {

                    Anamnese anamnese = new Anamnese(null, pacienteNaTela, dt, tf_descriacao);
                    int id = dao.inserir(anamnese);
                    anamnese.setId(id);
                    model.inserir(anamnese);
                    mostra_ultimo();

                } else {
                    int codigo = jT_datas.getSelectedRow();
                    Anamnese anaminese = model.getItem(codigo);
                    anaminese.setDescricao(tf_descriacao);
                    dao.alterar(anaminese);
                }
                ins_alt = 0;
                estadoBotoes("inicial");
            }
        } else {
            String idPaciente = tf_idPaciente.getText();
            if (idPaciente.equals("")) {
                JOptionPane.showMessageDialog(null, "Selecione um paciente!");
            } else if (tf_descricao1.getText().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite uma descrição!");
            } else {
                String tf_descriacao = tf_descricao1.getText();
                Date dt = null;
                try {
                    dt = (Date) converte(data);
                } catch (ParseException ex) {
                    Logger.getLogger(CadConsultaModal.class.getName()).log(Level.SEVERE, null, ex);
                }
                CadReceitaDAO dao = new CadReceitaDAO();

                Paciente cli = new Paciente(Integer.parseInt(idPaciente), null,null);
                if (ins_alt == 0) {

                    Receita receita = new Receita(null, cli, dt, tf_descriacao);
                    int id = dao.inserir(receita);
                    receita.setId(id);
                    model2.inserir(receita);
                    mostra_ultimo();

                } else {
                    int codigo = jT_receituario.getSelectedRow();
                    Receita receita = model2.getItem(codigo);
                    receita.setDescricao(tf_descriacao);
                    dao.alterar(receita);
                }
                ins_alt = 0;
                estadoBotoes("inicial");
            }
        }
    }//GEN-LAST:event_bt_salvarActionPerformed

    private void bt_novoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_novoActionPerformed
        if (jTPanel.getSelectedIndex() == 0) {
            limpCampFixa();
        } else {
            limpCampRec();
        }

        estadoBotoes("novo");
        ins_alt = 0;
    }//GEN-LAST:event_bt_novoActionPerformed

    private void bt_excluirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_excluirActionPerformed
        if (jTPanel.getSelectedIndex() == 0) {
            int i = jT_datas.getRowCount();
            if (i == 0) {

            } else {
                int c = jT_datas.getSelectedRow();

                if (c < 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um registro!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int opcao_escolhida = JOptionPane.showConfirmDialog(null, "Excluir registro selecionado?", "Exclusão ", JOptionPane.YES_NO_OPTION, 3);
                    if (opcao_escolhida == JOptionPane.YES_OPTION) {

                        CadAnamneseDAO dao = new CadAnamneseDAO();
                        Anamnese ana;

                        ana = model.getItem(c);
                        dao.remover(ana.getId());
                        model.excluir(ana);
                        mostra_ultimo();

                    }
                }
            }
        } else {
            int i = jT_receituario.getRowCount();
            if (i == 0) {

            } else {
                int c = jT_receituario.getSelectedRow();

                if (c < 0) {
                    JOptionPane.showMessageDialog(null, "Selecione um registro!", "Informação", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int opcao_escolhida = JOptionPane.showConfirmDialog(null, "Excluir registro selecionado?", "Exclusão ", JOptionPane.YES_NO_OPTION, 3);
                    if (opcao_escolhida == JOptionPane.YES_OPTION) {

                        CadReceitaDAO dao = new CadReceitaDAO();
                        Receita rec;

                        rec = model2.getItem(c);
                        dao.remover(rec.getId());
                        model2.excluir(rec);
                        mostra_ultimo();

                    }
                }
            }
        }
    }//GEN-LAST:event_bt_excluirActionPerformed

    private void bt_editarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_editarActionPerformed
        if (tf_idPaciente.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Selecione um paciente!");
        } else {
            estadoBotoes("editar");
            ins_alt = 1;
        }
    }//GEN-LAST:event_bt_editarActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        dispose();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void bt_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_cancelarActionPerformed
        String nome = "Deseja realmente cancelar?";
        int opcao_escolhida = JOptionPane.showConfirmDialog(null, nome, "Cancelar ", JOptionPane.YES_NO_OPTION);
        if (opcao_escolhida == JOptionPane.YES_OPTION) {
            estadoBotoes("inicial");
            ins_alt = 0;
            mostra_ultimo();
        }
    }//GEN-LAST:event_bt_cancelarActionPerformed

    private void bt_imprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_imprimirActionPerformed

        imprimir();

    }//GEN-LAST:event_bt_imprimirActionPerformed

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
            java.util.logging.Logger.getLogger(FichaModal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(() -> {
            FichaModal dialog = new FichaModal(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton bt_buscCli;
    private javax.swing.JButton bt_cancelar;
    private javax.swing.JButton bt_editar;
    private javax.swing.JButton bt_excluir;
    private javax.swing.JButton bt_imprimir;
    private javax.swing.JButton bt_novo;
    private javax.swing.JButton bt_salvar;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel_fixa;
    private javax.swing.JPanel jPanel_receita;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTabbedPane jTPanel;
    private javax.swing.JTable jT_arquivos;
    private javax.swing.JTable jT_datas;
    private javax.swing.JTable jT_datasConsultas;
    private javax.swing.JTable jT_procedimentos;
    private javax.swing.JTable jT_receituario;
    private javax.swing.JPanel jp_botoes;
    private javax.swing.JTextField tf_cidade;
    private javax.swing.JTextField tf_dataAtual;
    private javax.swing.JFormattedTextField tf_dataNasc;
    private javax.swing.JTextArea tf_descricao;
    private javax.swing.JTextArea tf_descricao1;
    private javax.swing.JTextField tf_fone;
    private javax.swing.JTextField tf_idPaciente;
    private javax.swing.JTextField tf_idade;
    private javax.swing.JTextField tf_nomClient;
    private javax.swing.JTextField tf_rua;
    // End of variables declaration//GEN-END:variables
public void telaBuscaClient() {
        BuscaPacientesModal telBuscClie = new BuscaPacientesModal(null, true);
        telBuscClie.setVisible(true);

        if (telBuscClie.okselecionado()) {
            Paciente c = telBuscClie.retornCliSele();
            if (c != null) {
                mostra_paciente(c);

            }
        }

    }

    public void mostra_paciente(Paciente p) {
        if (p != null) {
            pacienteNaTela = p;
            tf_idPaciente.setText(p.getId().toString());
            tf_nomClient.setText(p.getNome());
            if (p.getDataNasc() != null) {
                tf_dataNasc.setText(formato.format(p.getDataNasc()));
                try {
                    getIdade();
                } catch (ParseException ex) {
                    Logger.getLogger(Ficha.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            tf_fone.setText(p.getFoneFixo());
            tf_rua.setText(p.getCep().getLogradouro());
            tf_cidade.setText(p.getCep().getCidade());
            mostra_fixas();
            mostra_reiceitas();
            mostraArquivos();

        } else {
            pacienteNaTela = null;
        }
    }

    public void limpPaciente() {
        tf_nomClient.setText("");
        tf_rua.setText("");
        tf_cidade.setText("");
        tf_fone.setText("");
        tf_dataNasc.setText("");
        tf_idade.setText("");
        limpCampFixa();
        limpCampRec();
        model.limpar();
        model2.limpar();
    }

    public void mostra_fixas() {
        CadAnamneseDAO dao = new CadAnamneseDAO();
        List<Anamnese> datas = dao.datasAnamneses(Integer.parseInt(tf_idPaciente.getText()));
        model.listar(datas);

        if (jT_datas.getRowCount() > 0) {
            mostra_ultimo();
        } else {
            tf_descricao.setText("");
        }
    }

    public void mostra_reiceitas() {
        CadReceitaDAO dao = new CadReceitaDAO();
        List<Receita> datas = dao.datasReceitas(pacienteNaTela.getId());
        model2.listar(datas);

        if (jT_datas.getRowCount() > 0) {
            mostra_ultimo();
        } else {
            tf_descricao1.setText("");
        }
    }
    
    private void mostraArquivos(){
        CadConsultasDAO dao = new CadConsultasDAO();
        List<Consulta> con = dao.buscaConsultasPorIdPaciente(pacienteNaTela);
        modelDatasConsultas.listar(con);

    }

    public void mostra_ana(Anamnese ana) {
        tf_descricao.setText(ana.getDescricao());
    }

    public void mostra_receita(Receita rec) {
        tf_descricao1.setText(rec.getDescricao());
    }

    public void mostra_ultimo() {

        int ult = jT_datas.getRowCount();
        if (ult > 0) {
            mostra_ana(model.getItem(ult - 1));
            jT_datas.getSelectionModel().setSelectionInterval(ult - 1, ult - 1);

        } else {
            tf_descricao.setText("");
        }

        int ult2 = jT_receituario.getRowCount();
        if (ult2 > 0) {
            mostra_receita(model2.getItem(ult2 - 1));
            jT_receituario.getSelectionModel().setSelectionInterval(ult2 - 1, ult2 - 1);

        } else {
            tf_descricao1.setText("");
        }

    }

    private void estadoBotoes(String botao) {
        if ("inicial".equals(botao)) {
            bt_novo.setEnabled(true);
            bt_cancelar.setEnabled(false);
            bt_editar.setEnabled(true);
            bt_salvar.setEnabled(false);
            bt_excluir.setEnabled(true);
            bt_buscCli.setEnabled(true);
            bt_imprimir.setEnabled(true);

            tf_idPaciente.setEditable(true);
            tf_nomClient.setEditable(false);
            tf_descricao.setEditable(false);
            tf_descricao1.setEditable(false);

            jT_datas.setEnabled(true);
            jT_receituario.setEnabled(true);

            jTPanel.setEnabledAt(0, true);
            jTPanel.setEnabledAt(1, true);

        }

        if ("novo".equals(botao)) {
            bt_novo.setEnabled(false);
            bt_cancelar.setEnabled(true);
            bt_editar.setEnabled(true);
            bt_excluir.setEnabled(false);
            bt_editar.setEnabled(false);
            bt_salvar.setEnabled(true);
            bt_buscCli.setEnabled(true);
            bt_imprimir.setEnabled(false);

            tf_idPaciente.setEditable(true);
            tf_descricao.setEditable(true);
            tf_descricao1.setEditable(true);

            jT_datas.setEnabled(false);
            jT_receituario.setEnabled(false);

            if (jTPanel.getSelectedIndex() == 0) {
                jTPanel.setEnabledAt(1, false);
            } else {
                jTPanel.setEnabledAt(0, false);
            }

        }

        if ("cancelar".equals(botao)) {

            estadoBotoes("inicial");
        }

        if ("editar".equals(botao)) {
            bt_novo.setEnabled(false);
            bt_cancelar.setEnabled(true);
            bt_editar.setEnabled(true);
            bt_excluir.setEnabled(false);
            bt_editar.setEnabled(false);
            bt_salvar.setEnabled(true);
            bt_buscCli.setEnabled(false);
            bt_imprimir.setEnabled(false);

            tf_idPaciente.setEditable(false);
            tf_descricao.setEditable(true);
            tf_descricao1.setEditable(true);

            jT_datas.setEnabled(false);
            jT_receituario.setEnabled(false);

            if (jTPanel.getSelectedIndex() == 0) {
                jTPanel.setEnabledAt(1, false);
            } else {
                jTPanel.setEnabledAt(0, false);
            }
        }
        if ("salvar".equals(botao)) {
            estadoBotoes("inicial");

        }

        if ("buscar".equals(botao)) {
            estadoBotoes("inicial");

        }

    }

    public final java.util.Date converte(String dataConsul) throws ParseException {
        DateFormat forma = new SimpleDateFormat("dd/MM/yyyy");
        java.sql.Date dat = new java.sql.Date(forma.parse(dataConsul).getTime());

        return dat;
    }

    public void limpCampFixa() {
        if (ins_alt != 1) {
            tf_descricao.setText("");

        }
    }

    public void limpCampRec() {
        if (ins_alt != 1) {
            tf_descricao1.setText("");

        }
    }

    public void getIdade() throws ParseException {
        java.util.Date dataNasc = converte(tf_dataNasc.getText());

        Calendar dataNascimento = Calendar.getInstance();
        if (dataNasc != null) {
            dataNascimento.setTime(dataNasc);
            Calendar dataAtual = Calendar.getInstance();

            Integer diferencaMes = dataAtual.get(Calendar.MONTH) - dataNascimento.get(Calendar.MONTH);
            Integer diferencaDia = dataAtual.get(Calendar.DAY_OF_MONTH) - dataNascimento.get(Calendar.DAY_OF_MONTH);
            Integer idade = (dataAtual.get(Calendar.YEAR) - dataNascimento.get(Calendar.YEAR));

            if (diferencaMes < 0 || (diferencaMes == 0 && diferencaDia < 0)) {
                idade--;
            }
            tf_idade.setText(idade.toString());
        } else {
            tf_idade.setText("");
        }
    }

    public void buscaClient(String id) {
        ICadPacienteDAO dao = new CadPacienteDAO();

        Paciente client = dao.buscaPacientePorId(Integer.parseInt(id));
        if (client != null) {
            mostra_paciente(client);

        } else {
            JOptionPane.showMessageDialog(null, "paciente não cadastrado!", "Informação", JOptionPane.INFORMATION_MESSAGE);
            tf_idPaciente.requestFocus();

        }

    }
    private void buscaArquivos() {
        int procedimentoSelecionado = jT_procedimentos.getSelectedRow();
        if (procedimentoSelecionado >= 0) {
            ConsultaProcedimento cp = modelProcedimento.getItem(procedimentoSelecionado);
            CadImagensPacienteDAO dao = new CadImagensPacienteDAO();
            List<ArquivosProcedimento> ap = dao.buscaArquivosProcedimento(cp);
            if(!ap.isEmpty()){
            modelArquivos.listar(ap);
            }else{
                modelArquivos.limparTabela();
            }
        }
    }
       private void abriArquivos() {
        try {
            int sele = jT_arquivos.getSelectedRow();
            if (sele < 0) {
                return;
            }

            ArquivosProcedimento imp = modelArquivos.getItem(sele);
            File arquivo = new File(confBanco.getProperty("ip") + imp.getConsultaProcedimento().getId() + "/" + imp.getArquivo());
            if(Files.exists(arquivo.toPath())){
                java.awt.Desktop.getDesktop().open(arquivo.getAbsoluteFile());
            }else{
                JOptionPane.showMessageDialog(null, "Arquivo não encontrado");
            }
           
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Erro abrindo arquivo");
        }
    }
       private void imprimir() {
        jProgressBar1.setVisible(true);
        SwingWorker worker = new SwingWorker() {
            @Override
            protected Object doInBackground() throws Exception {
                Usuario u = Sessao.getInstance().getUsuario();
                Clinica e = Sessao.getInstance().getClinica();
                if (jTPanel.getSelectedIndex() == 0) {
                    if (jT_datas.getRowCount() > 0) {
                        CadAnamneseDAO dao = new CadAnamneseDAO();
                        Anamnese ana = model.getItem(jT_datas.getSelectedRow());
                        dao.ConectRelatorio(ana.getId(), u.getId(), e.getId());
                    }
                } else {
                    if (jT_receituario.getRowCount() > 0) {
                        CadReceitaDAO dao = new CadReceitaDAO();
                        Receita rec = model2.getItem(jT_receituario.getSelectedRow());
                        dao.ConectRelatorio(rec.getId(), u.getId(), e.getId());
                    }

                }
                return null;
            }

            @Override
            protected void done() {

                jProgressBar1.setVisible(false);
            }
        };
         worker.execute();
    }
   
    private void mostraProcedimentos() {
      CadConsultasDAO dao = new CadConsultasDAO();
        int consultaSelecionada = jT_datasConsultas.getSelectedRow();
        Consulta con = modelDatasConsultas.getItem(consultaSelecionada);
        List<ConsultaProcedimento> procedimentos = dao.BuscaProcedimetoEmpresa("", 'e', con.getId());
        if(!procedimentos.isEmpty()){
         modelProcedimento.listar(procedimentos);
        }else{
            modelProcedimento.limparTabela();
        }
        modelArquivos.limparTabela();
    }
}
