package Mydatabase;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;


import Dao.BlueTeachnology_Dao;
import Model.CartTable;
import Model.CategoryTable;
import Model.Cheackout;
import Model.Customer;
import Model.DataConverts;
import Model.ExpanseTable;
import Model.Invoice;
import Model.LocationTable;
import Model.PaymentMethod;
import Model.ProductTable;
import Model.UnitModel;
import Model.UserTable;
import Relationship.ManyToMany.CategoryProductsCrossRef;
import Relationship.ManyToMany.LocationWithProducts.LocationProductsCrossRef;

@Database(entities = {UserTable.class,
        CategoryTable.class,
        ProductTable.class,
        CategoryProductsCrossRef.class,
        Customer.class,
        PaymentMethod.class,
        CartTable.class,
        LocationTable.class,

        LocationProductsCrossRef.class,
        Invoice.class,
        ExpanseTable.class,
        Cheackout.class,
        UnitModel.class

}, version = 1)
@TypeConverters({ DataConverts.class})
public abstract class BlueTeachnology_Database extends RoomDatabase {

    public abstract BlueTeachnology_Dao blueTeachnology_dao();

    public static volatile BlueTeachnology_Database INSTRANCE;

    public static BlueTeachnology_Database getInstance(Context context){
        if(INSTRANCE == null){
            INSTRANCE = Room.databaseBuilder(context.getApplicationContext(), BlueTeachnology_Database.class, "BlueTeachnology_databaase")
                    .allowMainThreadQueries()

                    .build();
        }
        return INSTRANCE;
    }
}
