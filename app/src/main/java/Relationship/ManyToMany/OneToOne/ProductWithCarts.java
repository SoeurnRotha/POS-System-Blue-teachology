package Relationship.ManyToMany.OneToOne;

import androidx.room.Embedded;
import androidx.room.Relation;

import java.util.List;

import Model.CartTable;
import Model.ProductTable;

public class ProductWithCarts {
    @Embedded
    public ProductTable productTable;

    @Relation(
            parentColumn = "productId",
            entityColumn = "cartId"
    )
    public List<CartTable> cartTableList;

    public ProductTable getProductTable() {
        return productTable;
    }

    public void setProductTable(ProductTable productTable) {
        this.productTable = productTable;
    }

    public List<CartTable> getCartTableList() {
        return cartTableList;
    }

    public void setCartTableList(List<CartTable> cartTableList) {
        this.cartTableList = cartTableList;
    }

    @Override
    public String toString() {
        return "ProductWithCarts{" +
                "productTable=" + productTable +
                ", cartTableList=" + cartTableList +
                '}';
    }
}
