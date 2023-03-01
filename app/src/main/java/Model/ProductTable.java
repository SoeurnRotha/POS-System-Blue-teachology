package Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;


@Entity
public class ProductTable implements Serializable {


    @PrimaryKey(autoGenerate = true)
    int productId;

    @ColumnInfo
    String productName_eng;

    @ColumnInfo
    String productName_kh;

//    @ColumnInfo
//    String image_product;


    @ColumnInfo
    int categoryID;

    @ColumnInfo
    int locationId;


    @ColumnInfo
    String product_barCode;


    @ColumnInfo
    double product_Price;

    @ColumnInfo
    int product_qty;

    @ColumnInfo
    double tax;

    @ColumnInfo
    double product_cost;


    @ColumnInfo
    String product_date;


    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    byte [] imageProducts;


    public byte[] getImageProducts() {
        return imageProducts;
    }

    public void setImageProducts(byte[] imageProducts) {
        this.imageProducts = imageProducts;
    }





    public int getLocationId() {
        return locationId;
    }

    public void setLocationId(int locationId) {
        this.locationId = locationId;
    }



    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
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

    public int getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(int categoryID) {
        this.categoryID = categoryID;
    }

    public String getProduct_barCode() {
        return product_barCode;
    }

    public void setProduct_barCode(String product_barCode) {
        this.product_barCode = product_barCode;
    }

    public double getProduct_Price() {
        return product_Price;
    }

    public void setProduct_Price(double product_Price) {
        this.product_Price = product_Price;
    }

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public double getProduct_cost() {
        return product_cost;
    }

    public void setProduct_cost(double product_cost) {
        this.product_cost = product_cost;
    }

    public String getProduct_date() {
        return product_date;
    }

    public void setProduct_date(String product_date) {
        this.product_date = product_date;
    }

    @Override
    public String toString() {
        return "ProductTable{" +
                "productId=" + productId +
                ", productName_eng='" + productName_eng + '\'' +
                ", productName_kh='" + productName_kh + '\'' +
                ", categoryID=" + categoryID +
                ", product_barCode='" + product_barCode + '\'' +
                ", product_Price=" + product_Price +
                ", product_qty=" + product_qty +
                ", tax=" + tax +
                ", product_cost=" + product_cost +
                ", product_date='" + product_date + '\'' +
                '}';
    }
}
