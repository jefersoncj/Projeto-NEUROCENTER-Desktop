package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Especialidade;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class EspecialiTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_ID = 0;
    private final int COL_ESPECIALI = 1;

    
    //lista dos produtos que serão exibidos
    private List<Especialidade> especialidades;
            
    
     public EspecialiTableModel() {
        especialidades = new ArrayList();
    }
     
      public EspecialiTableModel(List<Especialidade> lista) {
        this();
        especialidades.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return especialidades.size();    
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
        
        }else if (column == COL_ESPECIALI) {
            return "Especialidade";
        
        }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == COL_ID) {
            return int.class;
        } else if (columnIndex == COL_ESPECIALI) {
            return String.class;
       }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Especialidade c = especialidades.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_ID){
            return c.getId();
        } 
        else if (columnIndex == COL_ESPECIALI) {
            return c.getEspecialidade();
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
     
     
     public void listar(List<Especialidade> c) {
        especialidades.clear();
        especialidades = c;
 
        fireTableDataChanged();
    }
    public Especialidade getItem(int pos) {
        if (pos < 0 || pos >= especialidades.size()) {
            return null;
        }
 
        return especialidades.get(pos);
    }
    
}
