package Model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class PaymentMethod implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int paymentId;


    @ColumnInfo
    String decription;

    @ColumnInfo
    String payment_date;


    public int getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(int paymentId) {
        this.paymentId = paymentId;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public String getPayment_date() {
        return payment_date;
    }

    public void setPayment_date(String payment_date) {
        this.payment_date = payment_date;
    }

    @Override
    public String toString() {
        return "PaymentMethod{" +
                "paymentId=" + paymentId +
                ", decription='" + decription + '\'' +
                ", payment_date='" + payment_date + '\'' +
                '}';
    }
}
