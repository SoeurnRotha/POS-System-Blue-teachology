package Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class ExpanseTable implements Serializable {
    @PrimaryKey(autoGenerate = true)
    int expanseId;

    @ColumnInfo
    int userId;

    @ColumnInfo
    double expense_mony;
    @ColumnInfo
    String expanse_description;
    @ColumnInfo
    String create_date;


    public int getExpanseId() {
        return expanseId;
    }

    public void setExpanseId(int expanseId) {
        this.expanseId = expanseId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public double getExpense_mony() {
        return expense_mony;
    }

    public void setExpense_mony(double expense_mony) {
        this.expense_mony = expense_mony;
    }

    public String getExpanse_description() {
        return expanse_description;
    }

    public void setExpanse_description(String expanse_description) {
        this.expanse_description = expanse_description;
    }

    public String getCreate_date() {
        return create_date;
    }

    public void setCreate_date(String create_date) {
        this.create_date = create_date;
    }
}
