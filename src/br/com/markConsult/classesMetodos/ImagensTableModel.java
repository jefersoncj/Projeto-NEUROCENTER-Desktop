
package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */




import br.com.markConsult.entidades.ArquivosPaciente;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;





/**
 *
 * @author jeferson
 */
public class ImagensTableModel extends AbstractTableModel{
    
//constantes que vão representar as colunas

     private final int COL_DATA = 0;
     private final int COL_NOME = 1;
     private final int COL_OBS = 2;
    

    
    //lista dos imagensPacientes que serão exibidos
    private List<ArquivosPaciente> imagensPacientes;
            
    
     public ImagensTableModel() {
        imagensPacientes = new ArrayList();
    }
     
      public ImagensTableModel(List<ArquivosPaciente> lista) {
        this();
        imagensPacientes.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada pedido na lista será uma linha
    
        return imagensPacientes.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 3;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
         if (column == COL_DATA) {
            return "Data";
        
        }else if (column == COL_NOME) {
            return "Nome";
        
        }else if (column == COL_OBS) {
            return "Observação";
        
        }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        
        if (columnIndex == COL_DATA) {
            return String.class;
        } else if (columnIndex == COL_NOME) {
            return String.class;
       } else if (columnIndex == COL_OBS) {
            return String.class;
       } 
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o pedido da linha
        ArquivosPaciente p = imagensPacientes.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_DATA) {
           return p.getDataCadastro();
        }else  if (columnIndex == COL_NOME){
            return p.getImagem();
        }else  if (columnIndex == COL_OBS){
            return p.getObservacao();
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
     
     public void listar(List<ArquivosPaciente> p) {
        imagensPacientes.clear();
        imagensPacientes = p;
 
        fireTableDataChanged();
    }
     
     public void limpaLista() {
        imagensPacientes.clear();
        fireTableDataChanged();
    }
    
     public List<ArquivosPaciente> getLista() {
         return imagensPacientes;
        
    }
    
    public ArquivosPaciente getItem(int pos) {
        if (pos < 0 || pos >= imagensPacientes.size()) {
            return null;
        }
 
        return imagensPacientes.get(pos);
    }
}
