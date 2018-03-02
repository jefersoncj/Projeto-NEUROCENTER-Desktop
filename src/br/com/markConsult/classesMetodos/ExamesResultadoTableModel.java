package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.dao.CadPacienteDAO;
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
public class ExamesResultadoTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    NumberFormat nf = new DecimalFormat("#,##0.00");
    CadPacienteDAO dao = new CadPacienteDAO();
    private final int DESC_EXAME = 0;
    private final int VALOR = 1;
    private final int RESULTADO = 2;
    
  

    
    //lista dos produtos que serão exibidos
    private List<Exame> exames;
            
    
     public ExamesResultadoTableModel() {
        exames = new ArrayList();
    }
     
      public ExamesResultadoTableModel(List<Exame> lista) {
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
        return 3;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == DESC_EXAME) {
            return "Descrição";
        
        }else if (column == VALOR) {
            return "Valor";
        
        }else if (column == RESULTADO) {
            return "Resultado";
        
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
       }else if (columnIndex == RESULTADO) {
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
        else if (columnIndex == RESULTADO) {
            return c.getResultado();
        }
      
        return "";
    
    }
     @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Exame exa = exames.get(rowIndex);
 
    switch (columnIndex) {

      case RESULTADO:
         
               exa.setResultado(aValue.toString().toUpperCase());
               dao.altResultadoExame(exa);
        break;
        
    default:
        // Não deve ocorrer, pois só existem 2 colunas
        throw new IndexOutOfBoundsException("columnIndex out of bounds");
    }
     
    fireTableCellUpdated(rowIndex, columnIndex);
    }
      
    
     @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //no nosso caso todas não vão ser editáveis, entao retorna false pra todas
        return columnIndex == RESULTADO;
    }
    
    public void limpaLista() {
        exames.clear();
 
        fireTableDataChanged();
    }
     public void listar(List<Exame> c) {
        exames.clear();
        exames = c;
 
        fireTableDataChanged();
        fireTableStructureChanged();
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
