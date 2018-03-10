package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.Paciente;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class PacienteTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_NOME = 0;
    private final int COL_NUM_CONVNIO = 1;
    private final int COL_CONVENIO= 2;
    private final int COL_CPFCNPJ= 3;
    private final int COL_RG= 4;
    private final int COL_LOGRADOURO= 5;
    private final int COL_DATANASCI= 6;
    private final int COL_OBS= 7;   
//    private final int COL_STATUS= 7;   
    
    //lista dos produtos que serão exibidos
    private List<Paciente> clientes;
            
    
     public PacienteTableModel() {
        clientes = new ArrayList();
    }
     
      public PacienteTableModel(List<Paciente> lista) {
        this();
        clientes.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return clientes.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 7;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_NOME) {
            return "Nome";
        
        }else if (column == COL_NUM_CONVNIO) {
            return "Nº Convênio";
        
        }else if (column == COL_CONVENIO) {
            return "Convênio";
        
        }  else if (column == COL_CPFCNPJ) {
            return "CPF";
            
        }else if (column == COL_RG) {
            return "RG";
            
        }
        
        else if (column == COL_LOGRADOURO) {
            return "Endereço";
            
        }
        else if (column == COL_DATANASCI) {
            return "Data Nasc.";
            
        }
        
        else if (column == COL_OBS) {
            return "Observação";
            
        }
//        else if (column == COL_STATUS) {
//            return "Status Últ. Cons.";
//            
//        }
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == COL_NOME) {
            return String.class;
        } else if (columnIndex == COL_NUM_CONVNIO) {
            return String.class;
       }else if (columnIndex == COL_CONVENIO) {
        return String.class;
       }
       else if (columnIndex == COL_CPFCNPJ) {
        return String.class;
       }
       else if (columnIndex == COL_RG) {
        return String.class;
       }
       else if (columnIndex == COL_LOGRADOURO) {
        return String.class;
       }else if (columnIndex == COL_DATANASCI) {
        return Date.class;
       }
       else if (columnIndex == COL_OBS) {
        return String.class;
       }
//       else if (columnIndex == COL_STATUS) {
//        return String.class;
//       }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Paciente c = clientes.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_NOME) {
            return c.getNome();
        } 
        else if (columnIndex == COL_NUM_CONVNIO) {
            return c.getNumConvenio();
        }
        else if (columnIndex == COL_CONVENIO) {
            return c.getConvenio().getDsConvenio();
        } 
        else if (columnIndex == COL_CPFCNPJ) {
            return c.getCpf();
        }
        else if (columnIndex == COL_RG) {
            return c.getRg();
        }
        else if (columnIndex == COL_LOGRADOURO) {
            return c.getCep().getLogradouro();
        }
        else if (columnIndex == COL_DATANASCI) {
            return c.getDataNasc();
        }
        else if (columnIndex == COL_OBS) {
            return c.getObs();
        }
//        
//        else if (columnIndex == COL_STATUS) {
//            
//            String status = c.getStatus().toString();
//            switch (status) {
//                case "1":
//                    status = "ABERTA";
//                    break;
//                case "2":
//                    status = "ENCERRADA";
//                    break;
//                case "3":
//                    status = "CANCELADA";
//                    break;
//                case "4":
//                    status = "FALTOU";
//                    break;
//                case "5":
//                    status = "AGUARDANDO";
//                    break;
//            }
//            return status;
//        }
        
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
      
     
     public void listar(List<Paciente> c) {
        clientes.clear();
        clientes = c;
 
        fireTableDataChanged();
    }
 
    public Paciente getItem(int pos) {
        if (pos < 0 || pos >= clientes.size()) {
            return null;
        }
 
        return clientes.get(pos);
    }
}
