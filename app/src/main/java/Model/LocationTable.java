package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class LocationTable implements Serializable {

    @PrimaryKey(autoGenerate = true)
    int location_Id;

    @ColumnInfo
    String locationName_eng;


    @ColumnInfo
    String locationName_kh;


    @ColumnInfo
    String createDate;


    @Override
    public String toString() {
        return "Location{" +
                "location_Id='" + location_Id + '\'' +
                ", locationName_eng='" + locationName_eng + '\'' +
                ", locationName_kh='" + locationName_kh + '\'' +
                '}';
    }

    public int getLocation_Id() {
        return location_Id;
    }

    public void setLocation_Id(int location_Id) {
        this.location_Id = location_Id;
    }

    public String getLocationName_eng() {
        return locationName_eng;
    }

    public void setLocationName_eng(String locationName_eng) {
        this.locationName_eng = locationName_eng;
    }

    public String getLocationName_kh() {
        return locationName_kh;
    }

    public void setLocationName_kh(String locationName_kh) {
        this.locationName_kh = locationName_kh;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
