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
public class CreateNewLineDialog extends JDialog {
    
    private final JTextField itemNameField;
    private final JTextField numberOfItemsField;
    private final JTextField itemPriceField;
    private final JLabel itemNameLbl;
    private final JLabel itemCountLbl;
    private final JLabel itemPriceLbl;
    private final JButton creationBtn;
    private final JButton cancellingBtn;
    
    public CreateNewLineDialog (SalesFrame frame) {
        itemNameLbl = new JLabel("Item Name:");
        add(itemNameLbl);
        itemNameField = new JTextField(18);
        add(itemNameField);
        
        itemCountLbl = new JLabel("Number of Items:");
        add(itemCountLbl);
        numberOfItemsField = new JTextField(18);
        add(numberOfItemsField);
        
        itemPriceLbl = new JLabel("Item Price:");
        add(itemPriceLbl);
        itemPriceField = new JTextField(18);
        add(itemPriceField);
        
        
        creationBtn = new JButton("Create");
        add(creationBtn);
        cancellingBtn = new JButton("Cancel, sure!");
        add(cancellingBtn);
        
        creationBtn.setActionCommand("Create Line");
        cancellingBtn.setActionCommand("Cancel Line Creating");
        
        creationBtn.addActionListener(frame.getActionHandler());
        cancellingBtn.addActionListener(frame.getActionHandler());
        setLayout(new GridLayout(4, 3));
       
        
        pack();
    }

    public JTextField getItemNameField() {
        return itemNameField;
    }

    public JTextField getNumberOfItemsField() {
        return numberOfItemsField;
    }

    public JTextField getItemPriceField() {
        return itemPriceField;
    }
    
}
