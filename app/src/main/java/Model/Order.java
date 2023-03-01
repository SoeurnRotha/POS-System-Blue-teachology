package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Order {

    @PrimaryKey(autoGenerate = true)
    int orderId;

    @ColumnInfo
    int customerId;


    @ColumnInfo
    int payment_id;

    @ColumnInfo
    String productName_eng;


    @ColumnInfo
    String productName_kh;


    @ColumnInfo
    int productQty;

    @ColumnInfo
    double totalAmount;

    @ColumnInfo
    double discount;



    @ColumnInfo
    String order_date;

    public Order(int customerId, int payment_id, String productName_eng, String productName_kh, int productQty, double totalAmount, double discount, String order_date) {
        this.customerId = customerId;
        this.payment_id = payment_id;
        this.productName_eng = productName_eng;
        this.productName_kh = productName_kh;
        this.productQty = productQty;
        this.totalAmount = totalAmount;
        this.discount = discount;
        this.order_date = order_date;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", customerId=" + customerId +
                ", payment_id=" + payment_id +
                ", productName_eng='" + productName_eng + '\'' +
                ", productName_kh='" + productName_kh + '\'' +
                ", productQty=" + productQty +
                ", totalAmount=" + totalAmount +
                ", discount=" + discount +
                ", order_date='" + order_date + '\'' +
                '}';
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public int getPayment_id() {
        return payment_id;
    }

    public void setPayment_id(int payment_id) {
        this.payment_id = payment_id;
    }

    public String getProductName_eng() {
        return productName_eng;
    }

    public void setProductName_eng(String productName_eng) {
        this.productName_eng = productName_eng;
    }

    public String getProductName_kh() {
        return productName_kh;
    }

    public void setProductName_kh(String productName_kh) {
        this.productName_kh = productName_kh;
    }

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getOrder_date() {
        return order_date;
    }

    public void setOrder_date(String order_date) {
        this.order_date = order_date;
    }
}
