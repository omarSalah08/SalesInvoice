/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author omars
 */
public class LineTable extends AbstractTableModel {
    
    private ArrayList <InvoiceLine> lines;

    public LineTable() {
    }

    public LineTable(ArrayList<InvoiceLine> lines) {
        this.lines = lines;
    }
    
    

    @Override
    public int getRowCount() {
        return lines.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }
    
     @Override
    public String getColumnName(int column) {
        switch (column)
        {
            case 0:
                return "No.";
             
            case 1:
                return "Item Name";
                
            case 2:
                return "Item Price";
                
            case 3:
                return "Count";
                
            case 4:
                return "Item Total";
                
            default:
                return null;    
        }
       
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceLine invoiceLine = lines.get(rowIndex);
        
        switch (columnIndex)
        {
            case 0:
                return invoiceLine.getInvoiceHeader().getInvoiceNumber();
                
            case 1:
                return invoiceLine.getItemName();
                
            case 2:
                return invoiceLine.getItemPrice();
                
            case 3:
                return invoiceLine.getNumberOfItems();
                
            case 4:
                return invoiceLine.calculateLineTotal();
                
            default:
                return null;
                
        }
        
    }
    
}
