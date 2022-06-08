package com.sales.view;
    import java.awt.GridLayout;
    import javax.swing.JButton;
    import javax.swing.JDialog;
    import javax.swing.JLabel;
    import javax.swing.JTextField;

public class LiDiago extends JDialog{
                        private final JTextField itNField;
                        private final JTextField itcouFiled;
                        private final JTextField ITFILEPRICE;
                        private final JLabel itemNameLLLB;
                        private final JLabel itemCount;
                        private final JLabel itemprice;
                        private final JButton okBinv;
                        private final JButton closeinvs;
    
    public LiDiago(InvoiceFrame frame) {
            itNField = new JTextField(20);
            itemNameLLLB = new JLabel("Item Name");
            itcouFiled = new JTextField(20);
            itemCount = new JLabel("Item Count");
            ITFILEPRICE = new JTextField(20);
            itemprice = new JLabel("Item Price");
            okBinv = new JButton("OK");
            closeinvs = new JButton("Cancel");
            okBinv.setActionCommand("createLineOK");
            closeinvs.setActionCommand("createLineCancel");
            okBinv.addActionListener(frame.getController());
            closeinvs.addActionListener(frame.getController());
            setLayout(new GridLayout(4, 2));
            add(itemNameLLLB);
            add(itNField);
            add(itemCount);
            add(itcouFiled);
            add(itemprice);
            add(ITFILEPRICE);
            add(okBinv);
            add(closeinvs);

        pack();
        
        
        
        
        
    }

    public JTextField getItNField() {
        
        return itNField;
    }

    public JTextField getItcouFiled() {
        
        
        return itcouFiled;
    }

    public JTextField getITFILEPRICE() {
        
        
        return ITFILEPRICE;
    }
}
