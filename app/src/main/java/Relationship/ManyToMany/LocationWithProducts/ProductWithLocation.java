package Relationship.ManyToMany.LocationWithProducts;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import Model.LocationTable;
import Model.ProductTable;

public class ProductWithLocation {

    @Embedded
    public ProductTable productTable;

    @Relation(
            parentColumn = "productId",
            entityColumn = "location_Id",
            associateBy = @Junction(LocationProductsCrossRef.class)
    )
    public List<LocationTable> locationTableList;


}
