package MyAdapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.R;
import com.example.bluesystemwithroomdatabase.category.Category_gridview;
import com.example.bluesystemwithroomdatabase.category.Update_Category2;


import java.util.List;

import Dao.BlueTeachnology_Dao;
import Model.CategoryTable;
import Mydatabase.BlueTeachnology_Database;

public class Category_Adapter extends RecyclerView.Adapter<Category_Adapter.ViewCategory> {

    List<CategoryTable> categoryTableList;
    Context context;


    public Category_Adapter(List<CategoryTable> categoryTableList, Context context) {
        this.categoryTableList = categoryTableList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewCategory onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_cagetory_desginer_gridview, parent,false);
        return new ViewCategory(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCategory holder, @SuppressLint("RecyclerView") int position) {

        holder.ceng.setText(String.valueOf(categoryTableList.get(position).getCategoryname_Eng()));
        holder.ckh.setText(String.valueOf(categoryTableList.get(position).getCategoryname_kh()));

        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
                builder.setTitle("DELETE");
                builder.setMessage("Do you want to Delete Category");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "You delete successful", Toast.LENGTH_SHORT).show();
                        BlueTeachnology_Dao blueTeachnology_dao =  BlueTeachnology_Database.getInstance(view.getContext()).blueTeachnology_dao();
                        blueTeachnology_dao.deleteUserByid(categoryTableList.get(position).getCategoryID());

                        categoryTableList.remove(position);

                        notifyDataSetChanged();

                    }
                });

                builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(builder.getContext(), "No", Toast.LENGTH_SHORT).show();
                        dialogInterface.cancel();
                    }
                });

                AlertDialog alertDialog = builder.create();

                alertDialog.show();
            }
        });


        holder.update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent = new Intent(new Intent(holder.update.getContext(), Update_Category2.class));

                intent.putExtra("c_id", String.valueOf(categoryTableList.get(position).getCategoryID()));
                intent.putExtra("cname_eng", String.valueOf(categoryTableList.get(position).getCategoryname_Eng()));
                intent.putExtra("cname_kh", String.valueOf(categoryTableList.get(position).getCategoryname_kh()));

                holder.update.getContext().startActivity(intent);


            }
        });

    }

    @Override
    public int getItemCount() {
        return categoryTableList.size();
    }

    public class ViewCategory extends RecyclerView.ViewHolder {
        ImageView delete;
        TextView  ceng,ckh;
        CardView update;
        public ViewCategory(@NonNull View itemView) {
            super(itemView);
            delete = itemView.findViewById(R.id.category_delete_grid);

            ceng= itemView.findViewById(R.id.category_name_eng);
            ckh = itemView.findViewById(R.id.category_name_kh);

            update = itemView.findViewById(R.id.category_edit_card);
        }
    }

}
