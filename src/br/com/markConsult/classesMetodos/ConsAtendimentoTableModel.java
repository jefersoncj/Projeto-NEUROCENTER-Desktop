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
public class ConsAtendimentoTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_SEQ = 0;
    private final int COL_NOME = 1;
    private final int COL_IDCONV = 2;
    private final int COL_CONVENO= 3;
    private final int COL_DATA = 4; 
    private final int COL_STATUS= 5;
    private final int COL_TIPO = 6;
    private final int COL_PROCE = 7;
    private final int COL_OBS = 8;
  
    
    
    //lista dos produtos que serão exibidos
    private List<Consulta> consultas;
            
    
     public ConsAtendimentoTableModel() {
        consultas = new ArrayList();
    }
     
      public ConsAtendimentoTableModel(List<Consulta> lista) {
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
        return 9;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_SEQ) {
            return "Nº";
        
        }else if (column == COL_NOME) {
            return "Nome";
        
        }else if (column == COL_IDCONV) {
            return "Nº Convênio";
        
        }
        else if (column == COL_CONVENO) {
            return "Convênio";
        
        }
       else if (column == COL_DATA) {
            return "Data Consulta";
        
        }  else if (column == COL_STATUS) {
            return "Status";
            
        }else if (column == COL_TIPO) {
            return "Tipo Consulta";
            
        }else if (column == COL_PROCE) {
            return "Procedimento";
            
        }else if (column == COL_OBS) {
            return "Observação";
            
        }
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == COL_SEQ) {
            return int.class;
        }else if (columnIndex == COL_NOME) {
        return String.class;
       }
        else if (columnIndex == COL_IDCONV) {
        return String.class;
       }else if (columnIndex == COL_CONVENO) {
        return String.class;
       }
       else if (columnIndex == COL_DATA) {
            return Date.class;
        }else if (columnIndex == COL_STATUS) {
        return String.class;
       }else if (columnIndex == COL_TIPO) {
        return String.class;
       }else if (columnIndex == COL_PROCE) {
        return String.class;
       }
        else if (columnIndex == COL_OBS) {
        return String.class;
       }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Consulta c = consultas.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_SEQ) {
            return consultas.indexOf(c)+1;
        } else if (columnIndex == COL_NOME) {
            return c.getPaciente().getNome();
        }else if (columnIndex == COL_IDCONV) {
            return c.getPaciente().getNumConvenio();
        }
        else if (columnIndex == COL_CONVENO) {
            return c.getConvenio().getDsConvenio();
        }
        else if (columnIndex == COL_DATA) {
            return c.getDataConsulta();
        }else if (columnIndex == COL_STATUS) {
            
            String status = c.getStatus().toString();
            switch (status) {
                case "1":
                    status = "ABERTA";
                    break;
                case "2":
                    status = "ENCERRADA";
                    break;
                case "3":
                    status = "CANCELADA";
                    break;
                case "4":
                    status = "FALTOU";
                    break;
                case "5":
                    status = "AGUARDANDO";
                    break;
                case "6":
                    status = "EM CONSULTA";
                    break;
            }
            return status;
        }
        else if (columnIndex == COL_TIPO) {
            return c.getTipo().getTipoCon();
        }
        else if (columnIndex == COL_OBS) {
            return c.getObs();
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
