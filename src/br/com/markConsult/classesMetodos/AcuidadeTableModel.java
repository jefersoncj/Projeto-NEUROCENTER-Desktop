package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.AcuidadeVisual;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
/**
 *
 * @author jeferson
 */
public class AcuidadeTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_CODIGO = 0;
    private final int COL_PACIENTE = 1;
    private final int COL_EMPRESA= 2;
    private final int COL_DATA= 3;
    
    //lista dos produtos que serão exibidos
    private List<AcuidadeVisual> empresas;
            
    
     public AcuidadeTableModel() {
        empresas = new ArrayList();
    }
     
      public AcuidadeTableModel(List<AcuidadeVisual> lista) {
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
        return 4;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        switch (column) {
            case COL_CODIGO:
                return "Código";
            case COL_PACIENTE:
                return "Paciente";
            case COL_EMPRESA:
                return "Empresa";
            case COL_DATA:
                return "Data";
            default:
                break;
        }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
        // retorna a classe que representa a coluna
        switch (columnIndex) {
            case COL_CODIGO:
                return String.class;
            case COL_PACIENTE:
                return String.class;
            case COL_EMPRESA:
                return String.class;
            case COL_DATA:
                return Date.class;
            default:
                break;
        }
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        AcuidadeVisual c = empresas.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        switch (columnIndex) {
            case COL_CODIGO:
                return c.getId();
            case COL_PACIENTE:
                return c.getPaciente().getNome();
            case COL_EMPRESA:
                return c.getPaciente().getEmpresa().getFantasia();
            case COL_DATA:
                return c.getData();
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
      
     
     public void listar(List<AcuidadeVisual> c) {
        empresas.clear();
        empresas = c;
 
        fireTableDataChanged();
    }
 
    public AcuidadeVisual getItem(int pos) {
        if (pos < 0 || pos >= empresas.size()) {
            return null;
        }
 
        return empresas.get(pos);
    }
}
