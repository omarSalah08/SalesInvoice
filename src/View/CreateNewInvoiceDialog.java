/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;

/**
 *
 * @author omars
 */
public class CreateNewInvoiceDialog extends JDialog {
    
    private final JTextField customerNameField;
    private final JTextField dateField;
    private final JLabel customerNameLbl;
    private final JLabel dateLbl;
    private final JButton creationBtn;
    private final JButton cancellingBtn;

    public CreateNewInvoiceDialog(SalesFrame frame) {
        customerNameLbl = new JLabel("Customer Name:");
        add(customerNameLbl);
        customerNameField = new JTextField(18);
        add(customerNameField);
        
        dateLbl = new JLabel("Invoice Date:");
        add(dateLbl);
        dateField = new JTextField(18);
        add(dateField);
        
        creationBtn = new JButton("Create");
        add(creationBtn);
        cancellingBtn = new JButton("Cancel, sure!");
        add(cancellingBtn);
        
        
        creationBtn.setActionCommand("Create Invoice");
        cancellingBtn.setActionCommand("Cancel Invoice Creating");
        
        creationBtn.addActionListener(frame.getActionHandler());
        cancellingBtn.addActionListener(frame.getActionHandler());
        setLayout(new GridLayout(4, 3));
      
        pack();
        
    }

    public JTextField getCustomerNameField() {
        return customerNameField;
    }

    public JTextField getDateField() {
        return dateField;
    }
    
    
}
