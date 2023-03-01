package MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Model.CategoryTable;

public class Select_baseAdapter_products extends BaseAdapter {

    Context context;
    List<CategoryTable> categoryTableList;

    public Select_baseAdapter_products(Context context, List<CategoryTable> categoryTableList) {
        this.context = context;
        this.categoryTableList = categoryTableList;
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
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        if(view == null){
            CategoryTable categoryTable = categoryTableList.get(i);
            view = LayoutInflater.from(context).inflate(R.layout.layout_spinner_for_items, null, false);

            TextView textView = view.findViewById(R.id.show_items);
            textView.setText(categoryTable.getCategoryname_Eng());
        }




        return null;
    }
}
