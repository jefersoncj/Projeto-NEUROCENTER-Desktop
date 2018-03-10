package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class UsuarioTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_ID = 0;
    private final int COL_NOMEUSU = 1;
    private final int COL_CRM = 2;
    private final int COL_ESP = 3;

    
    //lista dos produtos que serão exibidos
    private List<Usuario> usuarios;
            
    
     public UsuarioTableModel() {
        usuarios = new ArrayList();
    }
     
      public UsuarioTableModel(List<Usuario> lista) {
        this();
        usuarios.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return usuarios.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 4;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_ID) {
            return "Código";
        
        }else if (column == COL_NOMEUSU) {
            return "Nome";
        
        }else if (column == COL_CRM) {
            return "CRM";
        
        }
        else if (column == COL_ESP) {
            return "Especialidade";
        
        }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == COL_ID) {
            return int.class;
        } else if (columnIndex == COL_NOMEUSU) {
            return String.class;
       }else if (columnIndex == COL_CRM) {
            return String.class;
       }
        else if (columnIndex == COL_ESP) {
            return String.class;
       }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Usuario u = usuarios.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_ID){
            return u.getId();
        } 
        else if (columnIndex == COL_NOMEUSU) {
            return u.getNome();
        }
        else if (columnIndex == COL_CRM) {
            return u.getCrm();
        }
        else if (columnIndex == COL_ESP) {
            return u.getEspecialidade().getEspecialidade();
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
     
     public void listar(List<Usuario> u) {
        usuarios.clear();
        usuarios = u;
 
        fireTableDataChanged();
    }
 
    public Usuario getItem(int pos) {
        if (pos < 0 || pos >= usuarios.size()) {
            return null;
        }
 
        return usuarios.get(pos);
    }
}
