package com.sales.view;
    import java.awt.GridLayout;
    import javax.swing.JButton;
    import javax.swing.JDialog;
    import javax.swing.JLabel;
    import javax.swing.JTextField;

public class InvDiajo extends JDialog {
        
        
        
        
        
        
        
    private JTextField custFiled;
    
    
    
    
    
    
    
    
    private JTextField invDateField;
    private JLabel custmerNName;
    private JLabel invDateLbl;
    private JButton okBinv;
    private JButton closebinv;

    public InvDiajo(InvoiceFrame frame) {
        custmerNName = new JLabel("Customer Name:");
        custFiled = new JTextField(20);
        invDateLbl = new JLabel("Invoice Date:");
        invDateField = new JTextField(20);
        okBinv = new JButton("OK");
        closebinv = new JButton("Cancel");
        okBinv.setActionCommand("createInvoiceOK");
        closebinv.setActionCommand("createInvoiceCancel");
        okBinv.addActionListener(frame.getController());
        closebinv.addActionListener(frame.getController());
        setLayout(new GridLayout(3, 2));
        add(invDateLbl);
        add(invDateField);
        add(custmerNName);
        add(custFiled);
        add(okBinv);
        add(closebinv);
        
        pack();
        
        
        
        
        
    }

    public JTextField getCustFiled() {
        
        
        
        return custFiled;
    }

    public JTextField getInvDateField() {
        
        
        
        
        return invDateField;
    }
    
}
