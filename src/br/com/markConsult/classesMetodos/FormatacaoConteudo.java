/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.markConsult.classesMetodos;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;

/**
 *
 * @author jeferson
 */
public class FormatacaoConteudo extends DefaultTableCellRenderer implements TableCellRenderer {
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {  
          
        super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
      
        
        String tbl = table.getColumnName(0);
        int coluna = 0 ;
        
        switch (tbl) {
            case "Nº":
                coluna = 7;
                break;
            case "Nº Fila":
                 coluna = 5;
                break;
            case "Nome":
                 coluna = 7;
                break;
            default:
                throw new AssertionError();
        }
        Object o = table.getValueAt(row, coluna);
        switch (o.toString()) {
            case "ABERTA":
                setForeground(Color.BLUE); 
                break;
             case "CANCELADA":
                setForeground(Color.ORANGE);
                break;
             case "FALTOU":
                setForeground(Color.RED);
                break;
             case "ENCERRADA":
                setForeground(Color.BLACK);
                break;
             case "EM CONSULTA":
                setForeground(Color.pink);
                break;
            default:
                setForeground(Color.GREEN);
                
        }

        return this;  
          
    }  public void cor(){
             
        }
}
