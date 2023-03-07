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
    int userId;

    @ColumnInfo (name ="product_eng_list" )
    String product_name_english;

    @ColumnInfo (name ="product_kh_list" )
    String product_name_khmer;



    @ColumnInfo
    double amount;

    @ColumnInfo
    double discount;

    @ColumnInfo
    String paymentType;


    @ColumnInfo
    double grand_total_dollar;

    @ColumnInfo
    double grand_total_khmer;


    @ColumnInfo
    String invoice_date;

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

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }




    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
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

    public void setGrand_total_dollar(double grand_total_dollar) {
        this.grand_total_dollar = grand_total_dollar;
    }

    public double getGrand_total_khmer() {
        return grand_total_khmer;
    }

    public void setGrand_total_khmer(double grand_total_khmer) {
        this.grand_total_khmer = grand_total_khmer;
    }

    public String getProduct_name_english() {
        return product_name_english;
    }

    public void setProduct_name_english(String product_name_english) {
        this.product_name_english = product_name_english;
    }

    public String getProduct_name_khmer() {
        return product_name_khmer;
    }

    public void setProduct_name_khmer(String product_name_khmer) {
        this.product_name_khmer = product_name_khmer;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }
}
