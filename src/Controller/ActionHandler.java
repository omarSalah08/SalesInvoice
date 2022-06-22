/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.InvoiceHeader;
import Model.InvoiceLine;
import Model.HeaderTable;
import Model.LineTable;
import View.CreateNewInvoiceDialog;
import View.CreateNewLineDialog;
import View.SalesFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFileChooser;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author omars
 */
public class ActionHandler implements ActionListener, ListSelectionListener {
    
    private SalesFrame frame;
    private CreateNewInvoiceDialog invDialog;
    private CreateNewLineDialog lineDialog;
    
    
    
    
    public ActionHandler (SalesFrame frame)
    {
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        
        
        switch (e.getActionCommand()){
        
            case "New Invoice":
                newInvoice();
                break;
                
            case "Delete Invoice":
               deleteInvoice();
                break;
                
            case "New Item":
                newItem();
                break;
               
            case "Delete Item":
                deleteItem();
                break;
                
            case "Load files":
                loadFiles();
                break;
                
            case "Save files":
                saveFiles();
                break;
            
            case "Create Invoice":
                createInvoice();
                break;
                
            case "Cancel Invoice Creating":
                cancelInvoiceCreating();
                break;
                
            case "Create Line":
                createLine();
                break;
                
            case "Cancel Line Creating":
                cancelLineCreating();
                break;
        
        }
          
    }
    
    
     @Override
    public void valueChanged(ListSelectionEvent e) {
        
        int selectedRowIndex = frame.getHeaderTable().getSelectedRow();
        if (selectedRowIndex != -1)
        {
            
            InvoiceHeader currentInv = frame.getAllInvoices().get(selectedRowIndex);
            frame.getNumLabel().setText(currentInv.getInvoiceNumber() + "");
            frame.getCustomerLabel().setText(currentInv.getCustomerName());
            frame.getTotalLebal().setText(currentInv.calculateTotalCost() + "");
            frame.getDateLabel().setText(currentInv.getDate());

            LineTable lineTable = new LineTable(currentInv.getLines());
            frame.getLineTable().setModel(lineTable);
            lineTable.fireTableDataChanged(); //to make each selection make a change 
        }
    }  
    
    private void loadFiles(){
        
        JFileChooser fileChooser = new JFileChooser();
        
        try
        {
            int choose = fileChooser.showOpenDialog(frame);
            if(choose == JFileChooser.APPROVE_OPTION)
            {
               File headerDataFile;
                headerDataFile = fileChooser.getSelectedFile();
               Path headerFilePath;
                headerFilePath = Paths.get(headerDataFile.getAbsolutePath());
               List <String> headerList;
                headerList =  Files.readAllLines(headerFilePath);
                 
               ArrayList <InvoiceHeader> invoicesList;
                invoicesList = new ArrayList <> ();
                
                
               for(int i =0; i<headerList.size(); i++)
               {
                   String[] headerSplits;
                   headerSplits = headerList.get(i).split(",");
                   
                   int invoiceNumber;
                   invoiceNumber =Integer.parseInt(headerSplits[0]); //function to transform string to int
                   String invoiceDate;
                   invoiceDate = headerSplits[1];
                   String customerName;
                   customerName = headerSplits[2];
                   
                   InvoiceHeader invoice; 
                   invoice = new InvoiceHeader(invoiceNumber, invoiceDate,customerName);
                   invoicesList.add(invoice); 
               }
               
            choose = fileChooser.showOpenDialog(frame);
            if(choose == JFileChooser.APPROVE_OPTION)
            {
                File lineDataFile;
                lineDataFile = fileChooser.getSelectedFile();
                Path lineFilePath;
                lineFilePath = Paths.get(lineDataFile.getAbsolutePath());
                List <String> lineList;
                lineList =  Files.readAllLines(lineFilePath);
                
                
                for(int i = 0; i<lineList.size(); i++)
                {
                    String [] lineSplits;
                    lineSplits = lineList.get(i).split(",");
                    
                    int invoiceNumber;
                    invoiceNumber =Integer.parseInt(lineSplits[0]);
                    String itemName;
                    itemName = lineSplits[1];
                    double itemCost;
                    itemCost = Double.parseDouble(lineSplits[2]); //function to transform string to double
                    int itemCount;
                    itemCount = Integer.parseInt(lineSplits[3]); //function to transfrom string to int
                    
                    
                    InvoiceHeader matchFound = new InvoiceHeader();
                    for (InvoiceHeader invoicesList1 : invoicesList) {
                        
                        if (invoicesList1.getInvoiceNumber() == invoiceNumber)
                        {
                            matchFound = invoicesList1;
                            break;
                        }
                    }
                    
                    
                    InvoiceLine invoiceLine;
                    invoiceLine = new InvoiceLine(matchFound, itemCount, itemCost, itemName);
                    matchFound.getLines().add(invoiceLine);
                }
            }
            
            frame.setAllInvoices(invoicesList);
            HeaderTable invoiceTable = new HeaderTable(invoicesList);
            frame.setHeaderTableModel(invoiceTable);
            frame.getHeaderTable().setModel(invoiceTable);
            frame.getHeaderTableModel().fireTableDataChanged(); //make the new changes on the table 
   
            }
        }
        catch (IOException e){
        }
                   
        
    
    
    }
    
