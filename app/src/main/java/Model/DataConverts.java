package Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.List;

public class DataConverts implements Serializable {

    @TypeConverter
    public String fromOptionValuesList(List<Invoice> invoiceList){
        if (invoiceList == null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Invoice>>(){

        }.getType();
        String json = gson.toJson(invoiceList, type);
        return  json;
    }

    @TypeConverter
    public List<Invoice> toOptionValueList(String invoiceValueString){
        if(invoiceValueString ==null){
            return (null);
        }
        Gson gson = new Gson();
        Type type = new TypeToken<List<Invoice>>(){

        }.getType();
        List<Invoice> invoiceList = gson.fromJson(invoiceValueString, type);
        return invoiceList;
    }
}
