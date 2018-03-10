package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Empresa;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class ClienteTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_FATASIA = 0;
    private final int COL_RAZAO = 1;
    private final int COL_CNPJ= 2;
    private final int COL_LOGRADOURO= 3;
    private final int COL_FONE= 4; 
    
    //lista dos produtos que serão exibidos
    private List<Empresa> empresas;
            
    
     public ClienteTableModel() {
        empresas = new ArrayList();
    }
     
      public ClienteTableModel(List<Empresa> lista) {
        this();
        empresas.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return empresas.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 5;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_FATASIA) {
            return "Fantasia";
        
        }else if (column == COL_RAZAO) {
            return "Razão Social";
        
        }  else if (column == COL_CNPJ) {
            return "CNPJ";
            
        }else if (column == COL_LOGRADOURO) {
            return "Endereço";
            
        }
        else if (column == COL_FONE) {
            return "Fone.";
            
        }
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == COL_FATASIA) {
            return String.class;
        } else if (columnIndex == COL_FATASIA) {
            return String.class;
       }else if (columnIndex == COL_RAZAO) {
        return String.class;
       }
       else if (columnIndex == COL_CNPJ) {
        return String.class;
       }
       else if (columnIndex == COL_LOGRADOURO) {
        return String.class;
       }else if (columnIndex == COL_FONE) {
        return String.class;
       }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Empresa c = empresas.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_FATASIA) {
            return c.getFantasia();
        } 
        else if (columnIndex == COL_RAZAO) {
            return c.getRazao();
        }
        else if (columnIndex == COL_CNPJ) {
            return c.getCnpj();
        }
        else if (columnIndex == COL_LOGRADOURO) {
            return c.getCep().getLogradouro();
        }
        else if (columnIndex == COL_FONE) {
            return c.getFoneFixo();
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
      
     
     public void listar(List<Empresa> c) {
        empresas.clear();
        empresas = c;
 
        fireTableDataChanged();
    }
 
    public Empresa getItem(int pos) {
        if (pos < 0 || pos >= empresas.size()) {
            return null;
        }
 
        return empresas.get(pos);
    }
}
