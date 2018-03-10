package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.ConsultaProcedimento;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jeferson
 */
public class ConsultaProcedimentosNoSeleTableModel extends AbstractTableModel{
     NumberFormat nf = new DecimalFormat("#,##0.00");
//constantes que vão representar as colunas
    private final int COL_NOME = 0;
    private final int COL_VALOR = 1;
    private final int COL_STATUS = 2;

    
    //lista dos produtos que serão exibidos
    private List<ConsultaProcedimento> procedimentos;
            
    
     public ConsultaProcedimentosNoSeleTableModel() {
        procedimentos = new ArrayList();
    }
     
      public ConsultaProcedimentosNoSeleTableModel(List<ConsultaProcedimento> lista) {
        this();
        procedimentos.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return procedimentos.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 3;
    }

    
      @Override
    public String getColumnName(int column) {
         //qual o nome da coluna
         switch (column) {             
             case COL_NOME:
                 return "Descrição";
             case COL_VALOR:
                 return "Valor";
             case COL_STATUS:
                 return "Status";
             default:
                 break;
         }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
         // retorna a classe que representa a coluna
         switch (columnIndex) {
             case COL_NOME:
                 return String.class;
             case COL_VALOR:
                 return String.class;
             case COL_STATUS:
                 return String.class;
             default:
                 break;
         }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        ConsultaProcedimento u = procedimentos.get(rowIndex);
 
         //verifica qual valor deve ser retornado
         switch (columnIndex) {
             case COL_NOME:
                 return u.getEmpresaProcedimento().getProcedimento().getDsProcedimento();
             case COL_VALOR:
                 return nf.format(u.getValor());
             case COL_STATUS:
                 return u.getStatus();
             default:
                 break;
         }
      
        return "";
    
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        fireTableDataChanged(); 
    }
      
    public void inserir(ConsultaProcedimento c) {
        procedimentos.add(c);
        fireTableDataChanged();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //no nosso caso todas não vão ser editáveis, entao retorna false pra todas
        
        return false;
         
    }   
     
     public void listar(List<ConsultaProcedimento> c) {
        procedimentos.clear();
        procedimentos = c;
 
        fireTableDataChanged();
    }
     
     public List<ConsultaProcedimento> getProcedimentos() {

        return procedimentos;
    }
 
    public ConsultaProcedimento getItem(int pos) {
        if (pos < 0 || pos >= procedimentos.size()) {
            return null;
        }
 
        return procedimentos.get(pos);
    }
    
    public void inserir(List<ConsultaProcedimento> procedimento) {
        for (ConsultaProcedimento procedimento1 : procedimento) {
            if (!procedimentos.contains(procedimento1)) {
                procedimentos.add(procedimento1);
            }
            fireTableDataChanged();
        }

    }
      public void alterar(int id, ConsultaProcedimento i){
          procedimentos.set(id, i);       
          fireTableDataChanged();
      }
    
    public void limparTabela(){
        procedimentos.clear();
        fireTableDataChanged();
    }
//    public void removeProcedimento(List<ConsultaProcedimento> procedimento) {
//        for (ConsultaProcedimento procedimento1 : procedimento) {
//            procedimentos.remove(procedimento1);
//            fireTableDataChanged();
//        }
//    }
    
           public void removeProcedimento(List<ConsultaProcedimento> procedimento) {
            if (procedimento != null &&  !procedimento.isEmpty()) {
                 procedimentos.removeAll(procedimento);
                 fireTableDataChanged();
                }
            }
          
        public List<ConsultaProcedimento> removeProcedimentosSelecionados(){
        ArrayList<ConsultaProcedimento> itensSelecionados = new ArrayList<>();
        ArrayList<ConsultaProcedimento> itensSelecionados2 = new ArrayList<>();
        procedimentos.forEach((iten) -> {
            if (iten.getSelecao() != null && iten.getSelecao()) {
                itensSelecionados.add(iten);
            }else{
                itensSelecionados2.add(iten);
            }
         });
        procedimentos.clear();
          listar(itensSelecionados2);
        
          fireTableDataChanged();
         return itensSelecionados;
    }
 
}
