package Model;

import androidx.room.ColumnInfo;
import androidx.room.Entity;

@Entity
public class Setting {

    @ColumnInfo
    String companyName;
    @ColumnInfo
    String address;
    @ColumnInfo
    String phone1;
    @ColumnInfo
    String phone2;
    @ColumnInfo
    String locationId;


}
