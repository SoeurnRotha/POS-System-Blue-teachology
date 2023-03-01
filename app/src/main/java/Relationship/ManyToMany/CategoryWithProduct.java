package Relationship.ManyToMany;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import Model.CategoryTable;
import Model.ProductTable;

public class CategoryWithProduct {

    @Embedded
    public CategoryTable categoryTable;

    @Relation(
            parentColumn = "categoryID",
            entityColumn = "productId",
            associateBy = @Junction(CategoryProductsCrossRef.class)
    )
    public List<ProductTable> productTables;


    public CategoryWithProduct(CategoryTable categoryTable, List<ProductTable> productTables) {
        this.categoryTable = categoryTable;
        this.productTables = productTables;
    }

    @Override
    public String toString() {
        return "CategoryWithProduct{" +
                "categoryTable=" + categoryTable +
                ", productTables=" + productTables +
                '}';
    }

    public CategoryTable getCategoryTable() {
        return categoryTable;
    }

    public void setCategoryTable(CategoryTable categoryTable) {
        this.categoryTable = categoryTable;
    }

    public List<ProductTable> getProductTables() {
        return productTables;
    }

    public void setProductTables(List<ProductTable> productTables) {
        this.productTables = productTables;
    }
}
