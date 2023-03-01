package Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class ExpanseTable {
    @PrimaryKey(autoGenerate = true)
    int expanseId;

    @ColumnInfo
    int userId;

    @ColumnInfo
    double expense_mony;


    @ColumnInfo
    double income;

    @ColumnInfo
    double expense;

    @ColumnInfo
    String expanse_description;

    @ColumnInfo
    String expanse_income_or_expense;

    @ColumnInfo
    String create_date;




}
