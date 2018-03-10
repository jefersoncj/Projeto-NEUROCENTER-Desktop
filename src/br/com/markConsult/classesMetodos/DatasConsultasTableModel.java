package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Consulta;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jeferson
 */
public class DatasConsultasTableModel extends AbstractTableModel{
   
//constantes que vão representar as colunas
    private final int COL_DATA = 0;
    
    //lista dos produtos que serão exibidos
    private List<Consulta> consultas;
            
    
     public DatasConsultasTableModel() {
        consultas = new ArrayList();
    }
     
      public DatasConsultasTableModel(List<Consulta> lista) {
        this();
        consultas.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return consultas.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 1;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
       if (column == COL_DATA) {
            return "Data Consulta";
        } 
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
      if (columnIndex == COL_DATA) {
            return Date.class;
        }
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Consulta c = consultas.get(rowIndex);
 
        //verifica qual valor deve ser retornado
       if (columnIndex == COL_DATA) {
            return c.getDataConsulta();
        }
        return "";
        
    }
    
    
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {

        fireTableDataChanged();
    }
       public void setValores() {
           for (Iterator<Consulta> it = consultas.iterator(); it.hasNext();) {
               Consulta consulta = it.next();
               consulta.setSequencia(consultas.indexOf(consulta)+1);
              
           }
        fireTableDataChanged();
    }
    
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //no nosso caso todas não vão ser editáveis, entao retorna false pra todas
        return false;
    } 
     
     public void listar(List<Consulta> c) {
        consultas.clear();
        consultas = c;
 
        fireTableDataChanged();
    }
     
    
      public void alterar(int id, Consulta i){
         
          consultas.set(id, i);       
          fireTableDataChanged();
      }
    
 
    public void excluir(int pos) {
        consultas.remove(pos);
 
        fireTableDataChanged();
    }
 
    public void excluir(Consulta p) {
        consultas.remove(p);
 
        fireTableDataChanged();
    }
 
   
 
    public Consulta getItem(int pos) {
        if (pos < 0 || pos >= consultas.size()) {
            return null;
        }
 
        return consultas.get(pos);
    }
}
