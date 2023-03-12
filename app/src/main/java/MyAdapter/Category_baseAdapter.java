package MyAdapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.example.bluesystemwithroomdatabase.R;


import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CategoryTable;
import Mydatabase.BlueTeachnology_Database;

public class Category_baseAdapter extends BaseAdapter {

    List<CategoryTable> categoryTableList;
    Context context;


    public Category_baseAdapter(List<CategoryTable> categoryTableList, Context context) {
        this.categoryTableList = categoryTableList;
        this.context = context;
    }




    @Override
    public int getCount() {
        return categoryTableList.size();
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {


        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.single_cagetory_desginer_gridview, viewGroup, false);
        }

        TextView c_id , c_name_eng, c_name_kh;
        CardView edit_by_card;
        ImageView delete;



          c_name_eng =  view.findViewById(R.id.category_name_eng);
          c_name_kh = view.findViewById(R.id.category_name_kh);
          edit_by_card = view.findViewById(R.id.category_edit_card);
          delete = view.findViewById(R.id.category_delete_grid);



          c_name_eng.setText(String.valueOf(categoryTableList.get(i).getCategoryname_Eng()));
          c_name_kh.setText(String.valueOf(categoryTableList.get(i).getCategoryname_kh()));



          delete.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                  builder.setTitle("DELETE");
                  builder.setMessage("Do you want to Delete Category");
                  builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int index) {
                          Toast.makeText(builder.getContext(), "You delete successful", Toast.LENGTH_SHORT).show();
                          BlueTeachnology_Dao blueTeachnology_dao =  BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();
                          blueTeachnology_dao.deleteByid(categoryTableList.get(i).getCategoryID());
                          categoryTableList.remove(i);
                          notifyDataSetChanged();

                      }
                  });

                  builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                      @Override
                      public void onClick(DialogInterface dialogInterface, int index) {
                          Toast.makeText(builder.getContext(), "No", Toast.LENGTH_SHORT).show();
                          dialogInterface.cancel();
                      }
                  });

                  AlertDialog alertDialog = builder.create();
                  alertDialog.show();


//                  BlueTeachnology_Dao blueTeachnology_dao =  BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();
//
//                  blueTeachnology_dao.deleteByid(categoryTableList.get(i).getCategoryID());
//                  categoryTableList.remove(i);
//                  notifyDataSetChanged();
              }
          });

        return view;
    }

}
