/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;


/**
 *
 * @author omars
 */
public class InvoiceHeader {
    
    private int invoiceNumber;
    private String date;
    private String customerName;
    
    
    private ArrayList <InvoiceLine> lines; //this is arraylist to hold lines of each invoice
    
    public InvoiceHeader (){
    }
    
    
    public InvoiceHeader(int invoiceNumber, String date, String customerName) {
        this.invoiceNumber = invoiceNumber;
        this.date = date;
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(int invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList <InvoiceLine> getLines() {
        /* becuase we creat the lise = null that mean it's not allocated 
            at the memory and here we creat it if it's = null*/
        
        if (lines == null)
        {
            lines = new ArrayList <> ();
        }
        return lines;
    }
    
  
  
    public double calculateTotalCost()
    {
        double totalCost = 0;
        for (InvoiceLine line : getLines())
        {
           
            totalCost = line.calculateLineTotal() + totalCost;
        }
        
        return totalCost;
    }

  
    public String saveCSV()
    {
        return invoiceNumber + "," + date + "," + customerName;
    }
    
    
    
}