    private void saveFiles()
    {
        ArrayList <InvoiceHeader> allInvoices = frame.getAllInvoices();
        String headerContent = "";
        String lineContent = "";
        for (InvoiceHeader invoiceHeader : allInvoices)
        {
            String headerInvoiveCSV = invoiceHeader.saveCSV();
            headerContent = headerContent + headerInvoiveCSV;
            headerContent = headerContent + "\n";
            
            for (InvoiceLine invoiceLine : invoiceHeader.getLines())
            {
                String lineInvoiceCSV = invoiceLine.SaveCSV();
                lineContent = lineContent + lineInvoiceCSV;
                lineContent = lineContent + "\n";
            }
        }
        try
        {
            JFileChooser fileChooser = new JFileChooser();
            int choose;
            choose = fileChooser.showSaveDialog(frame);
            if (choose == JFileChooser.APPROVE_OPTION)
            {
                File InvoiceHeaderFile = fileChooser.getSelectedFile();
                FileWriter headerFileWriter = new FileWriter(InvoiceHeaderFile);
                headerFileWriter.write(headerContent);
                headerFileWriter.flush();
                headerFileWriter.close();
                

                choose = fileChooser.showSaveDialog(frame);
                if (choose == JFileChooser.APPROVE_OPTION)
                {
                    File InvoiceLineFile = fileChooser.getSelectedFile();
                    FileWriter lineFileWriter = new FileWriter(InvoiceLineFile);
                    lineFileWriter.write(lineContent);
                    lineFileWriter.flush();
                    lineFileWriter.close();
                }

            }
        }
        catch (Exception e)
        {
            
        }
        
    
    }
    
    private void deleteInvoice()
    {
        int selectedRow;
       selectedRow = frame.getHeaderTable().getSelectedRow();
       
       if(selectedRow != -1)
       {
           frame.getAllInvoices().remove(selectedRow);
           frame.getHeaderTableModel().fireTableDataChanged();
       }
        
    }
    
    
    private void deleteItem()
    {
         int selectedRow;
         int selectedHeaderInvoice;
       selectedRow = frame.getLineTable().getSelectedRow();
       selectedHeaderInvoice = frame.getHeaderTable().getSelectedRow();
       
       if(selectedRow != -1 && selectedHeaderInvoice != -1)
       {
          InvoiceHeader invoiceHeader = frame.getAllInvoices().get(selectedHeaderInvoice);
          invoiceHeader.getLines().remove(selectedRow);
          LineTable lineTable = new  LineTable(invoiceHeader.getLines());
          frame.getLineTable().setModel(lineTable);
          lineTable.fireTableDataChanged();
          frame.getHeaderTableModel().fireTableDataChanged();
       }
    }

    private void createInvoice() {
        String date = invDialog.getDateField().getText();
        String customerName = invDialog.getCustomerNameField().getText();
        int number = frame.getNextCreationNumber();
        
        InvoiceHeader newInvoice = new InvoiceHeader(number, date, customerName);
        frame.getAllInvoices().add(newInvoice);
        frame.getHeaderTableModel().fireTableDataChanged();
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog = null;
    }

    private void cancelInvoiceCreating() {
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog = null;
    }

    private void createLine() {
        String itemName = lineDialog.getItemNameField().getText();
        String countStr = lineDialog.getNumberOfItemsField().getText();
        int numberOfItems = Integer.parseInt(countStr);
        String priceStr = lineDialog.getItemPriceField().getText();
        double PriceOfItem = Double.parseDouble(priceStr);
        int selected = frame.getHeaderTable().getSelectedRow();
        if (selected != -1)
        {
           InvoiceHeader invoiceHeader = new InvoiceHeader();
           invoiceHeader = frame.getAllInvoices().get(selected); 
           InvoiceLine invoiceLine = new InvoiceLine(invoiceHeader, numberOfItems, PriceOfItem, itemName);
           invoiceHeader.getLines().add(invoiceLine);
           
           LineTable lineTable = new  LineTable(invoiceHeader.getLines());
           frame.getLineTable().setModel(lineTable);
           lineTable.fireTableDataChanged(); 
           frame.getHeaderTableModel().fireTableDataChanged();
        }
        
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
       
    }

    private void cancelLineCreating() {
        lineDialog.setVisible(false);
        lineDialog.dispose();
        lineDialog = null;
    }

    private void newInvoice() {
        invDialog = new CreateNewInvoiceDialog(frame);
        invDialog.setVisible(true);
    }

    private void newItem() {
        lineDialog = new CreateNewLineDialog(frame);
        lineDialog.setVisible(true);
    }

   
    
}
