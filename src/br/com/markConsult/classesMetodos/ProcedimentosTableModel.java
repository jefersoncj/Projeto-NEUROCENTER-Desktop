package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Procedimento;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class ProcedimentosTableModel extends AbstractTableModel{
     NumberFormat nf = new DecimalFormat("#,##0.00");
//constantes que vão representar as colunas
    private final int COL_ID = 0;
    private final int COL_NOME = 1;
    
    //lista dos produtos que serão exibidos
    private List<Procedimento> procedimentos;
            
    
     public ProcedimentosTableModel() {
        procedimentos = new ArrayList();
    }
     
      public ProcedimentosTableModel(List<Procedimento> lista) {
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
             case COL_ID:
                 return "Código";
             case COL_NOME:
                 return "Descrição";
             default:
                 break;
         }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
         // retorna a classe que representa a coluna
         switch (columnIndex) {
             case COL_ID:
                 return int.class;
             case COL_NOME:
                 return String.class;
             default:
                 break;
         }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Procedimento u = procedimentos.get(rowIndex);
 
         //verifica qual valor deve ser retornado
         switch (columnIndex) {
             case COL_ID:
                 return u.getId();
             case COL_NOME:
                 return u.getDsProcedimento();
             default:
                 break;
         }
      
        return "";
    
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        fireTableDataChanged();
    }
      
        public void inserir(Procedimento c) {
        if(!procedimentos.contains(c)){
           procedimentos.add(c);
           fireTableDataChanged();
        }
    }
    
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //no nosso caso todas não vão ser editáveis, entao retorna false pra todas
        return false;
    }
     
     public void listar(List<Procedimento> c) {
        procedimentos.clear();
        procedimentos = c;
 
        fireTableDataChanged();
    }
 
    public Procedimento getItem(int pos) {
        if (pos < 0 || pos >= procedimentos.size()) {
            return null;
        }
 
        return procedimentos.get(pos);
    }
    
        public void removeProcedimento(int procedimento) {
        procedimentos.remove(procedimento);
        fireTableDataChanged();
    }
        
        public void removeProcedimentos(List<Procedimento> procedimento) {
            if (procedimento != null &&  !procedimento.isEmpty()) {
                 procedimentos.removeAll(procedimento);
                 fireTableDataChanged();
            }
       
    }
        
    
}
