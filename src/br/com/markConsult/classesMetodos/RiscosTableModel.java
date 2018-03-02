package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Risco;
import br.com.markConsult.entidades.Setor;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class RiscosTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_ID = 0;
    private final int TIPO_RISCO = 1;
    private final int DESC_RISCO = 2;

    
    //lista dos produtos que serão exibidos
    private List<Risco> riscos;
            
    
     public RiscosTableModel() {
        riscos = new ArrayList();
    }
     
      public RiscosTableModel(List<Risco> lista) {
        this();
        riscos.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return riscos.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 3;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_ID) {
            return "Código";
        
        }else if (column == TIPO_RISCO) {
            return "Tipo Risco";
        
        }else if (column == DESC_RISCO) {
            return "Descrição";
        
        }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == COL_ID) {
            return int.class;
        } else if (columnIndex == TIPO_RISCO) {
            return String.class;
       } else if (columnIndex == DESC_RISCO) {
            return String.class;
       }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Risco c = riscos.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_ID){
            return c.getId();
        } 
        else if (columnIndex == TIPO_RISCO) {
            return c.getTipoRisco();
        }
        else if (columnIndex == DESC_RISCO) {
            return c.getDescRisco();
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
     
     public void limpaLista() {
        riscos.clear();
 
        fireTableDataChanged();
    }
     public void listar(List<Risco> c) {
        riscos.clear();
        riscos = c;
 
        fireTableDataChanged();
    }
     
     public List<Risco> getListar() {
        
        return riscos;
    }
    public Risco getItem(int pos) {
        if (pos < 0 || pos >= riscos.size()) {
            return null;
        }
 
        return riscos.get(pos);
    }
    
}
