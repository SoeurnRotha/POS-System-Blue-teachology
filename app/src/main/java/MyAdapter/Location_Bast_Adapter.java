package MyAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Model.LocationTable;

public class Location_Bast_Adapter extends BaseAdapter {

    List<LocationTable> locationTableList;
    Context context;

    public Location_Bast_Adapter(List<LocationTable> locationTableList, Context context) {
        this.locationTableList = locationTableList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return locationTableList.size();
    }

    @Override
    public Object getItem(int i) {
        return locationTableList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view ==null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_spinner_for_items,null , false);
            LocationTable locationTable = locationTableList.get(i);

            TextView textView = view.findViewById(R.id.show_items);

            textView.setText(locationTable.getLocationName_eng());
        }
        return view;
    }
}
