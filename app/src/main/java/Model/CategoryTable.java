package Model;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class CategoryTable implements Serializable {




    @PrimaryKey(autoGenerate = true)
    int categoryID;

    @ColumnInfo
    String categoryname_Eng;


    @ColumnInfo
    String categoryname_kh;



    @ColumnInfo
    String category_date;


    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategoryname_Eng() {
        return categoryname_Eng;
    }

    public void setCategoryname_Eng(String categoryname_Eng) {
        this.categoryname_Eng = categoryname_Eng;
    }

    public String getCategoryname_kh() {
        return categoryname_kh;
    }

    public void setCategoryname_kh(String categoryname_kh) {
        this.categoryname_kh = categoryname_kh;
    }

    public String getCategory_date() {
        return category_date;
    }

    public void setCategory_date(String category_date) {
        this.category_date = category_date;
    }

    @Override
    public String toString() {
        return "CategoryTable{" +
                "categoryID=" + categoryID +
                ", categoryname_Eng='" + categoryname_Eng + '\'' +
                ", categoryname_kh='" + categoryname_kh + '\'' +
                ", category_date='" + category_date + '\'' +
                '}';
    }
}
