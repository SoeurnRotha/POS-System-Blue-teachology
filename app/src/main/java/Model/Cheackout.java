package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

@Entity
public class Cheackout {
    @PrimaryKey(autoGenerate = true)
    int cheackoutId;

    @ColumnInfo
    String cashierName;

    @ColumnInfo
    ArrayList<String> qty;

    @ColumnInfo
    ArrayList<String> price;

    @ColumnInfo
    ArrayList<String> name;

    @ColumnInfo
    ArrayList<String> amount;

    @ColumnInfo
    double subtotal;

    @ColumnInfo
    String payment_method;

    @ColumnInfo
    double discountAmount;
    @ColumnInfo
    double total_dollar;

    @ColumnInfo
    double total_khmer;

    @ColumnInfo
    double discount;

    @ColumnInfo
    double converDollar_to_khmer;

    public int getCheackoutId() {
        return cheackoutId;
    }

    public void setCheackoutId(int cheackoutId) {
        this.cheackoutId = cheackoutId;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public ArrayList<String> getQty() {
        return qty;
    }

    public void setQty(ArrayList<String> qty) {
        this.qty = qty;
    }

    public ArrayList<String> getPrice() {
        return price;
    }

    public void setPrice(ArrayList<String> price) {
        this.price = price;
    }

    public ArrayList<String> getName() {
        return name;
    }

    public void setName(ArrayList<String> name) {
        this.name = name;
    }

    public ArrayList<String> getAmount() {
        return amount;
    }

    public void setAmount(ArrayList<String> amount) {
        this.amount = amount;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getPayment_method() {
        return payment_method;
    }

    public void setPayment_method(String payment_method) {
        this.payment_method = payment_method;
    }

    public double getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(double discountAmount) {
        this.discountAmount = discountAmount;
    }

    public double getTotal_dollar() {
        return total_dollar;
    }

    public void setTotal_dollar(double total_dollar) {
        this.total_dollar = total_dollar;
    }

    public double getTotal_khmer() {
        return total_khmer;
    }

    public void setTotal_khmer(double total_khmer) {
        this.total_khmer = total_khmer;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getConverDollar_to_khmer() {
        return converDollar_to_khmer;
    }

    public void setConverDollar_to_khmer(double converDollar_to_khmer) {
        this.converDollar_to_khmer = converDollar_to_khmer;
    }
}
