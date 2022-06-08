package com.sales.controller;
    import com.sales.model.InvoiceHeader;
    import com.sales.model.ITH;
    import com.sales.model.LineInvoice;
    import com.sales.model.LTL;
    import com.sales.view.InvDiajo;
    import com.sales.view.InvoiceFrame;
    import com.sales.view.LiDiago;
    import java.awt.HeadlessException;
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
    import javax.swing.JOptionPane;
    import javax.swing.event.ListSelectionEvent;
    import javax.swing.event.ListSelectionListener;

public class ControllerAction implements ActionListener, ListSelectionListener {
//try number 5 //
    
    private final InvoiceFrame josframe;
    private InvDiajo invoiceDialog;
    private LiDiago lineDialog;

    public ControllerAction(InvoiceFrame frame) {
        this.josframe = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String actionn = e.getActionCommand();
        System.out.println("Action: " + actionn);
        switch (actionn) {
            
       
            case "Save File":
                saveFile();
                break;
            
            case "Create New Invoice":
                createNewInvoice();
                break;
            
            
                
                case "Delete Invoice":
                deleteInvoice();
                break;
                
            
            case "Create New Item":
                createNewItem();
                break;
            
                
                  case "createInvoiceCancel":
                createInvoiceCancel();
                break;
                
            case "Delete Item":
                deleteItem();
                break;
            
          
            
                   
            case "createLineOK":
                createLineOK();
                break;
                
            case "createInvoiceOK":
                createInvoiceOK();
                break;
         
           
            case "createLineCancel":
                createLineCancel();
                break;
      
             case "Load File":
                loadFile();
                break;
            
        
        }
    }

    @Override ///9////
    public void valueChanged(ListSelectionEvent e) {
        int selectedIndex = josframe.getInvoiceTable().getSelectedRow();
        if (selectedIndex != -1) {
            
            
            
            
            
            System.out.println("You have selected row: " + selectedIndex);
            InvoiceHeader currentInvoice = josframe.getInvoices().get(selectedIndex);
          
            josframe.getInvoiceNumLabel().setText("" + currentInvoice.getNum());
            
            josframe.getInvoiceDateLabel().setText(currentInvoice.getInvDate());
            
            josframe.getCustomerNameLabel().setText(currentInvoice.getCustomer());
            
            josframe.getInvoiceTotalLabel().setText("" + currentInvoice.getInvoiceTotal());
            
            
            
            
            
            
            LTL linesTableModel = new LTL(currentInvoice.getLines());
            
            josframe.getLineTable().setModel(linesTableModel);
            
            linesTableModel.fireTableDataChanged();
        }
    }

    private void createInvoiceCancel() {
      
        invoiceDialog.setVisible(false);
        
        invoiceDialog.dispose();
        
        invoiceDialog = null;
    }
    
    private void saveFile() {
        ArrayList<InvoiceHeader> invoices = josframe.getInvoices();
       
        
        
        String headers = "";
        
        
        String lines = "";
        
        
        for (InvoiceHeader invoice : invoices) {
            String invCSV = invoice.getAsCSV();
            
        
            
            
            
            headers += invCSV;
        
            headers += "\n";

            for 
                    (LineInvoice line : invoice.getLines()) {
                
                String lineCSV = line.getAsCSV();
            
                
                
                lines += lineCSV;
                lines += "\n";
            }
        }
        System.out.println("Check point");
        try {
          
            
            JFileChooser fc = new JFileChooser();
            
            
            int result = fc.showSaveDialog(josframe);
            
            
            if (result == JFileChooser.APPROVE_OPTION) {
            
                
                File headerFile = fc.getSelectedFile();
                
                
                try (FileWriter hfw = new FileWriter(headerFile)) {
                
                    
                    hfw.write(headers);
                    
                    
                    hfw.flush();
                }
                result = fc.showSaveDialog(josframe);
                if (result == JFileChooser.APPROVE_OPTION) {
                   
                    
                    
                    
                    
                    
                    File lineFile = fc.getSelectedFile();
                    try (FileWriter lfw = new FileWriter(lineFile)) {
                        lfw.write(lines);
                    
                        
                        lfw.flush();
                    }
                }
            }
        } catch (HeadlessException | IOException ex) {

        }
    }

    private void createNewInvoice() {
        
        
        
        
        
        invoiceDialog = new InvDiajo(josframe);
       
        invoiceDialog.setVisible(true);
    }

  

    private void createNewItem() {
        lineDialog = new LiDiago(josframe);
        lineDialog.setVisible(true);
    }

    private void deleteItem() {
        int selectedRow = josframe.getLineTable().getSelectedRow();

        if (selectedRow != -1) {
            LTL linesTableModel = (LTL) josframe.getLineTable().getModel();
         
            
            linesTableModel.getLines().remove(selectedRow);
            
            
            
            linesTableModel.fireTableDataChanged();
            
            josframe.getInvoicesTableModel().fireTableDataChanged();
        }
    }

  

