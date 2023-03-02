package MyAdapter.pos_adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.bluesystemwithroomdatabase.Pos.Pos;
import com.example.bluesystemwithroomdatabase.Pos.Pos_Fragment;
import com.example.bluesystemwithroomdatabase.R;

import java.util.List;

import Model.CategoryTable;

public class Pos_category_adapter_show_list_view extends RecyclerView.Adapter<Pos_category_adapter_show_list_view.ViewCategoryPos>{

    List<CategoryTable> categoryTableList;

    public Pos_category_adapter_show_list_view(List<CategoryTable> categoryTableList) {
        this.categoryTableList = categoryTableList;
    }

    @NonNull
    @Override
    public ViewCategoryPos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =
                LayoutInflater
                        .from(parent.getContext())
                        .inflate(R.layout.single_list_item_category_show_in_pos, parent, false);

        return new ViewCategoryPos(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewCategoryPos holder, @SuppressLint("RecyclerView") int position) {
        holder.category_eng.setText(categoryTableList.get(position).getCategoryname_Eng());

        holder.category_kh.setText(categoryTableList.get(position).getCategoryname_kh());



    }

    @Override
    public int getItemCount() {
        return categoryTableList.size();
    }

    public class ViewCategoryPos extends RecyclerView.ViewHolder{

        TextView category_eng, category_kh;
        CardView card_pos_category_items;
        public ViewCategoryPos(@NonNull View itemView) {
            super(itemView);

            category_eng = itemView.findViewById(R.id.pos_category_eng);
            category_kh = itemView.findViewById(R.id.pos_category_kh);

            card_pos_category_items = itemView.findViewById(R.id.card_pos_category_items);
        }
    }
}
