package com.sales.model;
import java.util.ArrayList;
public class InvoiceHeader {
    private int num;
    private String invDate;
    private String customer;
    private ArrayList<LineInvoice> lines;
    
    public InvoiceHeader() {
    }

    public InvoiceHeader(int num, String date, String customer) {
        this.num = num;
        this.invDate = date;
        this.customer = customer;
    }

    public double getInvoiceTotal() {
        double total = 0.0;
        for (LineInvoice line : getLines()) {
            total += line.getLineTotal();
        }
        return total;
    }

    //
    
    
    
    

    public String getCustomer() {
        return customer;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    public int getNum() {
        return num;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public String getInvDate() {
        return invDate;
    }

    public void setInvDate(String invDate) {
        this.invDate = invDate;
    }

    @Override
    public String toString() {
        return "Invoice{" + "num=" + num + ", date=" + invDate + ", customer=" + customer + '}';
    }
    
    public String getAsCSV() {
        return num + "," + invDate + "," + customer;
    }
 public ArrayList<LineInvoice> getLines() {
        if (lines == null) {
            lines = new ArrayList<>();
        }
        return lines;
    }   
}
