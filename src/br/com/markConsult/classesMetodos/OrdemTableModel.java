package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.OrdemExames;

import java.util.ArrayList;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class OrdemTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
    private final int COL_CODIGO = 0;
    private final int COL_DATA_ORDEM = 1;
    private final int COL_EMPRESA = 2;
    private final int COL_PERIODICIDADE = 3;
    

    
    //lista dos produtos que serão exibidos
    private List<OrdemExames> ordemExamess;
            
    
     public OrdemTableModel() {
        ordemExamess = new ArrayList();
    }
     
      public OrdemTableModel(List<OrdemExames> lista) {
        this();
        ordemExamess.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return ordemExamess.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 4;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_CODIGO) {
            return "Código";
        
        }else if (column == COL_DATA_ORDEM) {
            return "Data";
        
        }else if (column == COL_EMPRESA) {
            return "Empresa";
        }else if (column == COL_PERIODICIDADE) {
            return "Periodicidade";
        }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == COL_DATA_ORDEM) {
            return int.class;
       } else if (columnIndex == COL_DATA_ORDEM) {
            return Date.class;
       }else if (columnIndex == COL_EMPRESA) {
            return String.class;
       }else if (columnIndex == COL_PERIODICIDADE) {
            return String.class;
       }
       
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        OrdemExames c = ordemExamess.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_CODIGO) {
            return c.getId();
        }
        else if (columnIndex == COL_DATA_ORDEM) {
            return formato.format(c.getData());
        }
       
        else if (columnIndex == COL_EMPRESA) {
            return c.getEmpresa();
        }
        else if (columnIndex == COL_PERIODICIDADE) {
            return c.getPeriodicidade();
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
        ordemExamess.clear();
 
        fireTableDataChanged();
    }
     public void listar(List<OrdemExames> c) {
        ordemExamess.clear();
        ordemExamess = c;
 
        fireTableDataChanged();
    }
     
     public List<OrdemExames> getListar() {
        
        return ordemExamess;
    }
    public OrdemExames getItem(int pos) {
        if (pos < 0 || pos >= ordemExamess.size()) {
            return null;
        }
 
        return ordemExamess.get(pos);
    }
    
}
