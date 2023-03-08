package Model;

import androidx.room.TypeConverter;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


public class DataConverts  {

    @TypeConverter
    public static ArrayList<String> fromString(String value){
        Gson gson = new Gson();
        Type type=new TypeToken<ArrayList<String>>(){}.getType();


        return gson.fromJson(value,type);
    }
    @TypeConverter
    public static String fromArrayList(ArrayList<String> list){
        Gson gson = new Gson();
        return gson.toJson(list);
    }
}
