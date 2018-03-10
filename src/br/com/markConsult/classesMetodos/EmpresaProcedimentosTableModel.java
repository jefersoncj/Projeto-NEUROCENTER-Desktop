package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */


import br.com.markConsult.entidades.EmpresaProcedimento;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author jeferson
 */
public class EmpresaProcedimentosTableModel extends AbstractTableModel{
     NumberFormat nf = new DecimalFormat("#,##0.00");
//constantes que vão representar as colunas
    private final int COL_SEL = 0;
    private final int COL_NOME = 1;
    private final int COL_VALOR = 2;

    
    //lista dos produtos que serão exibidos
    private List<EmpresaProcedimento> procedimentos;
            
    
     public EmpresaProcedimentosTableModel() {
        procedimentos = new ArrayList();
    }
     
      public EmpresaProcedimentosTableModel(List<EmpresaProcedimento> lista) {
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
        return 3;
    }

    
      @Override
    public String getColumnName(int column) {
         //qual o nome da coluna
         switch (column) {
             case COL_SEL:
                 return "Selecione";                
             case COL_NOME:
                 return "Descrição";
             case COL_VALOR:
                 return "Valor";
             default:
                 break;
         }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
         // retorna a classe que representa a coluna
         switch (columnIndex) {
             case COL_SEL:
                 return Boolean.class;
             case COL_NOME:
                 return String.class;
             case COL_VALOR:
                 return String.class;
             default:
                 break;
         }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        EmpresaProcedimento u = procedimentos.get(rowIndex);
 
         //verifica qual valor deve ser retornado
         switch (columnIndex) {
             case COL_SEL:
                 return u.getSelecao();
             case COL_NOME:
                 return u.getProcedimento().getDsProcedimento();
             case COL_VALOR:
                 return nf.format(u.getValor());
             default:
                 break;
         }
      
        return "";
    
    }
    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        EmpresaProcedimento p = procedimentos.get(rowIndex);  
        if (columnIndex == COL_SEL){
            p.setSelecao((Boolean) aValue);
        }  
        fireTableDataChanged(); 
    }
      
    public void inserir(EmpresaProcedimento c) {
        procedimentos.add(c);
        fireTableDataChanged();
    }
    
     public void AlteraValor(EmpresaProcedimento c, int ind) {
        procedimentos.set(ind, c);
        fireTableDataChanged();
    }
    
    
    
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        //no nosso caso todas não vão ser editáveis, entao retorna false pra todas
        
        return columnIndex == COL_SEL;
         
    }   
     
     public void listar(List<EmpresaProcedimento> c) {
        procedimentos.clear();
        procedimentos = c;
 
        fireTableDataChanged();
    }
     
     public List<EmpresaProcedimento> getProcedimentos() {

        return procedimentos;
    }
 
    public EmpresaProcedimento getItem(int pos) {
        if (pos < 0 || pos >= procedimentos.size()) {
            return null;
        }
 
        return procedimentos.get(pos);
    }
    
    public void inserir(List<EmpresaProcedimento> procedimento) {
        for (EmpresaProcedimento procedimento1 : procedimento) {
            if (!procedimentos.contains(procedimento1)) {
                procedimentos.add(procedimento1);
            }
            fireTableDataChanged();
        }

    }
    
    public void limparTabela(){
        procedimentos.clear();
        fireTableDataChanged();
    }
    
    public void removeProcedimento(List<EmpresaProcedimento> procedimento) {
    if (procedimento != null &&  !procedimento.isEmpty()) {
         procedimentos.removeAll(procedimento);
         fireTableDataChanged();
        }
    }
        
      public List<EmpresaProcedimento> removeProcedimentosSelecionados(){
        ArrayList<EmpresaProcedimento> itensSelecionados = new ArrayList<>();
        ArrayList<EmpresaProcedimento> itensSelecionados2 = new ArrayList<>();
        procedimentos.forEach((iten) -> {
            if (iten.getSelecao() != null && iten.getSelecao()) {
                itensSelecionados.add(iten);
            }else{
                itensSelecionados2.add(iten);
            }
         });
        procedimentos.clear();
          listar(itensSelecionados2);
        
          fireTableDataChanged();
         return itensSelecionados;
    }
      
}
