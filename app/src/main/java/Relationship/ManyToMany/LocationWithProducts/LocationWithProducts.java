package Relationship.ManyToMany.LocationWithProducts;

import android.location.Location;

import androidx.room.Embedded;
import androidx.room.Junction;
import androidx.room.Relation;

import java.util.List;

import Model.LocationTable;
import Model.ProductTable;

public class LocationWithProducts {
    @Embedded
    public LocationTable locationTable;
    @Relation(
            parentColumn = "location_Id",
            entityColumn = "productId",
            associateBy = @Junction(LocationProductsCrossRef.class)
    )
    public List<ProductTable>



            productTableList;


    public LocationWithProducts(LocationTable locationTable, List<ProductTable> productTableList) {
        this.locationTable = locationTable;
        this.productTableList = productTableList;
    }

    public LocationTable getLocationTable() {
        return locationTable;
    }

    public void setLocationTable(LocationTable locationTable) {
        this.locationTable = locationTable;
    }

    public List<ProductTable> getProductTableList() {
        return productTableList;
    }

    public void setProductTableList(List<ProductTable> productTableList) {
        this.productTableList = productTableList;
    }
}
