package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Funcao;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class FuncaoTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_ID = 0;
    private final int COL_NOME = 1;
    
    //lista dos produtos que serão exibidos
    private List<Funcao> funcaos;
            
    
     public FuncaoTableModel() {
        funcaos = new ArrayList();
    }
     
      public FuncaoTableModel(List<Funcao> lista) {
        this();
        funcaos.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return funcaos.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 2;
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
        Funcao u = funcaos.get(rowIndex);
 
         //verifica qual valor deve ser retornado
         switch (columnIndex) {
             case COL_ID:
                 return u.getId();
             case COL_NOME:
                 return u.getNome();
             default:
                 break;
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
     
     public void listar(List<Funcao> c) {
        funcaos.clear();
        funcaos = c;
 
        fireTableDataChanged();
    }
 
    public Funcao getItem(int pos) {
        if (pos < 0 || pos >= funcaos.size()) {
            return null;
        }
 
        return funcaos.get(pos);
    }
    

}
