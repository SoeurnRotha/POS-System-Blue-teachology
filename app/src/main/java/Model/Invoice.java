package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.Date;


@Entity
public class Invoice {

    @PrimaryKey(autoGenerate = true)
    String invoice_id;


    @ColumnInfo
    String customerId;

    @ColumnInfo
    String productId;

    @ColumnInfo
    int paymentId;

    @ColumnInfo
    String product_name_english;

    @ColumnInfo
    String product_name_kh;

    @ColumnInfo
    double productPrice;

    @ColumnInfo
    int productQty;

    @ColumnInfo
    double total_amount;

    //totalAmount = qty * price

    @ColumnInfo
    double invoice_total;


    @ColumnInfo
    double discount;

    @ColumnInfo
    String invoice_date;


    public Invoice(String customerId, String productId, int paymentId, String product_name_english, String product_name_kh, double productPrice, int productQty, double total_amount, double invoice_total, double discount, String invoice_date) {
        this.customerId = customerId;
        this.productId = productId;
        this.paymentId = paymentId;
        this.product_name_english = product_name_english;
        this.product_name_kh = product_name_kh;
        this.productPrice = productPrice;
        this.productQty = productQty;
        this.total_amount = total_amount;
        this.invoice_total = invoice_total;
        this.discount = discount;
        this.invoice_date = invoice_date;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoice_id='" + invoice_id + '\'' +
                ", customerId='" + customerId + '\'' +
                ", productId='" + productId + '\'' +
                ", paymentId=" + paymentId +
                ", product_name_english='" + product_name_english + '\'' +
                ", product_name_kh='" + product_name_kh + '\'' +
                ", productPrice=" + productPrice +
                ", productQty=" + productQty +
                ", total_amount=" + total_amount +
                ", invoice_total=" + invoice_total +
                ", discount=" + discount +
                ", invoice_date='" + invoice_date + '\'' +
                '}';
    }

    public String getInvoice_id() {
        return invoice_id;
    }

    public void setInvoice_id(String invoice_id) {
        this.invoice_id = invoice_id;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getProduct_name_english() {
        return product_name_english;
    }

    public void setProduct_name_english(String product_name_english) {
        this.product_name_english = product_name_english;
    }

    public String getProduct_name_kh() {
        return product_name_kh;
    }

    public void setProduct_name_kh(String product_name_kh) {
        this.product_name_kh = product_name_kh;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public double getTotal_amount() {
        return total_amount;
    }

    public void setTotal_amount(double total_amount) {
        this.total_amount = total_amount;
    }

    public double getInvoice_total() {
        return invoice_total;
    }

    public void setInvoice_total(double invoice_total) {
        this.invoice_total = invoice_total;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }
}
