package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Exame;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class ExamesTableModel1 extends AbstractTableModel{
//constantes que vão representar as colunas
    NumberFormat nf = new DecimalFormat("#,##0.00");
    private final int DESC_EXAME = 0;
    private final int VALOR = 1;
  

    
    //lista dos produtos que serão exibidos
    private List<Exame> exames;
            
    
     public ExamesTableModel1() {
        exames = new ArrayList();
    }
     
      public ExamesTableModel1(List<Exame> lista) {
        this();
        exames.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return exames.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 2;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == DESC_EXAME) {
            return "Descrição";
        
        }else if (column == VALOR) {
            return "Valor";
        
        }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == DESC_EXAME) {
            return String.class;
       } else if (columnIndex == VALOR) {
            return String.class;
       }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Exame c = exames.get(rowIndex);
 
        //verifica qual valor deve ser retornado
       if (columnIndex == DESC_EXAME) {
            return c.getDescExame();
        }
        else if (columnIndex == VALOR) {
            return nf.format(c.getValor());
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
        exames.clear();
 
        fireTableDataChanged();
    }
     public void listar(List<Exame> c) {
        exames.clear();
        exames = c;
 
        fireTableDataChanged();
    }
     
     public List<Exame> getListar() {
        
        return exames;
    }
    public Exame getItem(int pos) {
        if (pos < 0 || pos >= exames.size()) {
            return null;
        }
 
        return exames.get(pos);
    }
    
}
