package Relationship.ManyToMany;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import Model.CategoryTable;
import Model.ProductTable;

public class ProductWithCategory {
    @Embedded
    public ProductTable productTable;

    @Relation(
            parentColumn = "productId",
            entityColumn = "categoryId",
            associateBy = @Junction(CategoryProductsCrossRef.class)
    )
    public List<CategoryTable> categoryTableList;


}
