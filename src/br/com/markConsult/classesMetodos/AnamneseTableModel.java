package br.com.markConsult.classesMetodos;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



import br.com.markConsult.entidades.Anamnese;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;




/**
 *
 * @author jeferson
 */
public class AnamneseTableModel extends AbstractTableModel{
SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
//constantes que vão representar as colunas
    private final int COL_DATA = 0;
    

    
    //lista dos produtos que serão exibidos
    private List<Anamnese> anamneses;
            
    
     public AnamneseTableModel() {
        anamneses = new ArrayList();
    }
     
      public AnamneseTableModel(List<Anamnese> lista) {
        this();
        anamneses.addAll(lista);
    }
            
            
            
    @Override
    public int getRowCount() {
      //cada produto na lista será uma linha
    
        return anamneses.size();    
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
        Anamnese u = anamneses.get(rowIndex);
 
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
     
     public void inserir(Anamnese c) {
        anamneses.add(c);
 
        fireTableDataChanged();
    }
      public void movePcima(Anamnese c, int posicao) {
        anamneses.add(posicao, c);
 
        fireTableDataChanged();
    }
     
     public void listar(List<Anamnese> c) {
        anamneses.clear();
        anamneses = c;
 
        fireTableDataChanged();
    }
     public void limpar() {
         anamneses.clear();
        fireTableDataChanged();
    }
    
      public void alterar(int id, Anamnese i){
          anamneses.set(id, i);       
          fireTableDataChanged();
      }
    public void excluir(int pos) {
        anamneses.remove(pos);
 
        fireTableDataChanged();
    }
 
    public void excluir(Anamnese p) {
        anamneses.remove(p);
 
        fireTableDataChanged();
    }
 
    
     public List<Anamnese> lista() {
        
        return anamneses;
    }
 
    public Anamnese getItem(int pos) {
        if (pos < 0 || pos >= anamneses.size()) {
            return null;
        }
 
        return anamneses.get(pos);
    }
}
