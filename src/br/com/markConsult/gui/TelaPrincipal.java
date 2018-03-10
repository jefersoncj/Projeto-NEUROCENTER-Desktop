/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.gui;

import br.com.markConsult.dao.AlteraImagemDAO;
import br.com.markConsult.classesMetodos.BackGround;
import br.com.markConsult.dao.CadUsuarioDAO;
import br.com.markConsult.dao.Desktop;
import br.com.markConsult.entidades.Sessao;
import br.com.markConsult.entidades.Clinica;
import br.com.markConsult.entidades.Usuario;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.beans.PropertyVetoException;
import java.net.URL;
import java.text.ParseException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

/**
 *
 * @author jeferson
 */
public class TelaPrincipal extends javax.swing.JFrame {

   
    Desktop desk = new Desktop();
    Boolean escBarra;
    BackGround background = null;
    int trocar = 1;
    Usuario u;
    Clinica emp;
    String data = (new java.text.SimpleDateFormat("dd/MM/yyyy").format(new java.util.Date(System.currentTimeMillis())));

    /**
     * Creates new form TelaPrincipal
     */
    public TelaPrincipal() {
        initComponents();
        jMenu2.setVisible(false);
        lbData.setText(" " + data);
        u = Sessao.getInstance().getUsuario();
        emp = Sessao.getInstance().getClinica();
        if (u != null) {
            lb_usuario.setText(u.getNome());
            escBarra = u.isEscBarra();
            if (u.getCodTema() != 8) {
                mudaAparencia(u.getCodTema());
            }

        }

        if (emp != null) {
            jL_empresa.setText(emp.getFantasia());

        }
        
//          if (u.isHeAdm()) {
//            jM_fila.setVisible(false);
//            btListCon.setVisible(false);
//        } 
        if (u.isHeMedico()) {
            jM_ficha.setVisible(true);
            jM_fila.setVisible(true);
            jMenu_consultas.setVisible(false);
            btConsultas.setVisible(false);
            bt_marcaConsultas.setVisible(false);
            bt_buscaConsultas.setVisible(false);
            btRelCon.setVisible(false);
            bt_condPagto.setVisible(false);
            bt_convenios.setVisible(false);
            bt_empresas.setVisible(false);
            jMenu_relatorios.setVisible(false);
            jMenuItem_especialidades.setVisible(false);
            jMenuItem_condPagto.setVisible(false);
            menu_item_convenios.setVisible(false);
            jMenuIt_procedimentos.setVisible(false);
            jmiCadUsuario.setVisible(false);
            menuClinica.setVisible(false);
            menuEmpresas.setVisible(false);
            jM_funcoes.setVisible(false);
            
            jSeparator1.setVisible(false);
            jSeparator2.setVisible(false);
            jSeparator3.setVisible(false);
            jSeparator4.setVisible(false);
            jSeparator6.setVisible(false);
            jSeparator7.setVisible(false);
            jSeparator9.setVisible(false);
            jSeparator10.setVisible(false);
            jSeparator11.setVisible(false);
            jSeparator13.setVisible(false);
            
        } 
        
        if (u.isHeAdm() && u.isHeMedico()  ) {
            jM_ficha.setVisible(true);
            jM_fila.setVisible(true);
            jMenu_consultas.setVisible(true);
            btConsultas.setVisible(true);
            bt_marcaConsultas.setVisible(true);
            bt_buscaConsultas.setVisible(true);
            btRelCon.setVisible(true);
            bt_condPagto.setVisible(true);
            bt_convenios.setVisible(true);
            bt_empresas.setVisible(true);
            jMenu_relatorios.setVisible(true);
            jMenuItem_especialidades.setVisible(true);
            jMenuItem_condPagto.setVisible(true);
            menu_item_convenios.setVisible(true);
            jMenuIt_procedimentos.setVisible(true);
            jmiCadUsuario.setVisible(true);
            menuClinica.setVisible(true);
            menuEmpresas.setVisible(true);
            jM_funcoes.setVisible(true);
            
            
            jSeparator1.setVisible(true);
            jSeparator2.setVisible(true);
            jSeparator3.setVisible(true);
            jSeparator4.setVisible(true);
            jSeparator6.setVisible(true);
            jSeparator7.setVisible(true);
            jSeparator9.setVisible(true);
            jSeparator10.setVisible(true);
            jSeparator11.setVisible(true);
            jSeparator13.setVisible(true);
        }
        
         if (u.isHeAtendente()) {
            jMenu_consultas.setVisible(true);
            btConsultas.setVisible(true);
           bt_marcaConsultas.setVisible(true);
            btListCon.setVisible(false);
            jM_fila.setVisible(false);
            bt_buscaConsultas.setVisible(true);
            btRelCon.setVisible(true);
            bt_condPagto.setVisible(true);
            bt_convenios.setVisible(true);
            bt_empresas.setVisible(true);
            jMenu_relatorios.setVisible(true);
            jMenuItem_especialidades.setVisible(true);
            jMenuItem_condPagto.setVisible(true);
            menu_item_convenios.setVisible(true);
            jMenuIt_procedimentos.setVisible(true);
            jmiCadUsuario.setVisible(false);
            menuClinica.setVisible(true);
            menuEmpresas.setVisible(true);
            jM_funcoes.setVisible(true);
            
            
            jSeparator1.setVisible(true);
            jSeparator2.setVisible(true);
            jSeparator3.setVisible(true);
            jSeparator4.setVisible(true);
            jSeparator6.setVisible(true);
            jSeparator7.setVisible(true);
            jSeparator9.setVisible(true);
            jSeparator10.setVisible(true);
            jSeparator11.setVisible(true);
            jSeparator13.setVisible(true);
        } 
        
        if (u.getCodTema() == 8) {
            addpopup();
        }

        escBlo();

        // coloca uma figura na barra de título da janela  
        URL url = this.getClass().getResource("/br/com/markConsult/imagens/help.png");
        Image imagemTitulo = Toolkit.getDefaultToolkit().getImage(url);
        this.setIconImage(imagemTitulo);
        this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);

        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                if (e.getID() == WindowEvent.WINDOW_CLOSING) {
                    fecharTela();
                }
            }
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

        jPopupBarra = new javax.swing.JPopupMenu();
        jMEsconder = new javax.swing.JMenuItem();
        jMBloquear = new javax.swing.JMenuItem();
        lb_usuario = new javax.swing.JLabel();
        lbData = new javax.swing.JLabel();
        desktopPane =  background = new BackGround();
        desktopPane =  background;
        barraFerrament = new javax.swing.JToolBar();
        btCadPaciente = new javax.swing.JButton();
        bt_marcaConsultas = new javax.swing.JButton();
        btConsultas = new javax.swing.JButton();
        bt_buscaPaciente = new javax.swing.JButton();
        btListCon = new javax.swing.JButton();
        bt_buscaConsultas = new javax.swing.JButton();
        btRelCon = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        bt_condPagto = new javax.swing.JButton();
        bt_convenios = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        bt_empresas = new javax.swing.JButton();
        bt_acuidade = new javax.swing.JButton();
        lb_barra = new javax.swing.JLabel();
        jL_empresa = new javax.swing.JLabel();
        menu_principal = new javax.swing.JMenuBar();
        menu_cadastros = new javax.swing.JMenu();
        jMenuItem_especialidades = new javax.swing.JMenuItem();
        jSeparator1 = new javax.swing.JPopupMenu.Separator();
        menu_item_Paciente = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        jMenuItem_condPagto = new javax.swing.JMenuItem();
        jSeparator2 = new javax.swing.JPopupMenu.Separator();
        menu_item_convenios = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        jMenuIt_procedimentos = new javax.swing.JMenuItem();
        jSeparator12 = new javax.swing.JPopupMenu.Separator();
        menuUsuario = new javax.swing.JMenu();
        jmiCadUsuario = new javax.swing.JMenuItem();
        jSeparator9 = new javax.swing.JPopupMenu.Separator();
        jmiAlteraSenh = new javax.swing.JMenuItem();
        jSeparator10 = new javax.swing.JPopupMenu.Separator();
        menuClinica = new javax.swing.JMenuItem();
        jSeparator11 = new javax.swing.JPopupMenu.Separator();
        menuEmpresas = new javax.swing.JMenuItem();
        jSeparator13 = new javax.swing.JPopupMenu.Separator();
        jM_funcoes = new javax.swing.JMenuItem();
        menu_atendimento = new javax.swing.JMenu();
        jMenu_consultas = new javax.swing.JMenu();
        menu_item_conAgenda = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        menu_item_cadConsuta = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        menu_item_consulPclient = new javax.swing.JMenuItem();
        jM_fila = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenu_relatorios = new javax.swing.JMenu();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem2 = new javax.swing.JMenuItem();
        jM_ficha = new javax.swing.JMenu();
        jMenuItem1 = new javax.swing.JMenuItem();
        jMenu1 = new javax.swing.JMenu();
        jMenu6 = new javax.swing.JMenu();
        jMenuItemAjuda = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuTema = new javax.swing.JMenu();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuIAluminium = new javax.swing.JMenuItem();
        jMenuIAcrylLook = new javax.swing.JMenuItem();
        jMenuIGraphite = new javax.swing.JMenuItem();
        jMenuILuna = new javax.swing.JMenuItem();
        jMenuIMint = new javax.swing.JMenuItem();
        jMenuIMcWinLook = new javax.swing.JMenuItem();
        jMenuAero = new javax.swing.JMenuItem();

        jMEsconder.setText("Esconder Barra");
        jMEsconder.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMEsconderActionPerformed(evt);
            }
        });
        jPopupBarra.add(jMEsconder);

        jMBloquear.setText("Bloquear Barra");
        jMBloquear.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMBloquearActionPerformed(evt);
            }
        });
        jPopupBarra.add(jMBloquear);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("NEUROCENTER");
        addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                formMouseMoved(evt);
            }
        });

        lb_usuario.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lb_usuario.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        lbData.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        desktopPane.setOpaque(false);
        desktopPane.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                desktopPaneMouseMoved(evt);
            }
        });

        barraFerrament.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        barraFerrament.setFloatable(false);
        barraFerrament.setOrientation(javax.swing.SwingConstants.VERTICAL);
        barraFerrament.setRollover(true);
        barraFerrament.setAutoscrolls(true);
        barraFerrament.setPreferredSize(new java.awt.Dimension(48, 800));

        btCadPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/cliente.png"))); // NOI18N
        btCadPaciente.setToolTipText("Cadastro de Paciente");
        btCadPaciente.setFocusable(false);
        btCadPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btCadPaciente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btCadPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btCadPacienteActionPerformed(evt);
            }
        });
        barraFerrament.add(btCadPaciente);

        bt_marcaConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/consultas.png"))); // NOI18N
        bt_marcaConsultas.setToolTipText("Cadastro de Consultas");
        bt_marcaConsultas.setFocusable(false);
        bt_marcaConsultas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_marcaConsultas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_marcaConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_marcaConsultasActionPerformed(evt);
            }
        });
        barraFerrament.add(bt_marcaConsultas);

        btConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/consultasAgend.png"))); // NOI18N
        btConsultas.setToolTipText("Consultas agendadas");
        btConsultas.setFocusable(false);
        btConsultas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btConsultas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btConsultasActionPerformed(evt);
            }
        });
        barraFerrament.add(btConsultas);

        bt_buscaPaciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/buscaPaciente.png"))); // NOI18N
        bt_buscaPaciente.setToolTipText("Busca Paciente");
        bt_buscaPaciente.setFocusable(false);
        bt_buscaPaciente.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_buscaPaciente.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_buscaPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscaPacienteActionPerformed(evt);
            }
        });
        barraFerrament.add(bt_buscaPaciente);

        btListCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/listCon.png"))); // NOI18N
        btListCon.setToolTipText("Atendimento");
        btListCon.setFocusable(false);
        btListCon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btListCon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btListCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btListConActionPerformed(evt);
            }
        });
        barraFerrament.add(btListCon);

        bt_buscaConsultas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/BuscaConsultas.png"))); // NOI18N
        bt_buscaConsultas.setToolTipText("Lista Consultas");
        bt_buscaConsultas.setFocusable(false);
        bt_buscaConsultas.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_buscaConsultas.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_buscaConsultas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_buscaConsultasActionPerformed(evt);
            }
        });
        barraFerrament.add(bt_buscaConsultas);

        btRelCon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/relatórios.png"))); // NOI18N
        btRelCon.setToolTipText("Relatório de consultas");
        btRelCon.setFocusable(false);
        btRelCon.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btRelCon.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btRelCon.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRelConActionPerformed(evt);
            }
        });
        barraFerrament.add(btRelCon);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/password.png"))); // NOI18N
        jButton2.setToolTipText("Alterar Senha");
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        barraFerrament.add(jButton2);

        bt_condPagto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/payment-icon.png"))); // NOI18N
        bt_condPagto.setMnemonic('C');
        bt_condPagto.setToolTipText("Condição de pagamento");
        bt_condPagto.setFocusable(false);
        bt_condPagto.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_condPagto.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_condPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_condPagtoActionPerformed(evt);
            }
        });
        barraFerrament.add(bt_condPagto);

        bt_convenios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/contacts-icon.png"))); // NOI18N
        bt_convenios.setToolTipText("Convênios");
        bt_convenios.setFocusable(false);
        bt_convenios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_convenios.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_convenios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_conveniosActionPerformed(evt);
            }
        });
        barraFerrament.add(bt_convenios);

        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/file.png"))); // NOI18N
        jButton6.setToolTipText("Documento e imagens Paciente");
        jButton6.setFocusable(false);
        jButton6.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton6.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        barraFerrament.add(jButton6);

        bt_empresas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/company.png"))); // NOI18N
        bt_empresas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_empresasActionPerformed(evt);
            }
        });
        barraFerrament.add(bt_empresas);

        bt_acuidade.setIcon(new javax.swing.ImageIcon(getClass().getResource("/br/com/markConsult/imagens/acuidade.png"))); // NOI18N
        bt_acuidade.setMnemonic('A');
        bt_acuidade.setToolTipText("Teste de Acuidade Visual");
        bt_acuidade.setFocusable(false);
        bt_acuidade.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bt_acuidade.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bt_acuidade.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bt_acuidadeActionPerformed(evt);
            }
        });
        barraFerrament.add(bt_acuidade);

        desktopPane.add(barraFerrament);
        barraFerrament.setBounds(0, 0, 60, 520);

        lb_barra.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                lb_barraMouseMoved(evt);
            }
        });
        desktopPane.add(lb_barra);
        lb_barra.setBounds(0, 0, 1, 700);

        jL_empresa.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jL_empresa.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        menu_principal.setPreferredSize(new java.awt.Dimension(398, 20));

        menu_cadastros.setMnemonic('C');
        menu_cadastros.setText("Cadastros");
        menu_cadastros.setBorderPainted(true);
        menu_cadastros.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        menu_cadastros.setPreferredSize(new java.awt.Dimension(70, 76));

        jMenuItem_especialidades.setText("Especialidades Médicas");
        jMenuItem_especialidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_especialidadesActionPerformed(evt);
            }
        });
        menu_cadastros.add(jMenuItem_especialidades);
        menu_cadastros.add(jSeparator1);

        menu_item_Paciente.setMnemonic('c');
        menu_item_Paciente.setText("Pacientes");
        menu_item_Paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_PacienteActionPerformed(evt);
            }
        });
        menu_cadastros.add(menu_item_Paciente);
        menu_cadastros.add(jSeparator3);

        jMenuItem_condPagto.setMnemonic('C');
        jMenuItem_condPagto.setText("Cond. Pagamentos");
        jMenuItem_condPagto.setMaximumSize(getMaximumSize());
        jMenuItem_condPagto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem_condPagtoActionPerformed(evt);
            }
        });
        menu_cadastros.add(jMenuItem_condPagto);
        menu_cadastros.add(jSeparator2);

        menu_item_convenios.setMnemonic('C');
        menu_item_convenios.setText("Convênios");
        menu_item_convenios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_conveniosActionPerformed(evt);
            }
        });
        menu_cadastros.add(menu_item_convenios);
        menu_cadastros.add(jSeparator4);

        jMenuIt_procedimentos.setText("Procedimentos");
        jMenuIt_procedimentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIt_procedimentosActionPerformed(evt);
            }
        });
        menu_cadastros.add(jMenuIt_procedimentos);
        menu_cadastros.add(jSeparator12);

        menuUsuario.setMnemonic('U');
        menuUsuario.setText("Usuários");

        jmiCadUsuario.setText("Cadastro de usuário");
        jmiCadUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiCadUsuarioActionPerformed(evt);
            }
        });
        menuUsuario.add(jmiCadUsuario);
        menuUsuario.add(jSeparator9);

        jmiAlteraSenh.setText("Alterar senha");
        jmiAlteraSenh.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jmiAlteraSenhActionPerformed(evt);
            }
        });
        menuUsuario.add(jmiAlteraSenh);

        menu_cadastros.add(menuUsuario);
        menu_cadastros.add(jSeparator10);

        menuClinica.setText("Clínica");
        menuClinica.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuClinicaActionPerformed(evt);
            }
        });
        menu_cadastros.add(menuClinica);
        menu_cadastros.add(jSeparator11);

        menuEmpresas.setText("Empresas");
        menuEmpresas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuEmpresasActionPerformed(evt);
            }
        });
        menu_cadastros.add(menuEmpresas);
        menu_cadastros.add(jSeparator13);

        jM_funcoes.setText("Funções");
        jM_funcoes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_funcoesActionPerformed(evt);
            }
        });
        menu_cadastros.add(jM_funcoes);

        menu_principal.add(menu_cadastros);

        menu_atendimento.setMnemonic('o');
        menu_atendimento.setText("Consultas");
        menu_atendimento.setBorderPainted(true);
        menu_atendimento.setPreferredSize(new java.awt.Dimension(70, 76));

        jMenu_consultas.setMnemonic('C');
        jMenu_consultas.setText("Consultas");

        menu_item_conAgenda.setText("Consulta agendadas");
        menu_item_conAgenda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_conAgendaActionPerformed(evt);
            }
        });
        jMenu_consultas.add(menu_item_conAgenda);
        jMenu_consultas.add(jSeparator6);

        menu_item_cadConsuta.setText("Cadastro de Consulta");
        menu_item_cadConsuta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_cadConsutaActionPerformed(evt);
            }
        });
        jMenu_consultas.add(menu_item_cadConsuta);
        jMenu_consultas.add(jSeparator7);

        menu_item_consulPclient.setText("Lista Consultas");
        menu_item_consulPclient.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menu_item_consulPclientActionPerformed(evt);
            }
        });
        jMenu_consultas.add(menu_item_consulPclient);

        menu_atendimento.add(jMenu_consultas);

        jM_fila.setText("Atendimento");
        jM_fila.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jM_filaActionPerformed(evt);
            }
        });
        menu_atendimento.add(jM_fila);

        menu_principal.add(menu_atendimento);

        jMenu4.setText("Exames");

        jMenuItem8.setText("Acuidade Visual");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem8);

        menu_principal.add(jMenu4);

        jMenu2.setText("Controle Notas");

        jMenuItem6.setText("Busca notas");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem6);

        menu_principal.add(jMenu2);

        jMenu_relatorios.setMnemonic('R');
        jMenu_relatorios.setText("Relatórios");
        jMenu_relatorios.setBorderPainted(true);
        jMenu_relatorios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jMenu_relatorios.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jMenu_relatorios.setPreferredSize(new java.awt.Dimension(70, 76));

        jMenuItem7.setText("Movimento por condição de pagamento");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu_relatorios.add(jMenuItem7);

        jMenuItem3.setText("Consultas");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        jMenu_relatorios.add(jMenuItem3);

        jMenuItem2.setText("Empresas com movimento");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        jMenu_relatorios.add(jMenuItem2);

        menu_principal.add(jMenu_relatorios);

        jM_ficha.setText("Ficha/Receita");

        jMenuItem1.setText("Ficha/Receita");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jM_ficha.add(jMenuItem1);

        menu_principal.add(jM_ficha);

        jMenu1.setMnemonic('s');
        jMenu1.setText("Sair");
        jMenu1.setBorderPainted(true);
        jMenu1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu1MouseClicked(evt);
            }
        });
        menu_principal.add(jMenu1);

        jMenu6.setText("Ajuda");

        jMenuItemAjuda.setText("Contato");
        jMenuItemAjuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemAjudaActionPerformed(evt);
            }
        });
        jMenu6.add(jMenuItemAjuda);

        jMenu7.setText("Aparência do sistema");

        jMenuItem5.setText("Imagem de fundo");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem5);

        jMenuTema.setText("Tema");

        jMenuItem4.setText("Padrão");
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        jMenuTema.add(jMenuItem4);

        jMenuIAluminium.setText("Aluminium");
        jMenuIAluminium.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIAluminiumActionPerformed(evt);
            }
        });
        jMenuTema.add(jMenuIAluminium);

        jMenuIAcrylLook.setText("AcrylLook");
        jMenuIAcrylLook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIAcrylLookActionPerformed(evt);
            }
        });
        jMenuTema.add(jMenuIAcrylLook);

        jMenuIGraphite.setText("Graphite");
        jMenuIGraphite.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIGraphiteActionPerformed(evt);
            }
        });
        jMenuTema.add(jMenuIGraphite);

        jMenuILuna.setText("Luna");
        jMenuILuna.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuILunaActionPerformed(evt);
            }
        });
        jMenuTema.add(jMenuILuna);

        jMenuIMint.setText("Mint");
        jMenuIMint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIMintActionPerformed(evt);
            }
        });
        jMenuTema.add(jMenuIMint);

        jMenuIMcWinLook.setText("McWinLook");
        jMenuIMcWinLook.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuIMcWinLookActionPerformed(evt);
            }
        });
        jMenuTema.add(jMenuIMcWinLook);

        jMenuAero.setText("Aero");
        jMenuAero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuAeroActionPerformed(evt);
            }
        });
        jMenuTema.add(jMenuAero);

        jMenu7.add(jMenuTema);

        jMenu6.add(jMenu7);

        menu_principal.add(jMenu6);

        setJMenuBar(menu_principal);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(lb_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jL_empresa, javax.swing.GroupLayout.DEFAULT_SIZE, 845, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addComponent(lbData, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(desktopPane)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(desktopPane, javax.swing.GroupLayout.DEFAULT_SIZE, 576, Short.MAX_VALUE)
                .addGap(0, 0, 0)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lbData, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(lb_usuario, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addComponent(jL_empresa, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        desktopPane.getAccessibleContext().setAccessibleName("");

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu1MouseClicked
        fecharTela();

    }//GEN-LAST:event_jMenu1MouseClicked

    private void menu_item_PacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_PacienteActionPerformed

        if (abreFrame("CadPaciente") == null) {
            desk.createFrame(new CadPaciente(), desktopPane);
        }


    }//GEN-LAST:event_menu_item_PacienteActionPerformed

    private void menu_item_consulPclientActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_consulPclientActionPerformed

        if (abreFrame("VerConsultas") == null) {
            desk.createFrame(new VerConsultas(), desktopPane);
        }
    }//GEN-LAST:event_menu_item_consulPclientActionPerformed

    private void menu_item_conAgendaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_conAgendaActionPerformed

        try {
            if (abreFrame("CadConsultas") == null) {
                desk.createFrame(new AgendaConsultas(), desktopPane);
            }
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_menu_item_conAgendaActionPerformed

    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        if (abreFrame("RelaConsultas") == null) {
            desk.createFrame(new RelaConsultas(), desktopPane);
        }
    }//GEN-LAST:event_jMenuItem3ActionPerformed

    private void jMenuItem_condPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_condPagtoActionPerformed
        if (abreFrame("CadCondPagto") == null) {
            desk.createFrame(new CadCondPagto(), desktopPane);
        }
    }//GEN-LAST:event_jMenuItem_condPagtoActionPerformed

    private void menu_item_conveniosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_conveniosActionPerformed
        if (abreFrame("CadConvenio") == null) {
            desk.createFrame(new CadConvenio(), desktopPane);
        }
    }//GEN-LAST:event_menu_item_conveniosActionPerformed

    private void jmiCadUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiCadUsuarioActionPerformed
        if (abreFrame("CadUsuario") == null) {
            desk.createFrame(new CadUsuario(), desktopPane);
        }
    }//GEN-LAST:event_jmiCadUsuarioActionPerformed

    private void jmiAlteraSenhActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jmiAlteraSenhActionPerformed
        if (abreFrame("TrocSenhaUso") == null) {
            desk.createFrame(new TrocSenhaUso(), desktopPane);
        }
    }//GEN-LAST:event_jmiAlteraSenhActionPerformed

    private void formMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseMoved
        if (escBarra == true) {
            barraFerrament.setVisible(false);
        }
    }//GEN-LAST:event_formMouseMoved

    private void jMEsconderActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMEsconderActionPerformed
        escBarra = true;
        CadUsuarioDAO dao = new CadUsuarioDAO();
        Usuario usu = new Usuario(u.getId(), true);
        dao.escBlocBarra(usu);
        escBlo();
    }//GEN-LAST:event_jMEsconderActionPerformed

    private void jMBloquearActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMBloquearActionPerformed
        escBarra = false;
        CadUsuarioDAO dao = new CadUsuarioDAO();
        Usuario usu = new Usuario(u.getId(), false);
        dao.escBlocBarra(usu);
        escBlo();
    }//GEN-LAST:event_jMBloquearActionPerformed

    private void menuClinicaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuClinicaActionPerformed
        if (abreFrame("CadClinica") == null) {
            desk.createFrame(new CadClinica(), desktopPane);
        }
    }//GEN-LAST:event_menuClinicaActionPerformed

    private void jMenuIt_procedimentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIt_procedimentosActionPerformed
        if (abreFrame("CadProcedimento") == null) {
            desk.createFrame(new CadProcedimento(), desktopPane);
        }
    }//GEN-LAST:event_jMenuIt_procedimentosActionPerformed

    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        if (abreFrame("CadAnamnese") == null) {
            desk.createFrame(new Ficha(), desktopPane);
        }
    }//GEN-LAST:event_jMenuItem1ActionPerformed

    private void jM_filaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_filaActionPerformed
        try {
            if (abreFrame("Atendimento") == null) {
                desk.createFrame(new Atendimento(), desktopPane);
            }
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jM_filaActionPerformed

    private void jMenuItemAjudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemAjudaActionPerformed
        Ajuda ajuda = new Ajuda(this, true);
        ajuda.setVisible(true);

    }//GEN-LAST:event_jMenuItemAjudaActionPerformed

    private void desktopPaneMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desktopPaneMouseMoved
        if (escBarra == true) {
            barraFerrament.setVisible(false);
        }
    }//GEN-LAST:event_desktopPaneMouseMoved

    private void lb_barraMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lb_barraMouseMoved
        if (escBarra == true) {
            barraFerrament.setVisible(true);
        }
    }//GEN-LAST:event_lb_barraMouseMoved

    private void bt_conveniosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_conveniosActionPerformed
        if (abreFrame("CadConvenio") == null) {
            desk.createFrame(new CadConvenio(), desktopPane);
        }
    }//GEN-LAST:event_bt_conveniosActionPerformed

    private void bt_condPagtoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_condPagtoActionPerformed
        if (abreFrame("CadCondPagto") == null) {
            desk.createFrame(new CadCondPagto(), desktopPane);
        }
    }//GEN-LAST:event_bt_condPagtoActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (abreFrame("TrocSenhaUso") == null) {
            desk.createFrame(new TrocSenhaUso(), desktopPane);
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void btRelConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRelConActionPerformed
        if (abreFrame("RelaConsultas") == null) {
            desk.createFrame(new RelaConsultas(), desktopPane);
        }
    }//GEN-LAST:event_btRelConActionPerformed

    private void bt_buscaConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscaConsultasActionPerformed
        if (abreFrame("VerConsultas") == null) {
            desk.createFrame(new VerConsultas(), desktopPane);
        }
    }//GEN-LAST:event_bt_buscaConsultasActionPerformed

    private void btListConActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btListConActionPerformed

        try {
            if (abreFrame("Atendimento") == null) {
                desk.createFrame(new Atendimento(), desktopPane);
            }
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btListConActionPerformed

    private void btConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btConsultasActionPerformed
        try {
            if (abreFrame("AgendaConsultas"
                    + "") == null) {
                desk.createFrame(new AgendaConsultas(), desktopPane);
            }
        } catch (ParseException ex) {
            Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btConsultasActionPerformed

    private void btCadPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btCadPacienteActionPerformed
        if (abreFrame("CadPaciente") == null) {
            desk.createFrame(new CadPaciente(), desktopPane);
        }
    }//GEN-LAST:event_btCadPacienteActionPerformed

    private void jMenuIAluminiumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIAluminiumActionPerformed

        mudaAparencia(1);
    }//GEN-LAST:event_jMenuIAluminiumActionPerformed

    private void jMenuIAcrylLookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIAcrylLookActionPerformed

        mudaAparencia(2);
    }//GEN-LAST:event_jMenuIAcrylLookActionPerformed

    private void jMenuIMcWinLookActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIMcWinLookActionPerformed

        mudaAparencia(3);
    }//GEN-LAST:event_jMenuIMcWinLookActionPerformed

    private void jMenuAeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuAeroActionPerformed

        mudaAparencia(4);
    }//GEN-LAST:event_jMenuAeroActionPerformed

    private void jMenuIGraphiteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIGraphiteActionPerformed

        mudaAparencia(5);
    }//GEN-LAST:event_jMenuIGraphiteActionPerformed

    private void jMenuILunaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuILunaActionPerformed

        mudaAparencia(6);
    }//GEN-LAST:event_jMenuILunaActionPerformed

    private void jMenuIMintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuIMintActionPerformed

        mudaAparencia(7);
    }//GEN-LAST:event_jMenuIMintActionPerformed

    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        CadUsuarioDAO dao = new CadUsuarioDAO();
        Usuario usu = new Usuario(u.getId(), 8);
        dao.alteraTema(usu);
        JOptionPane.showMessageDialog(null, "Reinicie a aplicação para voltar o tema padrão!");
    }//GEN-LAST:event_jMenuItem4ActionPerformed

    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Importar imagem");
        fileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);
        // int a = fileChooser.showOpenDialog(null);    

        if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            String endImage = fileChooser.getSelectedFile().getPath();
            AlteraImagemDAO dao = new AlteraImagemDAO();
            dao.altImagem(endImage);
            setImage(endImage);
        }
    }//GEN-LAST:event_jMenuItem5ActionPerformed

    private void jMenuItem_especialidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem_especialidadesActionPerformed
        if (abreFrame("CadEspecialidade") == null) {
            desk.createFrame(new CadEspecialidade(), desktopPane);
        }
    }//GEN-LAST:event_jMenuItem_especialidadesActionPerformed

    private void bt_buscaPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_buscaPacienteActionPerformed
        if (abreFrame("BuscaPacientes") == null) {
            desk.createFrame(new BuscaPacientes(), desktopPane);
        }
    }//GEN-LAST:event_bt_buscaPacienteActionPerformed

    private void bt_marcaConsultasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_marcaConsultasActionPerformed
        if (abreFrame("CadConsulta") == null) {
            desk.createFrame(new CadConsulta(), desktopPane);
        }
    }//GEN-LAST:event_bt_marcaConsultasActionPerformed

    private void menu_item_cadConsutaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menu_item_cadConsutaActionPerformed
        if (abreFrame("CadConsulta") == null) {
            desk.createFrame(new CadConsulta(), desktopPane);
        }
    }//GEN-LAST:event_menu_item_cadConsutaActionPerformed

    private void menuEmpresasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuEmpresasActionPerformed
        if (abreFrame("CadEmpresa") == null) {
            desk.createFrame(new CadEmpresa(), desktopPane);
        }
    }//GEN-LAST:event_menuEmpresasActionPerformed

    private void jM_funcoesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jM_funcoesActionPerformed
              if (abreFrame("CadFuncao") == null) {
            desk.createFrame(new CadFuncao(), desktopPane);
        }
    }//GEN-LAST:event_jM_funcoesActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
            if (abreFrame("Ficha") == null) {
            desk.createFrame(new Ficha(), desktopPane);
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void bt_empresasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_empresasActionPerformed
            if (abreFrame("CadEmpresa") == null) {
            desk.createFrame(new CadEmpresa(), desktopPane);
        }
    }//GEN-LAST:event_bt_empresasActionPerformed

    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
          if (abreFrame("RelaMovimento") == null) {
            desk.createFrame(new RelaMovimento(), desktopPane);
        }
    }//GEN-LAST:event_jMenuItem7ActionPerformed

    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
          if (abreFrame("AcuidadeVisual") == null) {
            desk.createFrame(new CadAcuidadeVisual(), desktopPane);
        }
    }//GEN-LAST:event_jMenuItem8ActionPerformed

    private void bt_acuidadeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bt_acuidadeActionPerformed
       if (abreFrame("AcuidadeVisual") == null) {
            desk.createFrame(new CadAcuidadeVisual(), desktopPane);
        }
    }//GEN-LAST:event_bt_acuidadeActionPerformed

    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
       if (abreFrame("RelaMovimentoPorEmpresa") == null) {
            desk.createFrame(new RelaMovimentoPorEmpresa(), desktopPane);
        }
    }//GEN-LAST:event_jMenuItem2ActionPerformed

    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
      if (abreFrame("ControleNotas") == null) {
            desk.createFrame(new ControleNotas(), desktopPane);
        }
    }//GEN-LAST:event_jMenuItem6ActionPerformed

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
            java.util.logging.Logger.getLogger(TelaPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new TelaPrincipal().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToolBar barraFerrament;
    private javax.swing.JButton btCadPaciente;
    private javax.swing.JButton btConsultas;
    private javax.swing.JButton btListCon;
    private javax.swing.JButton btRelCon;
    private javax.swing.JButton bt_acuidade;
    private javax.swing.JButton bt_buscaConsultas;
    private javax.swing.JButton bt_buscaPaciente;
    private javax.swing.JButton bt_condPagto;
    private javax.swing.JButton bt_convenios;
    private javax.swing.JButton bt_empresas;
    private javax.swing.JButton bt_marcaConsultas;
    private javax.swing.JDesktopPane desktopPane;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jL_empresa;
    private javax.swing.JMenuItem jMBloquear;
    private javax.swing.JMenuItem jMEsconder;
    private javax.swing.JMenu jM_ficha;
    private javax.swing.JMenuItem jM_fila;
    private javax.swing.JMenuItem jM_funcoes;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu6;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuItem jMenuAero;
    private javax.swing.JMenuItem jMenuIAcrylLook;
    private javax.swing.JMenuItem jMenuIAluminium;
    private javax.swing.JMenuItem jMenuIGraphite;
    private javax.swing.JMenuItem jMenuILuna;
    private javax.swing.JMenuItem jMenuIMcWinLook;
    private javax.swing.JMenuItem jMenuIMint;
    private javax.swing.JMenuItem jMenuIt_procedimentos;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItemAjuda;
    private javax.swing.JMenuItem jMenuItem_condPagto;
    private javax.swing.JMenuItem jMenuItem_especialidades;
    private javax.swing.JMenu jMenuTema;
    private javax.swing.JMenu jMenu_consultas;
    private javax.swing.JMenu jMenu_relatorios;
    private javax.swing.JPopupMenu jPopupBarra;
    private javax.swing.JPopupMenu.Separator jSeparator1;
    private javax.swing.JPopupMenu.Separator jSeparator10;
    private javax.swing.JPopupMenu.Separator jSeparator11;
    private javax.swing.JPopupMenu.Separator jSeparator12;
    private javax.swing.JPopupMenu.Separator jSeparator13;
    private javax.swing.JPopupMenu.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator9;
    private javax.swing.JMenuItem jmiAlteraSenh;
    private javax.swing.JMenuItem jmiCadUsuario;
    private javax.swing.JLabel lbData;
    private javax.swing.JLabel lb_barra;
    private javax.swing.JLabel lb_usuario;
    private javax.swing.JMenuItem menuClinica;
    private javax.swing.JMenuItem menuEmpresas;
    private javax.swing.JMenu menuUsuario;
    private javax.swing.JMenu menu_atendimento;
    private javax.swing.JMenu menu_cadastros;
    private javax.swing.JMenuItem menu_item_Paciente;
    private javax.swing.JMenuItem menu_item_cadConsuta;
    private javax.swing.JMenuItem menu_item_conAgenda;
    private javax.swing.JMenuItem menu_item_consulPclient;
    private javax.swing.JMenuItem menu_item_convenios;
    private javax.swing.JMenuBar menu_principal;
    // End of variables declaration//GEN-END:variables

//public void permicao(){ 
//        CadUsuarioDAO dao = new CadUsuarioDAO();
//        Usuario u = dao.procuraPorUsu(Sessao.getInstance().getUsuario().getNome());
//        if (u != null) {
//            if (u.isHeAdm()) {
//            jmiCadUsuario.setVisible(true);
//        }else{
//            jmiCadUsuario.setVisible(false); 
//            }
//        }
//}
    private void addpopup() {
        barraFerrament.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {
                //Verificando se o botão direito foi pressionado  
                if ((e.getModifiers() & MouseEvent.BUTTON3_MASK) != 0) {

                    jPopupBarra.show(barraFerrament, e.getX(), e.getY());
                }
            }
        });
    }

    private void escBlo() {
        if (escBarra) {
            jMBloquear.setEnabled(true);
            jMEsconder.setEnabled(false);
        } else {
            jMBloquear.setEnabled(false);
            jMEsconder.setEnabled(true);
        }

    }

    public void setImage(String imagem) {

        background.setImage(imagem);
        background.repaint();
    }

    private void mudaAparencia(int tema) {

        if (desktopPane.getAllFrames().length > 0) {
            JOptionPane.showMessageDialog(null, "Feche todas as telas abertas e tente novamente! ");
        } else {

            String look = null;
            Properties props = new Properties();
            props.put("logoString", "Mark Consulta");

            try {
                switch (tema) {
                    case 1:
                        com.jtattoo.plaf.aluminium.AluminiumLookAndFeel.setTheme(props);
                        look = "com.jtattoo.plaf.aluminium.AluminiumLookAndFeel";
                        alterarUsuTema(1);
                        break;
                    case 2:
                        com.jtattoo.plaf.acryl.AcrylLookAndFeel.setTheme(props);
                        look = "com.jtattoo.plaf.acryl.AcrylLookAndFeel";
                        alterarUsuTema(2);
                        break;
                    case 3:
                        com.jtattoo.plaf.mcwin.McWinLookAndFeel.setTheme(props);
                        look = "com.jtattoo.plaf.mcwin.McWinLookAndFeel";
                        alterarUsuTema(3);
                        break;
                    case 4:
                        com.jtattoo.plaf.aero.AeroLookAndFeel.setTheme(props);
                        look = "com.jtattoo.plaf.aero.AeroLookAndFeel";
                        alterarUsuTema(4);
                        break;
                    case 5:
                        com.jtattoo.plaf.graphite.GraphiteLookAndFeel.setTheme(props);
                        look = "com.jtattoo.plaf.graphite.GraphiteLookAndFeel";
                        alterarUsuTema(5);
                        break;
                    case 6:
                        com.jtattoo.plaf.luna.LunaLookAndFeel.setTheme(props);
                        look = "com.jtattoo.plaf.luna.LunaLookAndFeel";
                        alterarUsuTema(6);
                        break;
                    case 7:
                        com.jtattoo.plaf.mint.MintLookAndFeel.setTheme(props);
                        look = "com.jtattoo.plaf.mint.MintLookAndFeel";
                        alterarUsuTema(7);
                        break;
                    default:
                        throw new AssertionError();
                }
                UIManager.setLookAndFeel(look);
                SwingUtilities.updateComponentTreeUI(this);
            } catch (InstantiationException | ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException o) {
            }

            this.repaint();
            //frame.pack();-não usar!        
        }
    }//fim de mudaAparencia()

    public void alterarUsuTema(int idTema) {
        CadUsuarioDAO dao = new CadUsuarioDAO();
        Usuario usu = new Usuario(u.getId(), idTema);
        dao.alteraTema(usu);
    }

    public JInternalFrame abreFrame(String tela) {
        JInternalFrame frame = null;

        for (JInternalFrame col : desktopPane.getAllFrames()) {
            if (col.getClass().getSimpleName().equals(tela)) {
                frame = col;

            }

        }
        if (frame != null) {
            if (frame.isIcon()) {
                try {
                    frame.setIcon(false);
                } catch (PropertyVetoException ex) {
                    Logger.getLogger(TelaPrincipal.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                frame.toFront();
            }
        }
        return frame;

    }

    private void fecharTela() {
        int selectedOption = JOptionPane.showConfirmDialog(null, "Deseja Sair Realmente?", "Sistema informa:", JOptionPane.YES_NO_OPTION);
        if (selectedOption == JOptionPane.YES_OPTION) {

            System.exit(0);
        }
    }

}
