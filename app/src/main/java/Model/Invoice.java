package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Entity
public class Invoice {

    @PrimaryKey(autoGenerate = true)
    int invoiceId;

    @ColumnInfo
    String customerName;

    @ColumnInfo
    String cashierName;
    @ColumnInfo (name ="product_eng_list" )
    ArrayList<String> product_name_english;

    @ColumnInfo (name ="product_kh_list" )
    ArrayList<String> product_name_khmer;



    @ColumnInfo
    public
    long amount;

    @ColumnInfo
    double discount;


    @ColumnInfo
    long subtotal;
    @ColumnInfo
    String paymentType;


    @ColumnInfo
    public
    long grand_total_dollar;

    @ColumnInfo
    public
    long grand_total_khmer;

    @ColumnInfo
    ArrayList<String> qty;


    @ColumnInfo
    ArrayList<String> price;


    @ColumnInfo
    String invoice_date;

    public long getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(long subtotal) {
        this.subtotal = subtotal;
    }

    public ArrayList<String> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<String> price) {
        this.price = price;
    }

    public int getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(int invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public ArrayList<String> getProduct_name_english() {
        return product_name_english;
    }

    public void setProduct_name_english(ArrayList<String> product_name_english) {
        this.product_name_english = product_name_english;
    }

    public ArrayList<String> getProduct_name_khmer() {
        return product_name_khmer;
    }

    public void setProduct_name_khmer(ArrayList<String> product_name_khmer) {
        this.product_name_khmer = product_name_khmer;
    }

    public double getAmount() {
        return amount;
    }



    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public double getGrand_total_dollar() {
        return grand_total_dollar;
    }



    public double getGrand_total_khmer() {
        return grand_total_khmer;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public void setGrand_total_dollar(long grand_total_dollar) {
        this.grand_total_dollar = grand_total_dollar;
    }



    public void setGrand_total_khmer(long grand_total_khmer) {
        this.grand_total_khmer = grand_total_khmer;
    }

    public ArrayList<String> getQty() {
        return qty;
    }

    public void setQty(ArrayList<String> qty) {
        this.qty = qty;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }
}
