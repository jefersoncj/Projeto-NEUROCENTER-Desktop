package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.ArquivosProcedimento;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jeferson
 */
public class ArquivosProcedimentosTableModel extends AbstractTableModel{
//constantes que vão representar as colunas
    private final int COL_DATA = 0;
    private final int COL_NOME_ARQUIVO = 1;

    
    //lista dos produtos que serão exibidos
    private List<ArquivosProcedimento> procedimentos;
            
    
     public ArquivosProcedimentosTableModel() {
        procedimentos = new ArrayList();
    }
     
      public ArquivosProcedimentosTableModel(List<ArquivosProcedimento> lista) {
        this();
        procedimentos.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return procedimentos.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 2;
    }

    
      @Override
    public String getColumnName(int column) {
         //qual o nome da coluna
         switch (column) {
             case COL_DATA:
                 return "Data";                
             case COL_NOME_ARQUIVO:
                 return "Arquivo";
             default:
                 break;
         }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
         // retorna a classe que representa a coluna
         switch (columnIndex) {
             case COL_DATA:
                 return Date.class;
             case COL_NOME_ARQUIVO:
                 return String.class;
             default:
                 break;
         }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        ArquivosProcedimento u = procedimentos.get(rowIndex);
 
         //verifica qual valor deve ser retornado
         switch (columnIndex) {
             case COL_DATA:
                 return u.getDataCadastro();
             case COL_NOME_ARQUIVO:
                 return u.getArquivo();
        
             default:
                 break;
         }
      
        return "";
    
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
     
        fireTableDataChanged(); 
    }
      
    public void inserir(ArquivosProcedimento c) {
        procedimentos.add(c);
        fireTableDataChanged();
    }
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //no nosso caso todas não vão ser editáveis, entao retorna false pra todas
        
        return false;
         
    }   
     
     public void listar(List<ArquivosProcedimento> c) {
        procedimentos.clear();
        procedimentos = c;
 
        fireTableDataChanged();
    }
     
     public List<ArquivosProcedimento> getProcedimentos() {

        return procedimentos;
    }
 
    public ArquivosProcedimento getItem(int pos) {
        if (pos < 0 || pos >= procedimentos.size()) {
            return null;
        }
 
        return procedimentos.get(pos);
    }
    
    public void inserir(List<ArquivosProcedimento> procedimento) {
        for (ArquivosProcedimento procedimento1 : procedimento) {
            if (!procedimentos.contains(procedimento1)) {
                procedimentos.add(procedimento1);
            }
            fireTableDataChanged();
        }

    }
      public void alterar(int id, ArquivosProcedimento i){
          procedimentos.set(id, i);       
          fireTableDataChanged();
      }
    
    public void limparTabela(){
        procedimentos.clear();
        fireTableDataChanged();
    }
//    public void removeProcedimento(List<ArquivosProcedimento> procedimento) {
//        for (ArquivosProcedimento procedimento1 : procedimento) {
//            procedimentos.remove(procedimento1);
//            fireTableDataChanged();
//        }
//    }
    
           public void removeProcedimento(List<ArquivosProcedimento> procedimento) {
            if (procedimento != null &&  !procedimento.isEmpty()) {
                 procedimentos.removeAll(procedimento);
                 fireTableDataChanged();
                }
            }
          
        public List<ArquivosProcedimento> removeProcedimentosSelecionados(){
        ArrayList<ArquivosProcedimento> itensSelecionados = new ArrayList<>();
        ArrayList<ArquivosProcedimento> itensSelecionados2 = new ArrayList<>();
        procedimentos.forEach((iten) -> {
//            if (iten.getSelecao() != null && iten.getSelecao()) {
//                itensSelecionados.add(iten);
//            }else{
//                itensSelecionados2.add(iten);
//            }
         });
        procedimentos.clear();
          listar(itensSelecionados2);
        
          fireTableDataChanged();
         return itensSelecionados;
    }
 
}
