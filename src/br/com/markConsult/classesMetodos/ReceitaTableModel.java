package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import br.com.markConsult.entidades.Receita;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class ReceitaTableModel extends AbstractTableModel{
SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//constantes que vão representar as colunas
    private final int COL_DATA = 0;
    

    
    //lista dos produtos que serão exibidos
    private List<Receita> receitas;
            
    
     public ReceitaTableModel() {
        receitas = new ArrayList();
    }
     
      public ReceitaTableModel(List<Receita> lista) {
        this();
        receitas.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return receitas.size();    
    }

    @Override
    public int getColumnCount() {
       
      //Quantidade de colunas
        return 1;
    }

    
      @Override
    public String getColumnName(int column) {
        //qual o nome da coluna
        if (column == COL_DATA) {
            return "Datas";
        
        }
        
        return "";
    }
      
      @Override
    public Class getColumnClass(int columnIndex) {
       // retorna a classe que representa a coluna
        if (columnIndex == COL_DATA) {
            return int.class;
        }
       
        return String.class;
      }
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        //pega o produto da linha
        Receita u = receitas.get(rowIndex);
 
        //verifica qual valor deve ser retornado
        if (columnIndex == COL_DATA){
            return formato.format(u.getDataCadastro());
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
     
     public void inserir(Receita c) {
        receitas.add(c);
 
        fireTableDataChanged();
    }
      public void movePcima(Receita c, int posicao) {
        receitas.add(posicao, c);
 
        fireTableDataChanged();
    }
     
     public void listar(List<Receita> c) {
        receitas.clear();
        receitas = c;
 
        fireTableDataChanged();
    }
     
    
      public void alterar(int id, Receita i){
          receitas.set(id, i);       
          fireTableDataChanged();
      }
    public void excluir(int pos) {
        receitas.remove(pos);
 
        fireTableDataChanged();
    }
     public void limpar() {
        receitas.clear();
        fireTableDataChanged();
    }
 
    public void excluir(Receita p) {
        receitas.remove(p);
 
        fireTableDataChanged();
    }
 
    
     public List<Receita> lista() {
        
        return receitas;
    }
 
    public Receita getItem(int pos) {
        if (pos < 0 || pos >= receitas.size()) {
            return null;
        }
 
        return receitas.get(pos);
    }
}
