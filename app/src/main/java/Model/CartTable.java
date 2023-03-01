package Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CartTable {

    @PrimaryKey(autoGenerate = true)
    int cartId;

    @ColumnInfo
    int productId;

    @ColumnInfo
    String productName_eng;


    @ColumnInfo
    String productName_kh;

    @ColumnInfo
    int productQty;


    @ColumnInfo
    double productPrice;




    @ColumnInfo
    double totalPrice;


    @ColumnInfo
    double discount;

    @ColumnInfo
    String cart_date;


    @Override
    public String toString() {
        return "CartTable{" +
                "cartId=" + cartId +
                ", productId=" + productId +
                ", productName_eng='" + productName_eng + '\'' +
                ", productName_kh='" + productName_kh + '\'' +
                ", productQty=" + productQty +
                ", productPrice=" + productPrice +
                ", totalPrice=" + totalPrice +
                ", discount=" + discount +
                ", cate_date='" + cart_date + '\'' +
                '}';
    }

    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public int getProductQty() {
        return productQty;
    }

    public void setProductQty(int productQty) {
        this.productQty = productQty;
    }

    public double getProductPrice() {
        return productPrice;
    }

    public void setProductPrice(double productPrice) {
        this.productPrice = productPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public String getCart_date() {
        return cart_date;
    }

    public void setCart_date(String cart_date) {
        this.cart_date = cart_date;
    }

    public CartTable(int productId, String productName_eng, String productName_kh, int productQty, double productPrice, double totalPrice, double discount, String cart_date) {
        this.productId = productId;
        this.productName_eng = productName_eng;
        this.productName_kh = productName_kh;
        this.productQty = productQty;
        this.productPrice = productPrice;
        this.totalPrice = totalPrice;
        this.discount = discount;
        this.cart_date = cart_date;
    }
}
