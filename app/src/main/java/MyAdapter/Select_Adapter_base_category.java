package MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.bluesystemwithroomdatabase.LanguageActivity;
import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Model.CategoryTable;

public class Select_Adapter_base_category extends BaseAdapter {

    List<CategoryTable> categoryTableList;
    Context context;

    public Select_Adapter_base_category(List<CategoryTable> categoryTableList, Context context) {
        this.categoryTableList = categoryTableList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return categoryTableList.size();
    }

    @Override
    public Object getItem(int i) {

        return categoryTableList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }


    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.layout_spinner_for_items,null , false);
            TextView categoryName = view.findViewById(R.id.show_items);
            categoryName.setText(String.valueOf(categoryTableList.get(i).getCategoryname_Eng()));
        }


        return view;
    }
}
