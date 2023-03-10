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
    ArrayList<String> qty;

    @ColumnInfo
    ArrayList<String> price;

    @ColumnInfo
    ArrayList<String> name;

    @ColumnInfo
    ArrayList<String> amount;

    @ColumnInfo
    long total_dollar;

    @ColumnInfo
    long total_khmer;

    public int getCheackoutId() {
        return cheackoutId;
    }

    public void setCheackoutId(int cheackoutId) {
        this.cheackoutId = cheackoutId;
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

    public long getTotal_dollar() {
        return total_dollar;
    }

    public void setTotal_dollar(long total_dollar) {
        this.total_dollar = total_dollar;
    }

    public long getTotal_khmer() {
        return total_khmer;
    }

    public void setTotal_khmer(long total_khmer) {
        this.total_khmer = total_khmer;
    }
}