    private void createInvoiceOK() {
        String date = invoiceDialog.getInvDateField().getText();
        String customer = invoiceDialog.getCustFiled().getText();
        int num = josframe.getNextInvoiceNum();
        try {
            String[] dateParts = date.split("-");  // "22-05-2013" -> {"22", "05", "2013"}  xy-qw-20ij
            if (dateParts.length < 3) {
       
                JOptionPane.showMessageDialog(josframe, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                
                int day = Integer.parseInt(dateParts[0]);
                
                int month = Integer.parseInt(dateParts[1]);
                
                int year = Integer.parseInt(dateParts[2]);
                
                if (day > 31 || month > 12) {
               
                    JOptionPane.showMessageDialog(josframe, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    InvoiceHeader invoice = new InvoiceHeader(num, date, customer);
                    josframe.getInvoices().add(invoice);
               
                    josframe.getInvoicesTableModel().fireTableDataChanged();
                    
                    invoiceDialog.setVisible(false);
                    
                    invoiceDialog.dispose();
                    
                    invoiceDialog = null;
                }
            }
        } catch (HeadlessException | NumberFormatException ex) {
            JOptionPane.showMessageDialog(josframe, "Wrong date format", "Error", JOptionPane.ERROR_MESSAGE);
        }

    }
  private void deleteInvoice() {
        int selectedRow = josframe.getInvoiceTable().getSelectedRow();
     
        if (selectedRow != -1) {
        
            josframe.getInvoices().remove(selectedRow);
            
            josframe.getInvoicesTableModel().fireTableDataChanged();
        }
    }
    private void createLineOK() {
   
        String item = lineDialog.getItNField().getText();
        
        String countStr = lineDialog.getItcouFiled().getText();
        
        String priceStr = lineDialog.getITFILEPRICE().getText();
        
        int count = Integer.parseInt(countStr);
        
        double price = Double.parseDouble(priceStr);
        
        int selectedInvoice = josframe.getInvoiceTable().getSelectedRow();
        
        if (selectedInvoice != -1) {
        
            InvoiceHeader invoice = josframe.getInvoices().get(selectedInvoice);
            
            LineInvoice line = new LineInvoice(item, price, count, invoice);
            
            invoice.getLines().add(line);
            
            
            LTL linesTableModel = (LTL) josframe.getLineTable().getModel();
            



                    linesTableModel.fireTableDataChanged();
                                josframe.getInvoicesTableModel().fireTableDataChanged();
                            }
                            
        lineDialog.setVisible(false);
        
        lineDialog.dispose();
        
        lineDialog = null;
                        }

    private void createLineCancel() {
     
        lineDialog.setVisible(false);
        
        lineDialog.dispose();
        
        
        lineDialog = null;
    }
    
  private void loadFile() {
        JFileChooser fc = new JFileChooser();
        
        
        
        try {
        
            int result = fc.showOpenDialog(josframe);
            
            if (result == JFileChooser.APPROVE_OPTION) {
            
                File headerFile = fc.getSelectedFile();
                
                Path headerPath = Paths.get(headerFile.getAbsolutePath());
                
                List<String> headerLines = Files.readAllLines(headerPath);
                
                System.out.println("Invoices have been read");
                
                
                
                ArrayList<InvoiceHeader> invoicesArray = new ArrayList<>();
                for (String headerLine : headerLines) {
                
                    
                    try {
                    
                        String[] headerParts = headerLine.split(",");
                        
                        int invoiceNum = Integer.parseInt(headerParts[0]);
                        
                        String invoiceDate = headerParts[1];
                        
                        
                        String customerName = headerParts[2];

                        
                        
                        InvoiceHeader invoice = new InvoiceHeader(invoiceNum, invoiceDate, customerName);
                        invoicesArray.add(invoice);
                    } catch
                            (NumberFormatException ex) {
                        
                        JOptionPane.showMessageDialog(josframe, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                }
                System.out.println("Check point");
 
                
                result = fc.showOpenDialog(josframe);
                
                if (result == JFileChooser.APPROVE_OPTION) {
                
                    File lineFile = fc.getSelectedFile();
                    
                    Path linePath = Paths.get(lineFile.getAbsolutePath());
                    
                    List<String> lineLines = Files.readAllLines(linePath);
                    
                    System.out.println("Lines have been read");
                    
                    for (String lineLine : lineLines) {
                    
                        try {
                        
                            String lineParts[] = lineLine.split(",");
                            
                            int invoiceNum = Integer.parseInt(lineParts[0]);
                            
                            String itemName = lineParts[1];
                                
                            double itemPrice = Double.parseDouble(lineParts[2]);
                                
                                int count = Integer.parseInt(lineParts[3]);
                                
                                InvoiceHeader inv = null;
                            
                                for (InvoiceHeader invoice : invoicesArray) {
                                    if (invoice.getNum() == invoiceNum) {
                                    inv = invoice;
                                    break;
                                }
                            }

                            LineInvoice line = new LineInvoice(itemName, itemPrice, count, inv);
                    
                            boolean jokoadd;
                            jokoadd = inv.getLines().add(line);
                        } catch (NumberFormatException ex) {
                            JOptionPane.showMessageDialog(josframe, "Error in line format", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                    System.out.println("Check point");
                }
                josframe.setInvoices(invoicesArray);
                ITH invoicesTableModel = new ITH(invoicesArray);
                josframe.setInvoicesTableModel(invoicesTableModel);
                josframe.getInvoiceTable().setModel(invoicesTableModel);
                josframe.getInvoicesTableModel().fireTableDataChanged();
            }
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(josframe, "Cannot read file", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
