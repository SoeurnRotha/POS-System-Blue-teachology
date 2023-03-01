package Relationship.ManyToMany.LocationWithProducts;

import androidx.room.Entity;

@Entity(primaryKeys = {"location_Id","productId"})
public class LocationProductsCrossRef {

    int location_Id;
    int productId;

    public LocationProductsCrossRef(int location_Id, int productId) {
        this.location_Id = location_Id;
        this.productId = productId;
    }

    public int getLocation_Id() {
        return location_Id;
    }

    public void setLocation_Id(int location_Id) {
        this.location_Id = location_Id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }
}
