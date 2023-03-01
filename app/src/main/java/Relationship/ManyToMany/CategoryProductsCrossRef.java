package Relationship.ManyToMany;


import androidx.annotation.NonNull;
import androidx.room.Entity;

@Entity(primaryKeys = {"productId", "categoryID"})
public class CategoryProductsCrossRef {




    public long productId;
    public long categoryID;

    public CategoryProductsCrossRef(long productId, long categoryID) {
        this.productId = productId;
        this.categoryID = categoryID;
    }

    @Override
    public String toString() {
        return "CategoryProductsCrossRef{" +
                "productId=" + productId +
                ", categoryID=" + categoryID +
                '}';
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(long categoryID) {
        this.categoryID = categoryID;
    }
}
