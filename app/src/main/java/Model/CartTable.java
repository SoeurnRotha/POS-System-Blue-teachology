package Model;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class CartTable {

    @PrimaryKey(autoGenerate = true)
    int cartId;

    @ColumnInfo
    String productName_eng;
    @ColumnInfo
    String productName_kh;

    @ColumnInfo
    int productQty;

    @ColumnInfo
    double productCost;


    @ColumnInfo
    String product_img;











    public int getCartId() {
        return cartId;
    }

    public void setCartId(int cartId) {
        this.cartId = cartId;
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

    public double getProductCost() {
        return productCost;
    }

    public void setProductCost(double productCost) {
        this.productCost = productCost;
    }

    public String getProduct_img() {
        return product_img;
    }

    public void setProduct_img(String product_img) {
        this.product_img = product_img;
    }
}
