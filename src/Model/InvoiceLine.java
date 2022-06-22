/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author omars
 */
public class InvoiceLine {
    
    private InvoiceHeader invoiceHeader; // object of the invoice header as it is the perant 
   
    private int numberOfItems;
    private double itemPrice;
    private String itemName;
    
    public InvoiceLine (){
    }

    public InvoiceLine(InvoiceHeader invoiceHeader, int numberOfItems, double itemPrice, String itemName) {
        this.invoiceHeader = invoiceHeader;
        this.numberOfItems = numberOfItems;
        this.itemPrice = itemPrice;
        this.itemName = itemName;
    }

    public InvoiceHeader getInvoiceHeader() {
        return invoiceHeader;
    }

    public void setInvoiceHeader(InvoiceHeader invoiceHeader) {
        this.invoiceHeader = invoiceHeader;
    }


       public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }


    public int getNumberOfItems() {
        return numberOfItems;
    }

    public void setNumberOfItems(int numberOfItems) {
        this.numberOfItems = numberOfItems;
    }

    public double getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(double itemPrice) {
        this.itemPrice = itemPrice;
    }
    
    
    public double calculateLineTotal()
    {
       
        return itemPrice * numberOfItems;
    }
    
    public String SaveCSV()
    {
        return invoiceHeader.getInvoiceNumber() + "," + itemName + "," + itemPrice + "," + numberOfItems;
    }
    
  
    
}
