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
public class HeaderTable extends AbstractTableModel {
    
    
    private ArrayList <InvoiceHeader> allInvoices;
   

    public HeaderTable() {
    }

    public HeaderTable(ArrayList<InvoiceHeader> allInvoices) {
        this.allInvoices = allInvoices;
    }
    
    

    @Override
    public int getRowCount() {
        return allInvoices.size();
    }

   

    @Override
    public int getColumnCount() {
        return 4;
    }
    
    

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceHeader invoiceHeader = allInvoices.get(rowIndex);
        
        switch (columnIndex)
        {
            case 0:
                return invoiceHeader.getInvoiceNumber();
                
            case 1:
                return invoiceHeader.getDate();
                
            case 2:
                return invoiceHeader.getCustomerName();
                
            case 3:
                return invoiceHeader.calculateTotalCost();
                
            default:
                return null;
        }
    }
    

    @Override
    public String getColumnName(int column) {
        switch (column)
        {
            case 0:
                return "No.";
             
            case 1:
                return "Date";
                
            case 2:
                return "Customer";
                
            case 3:
                return "Total";
                
            default:
                return null;    
        }
       
    }

  
    
    
}
