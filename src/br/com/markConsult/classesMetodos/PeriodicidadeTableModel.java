package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Periodicidade;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class PeriodicidadeTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_ID = 0;
    private final int COL_DESC_SETOR = 1;

    
    //lista dos produtos que serão exibidos
    private List<Periodicidade> periodicidades;
            
    
     public PeriodicidadeTableModel() {
        periodicidades = new ArrayList();
    }
     
      public PeriodicidadeTableModel(List<Periodicidade> lista) {
        this();
        periodicidades.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return periodicidades.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 2;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_ID) {
            return "Código";
        
        }else if (column == COL_DESC_SETOR) {
            return "Periodicidade";
        
        }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == COL_ID) {
            return int.class;
        } else if (columnIndex == COL_DESC_SETOR) {
            return String.class;
       }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Periodicidade c = periodicidades.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_ID){
            return c.getId();
        } 
        else if (columnIndex == COL_DESC_SETOR) {
            return c.getDescPeriodicidade();
        }
    
      
        
      
        return "";
    
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        fireTableDataChanged();
    }
      
    
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //no nosso caso todas não vão ser editáveis, entao retorna false pra todas
        return false;
    }
     
     
     public void listar(List<Periodicidade> c) {
        periodicidades.clear();
        periodicidades = c;
 
        fireTableDataChanged();
    }
     
     public void limpaLista() {
        periodicidades.clear();
        fireTableDataChanged();
    }
     
    public Periodicidade getItem(int pos) {
        if (pos < 0 || pos >= periodicidades.size()) {
            return null;
        }
 
        return periodicidades.get(pos);
    }
    
}
